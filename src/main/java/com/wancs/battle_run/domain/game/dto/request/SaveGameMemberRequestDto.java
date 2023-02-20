package com.wancs.battle_run.domain.game.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveGameMemberRequestDto {
    @NotNull
    private Long memberId;
}
