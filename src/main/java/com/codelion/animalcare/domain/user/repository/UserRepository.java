package com.codelion.animalcare.domain.user.repository;


import com.codelion.animalcare.domain.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);


//    UserInfo findByDtype(String patient);
}
