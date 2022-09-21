package com.codelion.animalcare.domain.animal.service;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    private final UserService userService;
    /**
     * 애완 동물 등록
     */


    public void save(AnimalDto animalDto, Principal principal) {
        Member member = userService.getMember(principal.getName());

        return animalRepository.save(animalDto.toEntity(member).getId());
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

    //memberEmail로 member가 갖고있는 동물 출력
    public List<Animal> findByMemberEmail(String email){
        return animalRepository.findByMemberEmail(email);
    }


    //애완동물 전체 조회
    public List<Animal> findAnimals() {
        return animalRepository.findAll();
    }


    public List<AnimalDto> findByMember(MemberDto memberDto) {
        List<Animal> animals = animalRepository.findByMemberEmail(memberDto.getEmail());
        List<AnimalDto> result = animals.stream()
                .map(o -> new AnimalDto(o))
                .collect(Collectors.toList());

        return result;
    }


}
