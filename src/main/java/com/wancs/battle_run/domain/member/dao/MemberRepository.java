package com.wancs.battle_run.domain.member.dao;

import com.wancs.battle_run.domain.auth.dto.LoginDto;
import com.wancs.battle_run.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByEmailAndType(LoginDto loginDto) {
        return em.createQuery(
                "select m " +
                        "from Member m " +
                        "where m.email = :email " +
                        "and m.type = :type"
                        , Member.class
                )
                .setParameter("email", loginDto.getEmail())
                .setParameter("type", loginDto.getType())
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByEmail(String email) {
        return em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

}
