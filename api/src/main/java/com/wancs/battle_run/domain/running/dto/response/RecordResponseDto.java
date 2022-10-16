package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.entity.Comment;
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

    private Long commentId;
    private String comment;

    @Builder
    public RecordResponseDto(Record entity, Comment comment){
        this.id = entity != null? entity.getId() : 0;
        this.distance = entity != null? entity.getDistance() : 0;
        this.runningTime = entity != null? entity.getRunningTime() : 0;
        this.face = entity != null? entity.getFace() : "";
        this.calorie = entity != null? entity.getCalorie() : 0;
        this.imageUrl = entity != null? entity.getImageUrl() : "";
        this.commentId = comment != null? comment.getId() : 0;
        this.comment = comment != null? comment.getComment() : "";
    }


    public static RecordResponseDto fromEntity(Record entity){
        return RecordResponseDto.builder()
                .entity(entity)
                .build();
    }
}
