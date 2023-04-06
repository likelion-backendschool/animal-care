package com.codelion.animalcare.domain.doctorqna.service;


import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionLikeRepository;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.user.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/*

TODO : SERVICE단에서 QUESTION ENTITY를 직접 반환하지 않고 DTO를 반환하는 것이 요청에 대한 응답만 함으로써
       성능, 보안적으로 이점을 얻을 수 있다고 생각한다. 하지만, SERVICE단에서 ENTITY가 필요한 경우에 findQuestionByQuestionId를 통해 직접 ENTITY를 얻고 있는데,
       이는 어찌보면 오버엔지니어링이 아닌가 하는 생각도 있다. 이를 어떻게 해결하는 것이 좋은 방법인지 확신이 서지 않는다.
       이를 개선하기 위해서 떠올린 방법으로는
       1. 그냥 service단에서 entity를 반환하기
       2. modelMapper를 사용하기

 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionQueryService {

    private final QuestionRepository questionRepository;
    private final QuestionLikeRepository questionLikeRepository;


    public QuestionResponseDto findById(Long id) {
        Question entity = findQuestionByQuestionId(id);
        return new QuestionResponseDto(entity);
    }

    public boolean questionAuthorized(Long id, Principal principal) {
        Question question = findQuestionByQuestionId(id);

        if (question.getMember().getEmail().equals(principal.getName())) {
            return false;
        }

        return true;
    }

    public boolean findLike(Long id, UserInfo user) {
        return questionLikeRepository.existsByQuestion_IdAndMember_Id(id, user.getId());
    }

    public Question findQuestionByQuestionId(Long id) {
        return questionRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));
    }

    public Page<Question> findAll(int page, String type, String kw) {
        List<Sort.Order> sortsList = new ArrayList<>();
        sortsList.add(Sort.Order.desc("createdAt"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortsList));

        switch (type != null ? type : " ") {
            case "title" -> {
                return questionRepository.findByTitleContaining(kw, pageable);
            }
            case "content" -> {
                return questionRepository.findByContentContaining(kw, pageable);
            }
            case "member" -> {
                return questionRepository.findByMemberContaining(kw, pageable);
            }
            case "titleAndContent" -> {
                return questionRepository.findByTitleOrContent(kw, pageable);
            }
            default -> {
                return questionRepository.findAll(pageable);
            }
        }

    }
}
