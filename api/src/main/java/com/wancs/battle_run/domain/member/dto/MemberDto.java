package com.wancs.battle_run.domain.member.dto;

import com.wancs.battle_run.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter()
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String nickName;
    private String email;
    private String password;
    private String type;

    @Builder
    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.nickName = member.getNickName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.type = member.getType();
    }
}
