package com.wancs.battle_run.domain.member.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UpdateMemberRequestDto {
    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty
    private String nickName;
    @NotEmpty
    @Email
    private String email;
}
