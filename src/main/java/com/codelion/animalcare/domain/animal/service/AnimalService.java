package com.codelion.animalcare.domain.animal.service;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    private final MemberService memberService;
    /**
     * 애완 동물 등록
     */

    public Long save(AnimalDto animalDto, Principal principal) {
        Member member = memberService.findMemberByEmail(principal.getName());
        return animalRepository.save(animalDto.toEntity(member)).getId();
    }

    public Long update(Long id, AnimalDto animalDto){
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("질문이 존재하지 않습니다."));
        animal.update(animalDto);
        System.out.println(animal.getName());
        animalRepository.save(animal);
        return id;
    }

    private void validateDuplicateAnimal(Animal animal) {
        List<Animal> findAnimals = animalRepository.findAll();
        if (!findAnimals.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 애완동물입니다.");
        }
    }

    public AnimalDto findById(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isEmpty()){
            throw new IllegalArgumentException("animal is null");
        }

        return new AnimalDto(animal.get());
    }

    //memberEmail로 member가 갖고있는 동물 출력
    public List<Animal> findByMemberEmail(String email){
        return animalRepository.findByMemberEmail(email);
    }


    //애완동물 전체 조회
    public List<Animal> findAnimals() {
        return animalRepository.findAll();
    }


    public List<AnimalDto> findByMember(String email) {
        List<Animal> animals = animalRepository.findByMemberEmail(email);
        List<AnimalDto> result = animals.stream()
                .map(o -> new AnimalDto(o))
                .collect(Collectors.toList());
        return result;
    }
    public boolean questionAuthorized(Long id, Principal principal){
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("작성된 글이 없습니다."));


        if(animal.getMember().getEmail().equals(principal.getName())) {
            return false;
        }

        return true;

    }
    public void delete(Long id){
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("작성된 글이 없습니다."));

        animalRepository.delete(animal);
    }


}
