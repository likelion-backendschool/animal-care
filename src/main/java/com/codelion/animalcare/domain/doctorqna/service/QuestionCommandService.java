package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.entity.QuestionLike;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionLikeRepository;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionCommandService {

    private final QuestionQueryService questionQueryService;

    private final MemberService memberService;

    private final QuestionHashtagService questionHashtagService;

    private final QuestionRepository questionRepository;

    private final QuestionLikeRepository questionLikeRepository;



    public Long save(QuestionSaveRequestDto questionSaveRequestDto, Principal principal) {
        Member member = memberService.findMemberByEmail(principal.getName());
        Question savedQuestion = questionRepository.save(questionSaveRequestDto.toEntity(member));

        questionHashtagService.saveHashtag(savedQuestion, questionSaveRequestDto.hashtags());


        return savedQuestion.getId();
    }

    public Long update(Long id, QuestionUpdateRequestDto questionUpdateRequestDto){
        Question question = questionQueryService.findQuestionByQuestionId(id);
        question.update(questionUpdateRequestDto.title(), questionUpdateRequestDto.content());

        return id;
    }


    public int updateView(Long id) {
        return this.questionRepository.updateView(id);
    }


    public void delete(Long id) {
        Question question = questionQueryService.findQuestionByQuestionId(id);
        questionRepository.delete(question);
    }


    public boolean saveLike(Long id, UserInfo user){

        if(!questionQueryService.findLike(id, user)) {

            Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            QuestionLike questionLike = new QuestionLike(user, question);
            questionLikeRepository.save(questionLike);
            questionRepository.plusLike(id);

            return true;
        }

        questionLikeRepository.deleteByQuestion_IdAndMember_Id(id, user.getId());
        questionRepository.minusLike(id);

        return false;
    }



}

