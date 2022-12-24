package com.wancs.battle_run.domain.member.dto.response;

import com.wancs.battle_run.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private Long id;
    private String name;
    private String nickName;
    private String email;
    private String type;

    @Builder
    public MemberResponseDto(Long id, String name, String nickName, String email, String type){
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.type = type;
    }

    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .type(member.getType())
                .build();
    }
}