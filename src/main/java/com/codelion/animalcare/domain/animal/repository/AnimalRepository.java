package com.codelion.animalcare.domain.animal.repository;
import com.codelion.animalcare.domain.animal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByMemberId(Long id);

    List<Animal> findListAnimalByMemberId(Long memberId);
    List<Animal> findByMemberEmail(String email);
}
