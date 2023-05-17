package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.mapper.LabelDtoMapper;
import com.genius.tms_c61_genius.model.domain.Label;
import com.genius.tms_c61_genius.model.request.LabelReqDto;
import com.genius.tms_c61_genius.model.response.LabelResDto;
import com.genius.tms_c61_genius.repository.LabelRepository;
import com.genius.tms_c61_genius.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;
    private final LabelDtoMapper labelDtoMapper;
    @Autowired
    public LabelServiceImpl(LabelRepository labelRepository, LabelDtoMapper labelDtoMapper) {
        this.labelRepository = labelRepository;
        this.labelDtoMapper = labelDtoMapper;
    }

    @Override
    public LabelResDto createLabel(LabelReqDto labelReqDto) {
        System.out.println(labelReqDto);
        Label savedLabel = labelRepository.save(labelDtoMapper.labelReqToLabel(labelReqDto));
        return labelDtoMapper.labelToLabelRes(savedLabel);
    }

    @Override
    public LabelResDto getLabelInfo(String labelName) {
        return labelDtoMapper.labelToLabelRes(labelRepository.getLabelByLabelName(labelName));
    }

    @Override
    public LabelResDto updateLabelInfo(LabelReqDto labelReqDto) {
        Label updatedLabel = labelRepository.getLabelByLabelName(labelReqDto.getLabelName());
        updatedLabel.setDescription(labelReqDto.getDescription());
        return labelDtoMapper.labelToLabelRes(updatedLabel);
    }

    @Override
    public void deleteLabel(String labelName) {
        labelRepository.delete(labelRepository.getLabelByLabelName(labelName));
    }
}
