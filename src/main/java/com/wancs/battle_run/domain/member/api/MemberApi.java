package com.wancs.battle_run.domain.member.api;

import com.wancs.battle_run.domain.member.application.MemberService;
import com.wancs.battle_run.domain.member.domain.Member;
import com.wancs.battle_run.domain.member.dto.CreateMemberRequest;
import com.wancs.battle_run.domain.member.dto.UpdateMemberRequest;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.nio.charset.Charset;
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApi {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<Object>> save(@Valid @RequestBody CreateMemberRequest createMemberRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        Long savedId = memberService.save(createMemberRequest);
        Member findMember = memberService.findById(savedId);

        ResponseDto<Object> dto = ResponseDto.builder()
                .code(StatusEnum.OK)
                .message("success")
                .data(findMember).build();

        return ResponseEntity
                .created(URI.create("/members/"+savedId))
                .headers(headers)
                .body(dto);
    }
    @PutMapping("")
    public ResponseEntity<ResponseDto<Object>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
/*        Long userId = memberService.update(id, request);
        Member findMember = memberService.findOne(userId);*/
        ResponseDto<Object> dto = ResponseDto.builder()
                .code(StatusEnum.OK)
                .message("success")
                .data("hello").build();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
}
