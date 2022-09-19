package com.codelion.animalcare.domain.user.controller;

import com.codelion.animalcare.domain.user.dto.DoctorSignUpDto;
import com.codelion.animalcare.domain.user.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/usr/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("doctorSignUpDto", new DoctorSignUpDto());
        return "login/doctorSignup";
    }

    @PostMapping("/signup")
    public String signup(@Valid DoctorSignUpDto doctorDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login/doctorSignup";
        }
        doctorService.save(doctorDto);
        return "redirect:/";
    }
}
