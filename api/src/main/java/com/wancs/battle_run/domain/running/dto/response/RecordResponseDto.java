package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.entity.Comment;
import com.wancs.battle_run.domain.running.entity.Record;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.wancs.battle_run.global.common.InitializationByType.*;

@Getter
public class RecordResponseDto {
    private Long id;
    private Float distance;
    private Long runningTime;
    private Integer pace;
    private Integer calorie;
    private String imageUrl;
    private LocalDateTime createdDate;
    private CommentResponseDto comment;

    @Builder
    public RecordResponseDto(Record entity, Comment comment){
        this.id = checkLong(entity.getId());
        this.distance = checkFloat(entity.getDistance());
        this.runningTime = checkLong(entity.getRunningTime());
        this.pace = checkInteger(entity.getPace());
        this.calorie = checkInteger(entity.getCalorie());
        this.imageUrl = checkString(entity.getImageUrl());
        this.createdDate = entity.getCreatedDate();

        this.comment = CommentResponseDto.builder()
                .comment(comment)
                .build();
    }
}
