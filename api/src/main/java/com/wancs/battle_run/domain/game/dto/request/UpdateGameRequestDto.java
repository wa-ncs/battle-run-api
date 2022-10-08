package com.wancs.battle_run.domain.game.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UpdateGameRequestDto {
    @NotNull
    private Long gameId;

    private String name;

    private Float distance;

    private Timestamp startTime;

    private Timestamp endTime;

    private String role;
}
