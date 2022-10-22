package com.wancs.battle_run.domain.running.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wancs.battle_run.domain.running.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.wancs.battle_run.global.common.InitializationByType.*;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDto {
    private Long id;
    private String content;

    @Builder
    public CommentResponseDto(Comment comment){
        if(comment == null){
            commentIsNull();
            return;
        }

        this.id = longCheck(comment.getId());
        this.content = stringCheck(comment.getContent());
    }

    //객체 자체가 null 일 때 일괄 셋팅
    public void commentIsNull(){
        this.id = null;
        this.content = null;
    }
}
