package com.wancs.battle_run.domain.run.api;

import com.wancs.battle_run.domain.run.dto.AllRecordResponseDto;
import com.wancs.battle_run.domain.run.dto.CommonResponseDto;
import com.wancs.battle_run.domain.run.dto.RecordResponseDto;
import com.wancs.battle_run.domain.run.dto.SaveRecordRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/run")
public class RunApi {

    @Operation(summary = "운동기록 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "201", description = "정상적으로 저장"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/save")
    public ResponseEntity<CommonResponseDto<RecordResponseDto>> saveRecord(SaveRecordRequestDto saveRecordRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        CommonResponseDto<RecordResponseDto> dto = new CommonResponseDto<RecordResponseDto>();

        return ResponseEntity
                .created(URI.create("")) //.created(URI.create("/run/detail/" + id))
                .headers(headers)
                .body(dto);
    }

    @Operation(summary = "수기 운동기록 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "201", description = "정상적으로 저장"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(value = "/customSave")
    public ResponseEntity<CommonResponseDto<RecordResponseDto>> customSaveRecord(SaveRecordRequestDto saveRecordRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));


        CommonResponseDto<RecordResponseDto> dto = new CommonResponseDto<RecordResponseDto>();
        return ResponseEntity
                .created(URI.create("")) //.created(URI.create("/run/detail/" + id))
                .headers(headers)
                .body(dto);
    }

    @Operation(summary = "수기 운동기록 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "201", description = "정상적으로 수정"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping(value = "/customUpdate")
    public ResponseEntity<CommonResponseDto<RecordResponseDto>> customUpdateRecord(SaveRecordRequestDto saveRecordRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        CommonResponseDto<RecordResponseDto> dto = new CommonResponseDto<RecordResponseDto>();

        return ResponseEntity
                .created(URI.create("")) //.created(URI.create("/run/detail/" + id))
                .headers(headers)
                .body(dto);
    }

    @Operation(summary = "수기 운동기록 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "정상적으로 삭제"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping(value = "/{recordId}/customDelete")
    public ResponseEntity<RecordResponseDto> customDeleteRecord(@PathVariable String recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return ResponseEntity
                .noContent()
                .headers(headers)
                .build();
    }

    @Operation(summary = "유저별 전체운동 기록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/{userId}/allRecord")
    public ResponseEntity<CommonResponseDto<AllRecordResponseDto>> allRecord(@PathVariable String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        CommonResponseDto<AllRecordResponseDto> dto = new CommonResponseDto<AllRecordResponseDto>();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }

    @Operation(summary = "유저별 단 건 운동 기록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/{recordId}/record")
    public ResponseEntity<CommonResponseDto<RecordResponseDto>> record(@PathVariable String recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        CommonResponseDto<RecordResponseDto> dto = new CommonResponseDto<RecordResponseDto>();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }

}
