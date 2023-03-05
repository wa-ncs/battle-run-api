package com.wancs.battle_run.domain.running.entity;

import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import com.wancs.battle_run.global.common.CommonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static com.wancs.battle_run.global.common.InitializationByType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Float distance;

    private Long runningTime;

    private Integer pace;

    private Integer calorie;


    private String imageUrl;

    @Builder
    public Record(Long id, Long userId, Float distance, Long runningTime,
                  Integer pace, Integer calorie, String imageUrl){
        this.id = id;
        this.userId = userId;
        this.distance = distance;
        this.runningTime = runningTime;
        this.pace = pace;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
    }

    public void changeRecord(UpdateRecordRequestDto requestDto){
        if(checkFloat(requestDto.getDistance()) > 0){
            this.distance = requestDto.getDistance();
        }

        if(checkInteger(requestDto.getCalorie()) > 0){
            this.calorie = requestDto.getCalorie();
        }

        if(checkLong(requestDto.getRunningTime()) > 0){
            this.runningTime = requestDto.getRunningTime();
        }

        if(checkInteger(requestDto.getPace()) > 0){
            this.pace = requestDto.getPace();
        }

        if(StringUtils.isNotBlank(requestDto.getImageUrl())){
            this.imageUrl = requestDto.getImageUrl();
        }
    }
}
