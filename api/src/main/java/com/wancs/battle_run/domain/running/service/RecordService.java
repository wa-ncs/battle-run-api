package com.wancs.battle_run.domain.running.service;

import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import com.wancs.battle_run.domain.running.entity.Record;
import com.wancs.battle_run.domain.running.dto.request.SaveRecordRequestDto;
import com.wancs.battle_run.domain.running.dao.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;

    public List<Record> findRecordsByUserId(Long userId){
        return recordRepository
                .findRecordByUserId(userId)
                .stream()
                .collect(Collectors.toList());
    }

    public Record findByRecord(Long recordId){
        return recordRepository
                .findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("no data"));
    }

    @Transactional
    public Long save(SaveRecordRequestDto requestDto){
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
        Record record = this.findByRecord(id);
        record.changeRecord(requestDto);
        return record.getId();
    }
}
