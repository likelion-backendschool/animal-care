package com.codelion.animalcare.domain.doctorQna.controller;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//TODO 질문 수정, 삭제 기능은 로그인 기능 구현 되면 연동해서 구현하기
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    //게시글 등록 화면
    @GetMapping("/usr/doctor-qna/write")
    public String saveForm(QuestionSaveRequestDto questionSaveRequestDto){
        return "doctorqna/doctorQnaQuestionForm";
    }

    //게시글 등록 TODO : VAILD 추가 , 로그인 기능 구현 후 작성자 표시
    @PostMapping("/usr/doctor-qna/write")
    public String save(QuestionSaveRequestDto questionSaveRequestDto) {

        questionService.save(questionSaveRequestDto);

        return "redirect:/usr/doctor-qna";
    }

    //게시글 수정 TODO : 로그인 기능 구현 후에
    @PostMapping("/usr/doctor-qna/{id}/modify")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto questionUpdateRequestDto){
        return questionService.update(id, questionUpdateRequestDto);
    }

    //개별 조회
    @GetMapping("/usr/doctor-qna/{id}")
    public String findById(Model model, @PathVariable Long id){
        model.addAttribute("question", questionService.findById(id));
        List<Answer> answerList = questionService.findById(id).getAnswerList();


        return "doctorqna/doctorQnaDetail";
    }
    //전체 조회
    @GetMapping("/usr/doctor-qna")
    public String findAllDesc(Model model) {
        model.addAttribute("questionlist", questionService.findAllDesc());

        return "/doctorqna/doctorQnaList";
    }

    //게시글 삭제 TODO : 로그인 기능 구현 후에
    @GetMapping("/usr/doctor-qna/{id}/delete")
    public String delete(@PathVariable Long id){
        questionService.delete(id);
        return "redirect:/usr/doctor-qna";
    }

}