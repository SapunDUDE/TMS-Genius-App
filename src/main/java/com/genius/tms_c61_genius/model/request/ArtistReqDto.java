package com.genius.tms_c61_genius.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistReqDto {
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 40)
    private String lastName;
    @NotEmpty
    @NotNull
    @Size(min = 7, max = 30)
    private String nickname;

    @NotNull
    @Size(max =500)
    private String bio;
    @NotEmpty
    @NotNull
    private Date birthDate;
    @NotEmpty
    @NotNull
    private String country;
    @NotEmpty
    @NotNull
    @Size(min = 5,max = 32)
    private String userLogin;
}


