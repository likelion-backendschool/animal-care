package com.codelion.animalcare.domain.user.service;

import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
