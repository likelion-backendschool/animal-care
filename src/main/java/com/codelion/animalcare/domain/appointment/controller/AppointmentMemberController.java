package com.codelion.animalcare.domain.appointment.controller;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.appointment.dto.AppointmentFormDto;
import com.codelion.animalcare.domain.appointment.service.AppointmentService;
import com.codelion.animalcare.domain.mypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.hospital.dto.LoadDoctorMyPageHospitalInfoManage;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/appointment")
public class AppointmentMemberController {
    private final MemberService memberService;

//    private final AnimalService animalService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final AppointmentService appointmentService;

    @GetMapping()
    public String appointment(Model model, Principal principal){
        MemberDto memberDto = findMemberDto(principal);
        model.addAttribute("memberAddress", memberDto.getAddress());

        return "appointments/appointmentHospitalMap";
    }

    @GetMapping("others")
    public String appointmentOthers(
            Model model,
            Principal principal,
            AppointmentFormDto appointmentFormDto
    ){

            MemberDto memberDto = findMemberDto(principal);

            LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findById(appointmentFormDto.getDoctorId());

            LoadDoctorMyPageHospitalInfoManage.ResponseDto hospitalDto = hospitalService.findById(appointmentFormDto.getHospitalId());

//        List<AnimalDto> animalDtoList = animalService.findByMember(memberDto.getEmail());
            List<AnimalDto> animalDtoList = memberDto.getAnimals();

            model.addAttribute("memberDto", memberDto);
            model.addAttribute("hospitalDto", hospitalDto);
            model.addAttribute("doctorDto", doctorDto);
            model.addAttribute("animalDtoList", animalDtoList);
            model.addAttribute("appointmentFormDto", appointmentFormDto);
            return "appointments/appointmentOthers";
    }

    @PostMapping()
    public String createAppointment(
            Model model,
            Principal principal,
            HttpServletRequest request,
            @Valid AppointmentFormDto appointmentFormDto,
            BindingResult bindingResult
    ){
            if(bindingResult.hasErrors()){
                String referer = request.getHeader("Referer");
                return "redirect:"+ referer;
            }

            MemberDto memberDto = findMemberDto(principal);

        try{
                appointmentService.appointment(memberDto, appointmentFormDto);
            } catch(RuntimeException e) {
                model.addAttribute("message", e.getMessage());
                return "error/error-with-message";
            }

            return "redirect:/";
    }

    private MemberDto findMemberDto(Principal principal) {
        String email = principal.getName();
        return memberService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("member email " + email + " was not found."));
    }
}
