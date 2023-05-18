package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.domain.SoundProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoundProducerRepository extends JpaRepository<SoundProducer,Integer>  {
    Optional<SoundProducer> getSoundProducerById(Integer id);
    Optional<SoundProducer>getSoundProducerByPersonInfoNickname(String nickName);

    List<SoundProducer> getSoundProducersByAlbums(Album album);

    void deleteSoundProducerById(Integer id);
    void deleteSoundProducerByPersonInfoNickname(String nickname);

    Boolean existsSoundProducerByPersonInfoNickname(String nickName);
    Boolean existsSoundProducerById(Integer id);
}
