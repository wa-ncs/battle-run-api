package com.wancs.battle_run.domain.member.api;

import com.wancs.battle_run.domain.auth.dto.CreateTokenDto;
import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.auth.service.AuthService;
import com.wancs.battle_run.domain.member.application.MemberService;
import com.wancs.battle_run.domain.member.dto.response.MemberJoinResponseDto;
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
    private final AuthService authService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<MemberJoinResponseDto>> save(
            @Valid @RequestBody CreateMemberRequestDto createMemberRequestDto) {

        Long savedId = memberService.save(createMemberRequestDto);
        Member findMember = memberService.findById(savedId);

        // 토큰 생성
        TokenDto tokenDto = authService.generateToken(findMember.getId());

        // 토큰 정보에 멤버 정보 가공
        CreateTokenDto createTokenDto = CreateTokenDto.builder()
                .member(findMember)
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        // 토큰 등록
        Long authId = authService.save(createTokenDto.toEntity());
        // 토큰 조회
        Auth auth = authService.findById(authId);

        MemberJoinResponseDto memberJoinResponseDto = MemberJoinResponseDto.fromEntity(findMember, auth);
        return ResponseEntity
                .created(URI.create("/members/"+savedId))
                .body(ResponseDto.<MemberJoinResponseDto>builder()
                        .code(StatusEnum.CREATED)
                        .message("success")
                        .data(memberJoinResponseDto)
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
