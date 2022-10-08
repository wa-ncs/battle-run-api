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
    private CommentResponseDto commentResponseDto;


    @Builder
    public RecordResponseDto(Record entity, CommentResponseDto commentResponseDto){
        this.id = entity.getId();
        this.distance = entity.getDistance();
        this.runningTime = entity.getRunningTime();
        this.face = entity.getFace();
        this.calorie = entity.getCalorie();
        this.imageUrl = entity.getImageUrl();
        this.commentResponseDto = commentResponseDto;
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
