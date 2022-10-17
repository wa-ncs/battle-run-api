package com.wancs.battle_run.domain.member.dto.response;

import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJoinResponseDto {

    private Long id;
    private String name;
    private String nickName;

    private String email;
    private String type;
    private String accessToken;
    private String refreshToken;

    @Builder
    public MemberJoinResponseDto(
            Long id, String name, String nickName,
            String email, String type, String accessToken,
            String refreshToken
    ) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.type = type;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static MemberJoinResponseDto fromEntity(Member member, Auth auth) {
        return MemberJoinResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .type(member.getType())
                .accessToken(auth.getAccessToken())
                .refreshToken(auth.getRefreshToken())
                .build();
    }
}