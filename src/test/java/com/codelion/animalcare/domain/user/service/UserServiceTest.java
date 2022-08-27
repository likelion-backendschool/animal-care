package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.repository.AdminRepository;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import com.codelion.animalcare.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorLoginRepository;

    @Test
    public void UserInfo_만들기(){
        UserInfo userInfo = UserInfo.builder()
                        .email("testEmail")
                        .password("testPasswd")
                        .name("testName")
                        .phoneNum("010-1234-5678")
                        .auth("ROLE_USER")
                        .build();
        userRepository.save(userInfo);
    }

}