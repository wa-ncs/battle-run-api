package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.member.domain.Member;
import com.wancs.battle_run.domain.member.dto.CreateMemberRequest;
import com.wancs.battle_run.domain.member.dto.UpdateMemberRequest;

import java.util.List;

public class MemberService {
    public Long save(CreateMemberRequest request) {

    }


    //@Transactional
    public Long update(Long id, UpdateMemberRequest request) {
        Member member= memberRepository.findOne(id);
        member.setName(request.getName());
        return member.getId();
    }

    public Member findOne(Long id) {
    }

    public List<Member> findMembers() {
    }
}
