package com.codelion.animalcare.domain.doctorQna.service;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.repository.Answer;
import com.codelion.animalcare.domain.doctorQna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public Long save(Long questionId, AnswerSaveRequestDto answerSaveRequestDto){
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));
        answerSaveRequestDto.setQuestion(question);
        return answerRepository.save(answerSaveRequestDto.toEntity()).getAnswerId();
    }

    @Transactional
    public Long update(Long questionId, Long answerId, AnswerUpdateRequestDto answerUpdateRequestDto) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("답변이 존재하지 않습니다."));

        answer.update(answerUpdateRequestDto.getTitle(), answerUpdateRequestDto.getContent());

        return answerId;
    }

    @Transactional
    public void delete(Long questionId, Long answerId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("답변이 존재하지 않습니다."));

        answerRepository.delete(answer);
    }
}
