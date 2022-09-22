package com.codelion.animalcare.domain.appointment.repository;

import com.codelion.animalcare.domain.appointment.AppointmentSearch;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AppointmentRepositoryImpl {

    private final EntityManager em;

    public AppointmentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public List<Appointment> findAllByString(AppointmentSearch appointmentSearch) {

//        String jpql = "select o from Appointment o join o.member m";
        String jpql = "select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h";
        boolean isFirstCondition = true;

        //예약 상태 검색
        if (appointmentSearch.getStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //멤버 이름 검색
        if (StringUtils.hasText(appointmentSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Appointment> query = em.createQuery(jpql, Appointment.class)
                .setMaxResults(1000);

        if (appointmentSearch.getStatus() != null) {
            query = query.setParameter("status", appointmentSearch.getStatus());
        }
        if (StringUtils.hasText(appointmentSearch.getMemberName())) {
            query = query.setParameter("name", appointmentSearch.getMemberName());
        }

        return query.getResultList();
    }
}
