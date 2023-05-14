package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albums_seq")
    @SequenceGenerator(name = "albums_seq", sequenceName = "album_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "album_title")
    private String albumTitle;

    @Column(name = "created")
    private Date created;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_type_id", referencedColumnName = "id")
    private AlbumType albumType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private Label label;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private  Genre genre;

    @ManyToMany(mappedBy = "albums")
    private List<Artist> artists;
    @ManyToMany(mappedBy = "albums")
    private List<SoundProducer> soundProducers;
}
