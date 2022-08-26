package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.repository.AdminRepository;
import com.codelion.animalcare.domain.user.repository.DoctorLoginRepository;
import com.codelion.animalcare.domain.user.repository.UserRepository;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

   /* @Autowired
    private PatientRepository patientRepository;*/

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorLoginRepository doctorLoginRepository;

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