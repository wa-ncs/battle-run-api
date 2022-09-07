package com.wancs.battle_run.domain.member.entity;

import com.wancs.battle_run.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String nickName;
    @Column(unique=true)
    private String email;

    @Builder
    public Member(Long id, String name, String nickName, String email) {
        this.id=id;
        this.name=name;
        this.nickName=nickName;
        this.email=email;
    }
}
