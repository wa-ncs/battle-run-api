package com.wancs.battle_run.domain.running.api;

import com.wancs.battle_run.domain.running.dto.response.AllRecordResponseDto;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.domain.running.dto.response.RecordResponseDto;
import com.wancs.battle_run.domain.running.dto.request.SaveRecordRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;

@RestControllerAdvice
@RequestMapping("${api-prefix}/running/records")
public class RecordApi {

    @Operation(summary = "러닝 기록 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "정상적으로 저장"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PostMapping(value = "")
    public ResponseEntity<ResponseDto<RecordResponseDto>> save(SaveRecordRequestDto saveRecordRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        ResponseDto<RecordResponseDto> dto = new ResponseDto<RecordResponseDto>();

        return ResponseEntity
                .created(URI.create("")) //.created(URI.create("/run/detail/" + id))
                .headers(headers)
                .body(dto);
    }

    @Operation(summary = "러닝 기록 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PutMapping(value = "/{recordId}")
    public ResponseEntity<ResponseDto<RecordResponseDto>> update(@PathVariable Integer recordId, SaveRecordRequestDto saveRecordRequestDto) {
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
    public ResponseEntity<RecordResponseDto> delete(@PathVariable Integer recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        return ResponseEntity
                .noContent()
                .headers(headers)
                .build();
    }

    @Operation(summary = "러닝 기록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @GetMapping(value = "")
    public ResponseEntity<ResponseDto<AllRecordResponseDto>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        ResponseDto<AllRecordResponseDto> dto = new ResponseDto<AllRecordResponseDto>();
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
    @Operation(summary = "상세 러닝 기록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @GetMapping(value = "/{recordId}")
    public ResponseEntity<ResponseDto<RecordResponseDto>> findById(@PathVariable String recordId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        ResponseDto<RecordResponseDto> dto = new ResponseDto<RecordResponseDto>();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
}
