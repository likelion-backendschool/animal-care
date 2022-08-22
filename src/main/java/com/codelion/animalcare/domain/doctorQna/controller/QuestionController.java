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

// 질문 등록, 목록에 표현, 답변 갯수 표시, 답변 등록, 답변 표시 완료
/* TODO : 로그인 연계 기능들(질문 삭제, 수정, 답변 삭제, 수정) , VALID 기능으로 폼에 입력 제한 걸기, 조회수 구현하기, 해시태그로 게시물 표시하기
    프론트 부트스트랩 적용, 질문 답변 작성자 표시기능, 질문 답변 좋아요 기능
*/

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