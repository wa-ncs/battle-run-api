package com.wancs.battle_run.domain.game.dto.request;

import com.wancs.battle_run.domain.game.entity.Game;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class SaveGameRequestDto {

    @NotBlank
    private String name;

    @NotNull
    private Float distance;

    //테스트용 현재시간 get -> new Timestamp(System.currentTimeMillis())
    @NotNull
    private Timestamp startTime;

    @NotNull
    private Timestamp endTime;

    private String reward;

    public Game toEntity(){
        return Game.builder()
                .name(this.name)
                .distance(this.distance)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .reward(this.reward)
                .build();
    }
}
