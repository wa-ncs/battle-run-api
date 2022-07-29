package com.wancs.howmuchspend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

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
        response.put("data",memberService.findAll());
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
        response.put("data",memberService.find(id));
        return response;
    }

    @PostMapping("")
    public Map<String, Object> save(@RequestBody MemberDTO member) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", memberService.save(member));
        return response;
    }
    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable("id") Long id,@RequestBody MemberDTO member) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", memberService.update(id, member));
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> update(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        memberService.delete(id);
        response.put("data", "success");
        return response;
    }
}
