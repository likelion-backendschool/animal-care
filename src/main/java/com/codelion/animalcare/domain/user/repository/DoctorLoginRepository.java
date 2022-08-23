package com.codelion.animalcare.domain.user.repository;

import com.codelion.animalcare.domain.user.entity.Admin;
import com.codelion.animalcare.domain.user.entity.DoctorLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorLoginRepository extends JpaRepository<DoctorLogin, Long> {
}
