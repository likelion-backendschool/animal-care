package com.codelion.animalcare.domain.member;

import com.codelion.animalcare.domain.animal.AnimalDto;
import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.member.controller.MemberController;
import com.codelion.animalcare.domain.member.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MemberDto {

    //id 빼야하나?
    private Long memberId;

    private String login_email;
    private String login_pwd;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;


    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private LocalDateTime birthDay;

    @Embedded
    private Address address;

    private String phone_num;

    private int gender_id;
    private List<AnimalDto> animals;

    public MemberDto(Member member) {
        memberId = member.getId();
        login_email = member.getLogin_email();
        name = member.getName();
        birthDay = member.getBirthday();
        address = member.getAddress();
        phone_num = member.getPhone_num();
        gender_id = member.getGender_id();
        animals = member.getAnimals().stream()
                .map(animals -> new AnimalDto(animals))
                .collect(Collectors.toList());
    }


}
