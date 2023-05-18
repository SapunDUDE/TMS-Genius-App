package com.genius.tms_c61_genius.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReqDto {
    @NotEmpty
    @NotNull
    @Size(min = 5, max = 30)
    private String nickname;
    @NotEmpty
    @NotNull
    @Size(min = 5, max = 32)
    private String login;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 32)
    @Pattern(regexp = "[a-zA-Z0-9]{6,}")
    private String password;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 32)
    private String role;
}


