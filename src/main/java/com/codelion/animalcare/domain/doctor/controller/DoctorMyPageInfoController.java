package com.codelion.animalcare.domain.doctor.controller;

import com.codelion.animalcare.domain.doctor.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.doctor.dto.UpdateDoctorMyPageInfoPassword;
import com.codelion.animalcare.domain.doctor.service.DoctorService;
import com.codelion.animalcare.global.error.exception.DoctorModifyAfterPasswordNotSameException;
import com.codelion.animalcare.global.error.exception.DoctorModifyBeforePasswordNotSameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usr/mypage/doctor/{doctorId}/info")
@RequiredArgsConstructor
public class DoctorMyPageInfoController {
    private final DoctorService doctorService;

    // 내 정보
    @GetMapping()
    public String loadDoctorMyPageInfo(Model model, @PathVariable long doctorId){
        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findById(doctorId);

        model.addAttribute("doctor", doctorDto);

        return "myPage/doctor/info";
    }

    // 내 정보 수정 페이지
    @GetMapping("modify")
    public String loadDoctorMyPageInfoModify(
            Model model,
            @PathVariable long doctorId
    ){
        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findById(doctorId);

        model.addAttribute("requestDto", doctorDto);
        model.addAttribute("doctorId", doctorId);

        return "myPage/doctor/info-modify";
    }


    // 내 정보 수정 요청(비밀번호 제외)
    @PostMapping("modify")
    public String updateDoctorMyPageInfo(
            Model model,
            @PathVariable long doctorId,
            @Valid UpdateDoctorMyPageInfo.RequestDto requestDto,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            System.out.println(errors);
            return "myPage/doctor/info-modify";
        }

        doctorService.update(requestDto);

        return "redirect:/usr/mypage/doctor/{doctorId}/info";
    }

    @GetMapping("modify/password")
    public String loadDoctorMyPageInfoPassword(
            Model model,
            @PathVariable Long doctorId,
            UpdateDoctorMyPageInfoPassword.RequestDto requestDto
    ){
        doctorService.findById(doctorId);

        model.addAttribute("doctorId", doctorId);
        return "myPage/doctor/info-modify-password";
    }

    // 비밀번호 수정.
    @PostMapping("modify/password")
    public String updateDoctorMyPageInfoPassword(
            @PathVariable long doctorId,
            Model model,
            @Valid UpdateDoctorMyPageInfoPassword.RequestDto requestDto,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            System.out.println(errors);
            return "myPage/doctor/info-modify-password";
        }

        // TODO try-catch문 대체품 찾기
        try{
            doctorService.updatePassword(requestDto, doctorId);
        } catch (DoctorModifyBeforePasswordNotSameException e){
            bindingResult.reject("DoctorModifyBeforePasswordNotSame", e.getMessage());
            model.addAttribute("passwordDto", requestDto);
            return "myPage/doctor/info-modify-password";
        } catch (DoctorModifyAfterPasswordNotSameException e){
            bindingResult.reject("DoctorModifyAfterPasswordNotSame", e.getMessage());
            model.addAttribute("passwordDto", requestDto);
            return "myPage/doctor/info-modify-password";
        }


        return "redirect:/usr/mypage/doctor/{doctorId}/info";
    }
}
