package com.wancs.battle_run.domain.running.dto;

import com.wancs.battle_run.domain.running.dto.response.RecordListResponseDto;
import com.wancs.battle_run.domain.running.dto.response.RecordResponseDto;
import com.wancs.battle_run.domain.running.entity.Record;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecordList {
    private List<Record> records;

    public RecordList(List<Record> records){
        this.records = records;
    }

    public List<RecordListResponseDto> toRecordListResponseDto(){
        List<RecordListResponseDto> responseDto = this.records.stream()
                .map(record -> RecordListResponseDto.fromEntity(record))
                .collect(Collectors.toList());

        return responseDto;
    }
}
