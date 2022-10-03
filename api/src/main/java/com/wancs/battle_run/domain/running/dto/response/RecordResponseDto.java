package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.entity.Record;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RecordResponseDto {
    private Long id;
    private Float distance;
    private Long runningTime;
    private String face;
    private Integer calorie;
    private String imageUrl;


    @Builder
    public RecordResponseDto(Long id, Float distance, Long runningTime, String face,
                              Integer calorie, String imageUrl){
        this.id = id;
        this.distance = distance;
        this.runningTime = runningTime;
        this.face = face;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
    }


    public static RecordResponseDto fromEntity(Record entity){
        return RecordResponseDto.builder()
                .id(entity.getId())
                .distance(entity.getDistance())
                .runningTime(entity.getRunningTime())
                .face(entity.getFace())
                .calorie(entity.getCalorie())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
