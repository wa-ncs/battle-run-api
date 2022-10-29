package com.wancs.battle_run.domain.game.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UpdateGameRequestDto {
    private String name;

    private Float distance;

    private Timestamp startTime;

    private Timestamp endTime;

    private String reward;
}
