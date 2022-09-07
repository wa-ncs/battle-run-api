package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequest;

public interface MemberService {

    Member findById(Long memberId);

    Long save(CreateMemberRequest member);

//    Member update(Long id, MemberDTO member);

//    void delete(Long id);
}