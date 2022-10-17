package com.wancs.battle_run.domain.auth.service;

import com.wancs.battle_run.domain.auth.dao.AuthRepository;
import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.global.config.jwt.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;

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

    public Auth findById(Long authId) {
        return authRepository.findById(authId);
    }
}
