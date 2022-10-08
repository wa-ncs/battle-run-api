package com.wancs.battle_run.domain.game.dto.request;

import com.wancs.battle_run.domain.game.entity.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

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

    private String role;

    private Long createMemberId;

    private List<Long> gameMembers;

    public Game toEntity(){
        return Game.builder()
                .name(this.name)
                .distance(this.distance)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .role(this.role)
                .createMemberId(this.createMemberId)
                .build();
    }
}
