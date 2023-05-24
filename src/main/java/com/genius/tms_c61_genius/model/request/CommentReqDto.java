package com.genius.tms_c61_genius.model.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReqDto {
    @NotNull
    private String content;

    @NotEmpty
    @NotNull
    @Max(5)
    private float rating;

    @NotEmpty
    @NotNull
    private Integer userId;

    @NotEmpty
    @NotNull
    private Integer songId;
}
