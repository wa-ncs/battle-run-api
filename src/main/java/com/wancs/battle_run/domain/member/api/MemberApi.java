package com.wancs.battle_run.domain.member.api;

import com.wancs.battle_run.domain.member.application.MemberService;
import com.wancs.battle_run.domain.member.dto.request.SaveMemberRequestDto;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.domain.member.dto.response.MemberResponseDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApi {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<MemberResponseDto>> save(
            @Valid @RequestBody SaveMemberRequestDto saveMemberRequestDto) {

        Long savedId = memberService.save(saveMemberRequestDto);

        return ResponseEntity
                .created(URI.create("/members/"+savedId))
                .body(ResponseDto.<MemberResponseDto>builder()
                        .code(StatusEnum.CREATED)
                        .message("success")
                        .data(memberService.findMemberById(savedId))
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<MemberResponseDto>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequestDto requestDto) {

        Long userId = memberService.update(id, requestDto);

        return ResponseEntity
                .ok()
                .body(ResponseDto.<MemberResponseDto>builder()
                        .code(StatusEnum.OK)
                        .message("success")
                        .data(memberService.findMemberById(userId))
                        .build());
    }
}
