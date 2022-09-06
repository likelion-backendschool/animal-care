package com.codelion.animalcare.domain.user.controller;

import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Member;
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
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/usr/mypage/member/new")
    public String createForm(Model model) {

        model.addAttribute("memberForm", new MemberForm());
        return "member/memberForm";
    }

    @PostMapping("/usr/mypage/member/new")
    public String create(@Valid MemberForm form, BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return "member/memberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Optional<Member> member = memberService.findByEmail(principal.getName());

        member.get().changeName(form.getName());
        member.get().changeAddress(address);

        memberService.join(member.get());

        return "redirect:/usr/mypage/member";
    }


    @GetMapping("/usr/mypage/member/info")
    public String list(Model model, Principal principal) {

        Optional<Member> member = memberService.findByEmail(principal.getName());

        model.addAttribute("member", member.get());
        return "member/memberInfo";
    }

    @GetMapping("/usr/member/signup")
    public String signup(MemberDto memberDto){
        return "login/memberSignup";
    }
}
