package com.codelion.animalcare.domain.mypage.controller;

import com.codelion.animalcare.domain.mypage.dto.UpdateUserInfoPassword;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.service.MemberService;
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
    public String loadMemberMyPageInfoModify(Model model, Principal principal
    ){
        String email = principal.getName();
        MemberDto memberDto =  memberService.findByEmail(email).get();

        model.addAttribute("memberDto", memberDto);

        return "myPage/member/info-modify";
    }

    @PostMapping("/info/modify")
    public String updateMemberMyPageInfo(@Valid MemberDto memberDto, BindingResult bindingResult){
        System.out.println("qweqweqwe");
        if(bindingResult.hasErrors()){
            return "myPage/doctor/info-modify";
        }
        System.out.println("---------------testetes");
        System.out.println(memberDto.getId());
        memberService.update(memberDto);

        return "redirect:/usr/member/mypage/info";
    }
    @GetMapping("/info/modify/password")
    public String loadMemberMyPageInfoPassword(
            UpdateUserInfoPassword.RequestDto requestDto
    ){
        return "myPage/member/info-modify-password";
    }
    @PostMapping("/info/modify/password")
    public String updateMemberMyPageInfoPassword(
            Model model,
            Principal principal,
            @Valid UpdateUserInfoPassword.RequestDto requestDto,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "myPage/member/info-modify-password";
        }

        String email = principal.getName();

        // TODO try-catch문 대체품 찾기
        try{
            memberService.updatePassword(requestDto, email);
        } catch (UserModifyBeforePasswordNotSameException e){
            bindingResult.reject("MemberModifyBeforePasswordNotSame", e.getMessage());
            // TODO  addAttribute 없애도 될듯?
            model.addAttribute("passwordDto", requestDto);
            return "myPage/member/info-modify-password";
        } catch (UserModifyAfterPasswordNotSameException e){
            bindingResult.reject("MemberModifyAfterPasswordNotSame", e.getMessage());
            model.addAttribute("passwordDto", requestDto);
            return "myPage/member/info-modify-password";
        }

        return "redirect:/usr/member/mypage/info";
    }
}
