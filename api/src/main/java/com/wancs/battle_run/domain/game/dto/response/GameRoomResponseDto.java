package com.wancs.battle_run.domain.game.dto.response;

import com.wancs.battle_run.domain.game.entity.Game;
import com.wancs.battle_run.domain.game.entity.GameMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class GameRoomResponseDto {
    private Game game;

    private List<GameMember> gameMembers;

    @Builder
    public GameRoomResponseDto(Game game, List<GameMember> gameMembers){
        this.game = game;
        this.gameMembers = gameMembers;
    }
}
