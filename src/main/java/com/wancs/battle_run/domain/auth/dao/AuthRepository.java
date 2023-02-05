package com.wancs.battle_run.domain.auth.dao;

import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthRepository {
    private final EntityManager em;

    public Long save(Auth auth) {
        em.persist(auth);
        return auth.getId();
    }

    public Auth findById(Long authId) {
        return em.find(Auth.class, authId);
    }
}
