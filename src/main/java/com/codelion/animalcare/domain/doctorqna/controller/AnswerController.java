package com.codelion.animalcare.domain.doctorqna.controller;


import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.service.AnswerQueryService;
import com.codelion.animalcare.domain.doctorqna.service.AnswerCommandService;
import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final AnswerCommandService answerCommandService;
    private final AnswerQueryService answerQueryService;
    private final QuestionService questionService;

    //답변 작성
    @PostMapping("/usr/doctor-qna/{questionId}/answers/write")
    public String save(Model model, @PathVariable Long questionId, @Valid AnswerSaveRequestDto answerSaveRequestDto, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()) {
            model.addAttribute("question", questionService.findById(questionId));
            return "doctorqna/doctorQnaDetail";
        }

//        if(!answerService.isDoctor(principal)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "의사만 답변을 작성할 수 있습니다.");
//        }

        answerCommandService.save(questionId, answerSaveRequestDto, principal);
        return "redirect:/usr/doctor-qna/%d".formatted(questionId);
    }

    @GetMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/modify")
    public String modify(Model model, @PathVariable Long questionId, @PathVariable Long answerId, AnswerUpdateRequestDto answerUpdateRequestDto, Principal principal){

        if(answerQueryService.answerAuthorized(answerId, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        model.addAttribute("answer", answerQueryService.findById(answerId));

        return "doctorqna/doctorQnaAnswerModifyForm";
    }
    @PostMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/modify")
    public String modify(@PathVariable Long questionId, @PathVariable Long answerId, @Valid AnswerUpdateRequestDto answerUpdateRequestDto, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()) {
            return "doctorqna/doctorQnaAnswerModifyForm";
        }

        if(answerQueryService.answerAuthorized(answerId, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        answerCommandService.update(questionId, answerId, answerUpdateRequestDto);
        return "redirect:/usr/doctor-qna/%d".formatted(questionId);
    }

    //답변 삭제
    @GetMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/delete")
    public String delete(@PathVariable Long questionId, @PathVariable Long answerId, Principal principal) {

        if(answerQueryService.answerAuthorized(answerId, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        answerCommandService.delete(questionId, answerId);
        return "redirect:/usr/doctor-qna/%d".formatted(questionId);
    }
}
