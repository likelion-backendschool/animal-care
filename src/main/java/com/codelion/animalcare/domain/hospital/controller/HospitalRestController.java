package com.codelion.animalcare.domain.hospital.controller;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
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

    @GetMapping()
    public ResponseEntity<String> loadHospitalsWithAjax(
            @RequestParam(value="page", defaultValue = "0") int page
    ){
        return ResponseEntity
                .ok("ajax response success");
    }
}
