package com.wancs.battle_run.domain.game.entity;

import com.wancs.battle_run.global.common.CommonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameMember extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long gameId;

    //초기값 : null
    //참여 : Y
    //불참 : N
    private String participationStatus;

    //배틀전 초기값 0
    private Integer ranking;

    @Builder
    public GameMember(Long id, Long memberId, Long gameId, String participationStatus, Integer ranking){
        this.id = id;
        this.memberId = memberId;
        this.gameId = gameId;
        this.participationStatus = participationStatus;
        this.ranking = ranking;
    }

}
