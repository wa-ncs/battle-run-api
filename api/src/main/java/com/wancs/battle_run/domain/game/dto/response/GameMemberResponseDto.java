package com.wancs.battle_run.domain.game.dto.response;

import com.wancs.battle_run.domain.game.entity.GameMember;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GameMemberResponseDto {
    private List<GameMember> gameMembers;

    @Builder
    public GameMemberResponseDto(List<GameMember> gameMembers){
        this.gameMembers = gameMembers;
    }
}
