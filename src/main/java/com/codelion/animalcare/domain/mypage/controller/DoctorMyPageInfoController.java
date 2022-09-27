package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.mypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.mypage.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.mypage.dto.UpdateUserInfoPassword;
import com.codelion.animalcare.domain.user.service.DoctorService;
import com.codelion.animalcare.global.error.exception.UserModifyAfterPasswordNotSameException;
import com.codelion.animalcare.global.error.exception.UserModifyBeforePasswordNotSameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/usr/doctor/mypage")
@RequiredArgsConstructor
public class DoctorMyPageInfoController {
    private final DoctorService doctorService;

    @GetMapping("")
    public String loadDoctorMyPage(){
        return "myPage/doctor/index";
    }
    // 내 정보
    @GetMapping("/info")
    public String loadDoctorMyPageInfo(
            Model model,
            Principal principal
    ){
        String email = principal.getName();
        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(email);

        model.addAttribute("doctor", doctorDto);

        return "myPage/doctor/info";
    }

    // 내 정보 수정 페이지
    @GetMapping("/info/modify")
    public String loadDoctorMyPageInfoModify(
            Model model,
            Principal principal
    ){
        String email = principal.getName();
        LoadDoctorMyPageInfo.ResponseDto doctorDto = doctorService.findByEmail(email);

        model.addAttribute("requestDto", doctorDto);

        return "myPage/doctor/info-modify";
    }

    // 내 정보 수정 요청(비밀번호 제외)
    @PostMapping("/info/modify")
    public String updateDoctorMyPageInfo(
            @Valid UpdateDoctorMyPageInfo.RequestDto requestDto,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "myPage/doctor/info-modify";
        }

        doctorService.update(requestDto);

        return "redirect:/usr/doctor/mypage/info";
    }

    @GetMapping("/info/modify/password")
    public String loadDoctorMyPageInfoPassword(
            UpdateUserInfoPassword.RequestDto requestDto
    ){
        return "myPage/doctor/info-modify-password";
    }

    // 비밀번호 수정.
    @PostMapping("/info/modify/password")
    public String updateDoctorMyPageInfoPassword(
            Model model,
            Principal principal,
            @Valid UpdateUserInfoPassword.RequestDto requestDto,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "myPage/doctor/info-modify-password";
        }

        String email = principal.getName();

        // TODO try-catch문 대체품 찾기
        try{
            doctorService.updatePassword(requestDto, email);
        } catch (UserModifyBeforePasswordNotSameException e){
            bindingResult.reject("DoctorModifyBeforePasswordNotSame", e.getMessage());
            // TODO  addAttribute 없애도 될듯?
            model.addAttribute("passwordDto", requestDto);
            return "myPage/doctor/info-modify-password";
        } catch (UserModifyAfterPasswordNotSameException e){
            bindingResult.reject("DoctorModifyAfterPasswordNotSame", e.getMessage());
            model.addAttribute("passwordDto", requestDto);
            return "myPage/doctor/info-modify-password";
        }

        return "redirect:/usr/doctor/mypage/info";
    }
}
