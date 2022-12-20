package com.wancs.battle_run.domain.auth.service;

import com.wancs.battle_run.domain.auth.dao.AuthRepository;
import com.wancs.battle_run.domain.auth.dto.LoginDto;
import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.member.dao.MemberRepository;
import com.wancs.battle_run.domain.member.entity.Member;
import com.wancs.battle_run.global.config.jwt.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final AuthRepository authRepository;
//    private final PasswordEncoder passwordEncoder;

    public TokenDto generateToken(Long memberId) {
        // 토큰 발급
        String accessToken = jwtTokenProvider.createAccessToken(memberId);
        String refreshToken = jwtTokenProvider.createRefreshToken(memberId);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public Long save(Auth auth) {
        return authRepository.save(auth);
    }

//    public Auth findById(Long authId) {
//        return authRepository.findById(authId);
//    }

    public TokenDto doLogin(LoginDto loginDto) {
        // 회원 정보 조회 ( 회원정보가 없을 시 IllegalArgumentException 발생 )
        Member member = this.findByEmailAndType(loginDto);

        // TODO : 디비 생성 후에는 Auth 정보 새로 조회하는 로직 추가 필요
        // 비밀번호 체크 ( 비밀번호가 다를 시 IllegalArgumentException 발생 )
        this.checkPassword(loginDto, member);

        TokenDto token = this.generateToken(member.getId());

        // 토큰 정보 갱신
        member.getAuth().updateAccessToken(token.getAccessToken());
        member.getAuth().updateRefreshToken(token.getRefreshToken());

        return TokenDto.builder()
                .accessToken(member.getAuth().getAccessToken())
                .refreshToken(member.getAuth().getRefreshToken())
                .build();
    }

    public TokenDto refresh(TokenDto tokenDto) {
        // TODO : @Auth로 memberId 가져와서 토큰 조회 하도록 수정 필요
        Member member = memberRepository.findById(1L);

        // 토큰 정보 체크 ( 엑세스 토큰 or 리프레시 토큰 둘중 하나라도 다를 시 IllegalArgumentException 발생 )
        this.checkToken(member.getAuth(), tokenDto);

        TokenDto token = this.generateToken(member.getId());

        // 토큰 정보 갱신
        member.getAuth().updateAccessToken(token.getAccessToken());
        member.getAuth().updateRefreshToken(token.getRefreshToken());

        return TokenDto.builder()
                .accessToken(member.getAuth().getAccessToken())
                .refreshToken(member.getAuth().getRefreshToken())
                .build();
    }

    private Member findByEmailAndType(LoginDto loginDto) {
        Member findMember = memberRepository.findByEmailAndType(loginDto);
        if (ObjectUtils.isEmpty(findMember)) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return findMember;
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

    private void checkToken(Auth auth, TokenDto tokenDto) {
        if (!(auth.getAccessToken().equals(tokenDto.getAccessToken()))
                || !(auth.getRefreshToken().equals(tokenDto.getRefreshToken()))) {
            throw new IllegalStateException("토큰 정보가 일치하지 않습니다.");
        }
    }
}
