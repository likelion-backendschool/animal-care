package com.codelion.animalcare.domain.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;
    private String street;
    private String zipcode;
    private String detail;

    private Double latitude; // 위도

    private Double longitude; // 경도

    public Address(String city, String street, String zipcode, String detail) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.detail = detail;
    }

    public Address(String city, String street, String zipcode, String detail, Double latitude, Double longitude) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.detail = detail;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

