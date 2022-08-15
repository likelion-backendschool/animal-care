package com.codelion.animalcare.domain.doctorQna.service;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto questionSaveRequestDto) {
        return questionRepository.save(questionSaveRequestDto.toEntity()).getQuestionId();
    }

    @Transactional
    public Long update(Long id, QuestionUpdateRequestDto questionUpdateRequestDto){
        Question question = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다. 글 번호=" + id));

        question.update(questionUpdateRequestDto.getTitle(), questionUpdateRequestDto.getContent());

        return id;
    }

    public QuestionResponseDto findById(Long id){
        Question entity = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. 글 번호=" + id));

        return new QuestionResponseDto(entity);

    }

}
