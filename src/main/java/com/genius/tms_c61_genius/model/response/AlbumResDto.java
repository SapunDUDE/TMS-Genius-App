package com.genius.tms_c61_genius.model.response;

import com.genius.tms_c61_genius.model.request.SongReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResDto {
    private String albumTitle;
    private Date created;

    private String albumType;

    private String label;

    private String genre;
}
