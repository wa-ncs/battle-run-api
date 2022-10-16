package com.wancs.battle_run.domain.running.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateCommentRequestDto {
    @NotNull
    private Long commentId;

    private String comment;
}
