package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.mypage.dto.LoadDoctorMyPageInfo;
import com.codelion.animalcare.domain.mypage.dto.UpdateDoctorMyPageInfo;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.MemberService;
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
@RequestMapping("/usr/member/mypage")
@RequiredArgsConstructor
public class MemberMyPageInfoController {
    private final MemberService memberService;

    @GetMapping("")
    public String myPageHome() {

        return "myPage/member/myPageForm";
    }
    @GetMapping("/info")
    public String loadMemberMyPageInfo(Model model, Principal principal){
        String email = principal.getName();
        MemberDto memberDto =  memberService.findByEmail(email).get();
        model.addAttribute("memberDto", memberDto);
        return "myPage/member/info";
    }
    @GetMapping("/info/modify")
    public String loadDoctorMyPageInfoModify(Model model, Principal principal
    ){
        String email = principal.getName();
        MemberDto memberDto =  memberService.findByEmail(email).get();

        model.addAttribute("memberDto", memberDto);

        return "myPage/member/info-modify";
    }

    @PostMapping("/info/modify")
    public String updateDoctorMyPageInfo(@Valid MemberDto memberDto, BindingResult bindingResult){
        System.out.println("qweqweqwe");
        if(bindingResult.hasErrors()){
            return "myPage/doctor/info-modify";
        }
        System.out.println("---------------testetes");
        System.out.println(memberDto.getId());
        memberService.update(memberDto);

        return "redirect:/usr/member/mypage/info";
    }
}
