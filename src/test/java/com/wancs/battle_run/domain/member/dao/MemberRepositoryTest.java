package com.wancs.battle_run.domain.member.dao;

import com.wancs.battle_run.domain.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active:local")
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
//    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        Member member = Member.builder()
                .name("임수빈")
                .nickName("sbim")
                .email("isb9082@naver.com")
                .build();
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember.getNickName()).isEqualTo(member.getNickName());
        Assertions.assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
    }
}