package com.wancs.battle_run.domain.run.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class RecordResponseDto {
    private float distance;
    private String runningTime;
    private String face;
    private int calorie;
    private String imageUrl;
    private LocalTime registrationDate;

    @Builder
    public RecordResponseDto(float distance, String runningTime, String face, int calorie,
                             String imageUrl, LocalTime registrationDate){
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
        this.registrationDate = registrationDate;
    }
}
