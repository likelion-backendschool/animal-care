package com.codelion.animalcare.domain.user.controller;

import com.codelion.animalcare.domain.user.dto.DoctorSignUpDto;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.dto.MemberSignUpDto;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.MemberForm;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

/**
 * 회원 마이페이지 내정보
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member")
public class MemberController {

    private final MemberService memberService;





    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("memberSignUpDto", new MemberSignUpDto());
        return "login/memberSignup";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberSignUpDto memberSignUpDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login/memberSignup";
        }
        memberService.save(memberSignUpDto);
        return "redirect:/";
    }
}
