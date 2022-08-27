package com.wancs.battle_run.domain.run.api;

import com.wancs.battle_run.domain.run.dto.response.AllRecordResponseDto;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.domain.run.dto.response.RecordResponseDto;
import com.wancs.battle_run.domain.run.dto.request.SaveRecordRequestDto;
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
@RequestMapping("/${ApiPrefix}/records")
public class RecordApi {

    @Operation(summary = "운동기록 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "201", description = "정상적으로 저장"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
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

    @Operation(summary = "운동기록 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "201", description = "정상적으로 수정"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
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

    @Operation(summary = "수기 운동기록 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "정상적으로 삭제"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
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

    //ToDo: 임의로 /total 로 지었는데 해당 path는 논의 후에 정하면 될 듯 합니다.
    @Operation(summary = "누적 운동 기록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "/total")
    public ResponseEntity<ResponseDto<AllRecordResponseDto>> findTotal() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        ResponseDto<AllRecordResponseDto> dto = new ResponseDto<AllRecordResponseDto>();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
    @Operation(summary = "운동 기록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(value = "")
    public ResponseEntity<ResponseDto<RecordResponseDto>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        ResponseDto<RecordResponseDto> dto = new ResponseDto<RecordResponseDto>();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
    @Operation(summary = "상세 운동 기록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
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
