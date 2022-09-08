package com.wancs.battle_run.domain.member.dto.request;

import com.wancs.battle_run.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequestDto {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String nickName;
    @NotNull
    @NotEmpty
    @Email
    private String email;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .nickName(this.nickName)
                .email(this.email)
                .build();
    }
}
