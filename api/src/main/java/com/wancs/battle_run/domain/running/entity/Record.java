package com.wancs.battle_run.domain.running.entity;

import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Float face;

    private Integer calorie;

    private String imageUrl;

    @Builder
    public Record(Long id, Long userId, Float distance, Long runningTime,
                  Float face, Integer calorie, String imageUrl){
        this.id = id;
        this.userId = userId;
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
    }

    public void changeRecord(UpdateRecordRequestDto requestDto){
        if(this.distance != requestDto.getDistance()){
            this.distance = requestDto.getDistance();
        }

        if(this.calorie != requestDto.getCalorie()){
            this.calorie = requestDto.getCalorie();
        }

        if(this.runningTime != requestDto.getRunningTime()){
            this.runningTime = requestDto.getRunningTime();
        }

        if(this.face != requestDto.getFace()){
            this.face = requestDto.getFace();
        }

        if(!this.imageUrl.equals(requestDto.getImageUrl())){
            this.imageUrl = requestDto.getImageUrl();
        }
    }
}
