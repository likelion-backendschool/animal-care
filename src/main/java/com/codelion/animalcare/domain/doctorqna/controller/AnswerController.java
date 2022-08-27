package com.codelion.animalcare.domain.doctorqna.controller;


import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.service.AnswerService;
import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    //답변 작성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/usr/doctor-qna/{questionId}/answers/write")
    public String save(Model model, @PathVariable Long questionId, @Valid AnswerSaveRequestDto answerSaveRequestDto, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()) {
            model.addAttribute("question", questionService.findById(questionId));
            return "doctorqna/doctorQnaDetail";
        }

        if(!answerService.isDoctor(principal)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "의사만 답변을 작성할 수 있습니다.");
        }

        answerService.save(questionId, answerSaveRequestDto, principal);
        return "redirect:/usr/doctor-qna/%d".formatted(questionId);
    }

    //TODO : 아래 기능들은 로그인 후 구현?

    @GetMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/modify")
    public String modify(Model model, @PathVariable Long questionId, @PathVariable Long answerId, AnswerUpdateRequestDto answerUpdateRequestDto){

        model.addAttribute("answer", answerService.findById(answerId));

        return "/doctorqna/doctorQnaAnswerModifyForm";
    }

    @PostMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/modify")
    public String modify(@PathVariable Long questionId, @PathVariable Long answerId, @Valid AnswerUpdateRequestDto answerUpdateRequestDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "/doctorqna/doctorQnaAnswerModifyForm";
        }
        answerService.update(questionId, answerId, answerUpdateRequestDto);
        return "redirect:/usr/doctor-qna/%d".formatted(questionId);
    }

    //답변 삭제
    @GetMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/delete")
    public String delete(@PathVariable Long questionId, @PathVariable Long answerId) {
        answerService.delete(questionId, answerId);
        return "redirect:/usr/doctor-qna/%d".formatted(questionId);
    }
}
