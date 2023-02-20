package com.wancs.battle_run.global.config.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${jwt_secret}")
    private String SECRET_KEY;
    @Value("${jwt_refresh}")
    private String REFRESH_KEY;

    private final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 24 * 365 * 1000L;   // 1년
    private final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 3650 * 1000L;   // 10년

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        System.out.println("SECRET_KEY : " + SECRET_KEY);
        System.out.println("REFRESH_KEY : " + REFRESH_KEY);
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        REFRESH_KEY = Base64.getEncoder().encodeToString(REFRESH_KEY.getBytes());
    }

    // JWT 토큰 생성
    public String createAccessToken(Long memberId) {
        Claims claims = Jwts.claims();//.setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("memberId", memberId);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 사용할 암호화 알고리즘과
                .compact();
    }

    public String createRefreshToken(Long memberId) {
        Claims claims = Jwts.claims();
        claims.put("memberId", memberId);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, REFRESH_KEY)
                .compact();
    }
}
