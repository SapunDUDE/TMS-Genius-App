package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "album_type")
public class AlbumType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_type_seq")
    @SequenceGenerator(name = "album_type_seq", sequenceName = "album_type_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "type_name")
    private String typeName;
}
