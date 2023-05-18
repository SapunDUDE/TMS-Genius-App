package com.genius.tms_c61_genius.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReqDto {
    private String content;
    private float rating;
    private Integer userId;
    private Integer songId;
}
