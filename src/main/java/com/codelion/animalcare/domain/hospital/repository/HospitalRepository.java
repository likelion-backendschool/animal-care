package com.codelion.animalcare.domain.hospital.repository;

import com.codelion.animalcare.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
