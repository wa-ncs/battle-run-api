package com.wancs.battle_run.domain.game.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateGameMemberRequestDto {

    @NotNull
    private Long gameId;

    private List<Long> insertList;

    private List<Long> deleteList;
}
