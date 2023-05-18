package com.genius.tms_c61_genius.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelReqDto {
    @NotEmpty
    @NotNull
    @Size(min = 5,max = 30)
    private String labelName;
    @NotNull
    @Size(max = 500)
    private String description;
}
