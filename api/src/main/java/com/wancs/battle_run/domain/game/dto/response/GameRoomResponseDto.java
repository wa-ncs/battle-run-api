package com.wancs.battle_run.domain.game.dto.response;

import com.wancs.battle_run.domain.game.entity.Game;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

@Getter
public class GameRoomResponseDto {
    private GameResponseDto game;

    private List<GameMemberResponseDto> gameMembers;

    @Builder
    public GameRoomResponseDto(Game game, List<GameMemberResponseDto> gameMembers){
        this.game = GameResponseDto.builder()
                .entity(game)
                .build();
        this.gameMembers = gameMembers;
    }
}
