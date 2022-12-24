package com.wancs.battle_run.domain.member.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UpdateMemberRequestDto {

    private String name;
    private String nickName;
    private String email;
    private String password;
    private String type;
}
