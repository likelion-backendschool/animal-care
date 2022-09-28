package com.codelion.animalcare.domain.hospital.dto;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.global.util.OpeningHour;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class UpdateDoctorMyPageHospitalInfoManage {
    @Getter
    @Setter
    public static class RequestDto extends OpeningHour{
        private Long id;
        @NotEmpty(message = "이름을 작성하여야 합니다.")
        private String name;

        @NotEmpty(message = "전화 번호를 작성하여야 합니다.")
        private String phoneNum;

        private String city;
        private String street;
        private String zipcode;
        private String detail;
        private String openingHours;

        private Double latitude; // 위도
        private Double longitude; // 경도
//        public RequestDto(String openingHours) {
//            super(openingHours);
//        }


        public RequestDto(String monStart, String monEnd, String tueStart, String tueEnd, String wedStart, String wedEnd, String thuStart, String thuEnd, String friStart, String friEnd, String satStart, String satEnd, String sunStart, String sunEnd, Long id, String name, String phoneNum, String city, String street, String zipcode, String detail, String openingHours, Double latitude, Double longitude) {
            super(monStart, monEnd, tueStart, tueEnd, wedStart, wedEnd, thuStart, thuEnd, friStart, friEnd, satStart, satEnd, sunStart, sunEnd);
            this.id = id;
            this.name = name;
            this.phoneNum = phoneNum;
            this.city = city;
            this.street = street;
            this.zipcode = zipcode;
            this.detail = detail;
            this.openingHours = openingHours;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Hospital toEntity(Hospital hospital){
            Address address = new Address(city, street, zipcode, detail,latitude, longitude);
            return Hospital.builder()
                    .id(id)
                    .name(name)
                    .address(address)
                    .phoneNum(phoneNum)
                    .openingHours(openingHourToString())
                    .createdAt(hospital.getCreatedAt())
                    .build();
        }

    }
}
