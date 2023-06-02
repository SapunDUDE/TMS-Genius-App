package com.genius.tms_c61_genius.service;

import com.genius.tms_c61_genius.model.request.LabelReqDto;
import com.genius.tms_c61_genius.model.response.LabelResDto;

public interface LabelService {
        LabelResDto createLabel(LabelReqDto labelReqDto);
        LabelResDto updateLabelInfo(LabelReqDto labelReqDto);
        LabelResDto getLabelInfo(String labelName);
        void deleteLabel(String labelName);


}
