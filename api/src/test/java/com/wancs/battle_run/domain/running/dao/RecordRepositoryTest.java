package com.wancs.battle_run.domain.running.dao;

import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import com.wancs.battle_run.domain.running.entity.Record;
import com.wancs.battle_run.domain.running.service.RecordService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active:local")
public class RecordRepositoryTest {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    RecordService recordService;

    @After("")
    public void cleanup(){
        recordRepository.deleteAll();
    }

    @Test
    public void repository_등록확인(){
        //given
        Float distance = 10.5F;
        Long runningTime = 23000L;
        Long userId = 123L;

        recordRepository.save(Record.builder()
                .distance(distance)
                .runningTime(runningTime)
                .userId(userId)
                .build());

        //when
        List<Record> recordList = recordRepository.findAll();

        //then
        Record record = recordList.get(0);
        assertThat(record.getDistance()).isEqualTo(distance);
        assertThat(record.getRunningTime()).isEqualTo(runningTime);
    }

    @Test
    public void service_수정확인(){

        Long recordsId = recordRepository.save(Record.builder()
                .distance(10.5F)
                .runningTime(23000L)
                .calorie(10)
                .imageUrl("22323")
                .build()).getId();

        UpdateRecordRequestDto requestDto = new UpdateRecordRequestDto();
        requestDto.setDistance(10.5F);
        requestDto.setRunningTime(56000L);
        requestDto.setCalorie(10);
        requestDto.setImageUrl("22323");

        recordService.update(recordsId, requestDto);

        //when
        Record records = recordService.findByRecord(recordsId);
        assertThat("20".equals(records.getRunningTime()));
    }
}
