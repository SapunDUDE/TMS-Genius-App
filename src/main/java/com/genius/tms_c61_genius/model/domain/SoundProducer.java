package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@EqualsAndHashCode(exclude={"albums","personInfo"})
@ToString(exclude = {"albums","personInfo"})
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sound_producer")
public class SoundProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producers_seq")
    @SequenceGenerator(name = "producers_seq", sequenceName = "sound_producer_id_seq", allocationSize = 1)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_info_id", referencedColumnName = "id")
    private PersonInfo personInfo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "l_sound_producer_album", joinColumns = @JoinColumn(name = "sound_producer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    private List<Album> albums;
}
