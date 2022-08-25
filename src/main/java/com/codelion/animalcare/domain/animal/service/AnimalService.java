package com.codelion.animalcare.domain.animal.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.doctor.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    /**
     * 애완 동물 등록
     */
    @Transactional
    public Long join(Animal animal) {

        validateDuplicateAnimal(animal); //중복 애완동물 검증
        animalRepository.save(animal);
        return animal.getId();
    }

    private void validateDuplicateAnimal(Animal animal) {
        List<Animal> findAnimals = animalRepository.findAll();
        if (!findAnimals.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 애완동물입니다.");
        }
    }

    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    public Optional<Animal> findByMemberId(Long id) {
        return animalRepository.findByMember(id);
    }


    //애완동물 전체 조회
    public List<Animal> findAnimals() {
        return animalRepository.findAll();
    }

}
