package com.wancs.battle_run.domain.running.api;

import com.wancs.battle_run.domain.running.entity.Record;
import com.wancs.battle_run.domain.running.dto.request.SaveRecordRequestDto;
import com.wancs.battle_run.domain.running.dao.RecordRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active:local")
public class RecordApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RecordRepository recordRepository;

    @After("")
    public void tearDown() throws Exception {
        recordRepository.deleteAll();
    }

    @Test
    public void api로_러닝기록등록() throws Exception {
        //given
        Float distance = 10.5F;
        Long runningTime = 23000L;
        SaveRecordRequestDto requestDto = SaveRecordRequestDto.builder()
                .distance(distance)
                .runningTime(runningTime)
                .userId(123L)
                .build();

        String url = "http://localhost:" + port + "/api/records";

        //when
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, requestDto, Object.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        List<Record> all = recordRepository.findAll();
        assertThat(all.get(0).getDistance()).isEqualTo(distance);
        assertThat(all.get(0).getRunningTime()).isEqualTo(runningTime);
    }

    @Test
    public void api로_러닝기록삭제() throws Exception {
        //given
        Float distance = 10.5F;
        Long runningTime = 23000L;
        Record savedRecord = recordRepository.save(Record.builder()
                .distance(distance)
                .runningTime(runningTime)
                .userId(123L)
                .build());
        Long deleteId = savedRecord.getId();

        String url = "http://localhost:"+port+"/api/records/"+deleteId;
        HttpEntity<Long> requestEntity = new HttpEntity<>(deleteId);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        List<Record> all = recordRepository.findAll();
        assertThat(all.isEmpty());
    }
}
