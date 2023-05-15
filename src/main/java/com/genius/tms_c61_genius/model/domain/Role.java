package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude="users")
@ToString(exclude = "users")
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "role_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "role_name")
    private String roleName;
    @OneToMany(mappedBy = "role")
    private Set<User> users;
}
