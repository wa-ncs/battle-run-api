package com.wancs.battle_run.domain.game.dao;


import com.wancs.battle_run.domain.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    @Query(value = "" +
            "SELECT " +
                "G.* " +
            "FROM GAME G " +
            "INNER JOIN GAME_MEMBER GM " +
                "ON G.id = GM.game_id " +
            "WHERE 1=1 " +
                "AND GM.participation_status = 'Y' " +
                "AND GM.member_id =?1 ", nativeQuery = true)
    List<Game> findByMemberId(Long memberId);
}
