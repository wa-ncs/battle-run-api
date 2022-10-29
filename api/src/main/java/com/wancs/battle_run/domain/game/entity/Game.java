package com.wancs.battle_run.domain.game.entity;

import com.wancs.battle_run.domain.game.dto.request.UpdateGameRequestDto;
import com.wancs.battle_run.global.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float distance;

    private Timestamp startTime;

    private Timestamp endTime;

    private String reward;

    private Long createMemberId;

    @Builder
    public Game(Long id, String name, Float distance, Timestamp startTime, Timestamp endTime, String reward, Long createMemberId){
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reward = reward;
        this.createMemberId = createMemberId;
    }

    public void changeGame(UpdateGameRequestDto requestDto){
        if(StringUtils.isNotBlank(requestDto.getName())){
            this.name = requestDto.getName();
        }

        if(requestDto.getDistance() != null && requestDto.getDistance() > 0){
            this.distance = requestDto.getDistance();
        }

        if(StringUtils.isNotBlank(requestDto.getReward())){
            this.reward = requestDto.getReward();
        }

        if(requestDto.getStartTime() != null){
            this.startTime = requestDto.getStartTime();
        }

        if(requestDto.getEndTime() != null){
            this.endTime = requestDto.getEndTime();
        }
    }
}
