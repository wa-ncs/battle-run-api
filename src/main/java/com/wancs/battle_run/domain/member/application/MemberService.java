package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.dto.LoginDto;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.SaveMemberRequestDto;

public interface MemberService {

    Member findById(Long memberId);

    Long save(SaveMemberRequestDto member);

    Long update(Long id, UpdateMemberRequestDto member);

//    void delete(Long id);
}