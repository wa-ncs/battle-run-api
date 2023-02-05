package com.wancs.battle_run.domain.game.dto.response;

import com.wancs.battle_run.domain.game.entity.GameMember;
import lombok.Builder;
import lombok.Getter;

import static com.wancs.battle_run.global.common.InitializationByType.*;

@Getter
public class GameMemberResponseDto {
    private Long id;

    private Long memberId;

    private String participationStatus;

    private Integer ranking;

    @Builder
    public GameMemberResponseDto(GameMember entity){
        this.id = checkLong(entity.getId());
        this.memberId = checkLong(entity.getMemberId());
        this.participationStatus = checkString(entity.getParticipationStatus());
        this.ranking = checkInteger(entity.getRanking());
    }

    public static GameMemberResponseDto fromEntity(GameMember entity){
        return GameMemberResponseDto.builder()
                .entity(entity)
                .build();
    }
}
