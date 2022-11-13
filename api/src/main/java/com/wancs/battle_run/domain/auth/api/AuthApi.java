package com.wancs.battle_run.domain.auth.api;

import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.service.AuthService;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wancs.battle_run.domain.auth.dto.LoginDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {

    @Autowired
    private AuthService authService;

    @Operation(summary = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<TokenDto>> login(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity
                .ok()
                .body(ResponseDto.<TokenDto>builder()
                        .code(StatusEnum.OK)
                        .message("success")
                        .data(authService.doLogin(loginDto))
                        .build());
    }

    @Operation(summary = "토큰 갱신")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto<TokenDto>> refresh(@RequestBody @Valid TokenDto tokenDto) {
        return ResponseEntity
                .ok()
                .body(ResponseDto.<TokenDto>builder()
                        .code(StatusEnum.OK)
                        .message("success")
                        .data(authService.refresh(tokenDto))
                        .build());
    }
}
