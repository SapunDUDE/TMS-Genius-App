package com.genius.tms_c61_genius.model.request;

import com.genius.tms_c61_genius.model.domain.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumReqDto {
    private String albumTitle;
    private Date created;

    private String albumType;

    private String label;

    private String genre;
    private List<SongReqDto> songs;
    private List<String> artistNickNames;
    private List<String> producerNickNames;


}
