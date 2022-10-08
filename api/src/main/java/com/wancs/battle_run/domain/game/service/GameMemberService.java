package com.wancs.battle_run.domain.game.service;

import com.wancs.battle_run.domain.game.dao.GameMemberRepository;
import com.wancs.battle_run.domain.game.entity.GameMember;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameMemberService {

    @Autowired
    private GameMemberRepository gameMemberRepository;

    public List<GameMember> findByGameId(Long gameId){
        return gameMemberRepository.findByGameId(gameId);
    }

    @Transactional
    public void gameMemberInsert(Long gameId, List<Long> insertList){
        for(Long memberId : insertList){
            GameMember gameMember = GameMember.builder()
                    .gameId(gameId)
                    .memberId(memberId)
                    .participationStatus(null)
                    .ranking(0)
                    .build();

            gameMemberRepository.save(gameMember);
        }
    }

    @Transactional
    public void gameMemberDelete(Long gameId, List<Long> deleteList){
        gameMemberRepository.deleteGameMemberByGameId(gameId, deleteList);
    }
}
