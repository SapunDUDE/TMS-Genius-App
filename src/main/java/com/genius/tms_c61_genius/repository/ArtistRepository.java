package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {
}
