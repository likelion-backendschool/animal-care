package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }


    public Optional<MemberDto> findByEmail(String email){

        Optional<Member> optionalMember = memberRepository.findOptionalByEmail(email);
        Optional<MemberDto> memberDto = optionalMember.map(member -> new MemberDto(member));

        return memberDto;
    }


//    @Transactional
//    public Long join(Member member) {
//        memberRepository.save(member);
//        return member.getId();
//    }

    @Transactional
    public void join(MemberDto memberDto) {

        // member check
        Member beforeMember = findMemberById(memberDto.getMemberId());

        // dto => entity
        Member newMember = memberDto.toEntity(beforeMember);

        memberRepository.save(newMember);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member id:" + memberId + " can't found."));
    }


}
