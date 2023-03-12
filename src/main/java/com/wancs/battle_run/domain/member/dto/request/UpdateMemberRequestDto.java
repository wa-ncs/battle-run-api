package com.wancs.battle_run.domain.member.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
public class UpdateMemberRequestDto {

    private String name;
    private String nickName;
    private String email;
    private String password;
}
