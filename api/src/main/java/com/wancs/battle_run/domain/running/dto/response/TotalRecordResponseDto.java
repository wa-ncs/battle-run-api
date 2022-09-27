package com.wancs.battle_run.domain.running.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TotalRecordResponseDto {
    private Float totalDistance;
    private Long totalRunningTime;
    private Integer totalCalorie;
    List<RecordResponseDto> recordList;

    @Builder
    public TotalRecordResponseDto(Float totalDistance, Long totalRunningTime, Integer totalCalorie,
                                  List<RecordResponseDto> recordList){
        this.totalDistance = totalDistance;
        this.totalRunningTime = totalRunningTime;
        this.totalCalorie = totalCalorie;
        this.recordList = recordList;
    }
}
