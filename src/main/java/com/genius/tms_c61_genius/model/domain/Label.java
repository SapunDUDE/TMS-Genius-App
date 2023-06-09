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
@Table(name = "label")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "labels_seq")
    @SequenceGenerator(name = "labels_seq", sequenceName = "label_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "description")
    private String description;
}
