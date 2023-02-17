package com.wancs.battle_run.domain.running.dao;

import com.wancs.battle_run.domain.running.dto.TotalRecordInterface;
import com.wancs.battle_run.domain.running.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record,Long> {
    public List<Record> findRecordsByUserId(Long userId);

    @Query(value = "" +
            "SELECT " +
                "SUM(distance) as totalDistance, " +
                "SUM(running_Time) as totalRunningTime, " +
                "SUM(calorie) as totalCalorie, " +
                "SUM(pace) as totalPace " +
            "FROM record r " +
            "WHERE r.user_Id = ?1", nativeQuery = true)
    public TotalRecordInterface findTotalRecordByUserId(Long userId);
}
