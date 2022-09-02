package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class MemberDto {

    private Long memberId;
    private String email;

    private String password;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String member_name;


    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @Embedded
    private Address address;

    private String phone_num;

    private int gender_id;
    private List<AnimalDto> animals;

    public MemberDto(Member member) {
        memberId = member.getId();
        email = member.getEmail();
        password = member.getPassword();
        member_name = member.getName();
        birthDay = member.getBirthday();
        address = member.getAddress();
        phone_num = member.getPhoneNum();
        gender_id = member.getGenderId();
        animals = member.getAnimals().stream()
                .map(animals -> new AnimalDto(animals))
                .collect(Collectors.toList());
    }

    public Member toEntity(Member member) {

        return Member.builder()
                .id(member.getId())
                .email(email)
                .password(member.getPassword())
                .name(member_name)
                .address(address)
                .birthday(birthDay)
                .phoneNum(phone_num)
                .genderId(gender_id)
                .animals(member.getAnimals())
                .createdAt(member.getCreatedAt())
                .auth(member.getAuth())
                .build();
    }
}
