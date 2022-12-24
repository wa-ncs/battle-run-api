package com.wancs.battle_run.domain.auth.dto;

import com.wancs.battle_run.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
