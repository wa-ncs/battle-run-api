package com.wancs.battle_run.domain.member.dto.request;

import com.wancs.battle_run.domain.member.entity.Member;
import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequestDto {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String nickName;
    @NotNull
    @NotBlank
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
