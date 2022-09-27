package com.codelion.animalcare.domain.user.repository;

import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);

    Optional<Member> findOptionalByEmail(String email);

}
