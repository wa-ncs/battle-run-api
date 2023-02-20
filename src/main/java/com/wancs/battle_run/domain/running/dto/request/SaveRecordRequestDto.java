package com.wancs.battle_run.domain.running.dto.request;

import com.wancs.battle_run.domain.running.entity.Record;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class SaveRecordRequestDto {
    private Long userId;

    @NotNull
    private Float distance;

    @NotNull
    private Long runningTime;

    @NotBlank
    private String imageUrl;

    private Integer calorie;

    private Integer pace;

    @Builder
    public SaveRecordRequestDto(Long userId, Float distance, Long runningTime, Integer pace, Integer calorie, String imageUrl){
        this.userId = userId;
        this.distance = distance;
        this.runningTime = runningTime;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
        this.pace = pace;
    }

    public Record toEntity(){
        return Record.builder()
                .userId(this.userId)
                .distance(this.distance)
                .runningTime(this.runningTime)
                .calorie(this.calorie)
                .imageUrl(this.imageUrl)
                .pace(this.pace)
                .build();
    }
}
