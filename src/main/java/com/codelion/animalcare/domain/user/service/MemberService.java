package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.dto.MemberSignUpDto;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
        Optional<MemberDto> memberDto = optionalMember.map(o -> new MemberDto(o));

        return memberDto;
    }

    @Transactional
    public void join(MemberDto memberDto) {

        // member check
        Member beforeMember = findMemberById(memberDto.getId());

        // dto => entity
        Member newMember = memberDto.toEntity(beforeMember);

        // TODO save가 아닌 update 형식으로 구현해야함
        memberRepository.save(newMember);
    }
    @Transactional
    public Member save(MemberSignUpDto memberSignUpDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberSignUpDto.setPassword(encoder.encode(memberSignUpDto.getPassword()));
        return memberRepository.save(memberSignUpDto.toEntity(memberSignUpDto));
    }
    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member id:" + memberId + " can't found."));
    }
    @Transactional
    public void update(MemberDto memberDto){
        Member beforeMember = findMemberById(memberDto.getId());
        Member newMember = memberDto.toEntity(beforeMember);
        memberRepository.save(newMember);
    }

}
