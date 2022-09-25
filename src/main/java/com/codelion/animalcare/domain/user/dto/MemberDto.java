package com.codelion.animalcare.domain.user.dto;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String email;

    private String password;

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;


    // @Temporal: 날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Embedded
    private Address address;

    private String phoneNum;

    private int genderId;

    private String auth;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private List<AnimalDto> animals;

    public MemberDto(Member member) {
        id = member.getId();
        email = member.getEmail();
        password = member.getPassword();
        name = member.getName();
        birthday = member.getBirthday();
        address = member.getAddress();
        phoneNum = member.getPhoneNum();
        genderId = member.getGenderId();
        auth = member.getAuth();
        createdAt = member.getCreatedAt();
        updatedAt = member.getUpdatedAt();
        animals = member.getAnimals().stream()
                .map(animals -> new AnimalDto(animals))
                .collect(Collectors.toList());
    }

    public Member toEntity(Member member) {
        return Member.builder()
                .id(member.getId())
                .email(email)
                .password(member.getPassword())
                .name(name)
                .address(address)
                .birthday(birthday)
                .phoneNum(phoneNum)
                .genderId(genderId)
                .auth(auth)
                .animals(member.getAnimals())
                .createdAt(member.getCreatedAt())
                .auth(member.getAuth())
                .build();
    }
}
