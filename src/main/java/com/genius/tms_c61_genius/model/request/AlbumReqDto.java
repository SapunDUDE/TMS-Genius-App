package com.genius.tms_c61_genius.model.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumReqDto {

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 40)
    private String albumTitle;
    @NotEmpty
    @NotNull
    private Date created;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 20)
    private String albumType;
    @NotEmpty
    @NotNull
    private String label;
    @NotEmpty
    @NotNull
    private String genre;
    @NotEmpty
    @NotNull
    private List<SongReqDto> songs;
    @NotEmpty
    @NotNull
    private List<String> artistNickNames;
    @NotEmpty
    @NotNull
    private List<String> producerNickNames;


}
