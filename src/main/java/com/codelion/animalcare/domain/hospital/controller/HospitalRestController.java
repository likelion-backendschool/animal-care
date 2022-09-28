package com.codelion.animalcare.domain.hospital.controller;

import com.codelion.animalcare.domain.hospital.dto.LatitudeLongitudeDto;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("usr/hospital")
@RequiredArgsConstructor
public class HospitalRestController {
    private final HospitalService hospitalService;

    @GetMapping()
    public ResponseEntity<Page<Hospital>> loadHospitalsWithAjax(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="keyword", defaultValue = "") String keyword,
            LatitudeLongitudeDto latitudeLongitudeDto
    ){
        // TODO dto 전환
        Page<Hospital> paging = hospitalService.findByLatitudeAndLongitude(latitudeLongitudeDto, page, keyword);

        System.out.println("" + latitudeLongitudeDto.getMinLatitude() + " " + latitudeLongitudeDto.getMaxLatitude()
                + " " + latitudeLongitudeDto.getMinLongitude() + " " + latitudeLongitudeDto.getMaxLongitude());

        return ResponseEntity
                .ok(paging);
    }
}
