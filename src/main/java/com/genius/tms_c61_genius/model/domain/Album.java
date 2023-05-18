package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude={"soundProducers", "artists","genre","label","albumType"})
@ToString(exclude = {"soundProducers", "artists", "genre", "label", "albumType"})
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albums_seq")
    @SequenceGenerator(name = "albums_seq", sequenceName = "album_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "album_title")
    private String albumTitle;
    @Column(name = "created")
    private Date created;
    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinColumn(name = "album_type_id", referencedColumnName = "id")
    private AlbumType albumType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private Label label;
    @OneToOne(fetch =FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private  Genre genre;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinTable(name = "l_artist_album",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id")
    )
    private List<Artist> artists;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinTable(name = "l_sound_producer_album", joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sound_producer_id", referencedColumnName = "id"))
    private List<SoundProducer> soundProducers;
}
