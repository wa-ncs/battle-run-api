package com.wancs.battle_run.domain.game.dao;

import com.wancs.battle_run.domain.game.entity.GameMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameMemberRepository extends JpaRepository<GameMember,Long> {

    List<GameMember> findByGameId(Long gameId);
}
