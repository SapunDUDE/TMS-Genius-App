package com.genius.tms_c61_genius.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

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
