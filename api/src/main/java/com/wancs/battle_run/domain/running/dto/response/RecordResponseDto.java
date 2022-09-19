package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.entity.Record;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RecordResponseDto {
    private Long id;
    private Float distance;
    private Long runningTime;
    private Float face;
    private Integer calorie;
    private String imageUrl;


    @Builder
    public RecordResponseDto(Record entity){
        this.id = entity.getId();
        this.distance = entity.getDistance();
        this.runningTime = entity.getRunningTime();
        this.face = entity.getFace();
        this.calorie = entity.getCalorie();
        this.imageUrl = entity.getImageUrl();
    }
}
