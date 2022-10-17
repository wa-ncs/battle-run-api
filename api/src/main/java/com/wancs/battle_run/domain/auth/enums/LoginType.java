package com.wancs.battle_run.domain.auth.enums;

import com.wancs.battle_run.domain.member.entity.Member;

public enum LoginType {
    BATTLE_RUN("BATTLE_RUN"),
    KAKAO("KAKAO"),
    GOOGLE("GOOGLE"),
    FACEBOOK("FACEBOOK");

    public final String type;
    private LoginType(String type) {
        this.type = type;
    }

    public static LoginType entityOf(Member member) {
        for (LoginType loginType : values()) {
            if (loginType.type.equals(member.getType())) {
                return loginType;
            }
        }
        return null;
    }
}
