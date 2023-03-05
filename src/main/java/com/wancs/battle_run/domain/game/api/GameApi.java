package com.wancs.battle_run.domain.game.api;

import com.wancs.battle_run.domain.game.dto.GameList;
import com.wancs.battle_run.domain.game.dto.GameMemberList;
import com.wancs.battle_run.domain.game.dto.request.SaveGameMemberRequestDto;
import com.wancs.battle_run.domain.game.dto.request.SaveGameRequestDto;
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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "422", description = "REQUIRED"),
    })
    @PostMapping(value = "")
    public ResponseEntity<ResponseDto<GameRoomResponseDto>> save(@RequestBody @Valid SaveGameRequestDto requestDto) {
        Long gameId = gameService.save(requestDto);
        //배틀런 생성 시 방장은 필수로 들어가기 때문에 NotNull
        // FIXME gameMemberService.gameMemberInsert(gameId, /*방장의 memberId 넣어야함*/);

        Game game = gameService.findById(gameId);
        GameMemberList gameMembers = gameMemberService.findByGameId(gameId);

        GameRoomResponseDto data = GameRoomResponseDto.builder()
                .game(game)
                .gameMembers(gameMembers.toGameMemberListResponseDto())
                .build();

        ResponseDto<GameRoomResponseDto> dto = ResponseDto.<GameRoomResponseDto>builder()
                .data(data)
                .code(StatusEnum.CREATED)
                .message("success")
                .build();

        return ResponseEntity
                .created(URI.create("/api/game/"+gameId))
                .body(dto);
    }

    @Operation(summary = "배틀런 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "REQUIRED"),
    })
    @GetMapping(value = "")
    public ResponseEntity<ResponseDto<List<GameResponseDto>>> findByMemberId(@RequestParam Long memberId) {
        GameList games = gameService.findByMemberId(memberId);

        ResponseDto<List<GameResponseDto>> dto = ResponseDto.<List<GameResponseDto>>builder()
                .data(games.toGameListResponseDto())
                .message("success")
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "배틀런 상세 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "REQUIRED"),
    })
    @GetMapping(value = "/{gameId}")
    public ResponseEntity<ResponseDto<GameRoomResponseDto>> findById(@PathVariable Long gameId) {
        Game game = gameService.findById(gameId);
        GameMemberList gameMembers = gameMemberService.findByGameId(gameId);

        GameRoomResponseDto data = GameRoomResponseDto.builder()
                .game(game)
                .gameMembers(gameMembers.toGameMemberListResponseDto())
                .build();

        ResponseDto<GameRoomResponseDto> dto = ResponseDto.<GameRoomResponseDto>builder()
                .data(data)
                .message("success")
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
            @ApiResponse(responseCode = "422", description = "REQUIRED"),
    })
    @PutMapping(value = "/{gameId}")
    public ResponseEntity<ResponseDto<GameResponseDto>> update(@RequestBody @Valid UpdateGameRequestDto requestDto,
                                                               @PathVariable Long gameId) {
        Long id = gameService.update(requestDto, gameId);
        Game game = gameService.findById(id);

        GameResponseDto data = GameResponseDto.builder()
                .entity(game)
                .build();

        ResponseDto<GameResponseDto> dto = ResponseDto.<GameResponseDto>builder()
                .data(data)
                .message("success")
                .code(StatusEnum.OK)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "배틀런 참여자 추가")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "REQUIRED"),
    })
    @PostMapping(value = "/{gameId}/member")
    public ResponseEntity<ResponseDto<GameMemberResponseDto>> gameMemberSave(@RequestBody SaveGameMemberRequestDto requestDto,
                                                                             @PathVariable Long gameId) {
        //게임방 존재유무 유효성 검사
        gameService.findById(gameId);
        Long gameMemberId = gameMemberService.gameMemberInsert(gameId, requestDto.getMemberId());

        GameMember gameMember = gameMemberService.findById(gameMemberId);
        GameMemberResponseDto data = GameMemberResponseDto.builder()
                .entity(gameMember)
                .build();

        ResponseDto<GameMemberResponseDto> dto = ResponseDto.<GameMemberResponseDto>builder()
                .data(data)
                .message("success")
                .code(StatusEnum.CREATED)
                .build();

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @Operation(summary = "배틀런 참여자 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "409", description = "CONFLICT"),
            @ApiResponse(responseCode = "422", description = "REQUIRED"),
    })
    @DeleteMapping(value = "/{gameId}/member/{gameMemberId}")
    public ResponseEntity<ResponseDto<GameMemberResponseDto>> gameMemberDelete(@PathVariable Long gameMemberId,
                                                                               @PathVariable Long gameId) {
        //게임방 존재유무 유효성 검사
        gameService.findById(gameId);
        gameMemberService.gameMemberDelete(gameMemberId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
