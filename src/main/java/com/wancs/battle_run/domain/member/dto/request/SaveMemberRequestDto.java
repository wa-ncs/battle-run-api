package com.wancs.battle_run.domain.member.dto.request;

import com.wancs.battle_run.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveMemberRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String nickName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String type;

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .nickName(this.nickName)
                .email(this.email)
                .password(this.password)
                .type(this.type)
                .build();
    }
}
