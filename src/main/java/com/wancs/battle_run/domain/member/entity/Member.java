package com.wancs.battle_run.domain.member.entity;

import com.wancs.battle_run.domain.member.dto.request.UpdateMemberRequestDto;
import com.wancs.battle_run.global.common.BaseTimeEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

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

    public void changeInfo(UpdateMemberRequestDto requestDto) {
        if(StringUtils.isNotBlank(requestDto.getName()))
            this.name = requestDto.getName();
        if(StringUtils.isNotBlank(requestDto.getNickName()))
            this.nickName = requestDto.getNickName();
        if(StringUtils.isNotBlank(requestDto.getEmail()))
            this.email = requestDto.getEmail();
    }

}
