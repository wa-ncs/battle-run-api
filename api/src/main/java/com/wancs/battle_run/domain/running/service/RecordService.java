package com.wancs.battle_run.domain.running.service;

import com.wancs.battle_run.domain.running.common.RecordCommonMethord;
import com.wancs.battle_run.domain.running.dto.TotalRecordInterface;
import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import com.wancs.battle_run.domain.running.dto.response.RecordResponseDto;
import com.wancs.battle_run.domain.running.dto.response.TotalRecordResponseDto;
import com.wancs.battle_run.domain.running.entity.Record;
import com.wancs.battle_run.domain.running.dto.request.SaveRecordRequestDto;
import com.wancs.battle_run.domain.running.dao.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final RecordCommonMethord recordCommonMethord;

    public TotalRecordResponseDto findRecordsByUserId(Long userId){
        List<RecordResponseDto> recordList = new ArrayList<>();

        List<Record> records = recordRepository.findRecordsByUserId(userId)
                .stream()
                .collect(Collectors.toList());

        for(Record record : records){
            recordList.add(RecordResponseDto.fromEntity(record));
        }

        //리턴으로 interface를 받게 된 이유 -> https://algorithmstudy-mju.tistory.com/153
        TotalRecordInterface totalRecord = recordRepository.findTotalRecordByUserId(userId);
        Float totalDistance = totalRecord.getTotalDistance();
        Long totalRunningTime = totalRecord.getTotalRunningTime();

        String totalFace = recordCommonMethord.getMinuteFace(totalDistance, totalRunningTime);

        TotalRecordResponseDto responseDto = TotalRecordResponseDto.builder()
                .totalDistance(totalDistance)
                .totalRunningTime(totalRunningTime)
                .totalFace(totalFace)
                .totalCalorie(totalRecord.getTotalCalorie())
                .recordList(recordList)
                .build();

        return responseDto;
    }

    public Record findByRecord(Long recordId){
        return recordRepository
                .findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("no data"));
    }

    @Transactional
    public Long save(SaveRecordRequestDto requestDto){
        String face = recordCommonMethord.getMinuteFace(requestDto.getDistance(), requestDto.getRunningTime());
        requestDto.setFace(face);

        Record record = requestDto.toEntity();

        return recordRepository
                .save(record)
                .getId();
    }

    @Transactional
    public void deleteById(Long recordId){
        recordRepository.deleteById(recordId);
    }

    @Transactional
    public Long update(Long id, UpdateRecordRequestDto requestDto){
        //거리와 시간이 바뀐 데이터로 넘어왔다면 face도 다시 계산
        if(requestDto.getDistance() > 0 && requestDto.getRunningTime() > 0){
            String face = recordCommonMethord.getMinuteFace(requestDto.getDistance(), requestDto.getRunningTime());
            requestDto.setFace(face);
        }

        Record record = this.findByRecord(id);
        record.changeRecord(requestDto);
        return record.getId();
    }
}
