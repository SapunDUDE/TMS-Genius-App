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
@Table(name = "album_type")
public class AlbumType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_type_seq")
    @SequenceGenerator(name = "album_type_seq", sequenceName = "album_type_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "type_name")
    private String typeName;
}
