package com.codelion.animalcare.domain.animal;

import com.codelion.animalcare.domain.animal.entity.Animal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AnimalDto {

        private String name;
        private LocalDateTime birthDay;
        private String registration_num;
        private int gender_id;


        public AnimalDto(Animal animal) {
            name = animal.getName();
            birthDay = animal.getBirthday();
            registration_num = animal.getRegistration_num();
            gender_id = animal.getGender_id();
        }
}
