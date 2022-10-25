package com.wancs.battle_run.domain.running.dao;

import com.wancs.battle_run.domain.running.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    public Comment findCommentByRecordId(Long recordId);
}
