package com.codelion.animalcare.domain.user.controller;

import com.codelion.animalcare.domain.mypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usr/domain/doctor")
public class DoctorRestController {
    private final DoctorService doctorService;

    //TODO url 다른방법 생각해보기
    @GetMapping("hospital/{hospitalId}")
    public List<LoadDoctorMyPageInfo.ResponseDto> findDoctorsByHospital(
            @PathVariable Long hospitalId
    ){
        return doctorService.findDoctorsByHospital(hospitalId);
    }
}
