package com.wancs.battle_run.domain.member.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UpdateMemberRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String email;
}
