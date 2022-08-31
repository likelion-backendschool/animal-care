package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberDto {

    private Long memberId;
    private String login_email;

    private String login_pwd;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String member_name;


    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private LocalDateTime birthDay;

    @Embedded
    private Address address;

    private String phone_num;

    private Long gender_id;
    private List<AnimalDto> animals;

    public MemberDto(Member member) {
        memberId = member.getId();
        login_email = member.getEmail();
        login_pwd = member.getPassword();
        member_name = member.getName();
        birthDay = member.getBirthday();
        address = member.getAddress();
        phone_num = member.getPhoneNum();
        gender_id = member.getId();
        animals = member.getAnimals().stream()
                .map(animals -> new AnimalDto(animals))
                .collect(Collectors.toList());
    }

}
