package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.response.AnswerResponseDto;
import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnswerQueryService {

    private final AnswerRepository answerRepository;

    public boolean answerAuthorized(Long answerId, Principal principal) {
        Answer answer = findAnswerByAnswerId(answerId);

        if (answer.getDoctor().getEmail().equals(principal.getName())) {
            return false;
        }

        return true;
    }

    public Answer findAnswerByAnswerId(Long answerId) {
        return answerRepository.findById(answerId)
                               .orElseThrow(() -> new IllegalArgumentException("답변이 존재하지 않습니다."));
    }

    public AnswerResponseDto findById(Long answerId) {
        Answer entity = findAnswerByAnswerId(answerId);

        return new AnswerResponseDto(entity);
    }
}
