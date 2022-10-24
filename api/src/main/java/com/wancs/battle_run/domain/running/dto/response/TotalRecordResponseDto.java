package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.dto.TotalRecordInterface;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.wancs.battle_run.global.common.InitializationByType.*;

@Getter
@NoArgsConstructor
public class TotalRecordResponseDto {
    private Float totalDistance;
    private Long totalRunningTime;
    private Integer totalCalorie;
    private Integer totalPace;
    List<RecordListResponseDto> records;

    @Builder
    public TotalRecordResponseDto(TotalRecordInterface totalRecord, List<RecordListResponseDto> records){
        this.totalDistance = checkFloat(totalRecord.getTotalDistance());
        this.totalRunningTime = checkLong(totalRecord.getTotalRunningTime());
        this.totalCalorie = checkInteger(totalRecord.getTotalCalorie());
        this.totalPace = totalRecord.getTotalPace() / records.size();
        this.records = records;
    }
}
