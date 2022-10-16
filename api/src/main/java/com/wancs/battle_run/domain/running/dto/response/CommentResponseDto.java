package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String comment;

    @Builder
    public CommentResponseDto(Comment comment){
        this.commentId = comment != null? comment.getId() : 0;
        this.comment = comment != null? comment.getComment() : "";
    }
}
