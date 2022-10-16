package com.wancs.battle_run.domain.running.service;

import com.wancs.battle_run.domain.running.common.RecordCommonMethod;
import com.wancs.battle_run.domain.running.dto.RecordList;
import com.wancs.battle_run.domain.running.dto.TotalRecordInterface;
import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import com.wancs.battle_run.domain.running.entity.Record;
import com.wancs.battle_run.domain.running.dto.request.SaveRecordRequestDto;
import com.wancs.battle_run.domain.running.dao.RecordRepository;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordList findRecordsByUserId(Long userId){
        return new RecordList(recordRepository.findRecordsByUserId(userId));
    }

    //리턴으로 interface를 받게 된 이유 -> https://algorithmstudy-mju.tistory.com/153
    public TotalRecordInterface findTotalRecord(Long userId){
        return recordRepository.findTotalRecordByUserId(userId);
    }

    public Optional<Record> findById(Long recordId){
        return recordRepository
                .findById(recordId);
    }

    @Transactional
    public Long save(SaveRecordRequestDto requestDto){
        String face = RecordCommonMethod.getFace(requestDto.getDistance(), requestDto.getRunningTime());
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
        Optional<Record> optionalRecord = this.findById(id);
        Record record = optionalRecord.get();
        record.changeRecord(requestDto);

        return record.getId();
    }

    public Boolean isExistRecord(Long recordId){
        Optional<Record> optionalRecord = this.findById(recordId);
        return optionalRecord.isPresent();
    }

    public ResponseDto badRequestErrorDto(String msg){
        ResponseDto dto = ResponseDto.builder()
                .message(msg)
                .code(StatusEnum.BAD_REQUEST)
                .build();
        return dto;
    }
}
