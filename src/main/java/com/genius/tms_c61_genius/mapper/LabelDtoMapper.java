package com.genius.tms_c61_genius.mapper;

import com.genius.tms_c61_genius.model.domain.Label;
import com.genius.tms_c61_genius.model.request.LabelReqDto;
import com.genius.tms_c61_genius.model.response.LabelResDto;
import org.springframework.stereotype.Component;

@Component
public class LabelDtoMapper {

    public Label labelReqToLabel(LabelReqDto labelReqDto){
        return Label.builder()
                .labelName(labelReqDto.getLabelName())
                .description(labelReqDto.getDescription())
                .build();
    }
    public LabelResDto labelToLabelRes (Label label){
       return LabelResDto.builder()
               .labelName(label.getLabelName())
               .description(label.getDescription())
               .build();
    }
}
