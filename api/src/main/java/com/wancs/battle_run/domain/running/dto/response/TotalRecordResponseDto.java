package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.common.RecordCommonMethod;
import com.wancs.battle_run.domain.running.dto.TotalRecordInterface;
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
    private String totalFace;
    List<RecordResponseDto> records;

    @Builder
    public TotalRecordResponseDto(TotalRecordInterface totalRecord, List<RecordResponseDto> records){
        String totalFace = RecordCommonMethod.getFace(totalRecord.getTotalDistance(), totalRecord.getTotalRunningTime());

        this.totalDistance = totalRecord.getTotalDistance();
        this.totalRunningTime = totalRecord.getTotalRunningTime();
        this.totalCalorie = totalRecord.getTotalCalorie();
        this.totalFace = totalFace;
        this.records = records;
    }
}
