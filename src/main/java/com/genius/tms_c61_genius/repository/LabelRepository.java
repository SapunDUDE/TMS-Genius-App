package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Integer> {
    Label getLabelByLabelName(String labelName);
}
