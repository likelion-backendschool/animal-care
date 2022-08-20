package com.codelion.animalcare.domain.doctorQna.service;

import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionListResponseDto;
import com.codelion.animalcare.domain.doctorQna.controller.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorQna.repository.Question;
import com.codelion.animalcare.domain.doctorQna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto questionSaveRequestDto) {
        return questionRepository.save(questionSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, QuestionUpdateRequestDto questionUpdateRequestDto){
        Question question = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다. 글 번호=" + id));

        question.update(questionUpdateRequestDto.getTitle(), questionUpdateRequestDto.getContent());

        return id;
    }
    @Transactional(readOnly = true)
    public QuestionResponseDto findById(Long id){
        Question entity = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. 글 번호=" + id));

        return new QuestionResponseDto(entity);

    }

    @Transactional(readOnly = true)
    public List<QuestionListResponseDto> findAllDesc() {
        return questionRepository.findAllDesc().stream()
                .map(QuestionListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        questionRepository.delete(question);
    }
}
