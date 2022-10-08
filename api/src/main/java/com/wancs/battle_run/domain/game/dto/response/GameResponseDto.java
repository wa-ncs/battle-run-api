package com.wancs.battle_run.domain.game.dto.response;

import com.wancs.battle_run.domain.game.entity.Game;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameResponseDto {
    private Game game;

    @Builder
    public GameResponseDto(Game game){
        this.game = game;
    }
}
