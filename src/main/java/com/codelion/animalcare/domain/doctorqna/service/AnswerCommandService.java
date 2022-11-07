package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerCommandService {

    private final AnswerQueryService answerQueryService;
    private final AnswerRepository answerRepository;
    private final DoctorRepository doctorRepository;
    private final QuestionQueryService questionQueryService;


    public Long save(Long questionId, AnswerSaveRequestDto answerSaveRequestDto, Principal principal){
        Question question = questionQueryService.findQuestionByQuestionId(questionId);
        answerSaveRequestDto.setQuestion(question);

        Doctor doctor = doctorRepository.findByEmail(principal.getName()).orElseThrow(() -> new IllegalArgumentException("의사가 존재하지 않습니다."));

//        if(!member.getAuth().equals("doctor")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "의사만 답변을 작성할 수 있습니다.");
//        }

        question.addAnswer(answerSaveRequestDto.toEntity(doctor));
        return answerRepository.save(answerSaveRequestDto.toEntity(doctor)).getId();
    }


    public Long update(Long answerId, AnswerUpdateRequestDto answerUpdateRequestDto) {
        Answer answer = answerQueryService.findAnswerByAnswerId(answerId);
        answer.update(answerUpdateRequestDto.getContent());

        return answerId;
    }


    public void delete(Long answerId) {
        Answer answer = answerQueryService.findAnswerByAnswerId(answerId);
        answerRepository.delete(answer);
    }

}
