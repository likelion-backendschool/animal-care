package com.codelion.animalcare.domain.api;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.member.Address;
import com.codelion.animalcare.domain.member.MemberDto;
import com.codelion.animalcare.domain.member.entity.Member;
import com.codelion.animalcare.domain.member.repository.MemberRepository;
import com.codelion.animalcare.domain.member.repository.MemberRepositoryTmp;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

//    private final MemberRepository memberRepository;
    private final MemberRepositoryTmp memberRepositoryTmp;


    @GetMapping("/api/v3.1/members")
    public List<MemberDto> membersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit){

        List<Member> members = memberRepositoryTmp.findAll(offset, limit);

        List<MemberDto> result = members.stream()
                .map(o -> new MemberDto(o))
                .collect(Collectors.toList());

        return result;
    }


}
