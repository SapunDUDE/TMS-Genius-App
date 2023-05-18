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
public class UpdateArtistReqDto {
    @NotNull
    @NotEmpty
    @Size(min = 2,max = 30)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 2,max = 40)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(min = 2,max = 30)
    private String nickname;
    @NotEmpty
    @Size(max = 500)
    private String bio;
    @NotNull
    @NotEmpty
    private Date birthDate;
    @NotNull
    @NotEmpty
    @Size(min = 5,max = 30)
    private String country;
}
