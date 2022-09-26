package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequestDto;

public interface MemberService {

    Member findById(Long memberId);

    Long save(CreateMemberRequestDto member);

    Long update(Long id, UpdateMemberRequestDto member);

//    void delete(Long id);
}