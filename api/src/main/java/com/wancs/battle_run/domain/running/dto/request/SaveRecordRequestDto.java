package com.wancs.battle_run.domain.running.dto.request;

import com.wancs.battle_run.domain.running.entity.Record;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveRecordRequestDto {
    private Long userId;
    private Float distance;
    private String runningTime;
    private String face;
    private Integer calorie;
    private String imageUrl;

    @Builder
    public SaveRecordRequestDto(Long userId, Float distance, String runningTime, String face, Integer calorie, String imageUrl){
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
