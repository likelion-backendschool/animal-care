package com.codelion.animalcare.domain.animal.repository;
import com.codelion.animalcare.domain.animal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
