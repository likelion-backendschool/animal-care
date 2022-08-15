package com.codelion.animalcare.domain.doctorQna.controller.dto;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/usr/doctor-qna/write")
    public Long save(@RequestBody QuestionSaveRequestDto questionSaveRequestDto) {
        return questionService.save(questionSaveRequestDto);
    }

    @PostMapping("/usr/doctor-qna/{id}/modify")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto questionUpdateRequestDto){
        return questionService.update(id, questionUpdateRequestDto);
    }

}
