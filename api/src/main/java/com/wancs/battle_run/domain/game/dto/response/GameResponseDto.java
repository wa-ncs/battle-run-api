package com.wancs.battle_run.domain.game.dto.response;

import com.wancs.battle_run.domain.game.entity.Game;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import static com.wancs.battle_run.global.common.InitializationByType.*;

@Getter
public class GameResponseDto {
    private Long id;

    private String name;

    private Float distance;

    private Timestamp startTime;

    private Timestamp endTime;

    private String reward;

    private Long createMemberId;


    @Builder
    public GameResponseDto(Game entity){
        this.id = checkLong(entity.getId());
        this.name = checkString(entity.getName());
        this.distance = checkFloat(entity.getDistance());
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.reward = checkString(entity.getReward());
        this.createMemberId = checkLong(entity.getCreateMemberId());
    }

    public static GameResponseDto fromEntity(Game entity){
        return GameResponseDto.builder()
                .entity(entity)
                .build();
    }
}
