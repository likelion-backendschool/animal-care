package com.codelion.animalcare.domain.member.repository;

import com.codelion.animalcare.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
