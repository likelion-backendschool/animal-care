package com.codelion.animalcare.domain.member.entity;

import com.codelion.animalcare.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Column(nullable = false, length = 50, unique = true)
    private String loginEmail;

    @Column(nullable = false, length = 50)
    private String loginPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    private LocalDateTime birthday;

    @Column(nullable = false, length = 70)
    private String address;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column()
    private LocalDateTime deletedAt;

    @Builder
    private Member(Long id, LocalDateTime createdAt, String loginEmail, String loginPwd, String name, LocalDateTime birthday, String address, String phoneNum, LocalDateTime deletedAt) {
        super(id, createdAt);
        this.loginEmail = loginEmail;
        this.loginPwd = loginPwd;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.phoneNum = phoneNum;
        this.deletedAt = deletedAt;
    }
}
