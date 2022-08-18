package com.codelion.animalcare.domain.animal.entity;

import com.codelion.animalcare.global.common.entity.BaseEntity;
import com.codelion.animalcare.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;

    @Column(nullable = false, length = 100)
    private String registrationNum;

    @Column(length = 45)
    private String health_status;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Animal(Long id, LocalDateTime createdAt, String name, Date birthday, String registrationNum, String health_status, LocalDateTime deletedAt, int genderId, Member member) {
        super(id, createdAt);
        this.name = name;
        this.birthday = birthday;
        this.registrationNum = registrationNum;
        this.health_status = health_status;
        this.deletedAt = deletedAt;
        this.genderId = genderId;
        this.member = member;
    }
}
