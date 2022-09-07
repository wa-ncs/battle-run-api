package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.member.dao.MemberRepository;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequest;
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
    public Long save(CreateMemberRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .nickName(request.getNickName())
                .email(request.getEmail())
                .build();
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
//
//    //@Transactional
//    public Long update(Long id, UpdateMemberRequest request) {
//        Member member= memberRepository.findOne(id);
//        member.setName(request.getName());
//        return member.getId();
//    }
//
//    public Member findOne(Long id) {
//    }
//
//    public List<Member> findMembers() {
//    }
}
