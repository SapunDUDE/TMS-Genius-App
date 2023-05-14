package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "songs_seq")
    @SequenceGenerator(name = "songs_seq", sequenceName = "song_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "song_title")
    private String songTitle;

    @Column(name = "text")
    private String text;
    @Column(name = "duration")
    private int duration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;
    @ManyToMany(mappedBy = "songs")
    private List<Artist> artists;

}
