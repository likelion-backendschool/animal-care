package com.codelion.animalcare.domain.doctorQna.controller;


import com.codelion.animalcare.domain.doctorQna.controller.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AnswerController {

    private final AnswerService answerService;


    //답변 작성
    @PostMapping("/usr/doctor-qna/{questionId}/answers/write")
    public Long save(@PathVariable Long questionId, @RequestBody AnswerSaveRequestDto answerSaveRequestDto){

        return answerService.save(questionId, answerSaveRequestDto);
    }

    //답변 수정
    @PostMapping("/usr/doctor-qna/{questionId}/answers/{answerId}/modify")
    public Long modify(@PathVariable Long questionId, @PathVariable Long answerId, @RequestBody AnswerUpdateRequestDto answerUpdateRequestDto){

        return answerService.update(questionId, answerId, answerUpdateRequestDto);
    }
}
