package com.codelion.animalcare.domain.medicalAppointment;

import com.codelion.animalcare.api.MedicalAppointmentSimpleApiController;
import com.codelion.animalcare.domain.animal.Animal;
import com.codelion.animalcare.domain.animal.AnimalService;
import com.codelion.animalcare.domain.doctorTmp.Doctor;
import com.codelion.animalcare.domain.doctorTmp.DoctorService;
import com.codelion.animalcare.domain.hospitalTmp.Hospital;
import com.codelion.animalcare.domain.hospitalTmp.HospitalService;
import com.codelion.animalcare.domain.member.Member;
import com.codelion.animalcare.domain.member.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MedicalAppointmentController {

    private final MedicalAppointmentService medicalAppointmentService;
    private final MemberService memberService;
    private final AnimalService animalService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;



    // 예약하기 임시 만듦1
    @GetMapping("/medical-appointment")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Animal> animals = animalService.findAnimals();
        List<Hospital> hospitals = hospitalService.findHospitals();
        List<Doctor> doctors = doctorService.findDoctors();

        model.addAttribute("members", members);
        model.addAttribute("animals", animals);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("doctors", doctors);

        return "medicalAppointments/medicalAppointmentForm";
    }


    // 예약하기 임시 만듦2
    @PostMapping("/medical-appointment")
    public String medicalAppointment(@RequestParam("memberId") Long memberId,
                                     @RequestParam("animalId") Long animalId,
                                     @RequestParam("hospitalId") Long hospitalId,
                                     @RequestParam("doctorId") Long doctorId)
                                      {

        medicalAppointmentService.medicalAppointment(memberId, animalId, hospitalId, doctorId);
        return "redirect:/usr/mypage/member/medical-appoint/medical-appointment-info";
    }



    // 마이페이지 회원 예약정보
//    @GetMapping("/usr/mypage/member/medical-appoint/medical-appointment-info")
//    public String medicalAppointmentList(@ModelAttribute("medicalAppointmentSearch") MedicalAppointmentSearch medicalAppointmentSearch, Model model) {
//
//        List<MedicalAppointment> medicalAppointments = medicalAppointmentService.findMedicalAppointmentsBefore(medicalAppointmentSearch);
//
//        model.addAttribute("medicalAppointments", medicalAppointments);
//
//        return "medicalAppointments/medicalAppointmentList";
//    }

    // 마이페이지 회원 예약정보 Dto 사용
    @GetMapping("/usr/mypage/member/medical-appoint/medical-appointment-info")
    public String medicalAppointmentListUseDto(Model model) {

        List<MedicalAppointment> medicalAppointments = medicalAppointmentService.findMedicalAppointments();

        List<SimpleMedicalAppointmentDto> simpleMedicalAppointmentDtos = medicalAppointments.stream()
                .map(o -> new SimpleMedicalAppointmentDto(o))
                .collect(Collectors.toList());

        model.addAttribute("simpleMedicalAppointmentDtos", simpleMedicalAppointmentDtos);

        return "medicalAppointments/medicalAppointmentList";
    }

    @Data
    static class SimpleMedicalAppointmentDto {

        private Long medicalAppointmentId;
        private String memberName;
        private String animalName;
        private String hospitalName;
        private String doctorName;

        private LocalDateTime medicalAppointmentDate;
        private MedicalAppointmentStatus medicalAppointmentStatus;


        public SimpleMedicalAppointmentDto(MedicalAppointment medicalAppointment) {
            medicalAppointmentId = medicalAppointment.getId();
            memberName = medicalAppointment.getMember().getName();
            animalName = medicalAppointment.getAnimal().getName();
            hospitalName = medicalAppointment.getHospital().getName();
            doctorName = medicalAppointment.getDoctor().getName();
            medicalAppointmentStatus = medicalAppointment.getMedicalAppointmentStatus();
        }
    }



    // 마이페이지 회원 예약정보 취소
    @PostMapping("/usr/mypage/member/medical-appoint/medical-appointment-info/{medicalAppointmentId}/cancel")
    public String cancelMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId) {
        medicalAppointmentService.cancelMedicalAppointment(medicalAppointmentId);
        return "redirect:/usr/mypage/member/medical-appoint/medical-appointment-info";
    }

    // 마이페이지 회원 예약정보 수정
//    @GetMapping("/usr/mypage/member/medical-appoint/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId, Model model) {
//        Book item = (Book) itemService.findOne(medicalAppointmentId);
//
//        BookForm form = new BookForm();
//        form.setId(item.getId());
//        form.setName(item.getName());
//
//        model.addAttribute("form", form);
//        return "items/updateItemForm";
//    }
//
//    @PostMapping("/usr/mypage/member/medical-appoint/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable Long medicalAppointmentId, LocalDateTime date) {
//
//        medicalAppointmentService.updateMedicalAppointment(medicalAppointmentId, date);
//
//        return "redirect:/items";
//    }
}
