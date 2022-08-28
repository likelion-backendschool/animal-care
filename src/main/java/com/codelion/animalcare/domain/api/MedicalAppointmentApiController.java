package com.codelion.animalcare.domain.api;


import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.medical_appointment.repository.MedicalAppointmentRepository;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedicalAppointmentApiController {

    private final MedicalAppointmentRepository medicalAppointmentRepository;
    private final MemberService memberService;
    private final AnimalService animalService;


    @GetMapping("/api/medical-appointment-member")
    public List<Member> createMemberForm() {

        List<Member> members = memberService.findMembers();

        // 아직 선택하지 않았는데 임의로 선택해서 그런가? 그러면 포스트를 여러개로??
//        Member member = members.get(0);
//        Optional<Animal> animalByMemberId = animalService.findByMemberId(member.getId());
//        List<Animal> animals = animalService.findAnimals();


//        List<Hospital> hospitals = hospitalService.findHospitals();
//        List<Doctor> doctors = doctorService.findDoctors();


//        model.addAttribute("members", members);
//        model.addAttribute("animals", animals);
//        model.addAttribute("animals", animalByMemberId);
//        model.addAttribute("hospitals", hospitals);
//        model.addAttribute("doctors", doctors);
        return members;
    }

    @GetMapping("/api/medical-appointment-animal")
    public List<Animal> createAnimalForm(Model model) {

        List<Animal> animals = animalService.findAnimals();

        return animals;
    }


}
