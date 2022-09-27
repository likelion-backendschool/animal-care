package com.codelion.animalcare.domain.doctorqna.service;

import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionSaveRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.request.QuestionUpdateRequestDto;
import com.codelion.animalcare.domain.doctorqna.dto.response.QuestionResponseDto;
import com.codelion.animalcare.domain.doctorqna.repository.Question;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionLike;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionLikeRepository;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.MemberService;
import com.codelion.animalcare.domain.user.service.UserService;
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

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    private final QuestionLikeRepository questionLikeRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto questionSaveRequestDto, Principal principal) {

        Member member = memberService.findMemberByEmail(principal.getName());

        return questionRepository.save(questionSaveRequestDto.toEntity(member)).getId();
    }

    @Transactional
    public Long update(Long id, QuestionUpdateRequestDto questionUpdateRequestDto){
        Question question = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));

        question.update(questionUpdateRequestDto.getTitle(), questionUpdateRequestDto.getContent());

        return id;
    }
    @Transactional(readOnly = true)
    public QuestionResponseDto findById(Long id){
        Question entity = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));

        return new QuestionResponseDto(entity);

    }

    @Transactional
    public int updateView(Long id) {
        return this.questionRepository.updateView(id);
    }

    @Transactional(readOnly = true)
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
            default -> {
                return questionRepository.findAll(pageable);
            }
        }

    }

    @Transactional
    public void delete(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));

        questionRepository.delete(question);
    }

    public boolean questionAuthorized(Long id, Principal principal){
        Question question = questionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("작성된 글이 없습니다."));


        if(question.getMember().getEmail().equals(principal.getName())) {
            return false;
        }

        return true;

    }

    public boolean findLike(Long id, Member member) {
        return questionLikeRepository.existsByQuestion_IdAndMember_Id(id, member.getId());
    }

    @Transactional(readOnly = false)
    public boolean saveLike(Long id, Member member){

        if(!findLike(id, member)) {

            Question question = questionRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            QuestionLike questionLike = new QuestionLike(member, question);
            questionLikeRepository.save(questionLike);
            questionRepository.plusLike(id);

            return true;
        }

        questionLikeRepository.deleteByQuestion_IdAndMember_Id(id, member.getId());
        questionRepository.minusLike(id);

        return false;
    }

    //delete flag



}

