package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.member.dao.MemberRepository;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active:local")
@Transactional
class MemberServiceImplTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
        // given
        CreateMemberRequest memberRequest = new CreateMemberRequest("임수빈","sbim", "isb9082@gmail.com");

        // when
        Long saveId = memberService.save(memberRequest);

        // then
        assertEquals(saveId, memberRepository.findById(saveId).getId());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        CreateMemberRequest member1Request = new CreateMemberRequest("임수빈","sbim", "isb9082@gmail.com");
        CreateMemberRequest member2Request = new CreateMemberRequest("수빈","sbim", "isb9082@gmail.com");
        // when
        memberService.save(member1Request);
        // then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.save(member2Request));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
    }
}