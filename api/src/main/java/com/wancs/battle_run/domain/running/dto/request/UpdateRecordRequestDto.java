package com.wancs.battle_run.domain.running.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateRecordRequestDto {

    @NotNull
    @NotBlank
    private Long id;

    @NotNull
    @NotBlank
    private Float distance;

    @NotNull
    @NotBlank
    private Long runningTime;

    @NotNull
    @NotBlank
    private Float face;

    @NotNull
    @NotBlank
    private Integer calorie;

    @NotNull
    @NotBlank
    private String imageUrl;

}
