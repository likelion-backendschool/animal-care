package com.codelion.animalcare.domain.doctorqna.controller;

import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.service.QuestionQueryService;
import com.codelion.animalcare.domain.doctorqna.service.QuestionCommandService;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;


@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionCommandService questionCommandService;
    private final QuestionQueryService questionQueryService;
    private final UserService userService;

    //게시글 등록 화면
    @GetMapping("/usr/doctor-qna/write")
    public String saveForm(QuestionSaveRequestDto questionSaveRequestDto){
        return "doctorqna/doctorQnaQuestionForm";
    }

    //게시글 등록
    @PostMapping("/usr/doctor-qna/write")
    public String save(@Valid QuestionSaveRequestDto questionSaveRequestDto, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()) {
            return "doctorqna/doctorQnaQuestionForm";
        }
        questionCommandService.save(questionSaveRequestDto, principal);

        return "redirect:/usr/doctor-qna";
    }

    //개별 조회
    @GetMapping("/usr/doctor-qna/{id}")
    public String findById(Model model, @PathVariable Long id, AnswerSaveRequestDto answerSaveRequestDto, HttpServletRequest request, HttpServletResponse response, Principal principal){
        // 조회수 쿠키로 중복방지
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(var cookie : cookies) {
                if(cookie.getName().equals("DoctorQnaView")) {
                    oldCookie = cookie;
                }
            }
        }

        if(oldCookie != null) {
            if(!oldCookie.getValue().contains("[" + id.toString() + "]")) {
                questionCommandService.updateView(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            questionCommandService.updateView(id);
            Cookie newCookie = new Cookie("DoctorQnaView", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }

        model.addAttribute("question", questionQueryService.findById(id));
        //글 추천
        boolean like = false; // 비로그인 유저라면 false

        UserInfo user = userService.getUserInfo(principal.getName()).orElse(null);

        if(user != null) { // 로그인 한 사용자라면
            model.addAttribute("login_id", user.getId());

            like = questionQueryService.findLike(id, user); // 로그인 유저의 추천 여부 확인

        }

        model.addAttribute("like", like);

        return "doctorqna/doctorQnaDetail";
    }
    //전체 조회
    @GetMapping("/usr/doctor-qna")
    public String findAll(Model model, @RequestParam(value="page", defaultValue="0") int page, String type, String kw) {

        Page<Question> paging = questionQueryService.findAll(page, type, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("type", type);


        return "doctorqna/doctorQnaList";
    }

    @GetMapping("/usr/doctor-qna/{id}/modify")
    public String update(Model model, @PathVariable Long id, QuestionUpdateRequestDto questionUpdateRequestDto, Principal principal){

        if(questionQueryService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        model.addAttribute("question", questionQueryService.findById(id));
        return "doctorqna/doctorQnaQuestionModifyForm";
    }
    @PostMapping("/usr/doctor-qna/{id}/modify")
    public String update(@PathVariable Long id, @Valid QuestionUpdateRequestDto questionUpdateRequestDto, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()) {
            return "doctorqna/doctorQnaQuestionModifyForm";
        }

        if(questionQueryService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        questionCommandService.update(id, questionUpdateRequestDto);

        return "redirect:/usr/doctor-qna/%d".formatted(id);
    }

    @GetMapping("/usr/doctor-qna/{id}/delete")
    public String delete(@PathVariable Long id, Principal principal){

        if(questionQueryService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        questionCommandService.delete(id);
        return "redirect:/usr/doctor-qna";
    }

}