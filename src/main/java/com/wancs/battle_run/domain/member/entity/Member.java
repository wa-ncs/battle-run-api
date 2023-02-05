package com.wancs.battle_run.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wancs.battle_run.domain.auth.entity.Auth;
import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.global.common.CommonEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member", indexes = @Index(name="unique_idx_member_email_type", columnList = "email, type", unique = true))
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
    @Column(name = "type", nullable = false)
    private String type;

    // TODO : 디비 생성 후에는 관계 제거 필요
    @JsonIgnore
    @OneToOne(mappedBy = "member", fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private Auth auth;

    @Builder
    public Member(Long id, String name, String nickName, String email, String password, String type, Auth auth) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.type = type;
        this.auth = auth;
    }

    public void changeInfo(UpdateMemberRequestDto requestDto) {
        if(StringUtils.isNotBlank(requestDto.getName()))
            this.name = requestDto.getName();
        if(StringUtils.isNotBlank(requestDto.getNickName()))
            this.nickName = requestDto.getNickName();
        if(StringUtils.isNotBlank(requestDto.getEmail()))
            this.email = requestDto.getEmail();
        if(StringUtils.isNotBlank(requestDto.getPassword()))
            this.password = requestDto.getPassword();
        if(StringUtils.isNotBlank(requestDto.getType()))
            this.type = requestDto.getType();
    }

}
