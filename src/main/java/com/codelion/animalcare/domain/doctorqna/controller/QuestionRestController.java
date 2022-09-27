package com.codelion.animalcare.domain.doctorqna.controller;

import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.MemberService;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class QuestionRestController {

    private final QuestionService questionService;
    private final MemberService memberService;

    @PostMapping("/usr/doctor-qna/like/{id}")
    public boolean like(@PathVariable Long id, Principal principal) {

        Member member = memberService.findMemberByEmail(principal.getName());

        boolean result = questionService.saveLike(id, member);

        return result;
    }
}
