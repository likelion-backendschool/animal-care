package com.codelion.animalcare.domain.diagnosis;

import com.codelion.animalcare.domain.animal.AnimalService;
import com.codelion.animalcare.domain.doctorTmp.DoctorService;
import com.codelion.animalcare.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final MemberService memberService;
    private final AnimalService animalService;
    private final DoctorService doctorService;

    // 진료하기 임시 만듦1
//    @GetMapping("/diagnosis")
//    public String createForm(Model model) {
//
//        List<Member> members = memberService.findMembers();
//        List<Animal> animals = animalService.findAnimals();
//        List<Doctor> doctors = doctorService.findDoctors();
//
//        model.addAttribute("members", members);
//        model.addAttribute("animals", animals);
//        model.addAttribute("doctors", doctors);
//
//        return "diagnosis/diagnosisForm";
//    }


    // 진료하기 임시 만듦2
//    @PostMapping("/diagnosis")
//    public String diagnosis(@RequestParam("memberId") Long memberId,
//                                     @RequestParam("animalId") Long animalId,
//                                     @RequestParam("doctorId") Long doctorId)
//    {
//
//        diagnosisService.diagnosis(memberId, animalId, doctorId);
//        return "redirect:/usr/mypage/member/diagnosis-info";
//    }


    // 마이페이지 회원 진료정보
    @GetMapping("/usr/mypage/member/diagnosis-info")
    public String showDiagnosis(@ModelAttribute("diagnosisSearch") DiagnosisSearch diagnosisSearch, Model model) {

        List<Diagnosis> diagnoses = diagnosisService.findDiagnoses(diagnosisSearch);
        model.addAttribute("diagnoses", diagnoses);

        return "diagnosis/diagnosisList";
    }

}
