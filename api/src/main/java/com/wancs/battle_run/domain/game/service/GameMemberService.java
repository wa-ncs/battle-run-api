package com.wancs.battle_run.domain.game.service;

import com.wancs.battle_run.domain.game.dao.GameMemberRepository;
import com.wancs.battle_run.domain.game.dto.GameMemberList;
import com.wancs.battle_run.domain.game.dto.response.GameMemberResponseDto;
import com.wancs.battle_run.domain.game.dto.response.GameRoomResponseDto;
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

    public GameMemberList findByGameId(Long gameId){
        return new GameMemberList(gameMemberRepository.findByGameId(gameId));
    }

    public GameMember findById(Long gameMemberId){
        return gameMemberRepository.findById(gameMemberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터는 존재하지 않습니다."));
    }

    @Transactional
    public Long gameMemberInsert(Long gameId, Long memberId){
        GameMember gameMember = GameMember.builder()
                .gameId(gameId)
                .memberId(memberId)
                .participationStatus(null)
                .ranking(0)
                .build();

        return gameMemberRepository.save(gameMember).getId();
    }

    @Transactional
    public void gameMemberDelete(Long gameMemberId){
        gameMemberRepository.deleteById(gameMemberId);
    }
}
