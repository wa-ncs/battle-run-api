package com.wancs.battle_run.domain.running.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRecordRequestDto {
    @NotNull
    private Long id;

    private Float distance;

    private Long runningTime;

    private Integer pace;

    private Integer calorie;

    private String imageUrl;

}
