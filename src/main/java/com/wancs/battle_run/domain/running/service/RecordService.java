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

    public Record findById(Long recordId){
        return recordRepository
                .findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지않는 운동 기록입니다."));
    }

    @Transactional
    public Long save(SaveRecordRequestDto requestDto){
        return recordRepository
                .save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public void deleteById(Long recordId){
        recordRepository.deleteById(recordId);
    }

    @Transactional
    public Long update(Long id, UpdateRecordRequestDto requestDto){
        Record record = this.findById(id);
        record.changeRecord(requestDto);

        return record.getId();
    }
}
