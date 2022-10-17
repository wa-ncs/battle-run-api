package com.wancs.battle_run.domain.auth.dto;

import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateTokenDto {
    private Member member;
    private String accessToken;
    private String refreshToken;

    @Builder
    public CreateTokenDto(Member member, String accessToken, String refreshToken) {
        this.member = member;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Auth toEntity() {
        return Auth.builder()
                .member(this.member)
                .accessToken(this.accessToken)
                .refreshToken(this.refreshToken)
                .build();
    }
}
