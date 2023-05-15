package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonInfoRepository extends JpaRepository<PersonInfo,Integer> {
}
