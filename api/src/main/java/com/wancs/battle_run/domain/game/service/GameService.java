package com.wancs.battle_run.domain.game.service;

import com.wancs.battle_run.domain.game.dao.GameRepository;
import com.wancs.battle_run.domain.game.dto.request.SaveGameRequestDto;
import com.wancs.battle_run.domain.game.dto.request.UpdateGameRequestDto;
import com.wancs.battle_run.domain.game.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public Long save(SaveGameRequestDto requestDto){
        return gameRepository.save(requestDto.toEntity()).getId();
    }

    public Game findGameById(Long gameId){
        return gameRepository
                .findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("no data"));
    }

    @Transactional
    public Long update(UpdateGameRequestDto requestDto){
        Game game = this.findGameById(requestDto.getGameId());
        game.changeGame(requestDto);

        return game.getId();
    }
}
