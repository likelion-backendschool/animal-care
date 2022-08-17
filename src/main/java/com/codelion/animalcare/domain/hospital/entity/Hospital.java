package com.codelion.animalcare.domain.hospital.entity;

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
public class Hospital extends BaseEntity {
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 70)
    private String address;

    @Column(length = 300)
    private String openingHours;

    @Column()
    private LocalDateTime deletedAt;

    @Builder
    private Hospital(Long id, LocalDateTime createdAt, String name, String address, String openingHours, LocalDateTime deletedAt) {
        super(id, createdAt);
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
        this.deletedAt = deletedAt;
    }
}
