package com.wancs.howmuchspend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member find(Long id) {
        return memberRepository.find(id);
    }
    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public Long save(MemberDTO member) {
        Member entityMember = new Member();
        entityMember.setName(member.getName());
        return memberRepository.save(entityMember);
    }

    @Override
    @Transactional
    public Member update(Long id, MemberDTO member) {
        Member findMember = memberRepository.find(id);
        findMember.setName(member.getName());
        return findMember;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        memberRepository.remove(id);
    }
}
