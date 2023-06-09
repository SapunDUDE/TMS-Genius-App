package com.genius.tms_c61_genius.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongResDto {
    private String songTitle;
    private String text;
    private int duration;

}
