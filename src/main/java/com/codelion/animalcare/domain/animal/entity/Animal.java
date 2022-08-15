package com.codelion.animalcare.domain.animal.entity;

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
public class Animal extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthday;

    @Column(nullable = false, length = 100)
    private String registrationNum;

    @Column(length = 45)
    private String health_status;

    @Column()
    private LocalDateTime deletedAt;

    @Column()
    private int genderId;
}
