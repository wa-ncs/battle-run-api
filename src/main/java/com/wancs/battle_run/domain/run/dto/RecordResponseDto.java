package com.wancs.battle_run.domain.run.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecordResponseDto {
    private float distance;
    private String runningTime;
    private String face;
    private int calorie;
    private String imageUrl;

    @Builder
    public RecordResponseDto(float distance, String runningTime, String face, int calorie, String imageUrl){
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
    }
}
