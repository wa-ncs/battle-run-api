package com.wancs.battle_run.domain.running.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AllRecordResponseDto {
    private Float totalDistance;
    private String totalRunningTime;
    private String totalFace;
    private Integer totalCalorie;
    List<RecordResponseDto> recordList;

    @Builder
    public AllRecordResponseDto(Float totalDistance, String totalRunningTime, String totalFace, Integer totalCalorie,
                                List<RecordResponseDto> recordList){
        this.totalDistance = totalDistance;
        this.totalRunningTime = totalRunningTime;
        this.totalFace = totalFace;
        this.totalCalorie = totalCalorie;
        this.recordList = recordList;

    }
}
