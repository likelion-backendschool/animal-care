package com.codelion.animalcare.domain.doctorqna.controller;

import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping(("/usr/doctor-qna"))
@RestController
public class QuestionRestController {

    private final QuestionService questionService;
    private final UserService userService;

    @PostMapping("/like/{id}")
    public boolean like(@PathVariable Long id, Principal principal) {

        Member member = userService.getMember(principal.getName());

        boolean result = questionService.saveLike(id, member);

        return result;
    }
}
