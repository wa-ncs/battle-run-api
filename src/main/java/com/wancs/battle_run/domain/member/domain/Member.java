package com.wancs.battle_run.domain.member.domain;

import com.wancs.battle_run.global.common.BaseTimeEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
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
