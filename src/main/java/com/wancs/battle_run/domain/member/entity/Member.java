package com.wancs.battle_run.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.global.common.CommonEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends CommonEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "nick_name", nullable = false, unique=true)
    private String nickName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    public Member(Long id, String name, String nickName, String email, String password) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    public void changeInfo(UpdateMemberRequestDto requestDto) {
        if (StringUtils.isNotBlank(requestDto.getName()))
            this.name = requestDto.getName();
        if (StringUtils.isNotBlank(requestDto.getNickName()))
            this.nickName = requestDto.getNickName();
        if (StringUtils.isNotBlank(requestDto.getEmail()))
            this.email = requestDto.getEmail();
        if (StringUtils.isNotBlank(requestDto.getPassword()))
            this.password = requestDto.getPassword();
    }

}
