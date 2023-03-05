package com.wancs.battle_run.domain.running.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UpdateCommentRequestDto {
    @NotNull
    private Long id;

    private String content;
}
