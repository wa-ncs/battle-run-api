package com.wancs.battle_run.domain.running.entity;

import com.wancs.battle_run.domain.running.dto.request.UpdateCommentRequestDto;
import com.wancs.battle_run.global.common.CommonEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recordId;

    private Long userId;

    private String comment;

    @Builder
    public Comment(Long id, Long recordId, Long userId, String comment){
        this.id = id;
        this.recordId = recordId;
        this.userId = userId;
        this.comment = comment;
    }

    public void chageComment(UpdateCommentRequestDto requestDto){
        if(StringUtils.isNotBlank(requestDto.getComment())){
            this.comment = requestDto.getComment();
        }
    }
}
