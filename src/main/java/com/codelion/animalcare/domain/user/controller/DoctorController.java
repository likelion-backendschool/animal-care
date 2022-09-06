package com.codelion.animalcare.domain.user.controller;

import com.codelion.animalcare.domain.user.dto.DoctorDto;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usr/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("doctorDto", new DoctorDto());
        return "login/doctorSignup";
    }

    @PostMapping("/signup")
    public String signup(DoctorDto doctorDto){
        doctorService.save(doctorDto);
        return "main";
    }
}
