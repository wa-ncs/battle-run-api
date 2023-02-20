package com.wancs.battle_run.domain.running.entity;

import com.wancs.battle_run.domain.running.dto.request.UpdateCommentRequestDto;
import com.wancs.battle_run.global.common.CommonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;



@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recordId;

    private Long userId;

    private String content;

    @Builder
    public Comment(Long id, Long recordId, Long userId, String content){
        this.id = id;
        this.recordId = recordId;
        this.userId = userId;
        this.content = content;
    }

    public void chageComment(UpdateCommentRequestDto requestDto){
        if(StringUtils.isNotBlank(requestDto.getContent())){
            this.content = requestDto.getContent();
        }
    }
}
