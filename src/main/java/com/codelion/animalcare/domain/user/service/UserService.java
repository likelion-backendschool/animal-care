package com.codelion.animalcare.domain.user.service;


import com.codelion.animalcare.domain.user.entity.Admin;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.dto.UserInfoDto;
import com.codelion.animalcare.domain.user.repository.AdminRepository;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.user.repository.MemberRepository;

import com.codelion.animalcare.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;

    private final DoctorRepository doctorLoginRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    /**
     * 회원정보 저장
     *
     * @param infoDto 회원정보가 들어있는 DTO
     * @return 저장되는 회원의 PK
     */
    public Long save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        if(infoDto.getAuth().contains("ROLE_ADMIN")){
            return adminRepository.save(Admin.builder()
                    .email(infoDto.getEmail())
                    .auth(infoDto.getAuth())
                    .password(infoDto.getPassword()).build()).getId();
        }

        if(infoDto.getAuth().contains("ROLE_DOCTOR")){
            return doctorLoginRepository.save(Doctor.builder()
                    .email(infoDto.getEmail())
                    .auth(infoDto.getAuth())
                    .password(infoDto.getPassword()).build()).getId();
        }

        return memberRepository.save(Member.builder()
                .email(infoDto.getEmail())
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword())
                .build()).getId();
    }

//    public UserInfo findPatientAndDoctor(){
//        String tmp = "Patient";
//        return userRepository.findByDtype(tmp);
//    }








}