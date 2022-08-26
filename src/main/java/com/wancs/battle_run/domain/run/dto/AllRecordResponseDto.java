package com.wancs.battle_run.domain.run.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class AllRecordResponseDto {
    private float totalDistance;
    private float distance;
    private String runningTime;
    private String totalRunningTime;
    private LocalTime registrationDate;

    @Builder
    public AllRecordResponseDto(float totalDistance, float distance, String totalRunningTime
            ,String runningTime, LocalTime registrationDate){
        this.totalDistance = totalDistance;
        this.distance = distance;
        this.totalRunningTime = totalRunningTime;
        this.runningTime = runningTime;
        this.registrationDate = registrationDate;
    }
}
