package com.codelion.animalcare.domain.hospital.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.global.util.OpeningHour;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateHospital {
    @Getter
    @Setter
    public static class RequestDto extends OpeningHour{
        private Long id;
        private String name;

        private String phoneNum;

        private String city;
        private String street;
        private String zipcode;
        private String detail;

        private String openingHours;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private List<Doctor> doctorList;

        public RequestDto(String monStart, String monEnd, String tueStart, String tueEnd, String wedStart, String wedEnd, String thuStart, String thuEnd, String friStart, String friEnd, String satStart, String satEnd, String sunStart, String sunEnd, Long id, String name, String phoneNum, String city, String street, String zipcode, String detail, String openingHours) {
            super(monStart, monEnd, tueStart, tueEnd, wedStart, wedEnd, thuStart, thuEnd, friStart, friEnd, satStart, satEnd, sunStart, sunEnd);
            this.id = id;
            this.name = name;
            this.phoneNum = phoneNum;
            this.city = city;
            this.street = street;
            this.zipcode = zipcode;
            this.detail = detail;
            this.openingHours = openingHours;
        }

        public Hospital toEntity(){
            Address address = new Address(city, street, zipcode, detail);
            return Hospital.builder()
                    .name(name)
                    .phoneNum(phoneNum)
                    .address(address)
                    .openingHours(openingHourToString())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

        }
    }
}
