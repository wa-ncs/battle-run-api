package com.wancs.battle_run.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LoginDto {

    @NotBlank()
    @Email()
    private String email;
    @NotBlank()
    private String password;
    @NotBlank()
    private String type;

    @Builder
    public LoginDto(String email, String type) {
        this.email = email;
        this.type = type;
    }

    @Builder
    public LoginDto(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
