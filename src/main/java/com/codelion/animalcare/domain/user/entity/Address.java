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

    public Address(String city, String street, String zipcode, String detail) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.detail = detail;
    }
}

