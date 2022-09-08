package com.codelion.animalcare.domain.hospital.controller;

import com.codelion.animalcare.domain.hospital.dto.LatitudeLongitudeDto;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import com.codelion.animalcare.domain.user.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("usr/hospital")
@RequiredArgsConstructor
public class HospitalRestController {
    private final HospitalService hospitalService;

    @GetMapping()
    public ResponseEntity<Page<Hospital>> loadHospitalsWithAjax(
            @RequestParam(value="page", defaultValue = "0") int page,
            // TODO dto 전환
            LatitudeLongitudeDto latitudeLongitudeDto
    ){

        // TODO  경도,위도,Pageable 사용하여 hospitalService에서 지도 범위 안에 있는 병원 Page<Hospital>로 받아오기.

        Page<Hospital> paging = hospitalService.findByLatitudeAndLongitude(latitudeLongitudeDto, page);

        // 임시 병원 삽입
        System.out.println("" + latitudeLongitudeDto.getMinLatitude() + " " + latitudeLongitudeDto.getMaxLatitude()
                + " " + latitudeLongitudeDto.getMinLongitude() + " " + latitudeLongitudeDto.getMaxLongitude());
//        List<Hospital> hospitals = Arrays.asList(Hospital.builder()
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .name("대박 병원")
//                .openingHours("02:57~02:57/02:57~02:57/02:57~02:58/02:58~02:58/02:58~02:58/02:58~02:58/02:58~02:58/")
//                .phoneNum("01033334444")
//                .address(new Address("경기 용인시 기흥구 구갈동 593-3", "경기 용인시 기흥구 강남동로 7", "16979","101동",37.2716680153549, 127.128387356766))
//                .build());

//        Page<Hospital> paging = new PageImpl<>(hospitals);

        return ResponseEntity
                .ok(paging);
    }
}
