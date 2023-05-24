package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
@EqualsAndHashCode(exclude={"songs", "albums","personInfo","user"})
@ToString(exclude = {"songs", "albums","personInfo","user"})
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artists_seq")
    @SequenceGenerator(name = "artists_seq", sequenceName = "artist_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "img")
    private String img;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_info_id", referencedColumnName = "id")
    private PersonInfo personInfo;

    @ManyToMany(mappedBy = "artists")
    private List<Album> albums;

    @ManyToMany(mappedBy = "artists")
    private List<Song> songs;
}
