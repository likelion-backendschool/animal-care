package com.codelion.animalcare.domain.member.repository;


import com.codelion.animalcare.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MemberRepositoryTmp {

    private final EntityManager em;

    public MemberRepositoryTmp(EntityManager em) {
        this.em = em;
    }

    public List<Member> findAll(int offset, int limit) {

        return em.createQuery(
                "select m from Member m", Member.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

}
