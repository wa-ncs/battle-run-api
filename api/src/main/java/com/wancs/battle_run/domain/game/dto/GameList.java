package com.wancs.battle_run.domain.game.dto;

import com.wancs.battle_run.domain.game.dto.response.GameResponseDto;
import com.wancs.battle_run.domain.game.entity.Game;

import java.util.List;
import java.util.stream.Collectors;

public class GameList {
    private List<Game> games;


    public GameList(List<Game> games){
        this.games = games;
    }

    public List<GameResponseDto> toGameListResponseDto(){
        return this.games.stream()
                .map(game -> GameResponseDto.fromEntity(game))
                .collect(Collectors.toList());
    }
}
