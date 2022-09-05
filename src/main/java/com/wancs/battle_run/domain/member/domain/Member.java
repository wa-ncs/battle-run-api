package com.wancs.battle_run.domain.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String nickName;
    private String email;
}
