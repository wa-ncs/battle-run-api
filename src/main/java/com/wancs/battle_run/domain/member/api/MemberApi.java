package com.wancs.battle_run.domain.member.api;

import com.wancs.battle_run.domain.member.application.MemberService;
import com.wancs.battle_run.domain.member.dto.response.MemberResponse;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequestDto;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
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
    public ResponseEntity<ResponseDto<MemberResponse>> save(@Valid @RequestBody CreateMemberRequestDto createMemberRequestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        Long savedId = memberService.save(createMemberRequestDto);
        Member findMember = memberService.findById(savedId);

        ResponseDto<MemberResponse> dto = ResponseDto.<MemberResponse>builder()
                .code(StatusEnum.CREATED)
                .message("success")
                .data(MemberResponse.fromEntity(findMember))
                .build();

        return ResponseEntity
                .created(URI.create("/members/"+savedId))
                .headers(headers)
                .body(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<MemberResponse>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        Long userId = memberService.update(id, requestDto);
        Member findMember = memberService.findById(userId);

        ResponseDto<MemberResponse> dto = ResponseDto.<MemberResponse>builder()
                .code(StatusEnum.OK)
                .message("success")
                .data(MemberResponse.fromEntity(findMember)).build();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
}
