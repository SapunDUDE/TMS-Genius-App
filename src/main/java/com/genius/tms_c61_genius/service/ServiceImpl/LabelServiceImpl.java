package com.genius.tms_c61_genius.service.ServiceImpl;

import com.genius.tms_c61_genius.exception.BadDataException;
import com.genius.tms_c61_genius.exception.NotFoundException;
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
        if(labelRepository.existsLabelByLabelName(labelReqDto.getLabelName())) {
            throw new BadDataException("label with such name is already exist");
        }
        Label savedLabel = labelRepository.save(labelDtoMapper.labelReqToLabel(labelReqDto));
        return labelDtoMapper.labelToLabelRes(savedLabel);
    }

    @Override
    public LabelResDto getLabelInfo(String labelName) {
        if(!labelRepository.existsLabelByLabelName(labelName)) {
            throw new NotFoundException("label not found");
        }
        return labelDtoMapper.labelToLabelRes(labelRepository.getLabelByLabelName(labelName).get());
    }

    @Override
    public LabelResDto updateLabelInfo(LabelReqDto labelReqDto) {
        if(!labelRepository.existsLabelByLabelName(labelReqDto.getLabelName())) {
            throw new NotFoundException("label not found");
        }
        Label updatedLabel = labelRepository.getLabelByLabelName(labelReqDto.getLabelName()).get();
        updatedLabel.setDescription(labelReqDto.getDescription());
        return labelDtoMapper.labelToLabelRes(updatedLabel);
    }

    @Override
    public void deleteLabel(String labelName) {
        if(!labelRepository.existsLabelByLabelName(labelName)) {
            throw new NotFoundException("label not found");
        }
        labelRepository.delete(labelRepository.getLabelByLabelName(labelName).get());
    }
}
