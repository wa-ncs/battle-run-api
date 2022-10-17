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
@Table(indexes = @Index(name="unique_idx_member_email_type", columnList = "email, type", unique = true))
public class Member extends CommonEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String name;
    private String nickName;
//    @Column(unique=true)
    private String email;
    private String password;
    private String type;

    @JsonIgnore
    @OneToOne(mappedBy = "member", fetch = LAZY)
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
