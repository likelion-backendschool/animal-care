package com.codelion.animalcare.domain.doctorQna.controller;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionListResponseDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorQna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final QuestionService questionService;
    //게시글 등록
    @PostMapping("/usr/doctor-qna/write")
    public Long save(@RequestBody QuestionSaveRequestDto questionSaveRequestDto) {
        return questionService.save(questionSaveRequestDto);
    }
    //게시글 수정
    @PostMapping("/usr/doctor-qna/{id}/modify")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto questionUpdateRequestDto){
        return questionService.update(id, questionUpdateRequestDto);
    }

    //개별 조회
    @GetMapping("/usr/doctor-qna/{id}")
    public QuestionResponseDto findById(@PathVariable Long id){
        return questionService.findById(id);
    }
    //전체 조회
    @GetMapping("/usr/doctor-qna")
    public List<QuestionListResponseDto> findAllDesc() {
        return questionService.findAllDesc();
    }

    //게시글 삭제
    @GetMapping("/usr/doctor-qna/{id}/delete")
    public void delete(@PathVariable Long id){
        questionService.delete(id);
    }

}