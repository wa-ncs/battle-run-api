package com.wancs.battle_run.domain.member.dto.response;

import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.running.dto.response.RecordResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberResponse {

    private Long id;
    private String name;
    private String nickName;
    private String email;

    @Builder
    public CreateMemberResponse(Long id, String name, String nickName, String email){
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
    }

    public static CreateMemberResponse fromEntity(Member member) {
        return CreateMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .build();
    }
}