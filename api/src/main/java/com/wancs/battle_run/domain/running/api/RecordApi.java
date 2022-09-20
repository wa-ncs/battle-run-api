package com.wancs.battle_run.domain.running.api;

import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import com.wancs.battle_run.domain.running.dto.response.TotalRecordResponseDto;
import com.wancs.battle_run.domain.running.entity.Record;
import com.wancs.battle_run.domain.running.service.RecordService;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.domain.running.dto.response.RecordResponseDto;
import com.wancs.battle_run.domain.running.dto.request.SaveRecordRequestDto;

import com.wancs.battle_run.global.common.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequestMapping("/api/records")
public class RecordApi {
    @Autowired
    private RecordService recordService;

    @Operation(summary = "러닝 기록 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "정상적으로 저장"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PostMapping(value = "")
    public ResponseEntity<ResponseDto<Record>> save(@Valid SaveRecordRequestDto saveRecordRequestDto) {
        Long recordId = recordService.save(saveRecordRequestDto);
        Record record = recordService.findByRecord(recordId);

        ResponseDto<Record> dto = ResponseDto.<Record>builder()
                .data(record)
                .code(StatusEnum.CREATED)
                .build();

        return ResponseEntity
                .created(URI.create("/records/"+recordId))
                .body(dto);
    }

    @Operation(summary = "수기 러닝 기록 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PutMapping(value = "/{recordId}")
    public ResponseEntity<ResponseDto<Record>> update(@PathVariable(required = true) Long recordId, @Valid UpdateRecordRequestDto updateRecordRequestDto) {
        Long id = recordService.update(recordId, updateRecordRequestDto);
        Record record = recordService.findByRecord(id);

        ResponseDto<Record> dto = ResponseDto.<Record>builder()
                .data(record)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "수기 러닝 기록 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "정상적으로 삭제"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @DeleteMapping(value = "/{recordId}")
    public ResponseEntity<RecordResponseDto> deleteById(@PathVariable(required = true) Long recordId) {
        recordService.deleteById(recordId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @Operation(summary = "개인 러닝 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<ResponseDto<TotalRecordResponseDto>> findRecordByUserId(@PathVariable(required = true) Long userId) {
        Float totalDistance = 0F;
        Long totalRunningTime = 0L;
        Integer totalCalorie = 0;
        List<RecordResponseDto> recordList = new ArrayList<>();

        List<Record> records = recordService.findRecordsByUserId(userId);

        for(Record record : records){
            RecordResponseDto recordResponse = RecordResponseDto.builder()
                    .entity(record)
                    .build();
            recordList.add(recordResponse);

            totalDistance += record.getDistance();
            totalRunningTime += record.getRunningTime();
            totalCalorie += record.getCalorie();
        }

        //온전한 형태의 시간이 아닌 ms 형태의 시간이라 프론트에서 totalFace 계산해주기
        //Float totalFace = (totalDistance / totalRunningTime * 100) / 100;

        TotalRecordResponseDto responseDto = TotalRecordResponseDto.builder()
                .totalDistance(totalDistance)
                .totalRunningTime(totalRunningTime)
                .totalCalorie(totalCalorie)
                .recordList(recordList)
                .build();

        ResponseDto<TotalRecordResponseDto> dto = ResponseDto.<TotalRecordResponseDto>builder()
                .code(StatusEnum.OK)
                .message("success")
                .data(responseDto)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }
    @Operation(summary = "개인 러닝 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @GetMapping(value = "/{recordId}")
    public ResponseEntity<ResponseDto<Record>> findById(@PathVariable(required = true) Long recordId) {
        Record record = recordService.findByRecord(recordId);

        ResponseDto<Record> dto = ResponseDto.<Record>builder()
                .data(record)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }
}
