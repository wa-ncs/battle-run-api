package com.wancs.battle_run.domain.member.application;

import com.wancs.battle_run.domain.auth.dao.AuthRepository;
import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.dto.LoginDto;
import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.auth.service.AuthService;
import com.wancs.battle_run.domain.member.dao.MemberRepository;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.domain.member.dto.request.CreateMemberRequestDto;
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
    private final AuthRepository authRepository;
    private final AuthService authService;
//    private final PasswordEncoder passwordEncoder;

    public TokenDto doLogin(LoginDto loginDto) {
        // 회원 정보 조회 ( 회원정보가 없을 시 IllegalArgumentException 발생 )
        Member member = this.findByEmailAndType(loginDto);

        // 비밀번호 체크 ( 비밀번호가 다를 시 IllegalArgumentException 발생 )
        this.checkPassword(loginDto, member);

        // TODO : 로그인 할때 토큰을 갱신해주는 것도 좋을거 같음. 의견 필요
        // 토큰 발급
        // TokenDto tokenDto = authService.generateToken(member.getId());

        // 토큰 정보 갱신
        // member.getAuth().updateAccessToken(tokenDto.getAccessToken());
        // member.getAuth().updateRefreshToken(tokenDto.getRefreshToken());

        return TokenDto.builder()
                .accessToken(member.getAuth().getAccessToken())
                .refreshToken(member.getAuth().getRefreshToken())
                .build();
    }

    private void checkPassword(LoginDto loginDto, Member member) {
        if (!loginDto.getPassword().equals(member.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        // 시큐리티 설정이 빠져 있어서 임시 제거
//        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
//            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
//        }
    }

    @Transactional
    public Long save(CreateMemberRequestDto requestDto) {
        Member member = requestDto.toEntity();
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }


    public Member findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
    private Member findByEmailAndType(LoginDto loginDto) {
        Member findMember = memberRepository.findByEmailAndType(loginDto);
        if (ObjectUtils.isEmpty(findMember)) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return findMember;
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
