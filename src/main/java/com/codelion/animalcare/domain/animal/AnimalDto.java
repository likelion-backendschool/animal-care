package com.codelion.animalcare.domain.animal;

import com.codelion.animalcare.domain.animal.entity.Animal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class AnimalDto {

        private Long animalId;
        @NotEmpty(message = "애완동물 이름은 필수 입니다")
        private String name;

        // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
        @Temporal(TemporalType.DATE)
        private LocalDateTime birthDay;

        private String registration_num;

        private int gender_id;


        public AnimalDto(Animal animal) {
            animalId = animal.getId();
            name = animal.getName();
            birthDay = animal.getBirthday();
            registration_num = animal.getRegistrationNum();
            gender_id = animal.getGenderId();
        }

}
