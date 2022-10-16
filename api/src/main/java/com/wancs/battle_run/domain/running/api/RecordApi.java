package com.wancs.battle_run.domain.running.api;

import com.wancs.battle_run.domain.running.dto.RecordList;
import com.wancs.battle_run.domain.running.dto.TotalRecordInterface;
import com.wancs.battle_run.domain.running.dto.request.SaveCommentRequestDto;
import com.wancs.battle_run.domain.running.dto.request.UpdateCommentRequestDto;
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
import java.util.Optional;


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
    public ResponseEntity<ResponseDto<RecordResponseDto>> save(@RequestBody @Valid SaveRecordRequestDto saveRecordRequestDto) {
        Long recordId = recordService.save(saveRecordRequestDto);
        Optional<Record> record = recordService.findById(recordId);

        RecordResponseDto data = RecordResponseDto.builder()
                .entity(record.get())
                .build();

        ResponseDto<RecordResponseDto> dto = ResponseDto.<RecordResponseDto>builder()
                .data(data)
                .message("success")
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
    public ResponseEntity<ResponseDto<RecordResponseDto>> update(@PathVariable Long recordId,
                                                                 @RequestBody @Valid UpdateRecordRequestDto updateRecordRequestDto) {
        Boolean isExistRecord = recordService.isExistRecord(recordId);
        if(!isExistRecord){
            return ResponseEntity
                    .badRequest()
                    .body(recordService.badRequestErrorDto("존재 하지않는 운동 기록입니다."));
        }

        Long id = recordService.update(recordId, updateRecordRequestDto);
        Optional<Record> record = recordService.findById(id);

        RecordResponseDto data = RecordResponseDto.builder()
                .entity(record.get())
                .build();

        ResponseDto<RecordResponseDto> dto = ResponseDto.<RecordResponseDto>builder()
                .data(data)
                .message("success")
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
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long recordId) {
        Boolean isExistRecord = recordService.isExistRecord(recordId);
        if(!isExistRecord){
            return ResponseEntity
                    .badRequest()
                    .body(recordService.badRequestErrorDto("존재 하지않는 운동 기록입니다."));
        }

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
    public ResponseEntity<ResponseDto<RecordResponseDto>> findById(@PathVariable Long recordId) {
        Boolean isExistRecord = recordService.isExistRecord(recordId);
        if(!isExistRecord){
            return ResponseEntity
                    .badRequest()
                    .body(recordService.badRequestErrorDto("존재 하지않는 운동 기록입니다."));
        }

        Optional<Record> record = recordService.findById(recordId);
        Comment comment = commentService.findCommentByRecordId(recordId);

        RecordResponseDto data = RecordResponseDto.builder()
                .entity(record.get())
                .comment(comment)
                .build();

        ResponseDto<RecordResponseDto> dto = ResponseDto.<RecordResponseDto>builder()
                .data(data)
                .message("success")
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "오늘도수고한나에게한마디 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PostMapping(value = "/{recordId}/comment")
    public ResponseEntity<ResponseDto<CommentResponseDto>> saveComment(@PathVariable Long recordId,
                                                                       @RequestBody SaveCommentRequestDto requestDto) {
        Boolean isExistRecord = recordService.isExistRecord(recordId);
        if(!isExistRecord){
            return ResponseEntity
                    .badRequest()
                    .body(recordService.badRequestErrorDto("존재 하지않는 운동 기록입니다."));
        }

        Long commentId = commentService.save(requestDto, recordId);
        Optional<Comment> comment = commentService.findById(commentId);

        CommentResponseDto data = CommentResponseDto.builder()
                .comment(comment.get())
                .build();

        ResponseDto<CommentResponseDto> dto = ResponseDto.<CommentResponseDto>builder()
                .data(data)
                .message("success")
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .created(URI.create("/api/records/"+recordId))
                .body(dto);
    }

    @Operation(summary = "오늘도수고한나에게한마디 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PutMapping(value = "/{recordId}/comment")
    public ResponseEntity<ResponseDto<CommentResponseDto>> updateComment(@PathVariable Long recordId,
                                                                         @RequestBody @Valid UpdateCommentRequestDto requestDto) {
        Boolean isExistRecord = recordService.isExistRecord(recordId);
        if(!isExistRecord){
            return ResponseEntity
                    .badRequest()
                    .body(recordService.badRequestErrorDto("존재 하지않는 운동 기록입니다."));
        }

        Boolean isExistComment = commentService.isExistComment(requestDto.getCommentId());
        if(!isExistComment){
            return ResponseEntity
                    .badRequest()
                    .body(recordService.badRequestErrorDto("존재 하지않는 comment 입니다."));
        }

        Long commentId = commentService.update(requestDto);
        Optional<Comment> comment = commentService.findById(commentId);

        CommentResponseDto data = CommentResponseDto.builder()
                .comment(comment.get())
                .build();

        ResponseDto<CommentResponseDto> dto = ResponseDto.<CommentResponseDto>builder()
                .data(data)
                .message("success")
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }
}
