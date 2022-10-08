package com.wancs.battle_run.domain.game.api;

import com.wancs.battle_run.domain.game.dto.request.SaveGameRequestDto;
import com.wancs.battle_run.domain.game.dto.request.UpdateGameMemberRequestDto;
import com.wancs.battle_run.domain.game.dto.request.UpdateGameRequestDto;
import com.wancs.battle_run.domain.game.dto.response.GameMemberResponseDto;
import com.wancs.battle_run.domain.game.dto.response.GameResponseDto;
import com.wancs.battle_run.domain.game.dto.response.GameRoomResponseDto;
import com.wancs.battle_run.domain.game.entity.Game;
import com.wancs.battle_run.domain.game.entity.GameMember;
import com.wancs.battle_run.domain.game.service.GameMemberService;
import com.wancs.battle_run.domain.game.service.GameService;
import com.wancs.battle_run.global.common.ResponseDto;
import com.wancs.battle_run.global.common.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestControllerAdvice
@RequestMapping("/api/game")
public class GameApi {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMemberService gameMemberService;

    @Operation(summary = "배틀런 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "정상적으로 저장"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PostMapping(value = "")
    public ResponseEntity<ResponseDto> save(@Valid SaveGameRequestDto requestDto) {
        Long gameId = gameService.save(requestDto);

        //배틀런 생성 시 방장은 필수로 들어가기 때문에 NotNull
        gameMemberService.gameMemberInsert(gameId, requestDto.getGameMembers());

        ResponseDto dto = ResponseDto.builder()
                .code(StatusEnum.CREATED)
                .build();

        return ResponseEntity
                .created(URI.create("/api/game/"+gameId))
                .body(dto);
    }

    @Operation(summary = "배틀런 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @GetMapping(value = "")
    public ResponseEntity<ResponseDto<GameRoomResponseDto>> findGameById(@RequestParam Long gameId) {
        Game game = gameService.findGameById(gameId);
        List<GameMember> gameMembers = gameMemberService.findByGameId(gameId);

        GameRoomResponseDto data = GameRoomResponseDto.builder()
                .game(game)
                .gameMembers(gameMembers)
                .build();

        ResponseDto<GameRoomResponseDto> dto = ResponseDto.<GameRoomResponseDto>builder()
                .data(data)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "배틀런 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PutMapping(value = "")
    public ResponseEntity<ResponseDto<GameResponseDto>> update(@Valid UpdateGameRequestDto requestDto) {
        Long id = gameService.update(requestDto);
        Game game = gameService.findGameById(id);

        GameResponseDto data = GameResponseDto.builder()
                .game(game)
                .build();

        ResponseDto<GameResponseDto> dto = ResponseDto.<GameResponseDto>builder()
                .data(data)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "배틀런 참여자 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "Required"),
    })
    @PutMapping(value = "/{gameId}/gameMember")
    public ResponseEntity<ResponseDto<GameMemberResponseDto>> gameMemberUpdate(@Valid UpdateGameMemberRequestDto requestDto) {

        if(requestDto.getInsertList() != null && requestDto.getInsertList().size() > 0){
            gameMemberService.gameMemberInsert(requestDto.getGameId(), requestDto.getInsertList());
        }

        if(requestDto.getDeleteList() != null && requestDto.getDeleteList().size() > 0){
            gameMemberService.gameMemberDelete(requestDto.getGameId(), requestDto.getDeleteList());
        }

        List<GameMember> gameMembers = gameMemberService.findByGameId(requestDto.getGameId());
        GameMemberResponseDto data = GameMemberResponseDto.builder()
                .gameMembers(gameMembers)
                .build();

        ResponseDto<GameMemberResponseDto> dto = ResponseDto.<GameMemberResponseDto>builder()
                .data(data)
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }
}
