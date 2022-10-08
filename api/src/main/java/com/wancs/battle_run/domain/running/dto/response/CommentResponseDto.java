package com.wancs.battle_run.domain.running.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long commentId;
    private String comment;

    @Builder
    public CommentResponseDto(Long commentId, String comment){
        this.commentId = commentId;
        this.comment = comment;
    }
}
