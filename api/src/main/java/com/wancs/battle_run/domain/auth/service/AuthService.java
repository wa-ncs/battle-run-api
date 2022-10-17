package com.wancs.battle_run.domain.auth.service;

import com.wancs.battle_run.domain.auth.dto.TokenDto;
import com.wancs.battle_run.domain.auth.entity.Auth;

public interface AuthService {
    TokenDto generateToken(Long memberId);
    Long save(Auth auth);
    Auth findById(Long authId);
}
