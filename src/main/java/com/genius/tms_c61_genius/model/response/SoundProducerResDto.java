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
public class SoundProducerResDto {
    private String firstName;
    private String lastName;
    private String nickname;
    private String bio;
    private Date birthDate;
    private String country;
}
