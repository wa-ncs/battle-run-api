package com.wancs.battle_run.domain.running.dao;

import com.wancs.battle_run.domain.running.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record,Long> {
    public List<Record> findRecordByUserId(Long userId);
}
