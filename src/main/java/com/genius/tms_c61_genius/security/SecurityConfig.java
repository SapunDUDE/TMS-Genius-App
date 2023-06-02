package com.genius.tms_c61_genius.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        return http.httpBasic().disable()
                .csrf().disable()
                .authorizeHttpRequests((authorize)-> authorize
                        .requestMatchers("/actuator/**").hasRole("ADMIN")
                        .requestMatchers("/role/**","/genre/**","/albumtype/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/producer/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/producer/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/producer/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/song/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/song/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/label/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/label/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/label/**").hasRole("ADMIN")
                        .requestMatchers("/user/registration","/auth").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/auth"))
                .and().httpBasic()
                .and().build();
    }

}
