package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
import com.genius.tms_c61_genius.model.domain.User;
import com.genius.tms_c61_genius.model.request.AuthReqDto;
import com.genius.tms_c61_genius.repository.UserRepository;
import com.genius.tms_c61_genius.security.JwtService;
import com.genius.tms_c61_genius.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public String getTokenFromAuthRequest(AuthReqDto authReqDto) {
        User user = userRepository.getUserByLogin(authReqDto.getLogin()).orElseThrow(() -> new NotFoundException("user not found"));
        if (passwordEncoder.matches(authReqDto.getPassword(), user.getPassword()) && userRepository.existsUserByLogin(authReqDto.getLogin())) {
            return jwtService.createJwtToken(authReqDto.getLogin());
        } else {
            throw new BadDataException("Login or password is incorrect");
        }
    }
}
