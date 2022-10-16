package com.wancs.battle_run.domain.running.service;

import com.wancs.battle_run.domain.running.dao.CommentRepository;
import com.wancs.battle_run.domain.running.dto.request.SaveCommentRequestDto;
import com.wancs.battle_run.domain.running.dto.request.UpdateCommentRequestDto;
import com.wancs.battle_run.domain.running.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment findCommentByRecordId(Long recordId){
        return commentRepository.findCommentByRecordId(recordId);
    }

    public Optional<Comment> findById(Long commentId){
        return commentRepository.findById(commentId);
    }

    @Transactional
    public Long save(SaveCommentRequestDto requestDto, Long recordId){
        return commentRepository.save(requestDto.toEntity(recordId)).getId();
    }

    @Transactional
    public Long update(UpdateCommentRequestDto requestDto){
        Optional<Comment> optionalComment = this.findById(requestDto.getCommentId());
        Comment comment = optionalComment.get();
        comment.chageComment(requestDto);
        return comment.getId();
    }

    public Boolean isExistComment(Long commentId){
        Optional<Comment> optionalRecord = this.findById(commentId);
        return optionalRecord.isPresent();
    }
}
