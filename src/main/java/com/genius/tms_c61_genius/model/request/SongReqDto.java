package com.genius.tms_c61_genius.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongReqDto {
    private String songTitle;
    private String text;
    private int duration;

}