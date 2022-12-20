package com.wancs.battle_run.domain.auth.dto;

import com.wancs.battle_run.domain.auth.entity.Auth;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TokenDto {
    @NotBlank()
    private String accessToken;
    @NotBlank()
    private String refreshToken;

    @Builder
    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Auth toEntity() {
        return Auth.builder()
                .accessToken(this.accessToken)
                .refreshToken(this.refreshToken)
                .build();
    }

    public static TokenDto fromEntity(Auth entity) {
        return TokenDto.builder()
                .accessToken(entity.getAccessToken())
                .refreshToken(entity.getRefreshToken())
                .build();
    }
}
