package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label,Integer> {
    Optional<Label> getLabelByLabelName(String labelName);
    Boolean existsLabelByLabelName(String labelName);
}
