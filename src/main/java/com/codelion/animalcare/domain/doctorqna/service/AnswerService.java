package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.AnswerUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.AnswerResponseDto;
import com.codelion.animalcare.domain.doctorqna.repository.Answer;
import com.codelion.animalcare.domain.doctorqna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
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
        question.addAnswer(answerSaveRequestDto.toEntity());
        return answerRepository.save(answerSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long questionId, Long answerId, AnswerUpdateRequestDto answerUpdateRequestDto) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("답변이 존재하지 않습니다."));

        answer.update(answerUpdateRequestDto.getContent());

        return answerId;
    }

    @Transactional
    public void delete(Long questionId, Long answerId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("답변이 존재하지 않습니다."));

        answerRepository.delete(answer);
    }

    @Transactional(readOnly = true)
    public AnswerResponseDto findById(Long id){
        Answer entity = answerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. 글 번호=" + id));

        return new AnswerResponseDto(entity);
    }
}
