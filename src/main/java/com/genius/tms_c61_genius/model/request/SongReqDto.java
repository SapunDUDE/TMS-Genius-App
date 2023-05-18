package com.genius.tms_c61_genius.model.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongReqDto {
    @NotEmpty
    @NotNull
    @Size(max = 30)
    private String songTitle;
    @NotEmpty
    @NotNull
    @Size(max = 2000)
    private String text;
    @Min(1)
    @Max(600)
    private int duration;

}
