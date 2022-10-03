package com.wancs.battle_run.domain.running.dto;

import com.wancs.battle_run.domain.running.dto.response.RecordResponseDto;
import com.wancs.battle_run.domain.running.entity.Record;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecordList {
    private List<Record> records;

    public RecordList(List<Record> records){
        this.records = records;
    }

    public List<RecordResponseDto> toRecordResponseDto(){
        List<RecordResponseDto> responseDto = new ArrayList<>();

        for(Record record : this.records){
            responseDto.add(RecordResponseDto.fromEntity(record));
        }

        return responseDto;
    }
}
