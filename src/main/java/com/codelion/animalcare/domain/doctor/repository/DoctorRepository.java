package com.codelion.animalcare.domain.doctor.repository;

import com.codelion.animalcare.domain.doctor.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
