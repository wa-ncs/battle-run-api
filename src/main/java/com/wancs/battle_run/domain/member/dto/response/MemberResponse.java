package com.wancs.battle_run.domain.member.dto.response;

import com.wancs.battle_run.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    private Long id;
    private String name;
    private String nickName;
    private String email;

    @Builder
    public MemberResponse(Long id, String name, String nickName, String email){
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
    }

    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .build();
    }
}