package com.wancs.battle_run.domain.game.service;

import com.wancs.battle_run.domain.game.dao.GameRepository;
import com.wancs.battle_run.domain.game.dto.GameList;
import com.wancs.battle_run.domain.game.dto.request.SaveGameRequestDto;
import com.wancs.battle_run.domain.game.dto.request.UpdateGameRequestDto;
import com.wancs.battle_run.domain.game.entity.Game;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public Long save(SaveGameRequestDto requestDto){
        return gameRepository.save(requestDto.toEntity()).getId();
    }

    public Game findById(Long gameId){
        return gameRepository
                .findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 배틀런입니다."));
    }

    public GameList findByMemberId(Long memberId){
        return new GameList(gameRepository.findByMemberId(memberId));
    }

    @Transactional
    public Long update(UpdateGameRequestDto requestDto, Long gameId){
        Game game = this.findById(gameId);
        game.changeGame(requestDto);

        return game.getId();
    }
}
