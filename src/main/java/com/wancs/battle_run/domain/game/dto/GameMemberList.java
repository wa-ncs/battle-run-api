package com.wancs.battle_run.domain.game.dto;

import com.wancs.battle_run.domain.game.dto.response.GameMemberResponseDto;
import com.wancs.battle_run.domain.game.entity.GameMember;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameMemberList {
    private List<GameMember> gameMembers;

    public GameMemberList(List<GameMember> gameMembers){
        this.gameMembers = gameMembers;
    }

    public List<GameMemberResponseDto> toGameMemberListResponseDto(){
        return this.gameMembers.stream()
                .map(gameMember -> GameMemberResponseDto.fromEntity(gameMember))
                .collect(Collectors.toList());
    }
}
