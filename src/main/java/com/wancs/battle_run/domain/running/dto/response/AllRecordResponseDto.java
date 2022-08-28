package com.wancs.battle_run.domain.running.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AllRecordResponseDto {
    private float totalDistance;
    private String totalRunningTime;
    private String totalFace;
    private int totalCalorie;
    List<RecordResponseDto> recordList;

    @Builder
    public AllRecordResponseDto(float totalDistance, String totalRunningTime, String totalFace, int totalCalorie,
                                List<RecordResponseDto> recordList){
        this.totalDistance = totalDistance;
        this.totalRunningTime = totalRunningTime;
        this.totalFace = totalFace;
        this.totalCalorie = totalCalorie;
        this.recordList = recordList;

    }
}
