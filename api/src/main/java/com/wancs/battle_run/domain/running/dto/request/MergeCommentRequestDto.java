package com.wancs.battle_run.domain.running.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MergeCommentRequestDto {
    @NotNull
    private Long recordId;

    private Long userId;

    private Long comment;
}
