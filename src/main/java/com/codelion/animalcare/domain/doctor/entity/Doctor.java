package com.codelion.animalcare.domain.doctor.entity;

import com.codelion.animalcare.common.entity.BaseEntity;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor extends BaseEntity {
    @Column(nullable = false, length = 50, unique = true)
    private String loginEmail;

    @Column(nullable = false, length = 50)
    private String loginPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column()
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;

    @Column(nullable = false, length = 30)
    private String major;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public void updateLoginPwd(String newLoginPwd){
        loginPwd = newLoginPwd;
    }

    @Builder
    private Doctor(Long id, LocalDateTime createdAt, String loginEmail, String loginPwd, String name, Date birthday, String major, String phoneNum, String introduce, LocalDateTime deletedAt, int genderId, Hospital hospital) {
        super(id, createdAt);
        this.loginEmail = loginEmail;
        this.loginPwd = loginPwd;
        this.name = name;
        this.birthday = birthday;
        this.major = major;
        this.phoneNum = phoneNum;
        this.introduce = introduce;
        this.deletedAt = deletedAt;
        this.genderId = genderId;
        this.hospital = hospital;
    }
}
