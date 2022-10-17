package com.wancs.battle_run.domain.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wancs.battle_run.global.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.wancs.battle_run.domain.member.entity.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Auth {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String accessToken;
    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "member")
    private Member member;

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
