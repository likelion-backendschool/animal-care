package com.codelion.animalcare.domain.member.service;

import com.codelion.animalcare.domain.member.MemberDto;
import com.codelion.animalcare.domain.member.entity.Member;
import com.codelion.animalcare.domain.member.repository.MemberRepository;
import com.codelion.animalcare.domain.member.repository.MemberRepositoryTmp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findAll();
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }


//    @setter 막혀있음
//    @Transactional
//    public void update(long id, String name) {
//        Member member = memberRepository.findById(id).get();
//        member.setName(name);
//    }




}
