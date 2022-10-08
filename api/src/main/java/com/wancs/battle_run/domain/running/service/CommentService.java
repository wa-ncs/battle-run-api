package com.wancs.battle_run.domain.running.service;

import com.wancs.battle_run.domain.running.dao.CommentRepository;
import com.wancs.battle_run.domain.running.dto.request.SaveCommentRequestDto;
import com.wancs.battle_run.domain.running.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment findCommentByRecordId(Long recordId){
        return commentRepository.findCommentByRecordId(recordId);
    }

    public Comment findbyId(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("no data"));
    }

    @Transactional
    public Long save(SaveCommentRequestDto requestDto){
        return commentRepository.save(requestDto.toEntity()).getId();
    }
}
