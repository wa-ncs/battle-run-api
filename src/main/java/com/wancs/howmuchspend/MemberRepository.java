package com.wancs.howmuchspend;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

//    커멘더와 쿼리를 분리해라. 저장을 하면, 사이드 이팩트 즉 커멘더성이기 때문에 리턴값을 만들지 않는다. id정도만 가져오도록
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
