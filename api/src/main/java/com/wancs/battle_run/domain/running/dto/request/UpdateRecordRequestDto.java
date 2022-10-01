package com.wancs.battle_run.domain.running.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRecordRequestDto {
    private Long id;

    private Float distance;

    private Long runningTime;

    private String face;

    private Integer calorie;

    private String imageUrl;

    private String todayMemo;

}
