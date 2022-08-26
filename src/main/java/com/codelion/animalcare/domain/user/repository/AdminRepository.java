package com.codelion.animalcare.domain.user.repository;

import com.codelion.animalcare.domain.user.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
