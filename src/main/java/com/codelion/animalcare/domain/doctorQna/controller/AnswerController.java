package com.codelion.animalcare.domain.doctorQna.controller;


import com.codelion.animalcare.domain.doctorQna.controller.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final AnswerService answerService;


    //답변 작성
    @PostMapping("/usr/doctor-qna/{questionId}/answers/write")
    public String save(@PathVariable Long questionId, AnswerSaveRequestDto answerSaveRequestDto){
        answerService.save(questionId, answerSaveRequestDto);
        return "redirect:/usr/doctor-qna/%d".formatted(questionId);
    }

    //답변 수정
    @PostMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/modify")
    public Long modify(@PathVariable Long questionId, @PathVariable Long answerId, @RequestBody AnswerUpdateRequestDto answerUpdateRequestDto){

        return answerService.update(questionId, answerId, answerUpdateRequestDto);
    }

    //답변 삭제
    @GetMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/delete")
    public void delete(@PathVariable Long questionId, @PathVariable Long answerId) {
        answerService.delete(questionId, answerId);
    }
}
