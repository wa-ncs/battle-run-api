package com.wancs.battle_run.domain.running.entity;

import com.wancs.battle_run.domain.running.common.RecordCommonMethod;
import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
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

    private String face;

    private Integer calorie;

    private String imageUrl;

    @Builder
    public Record(Long id, Long userId, Float distance, Long runningTime,
                  String face, Integer calorie, String imageUrl){
        this.id = id;
        this.userId = userId;
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
    }

    public void changeRecord(UpdateRecordRequestDto requestDto){
        if(requestDto.getDistance() > 0){
            this.distance = requestDto.getDistance();
        }

        if(requestDto.getCalorie() > 0){
            this.calorie = requestDto.getCalorie();
        }

        if(requestDto.getRunningTime() > 0){
            this.runningTime = requestDto.getRunningTime();
        }

        if(StringUtils.isNotBlank(requestDto.getFace())){
            this.face = requestDto.getFace();
        }

        if(StringUtils.isNotBlank(requestDto.getImageUrl())){
            this.imageUrl = requestDto.getImageUrl();
        }

        //거리와 시간이 새로 들어오면 face 재계산
        if(requestDto.getDistance() > 0 && requestDto.getRunningTime() > 0){
            this.face = RecordCommonMethod.getFace(this.getDistance(), this.getRunningTime());
        }

    }
}
