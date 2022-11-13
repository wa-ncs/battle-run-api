package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.auth.dao.AuthRepository;
import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.dto.LoginDto;
import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.auth.service.AuthService;
import com.wancs.battle_run.domain.member.dao.MemberRepository;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.SaveMemberRequestDto;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Transactional
    public Long save(SaveMemberRequestDto requestDto) {
        // TODO : security 설정 후 비밀번호 sha256 encoding 로직 추가 필요
        Member member = requestDto.toEntity();
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }


    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!ObjectUtils.isEmpty(findMembers)) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

//
    @Transactional
    public Long update(Long id, UpdateMemberRequestDto requestDto) {
        Member member= memberRepository.findById(id);
        member.changeInfo(requestDto);
        return member.getId();
    }
//
//    public Member findOne(Long id) {
//    }
//
//    public List<Member> findMembers() {
//    }
}
