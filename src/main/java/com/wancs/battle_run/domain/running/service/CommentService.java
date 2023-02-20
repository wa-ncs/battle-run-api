package com.wancs.battle_run.domain.running.service;

import com.wancs.battle_run.domain.running.dao.CommentRepository;
import com.wancs.battle_run.domain.running.dto.request.SaveCommentRequestDto;
import com.wancs.battle_run.domain.running.dto.request.UpdateCommentRequestDto;
import com.wancs.battle_run.domain.running.entity.Comment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment findCommentByRecordId(Long recordId){
        return commentRepository.findCommentByRecordId(recordId);
    }

    public Comment findById(Long commentId){
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재 하지않는 comment 입니다."));
    }

    @Transactional
    public Long save(SaveCommentRequestDto requestDto, Long recordId){
        return commentRepository.save(requestDto.toEntity(recordId)).getId();
    }

    @Transactional
    public Long update(UpdateCommentRequestDto requestDto){
        Comment comment = this.findById(requestDto.getId());
        comment.chageComment(requestDto);
        return comment.getId();
    }
}
