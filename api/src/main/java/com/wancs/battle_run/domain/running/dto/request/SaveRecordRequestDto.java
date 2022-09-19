package com.wancs.battle_run.domain.running.dto.request;

import com.wancs.battle_run.domain.running.entity.Record;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SaveRecordRequestDto {
    private Long userId;
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

    @Builder
    public SaveRecordRequestDto(Long userId, Float distance, Long runningTime, Float face, Integer calorie, String imageUrl){
        this.userId = userId;
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
    }

    public Record toEntity(){
        return Record.builder()
                .userId(this.userId)
                .distance(this.distance)
                .runningTime(this.runningTime)
                .face(this.face)
                .calorie(this.calorie)
                .imageUrl(this.imageUrl)
                .build();
    }
}
