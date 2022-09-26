package com.codelion.animalcare.domain.doctorqna.controller;

import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

// 질문 등록, 목록에 표현, 답변 갯수 표시, 답변 등록, 답변 표시, 수정 삭제 (임시)프론트, 로그인 연계 기능들(질문 삭제, 수정, 답변 삭제, 수정) , VALID 기능으로 폼에 입력 제한 걸기
// 구현 완료
/* TODO : 조회수 구현하기, 해시태그로 게시물 표시하기
    프론트 부트스트랩 적용, 질문 답변 작성자 표시기능, 질문 답변 좋아요 기능, 수정시간 > 입력시간? 수정시간 : 입력시간 구현하기
    페이징(우용님꺼랑 같게), 앵커, 추천, 검색
*/

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    private final UserService userService;
    //게시글 등록 화면
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/usr/doctor-qna/write")
    public String saveForm(QuestionSaveRequestDto questionSaveRequestDto){
        return "/doctorqna/doctorQnaQuestionForm";
    }

    //게시글 등록
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/usr/doctor-qna/write")
    public String save(@Valid QuestionSaveRequestDto questionSaveRequestDto, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()) {
            return "/doctorqna/doctorQnaQuestionForm";
        }

        questionService.save(questionSaveRequestDto, principal);

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
                questionService.updateView(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            questionService.updateView(id);
            Cookie newCookie = new Cookie("DoctorQnaView", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }


        model.addAttribute("question", questionService.findById(id));

        //글 추천
        boolean like = false; // 비로그인 유저라면 false
        UserInfo member = userService.getUserInfo(principal.getName()).orElse(null);
        if(member != null) { // 로그인 한 사용자라면
            model.addAttribute("login_id", member.getId());
            like = questionService.findLike(id, member); // 로그인 유저의 추천 여부 확인
        }

        model.addAttribute("like", like);

        return "/doctorqna/doctorQnaDetail";
    }
    //전체 조회
    @GetMapping("/usr/doctor-qna")
    public String findAll(Model model, @RequestParam(value="page", defaultValue="0") int page, String type, String kw) {

        Page<Question> paging = questionService.findAll(page, type, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);


        return "/doctorqna/doctorQnaList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/usr/doctor-qna/{id}/modify")
    public String update(Model model, @PathVariable Long id, QuestionUpdateRequestDto questionUpdateRequestDto, Principal principal){

        if(questionService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        model.addAttribute("question", questionService.findById(id));
        return "/doctorqna/doctorQnaQuestionModifyForm";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/usr/doctor-qna/{id}/modify")
    public String update(@PathVariable Long id, @Valid QuestionUpdateRequestDto questionUpdateRequestDto, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()) {
            return "/doctorqna/doctorQnaQuestionModifyForm";
        }

        if(questionService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        questionService.update(id, questionUpdateRequestDto);

        return "redirect:/usr/doctor-qna/%d".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/usr/doctor-qna/{id}/delete")
    public String delete(@PathVariable Long id, Principal principal){

        if(questionService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        questionService.delete(id);
        return "redirect:/usr/doctor-qna";
    }

}