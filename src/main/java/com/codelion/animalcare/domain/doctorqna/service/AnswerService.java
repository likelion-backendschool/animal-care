package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.AnswerResponseDto;
import com.codelion.animalcare.domain.doctorqna.repository.Answer;
import com.codelion.animalcare.domain.doctorqna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final DoctorRepository doctorRepository;
    private final QuestionService questionService;

    @Transactional
    public Long save(Long questionId, AnswerSaveRequestDto answerSaveRequestDto, Principal principal){
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));
        answerSaveRequestDto.setQuestion(question);

        Doctor doctor = doctorRepository.findByEmail(principal.getName()).orElseThrow(() -> new IllegalArgumentException("의사가 존재하지 않습니다."));

//        if(!member.getAuth().equals("doctor")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "의사만 답변을 작성할 수 있습니다.");
//        }

        question.addAnswer(answerSaveRequestDto.toEntity(doctor));
        return answerRepository.save(answerSaveRequestDto.toEntity(doctor)).getId();
    }

    @Transactional
    public Long update(Long questionId, Long answerId, AnswerUpdateRequestDto answerUpdateRequestDto) {

        Question question = questionService.findQuestionByQuestionId(questionId);
        Answer answer = findAnswerByAnswerId(answerId);

        answer.update(answerUpdateRequestDto.getContent());

        return answerId;
    }


    @Transactional
    public void delete(Long questionId, Long answerId) {
        Question question = questionService.findQuestionByQuestionId(questionId);
        Answer answer = findAnswerByAnswerId(answerId);

        answerRepository.delete(answer);
    }

    @Transactional(readOnly = true)
    public AnswerResponseDto findById(Long answerId){
        Answer entity = findAnswerByAnswerId(answerId);

        return new AnswerResponseDto(entity);
    }


    public boolean answerAuthorized(Long answerId, Principal principal) {
        Answer answer = findAnswerByAnswerId(answerId);

        if(answer.getDoctor().getEmail().equals(principal.getName())) {
            return false;
        }

        return true;
    }

    public Answer findAnswerByAnswerId(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("답변이 존재하지 않습니다."));
    }
}
