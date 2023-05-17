package com.genius.tms_c61_genius.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArtistReqDto {
    private String firstName;
    private String lastName;
    private String nickname;
    private String bio;
    private Date birthDate;
    private String country;
}
