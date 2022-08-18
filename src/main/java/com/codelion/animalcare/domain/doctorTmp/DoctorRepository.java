package com.codelion.animalcare.domain.doctorTmp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 임시 만듦
 */
@Repository
@RequiredArgsConstructor
public class DoctorRepository {

    private final EntityManager em;

    public void save(Doctor doctor) {
        em.persist(doctor);
    }

    public Doctor findOne(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }

    public List<Doctor> findByName(String name) {
        return em.createQuery("select d from Doctor d where d.name = :name", Doctor.class)
                .setParameter("name", name)
                .getResultList();
    }
}
