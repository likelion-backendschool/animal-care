package com.codelion.animalcare.domain.medical_appointment.repository;

import com.codelion.animalcare.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MedicalAppointmentRepositoryTmp {

    private final EntityManager em;

    public MedicalAppointmentRepositoryTmp(EntityManager em) {
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
