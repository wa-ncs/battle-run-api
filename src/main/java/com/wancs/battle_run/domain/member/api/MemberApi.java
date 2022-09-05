package com.wancs.battle_run.domain.member.api;

import com.wancs.battle_run.domain.member.application.MemberService;
import com.wancs.battle_run.domain.member.domain.Member;
import com.wancs.battle_run.domain.member.dto.CreateMemberRequest;
import com.wancs.battle_run.domain.member.dto.UpdateMemberRequest;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApi {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<Object>> save(@RequestBody @Valid CreateMemberRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Long id = memberService.save(request);

        ResponseDto<Object> dto = ResponseDto.builder()
                .code(StatusEnum.OK)
                .message("success")
                .data(id).build();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
    @PutMapping("")
    public ResponseEntity<ResponseDto<Object>> update(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Long userId = memberService.update(id, request);
        Member findMember = memberService.findOne(userId);
        ResponseDto<Object> dto = ResponseDto.builder()
                .code(StatusEnum.OK)
                .message("success")
                .data(findMember).build();

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(dto);
    }
    @Operation(summary = "전체 멤버 조회", description = "hello api example")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("")
    public Map<String, Object> findAll() {
        Map<String, Object> response = new HashMap<>();
        List<Member> findMembers = memberService.findMembers();
//      List<MembmerDTO> collect = findMembers.stream()
//                        .map(m->new MemberDTO(m.getName()));
        response.put("data","hello");
        return response;
    }
    @Operation(summary = "멤버 조회", description = "hello api example")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{id}")
    public Map<String, Object> find(
            @Parameter(description = "id", required = true, example = "1")
            @PathVariable("id") Long id
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("data","hello");
        return response;
    }
}
