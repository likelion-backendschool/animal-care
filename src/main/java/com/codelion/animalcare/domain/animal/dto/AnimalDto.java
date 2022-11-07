package com.codelion.animalcare.domain.animal.dto;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

        private String breedingPlace;

        private String animalType;

        private String animalBreed;

        private String animalCoatColor;

        private String animalSpecial;
        private int age;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        public AnimalDto(Animal animal) {
                id = animal.getId();
                name = animal.getName();
                birthday = animal.getBirthday();
                registrationNum = animal.getRegistrationNum();
                genderId = animal.getGenderId();
                breedingPlace = animal.getBreedingPlace();
                animalType = animal.getAnimalType();
                animalBreed = animal.getAnimalBreed();
                animalCoatColor = animal.getAnimalCoatColor();
                animalSpecial = animal.getAnimalSpecial();
                age = calAge();
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
                    .breedingPlace(breedingPlace)
                    .animalType(animalType)
                    .animalBreed(animalBreed)
                    .animalCoatColor(animalCoatColor)
                    .animalSpecial(animalSpecial)
                    .build();
        }

        private int calAge(){
                int birthYear = birthday.getYear();
                int birthMonth = birthday.getMonth() + 1;
                int birthDay = birthday.getDay();
                Date current = new Date(System.currentTimeMillis());
                int currentYear  = current.getYear();
                int currentMonth = current.getMonth() + 1;
                int currentDay   = current.getDay();
                int age = currentYear - birthYear;

                if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay)
                        age--;
                return age;
        }
}
