package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude={"artist"})
@ToString(exclude = {"artist"})
@Builder
@Entity
@Table(name = "person_info")
public class PersonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "peron_infos_seq")
    @SequenceGenerator(name = "peron_infos_seq", sequenceName = "person_info_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "bio")
    private String bio;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "country")
    private String country;
    @OneToOne(mappedBy = "personInfo")
    private Artist artist;

}
