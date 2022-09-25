package com.codelion.animalcare.domain.animal.dto;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class AnimalDto {

        private Long id;
        @NotEmpty(message = "애완동물 이름은 필수 입니다")
        private String name;

        // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
        @Temporal(TemporalType.DATE)
        private Date birthday;

        @NotEmpty(message = "등록번호는 필수 입니다")
        private String registrationNum;

//        @NotEmpty(message = "생일은 필수 입니다.")
        private int genderId;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
        public AnimalDto(Animal animal) {
                id = animal.getId();
                name = animal.getName();
                birthday = animal.getBirthday();
                registrationNum = animal.getRegistrationNum();
                genderId = animal.getGenderId();
                createdAt = animal.getCreatedAt();
                updatedAt = animal.getUpdatedAt();
        }

        public Animal toEntity(Member member){
            return Animal.builder()
                    .name(name)
                    .birthday(birthday)
                    .member(member)
                    .registrationNum(registrationNum)
                    .genderId(genderId)
                    .build();
        }

}
