package com.genius.tms_c61_genius.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteArtistReqDto {
    private String nickname;
    private String userLogin;
}
