package com.wancs.battle_run.domain.game.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SaveGameMemberRequestDto {
    @NotNull
    private Long memberId;
}
