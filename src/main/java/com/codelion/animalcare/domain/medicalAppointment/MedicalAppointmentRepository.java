package com.codelion.animalcare.domain.medicalAppointment;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class MedicalAppointmentRepository {

    private final EntityManager em;

    public MedicalAppointmentRepository(EntityManager em) {
        this.em = em;
    }

    public List<MedicalAppointment> findAllByString(MedicalAppointmentSearch medicalAppointmentSearch) {

        String jpql = "select ma from MedicalAppointment ma join ma.member m";
        boolean isFirstCondition = true;

        //예약 상태 검색
        if (medicalAppointmentSearch.getMedicalAppointmentStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " ma.status = :status";
        }

        //회원 이름 검색
        if (StringUtils.hasText(medicalAppointmentSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<MedicalAppointment> query = em.createQuery(jpql, MedicalAppointment.class)
                .setMaxResults(1000);

        if (medicalAppointmentSearch.getMedicalAppointmentStatus() != null) {
            query = query.setParameter("status", medicalAppointmentSearch.getMedicalAppointmentStatus());
        }
        if (StringUtils.hasText(medicalAppointmentSearch.getMemberName())) {
            query = query.setParameter("name", medicalAppointmentSearch.getMemberName());
        }

        return query.getResultList();

    }

    public void save(MedicalAppointment medicalAppointment) {
        em.persist(medicalAppointment);
    }

    public MedicalAppointment findOne(Long id) {
        return em.find(MedicalAppointment.class, id);
    }

    public List<MedicalAppointment> findAllWithMemberAnimalDoctorHospital() {
        return em.createQuery(
                "select ma from MedicalAppointment ma" +
                        " join fetch  ma.member m" +
                        " join fetch  ma.animal a" +
                        " join fetch ma.doctor d" +
                        " join fetch ma.hospital h", MedicalAppointment.class
        ).getResultList();
    }
}
