package com.wancs.battle_run.domain.running.dto.request;

import com.wancs.battle_run.domain.running.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SaveCommentRequestDto {
    private Long commentId;

    @NotNull
    private Long recordId;

    private Long userId;

    private String comment;

    public Comment toEntity(){
        return Comment.builder()
                .id(this.commentId)
                .recordId(this.recordId)
                .userId(this.userId)
                .comment(this.comment)
                .build();
    }
}
