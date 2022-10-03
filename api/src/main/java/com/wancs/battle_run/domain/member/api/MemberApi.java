package com.wancs.battle_run.domain.member.api;

import com.wancs.battle_run.domain.member.application.MemberService;
import com.wancs.battle_run.domain.member.dto.response.MemberResponseDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequestDto;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApi {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<MemberResponseDto>> save(
            @Valid @RequestBody CreateMemberRequestDto createMemberRequestDto) {

        Long savedId = memberService.save(createMemberRequestDto);
        Member findMember = memberService.findById(savedId);

        MemberResponseDto memberResponseDto = MemberResponseDto.fromEntity(findMember);
        return ResponseEntity
                .created(URI.create("/members/"+savedId))
                .body(ResponseDto.<MemberResponseDto>builder()
                        .code(StatusEnum.CREATED)
                        .message("success")
                        .data(memberResponseDto)
                        .build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<MemberResponseDto>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequestDto requestDto) {

        Long userId = memberService.update(id, requestDto);
        Member findMember = memberService.findById(userId);

        MemberResponseDto memberResponseDto = MemberResponseDto.fromEntity(findMember);

        return ResponseEntity
                .ok()
                .body(ResponseDto.<MemberResponseDto>builder()
                        .code(StatusEnum.OK)
                        .message("success")
                        .data(memberResponseDto)
                        .build());
    }
}
