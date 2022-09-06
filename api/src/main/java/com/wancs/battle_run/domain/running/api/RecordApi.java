package com.wancs.battle_run.domain.running.api;

import com.wancs.battle_run.domain.running.Entity.Record;
import com.wancs.battle_run.domain.running.dto.response.AllRecordResponseDto;
import com.wancs.battle_run.domain.running.service.RecordService;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.domain.running.dto.response.RecordResponseDto;
import com.wancs.battle_run.domain.running.dto.request.SaveRecordRequestDto;
import com.wancs.battle_run.global.common.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@RequestMapping("${api-prefix}/records")
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
    public ResponseEntity<ResponseDto<Record>> save(SaveRecordRequestDto saveRecordRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        Long recordId = recordService.save(saveRecordRequestDto);
        Record record = recordService.findByRecord(recordId);

        ResponseDto<Record> dto = ResponseDto.<Record>builder()
                .data(record)
                .code(StatusEnum.CREATED)
                .build();

        return ResponseEntity
                .created(URI.create("")) //.created(URI.create("/run/detail/" + id))
                .headers(headers)
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
    public ResponseEntity<ResponseDto<RecordResponseDto>> update(@PathVariable Long recordId, SaveRecordRequestDto saveRecordRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        ResponseDto<RecordResponseDto> dto = new ResponseDto<RecordResponseDto>();

        return ResponseEntity
                .created(URI.create("")) //.created(URI.create("/run/detail/" + id))
                .headers(headers)
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
    public ResponseEntity<RecordResponseDto> deleteById(@PathVariable Long recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        recordService.deleteById(recordId);

        return ResponseEntity
                .noContent()
                .headers(headers)
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
    @GetMapping(value = "")
    public ResponseEntity<ResponseDto<Object>> findRecordByUserId(@PathVariable Long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        List<Record> records = recordService.findRecordsByUserId(userId);

        AllRecordResponseDto RecordDTO = new AllRecordResponseDto();

        ResponseDto<Object> dto = ResponseDto.builder()
                .code(StatusEnum.OK)
                .message("success")
                .data(RecordDTO).build();

        return ResponseEntity
                .ok()
                .headers(headers)
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
    public ResponseEntity<ResponseDto<Record>> findById(@PathVariable Long recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        Record record = recordService.findByRecord(recordId);

        ResponseDto<Record> dto = ResponseDto.<Record>builder()
                .data(record)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
}
