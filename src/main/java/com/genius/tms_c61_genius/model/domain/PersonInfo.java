package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person_info")
public class PersonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peron_infos_seq")
    @SequenceGenerator(name = "peron_infos_seq", sequenceName = "person_info_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "bio")
    private String bio;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "country")
    private String country;
}
