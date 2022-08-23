package com.codelion.animalcare.domain.user.repository;

import com.codelion.animalcare.domain.user.entity.Admin;
import com.codelion.animalcare.domain.user.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
