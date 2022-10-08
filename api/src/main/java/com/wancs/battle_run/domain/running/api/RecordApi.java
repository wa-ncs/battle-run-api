package com.wancs.battle_run.domain.running.api;

import com.wancs.battle_run.domain.running.dto.RecordList;
import com.wancs.battle_run.domain.running.dto.TotalRecordInterface;
import com.wancs.battle_run.domain.running.dto.request.SaveCommentRequestDto;
import com.wancs.battle_run.domain.running.dto.request.UpdateRecordRequestDto;
import com.wancs.battle_run.domain.running.dto.response.CommentResponseDto;
import com.wancs.battle_run.domain.running.dto.response.TotalRecordResponseDto;
import com.wancs.battle_run.domain.running.entity.Comment;
import com.wancs.battle_run.domain.running.entity.Record;
import com.wancs.battle_run.domain.running.service.CommentService;
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


@RestControllerAdvice
@RequestMapping("/api/records")
public class RecordApi {
    @Autowired
    private RecordService recordService;

    @Autowired
    private CommentService commentService;

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

        ResponseDto<Record> dto = ResponseDto.<Record>builder()
                .code(StatusEnum.CREATED)
                .build();

        return ResponseEntity
                .created(URI.create("/api/records/"+recordId))
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
    public ResponseEntity<ResponseDto<RecordResponseDto>> update(@PathVariable(required = true) Long recordId, @Valid UpdateRecordRequestDto updateRecordRequestDto) {
        Long id = recordService.update(recordId, updateRecordRequestDto);
        Record record = recordService.findByRecord(id);

        RecordResponseDto data = RecordResponseDto.builder()
                .entity(record)
                .build();

        ResponseDto<RecordResponseDto> dto = ResponseDto.<RecordResponseDto>builder()
                .data(data)
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
    @GetMapping(value = "")
    public ResponseEntity<ResponseDto<TotalRecordResponseDto>> findRecordsByUserId(@RequestParam Long userId) {
        RecordList records = recordService.findRecordsByUserId(userId);
        TotalRecordInterface totalRecord = recordService.findTotalRecord(userId);

        TotalRecordResponseDto responseDto = TotalRecordResponseDto.builder()
                .totalRecord(totalRecord)
                .records(records.toRecordResponseDto())
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
    public ResponseEntity<ResponseDto<RecordResponseDto>> findById(@PathVariable(required = true) Long recordId) {
        Record record = recordService.findByRecord(recordId);
        Comment comment = commentService.findCommentByRecordId(recordId);

        CommentResponseDto commentDto = CommentResponseDto.builder()
                .commentId(comment.getId())
                .comment(comment.getComment())
                .build();

        RecordResponseDto data = RecordResponseDto.builder()
                .entity(record)
                .commentResponseDto(commentDto)
                .build();

        ResponseDto<RecordResponseDto> dto = ResponseDto.<RecordResponseDto>builder()
                .data(data)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "오늘도수고한나에게 추가 및 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PostMapping(value = "/{recordId}/comment")
    public ResponseEntity<ResponseDto<CommentResponseDto>> saveComment(SaveCommentRequestDto requestDto) {
        Long commentId = commentService.save(requestDto);
        Comment comment = commentService.findbyId(commentId);

        CommentResponseDto data = CommentResponseDto.builder()
                .commentId(comment.getId())
                .comment(comment.getComment())
                .build();

        ResponseDto<CommentResponseDto> dto = ResponseDto.<CommentResponseDto>builder()
                .data(data)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }
}
