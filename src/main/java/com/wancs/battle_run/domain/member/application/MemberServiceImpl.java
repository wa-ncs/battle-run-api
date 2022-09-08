package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.member.dao.MemberRepository;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(CreateMemberRequestDto requestDto) {
        Member member = requestDto.toEntity();
        validateDuplicateMember(member);
        Long saveId = memberRepository.save(member);
        return saveId;
    }


    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()) {
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
