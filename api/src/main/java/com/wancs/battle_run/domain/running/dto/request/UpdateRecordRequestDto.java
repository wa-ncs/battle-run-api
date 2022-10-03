package com.wancs.battle_run.domain.running.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateRecordRequestDto {
    @NotNull
    private Long id;

    private Float distance;

    private Long runningTime;

    private String face;

    private Integer calorie;

    private String imageUrl;

}
