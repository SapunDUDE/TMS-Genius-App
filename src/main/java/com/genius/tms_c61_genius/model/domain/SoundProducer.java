package com.genius.tms_c61_genius.model.domain;

import jakarta.persistence.CascadeType;
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
@EqualsAndHashCode(exclude={"albums","personInfo"})
@ToString(exclude = {"albums","personInfo"})
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sound_producer")
public class SoundProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producers_seq")
    @SequenceGenerator(name = "producers_seq", sequenceName = "sound_producer_id_seq", allocationSize = 1)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_info_id", referencedColumnName = "id")
    private PersonInfo personInfo;

    @ManyToMany(mappedBy = "soundProducers")
    private List<Album> albums;
}
