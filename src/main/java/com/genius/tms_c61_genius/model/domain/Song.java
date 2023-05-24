package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@EqualsAndHashCode(exclude={"album","artists"})
@ToString(exclude = {"album","artists"})
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "songs_seq")
    @SequenceGenerator(name = "songs_seq", sequenceName = "song_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "song_title")
    private String songTitle;

    @Column(name = "text")
    private String text;

    @Column(name = "duration")
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinTable(name = "l_artist_song",
            joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id")
    )
    private List<Artist> artists;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
