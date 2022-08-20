package com.codelion.animalcare.domain.doctorQna.controller;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionListResponseDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    //게시글 등록
    @PostMapping("/usr/doctor-qna/write")
    public String save(Model model, @Valid QuestionSaveRequestDto questionSaveRequestDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "";
        }

        questionService.save(questionSaveRequestDto);
        return "redirect:/doctorqna/doctorQnaList";
    }

    //게시글 수정
    @PostMapping("/usr/doctor-qna/{id}/modify")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto questionUpdateRequestDto){
        return questionService.update(id, questionUpdateRequestDto);
    }

    //개별 조회
    @GetMapping("/usr/doctor-qna/{id}")
    public String findById(Model model, @PathVariable Long id){
        QuestionResponseDto question = questionService.findById(id);
        model.addAttribute("question", question);

        return "doctorqna/doctorQnaDetail";
    }
    //전체 조회
    @GetMapping("/usr/doctor-qna")
    public String findAllDesc(Model model) {
        List<Question> questionList = questionService.findAll();
        model.addAttribute("questionlist", questionList);

        return "/doctorqna/doctorQnaList";
    }

    //게시글 삭제
    @GetMapping("/usr/doctor-qna/{id}/delete")
    public String delete(@PathVariable Long id){
        questionService.delete(id);
        return "redirect:/doctorqna/doctorQnaList";
    }

}