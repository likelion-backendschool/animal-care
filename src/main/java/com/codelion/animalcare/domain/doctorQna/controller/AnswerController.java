package com.codelion.animalcare.domain.doctorQna.controller;


import com.codelion.animalcare.domain.doctorQna.controller.dto.request.AnswerSaveRequestDto;
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
    @PostMapping("/usr/doctor-qna/{id}/answers/write")
    public Long save(@PathVariable Long id, @RequestBody AnswerSaveRequestDto answerSaveRequestDto){

        return answerService.save(id, answerSaveRequestDto);
    }
}
