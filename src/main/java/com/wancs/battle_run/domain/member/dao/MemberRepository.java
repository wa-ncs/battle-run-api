package com.wancs.battle_run.domain.member.dao;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wancs.battle_run.domain.member.dto.response.MemberResponseDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.entity.QMember;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    private final QMember qMember = QMember.member;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public MemberResponseDto findMemberById(Long id) {
        return jpaQueryFactory
            .select(
                Projections.constructor(MemberResponseDto.class,
                    qMember.id
                    , qMember.name
                    , qMember.nickName
                    , qMember.email
                )
            )
            .from(qMember)
            .where(qMember.id.eq(id))
            .fetchOne();
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
