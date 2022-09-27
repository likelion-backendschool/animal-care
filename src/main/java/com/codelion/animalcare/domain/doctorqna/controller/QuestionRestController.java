package com.codelion.animalcare.domain.doctorqna.controller;

import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
import com.codelion.animalcare.domain.user.entity.UserInfo;
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
    private final UserService userService;

    @PostMapping("/usr/doctor-qna/like/{id}")
    public boolean like(@PathVariable Long id, Principal principal) {

        UserInfo user = userService.getUserInfo(principal.getName()).orElse(null);

        boolean result = questionService.saveLike(id, user);

        return result;
    }
}
