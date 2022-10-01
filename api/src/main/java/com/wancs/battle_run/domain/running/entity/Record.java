package com.wancs.battle_run.domain.running.entity;

import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
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
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Float distance;

    private Long runningTime;

    private String face;

    private Integer calorie;

    private String imageUrl;

    private String todayMemo;

    @Builder
    public Record(Long id, Long userId, Float distance, Long runningTime,
                  String face, Integer calorie, String imageUrl, String todayMemo){
        this.id = id;
        this.userId = userId;
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
        this.todayMemo = todayMemo;
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

        if(StringUtils.isNotBlank(requestDto.getTodayMemo())){
            this.todayMemo = requestDto.getTodayMemo();
        }
    }
}
