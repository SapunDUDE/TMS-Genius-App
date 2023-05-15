package com.genius.tms_c61_genius.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto {
    private String nickname;
    private String login;
    private String password;
    private String role;
}


