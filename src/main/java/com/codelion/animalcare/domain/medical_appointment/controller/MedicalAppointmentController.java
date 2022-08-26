package com.codelion.animalcare.domain.medical_appointment.controller;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.doctor.service.DoctorService;
import com.codelion.animalcare.domain.hospital.service.HospitalService;
import com.codelion.animalcare.domain.medical_appointment.MedicalAppointmentStatus;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentQueryService;
import com.codelion.animalcare.domain.medical_appointment.service.MedicalAppointmentService;
import com.codelion.animalcare.domain.member.MemberDto;
import com.codelion.animalcare.domain.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MedicalAppointmentController {

    private final MedicalAppointmentQueryService medicalAppointmentQueryService;
    private final MedicalAppointmentService medicalAppointmentService;
    private final MemberService memberService;
    private final AnimalService animalService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;


    // 예약하기 임시 만듦1
    @GetMapping("/medical-appointment-member")
    public String createMemberForm(Model model,
                                   @RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "limit", defaultValue = "100") int limit) {

//        List<Member> members = memberService.findMembers();
        List<MemberDto> members = medicalAppointmentQueryService.findMembers(offset, limit);

                List<Animal> animals = animalService.findAnimals();
//        List<Hospital> hospitals = hospitalService.findHospitals();
//        List<Doctor> doctors = doctorService.findDoctors();


        model.addAttribute("members", members);
        model.addAttribute("animals", animals);
//        model.addAttribute("animals", animalByMemberId);
//        model.addAttribute("hospitals", hospitals);
//        model.addAttribute("doctors", doctors);

        return "medicalAppointments/medicalAppointmentMemberForm";
    }


    // 예약하기 임시 만듦2
    @PostMapping("/medical-appointment")
    public String medicalAppointment(@RequestParam("memberId") Long memberId,
                                     @RequestParam("animalId") Long animalId,
                                     @RequestParam("hospitalId") Long hospitalId,
                                     @RequestParam("doctorId") Long doctorId)
                                      {

        medicalAppointmentService.medicalAppointment(memberId, animalId, hospitalId, doctorId);
        return "redirect:/usr/mypage/member/{memberId}/medical-appointment/{medicalAppointmentId}/medical-appointment-info";
    }



    // 마이페이지 회원 예약내역
//    @GetMapping("/usr/mypage/member/medical-appoint/medical-appointment-info")
//    public String medicalAppointmentList(@ModelAttribute("medicalAppointmentSearch") MedicalAppointmentSearch medicalAppointmentSearch, Model model) {
//
//        List<MedicalAppointment> medicalAppointments = medicalAppointmentService.findMedicalAppointmentsOld(medicalAppointmentSearch);
//
//        model.addAttribute("medicalAppointments", medicalAppointments);
//
//        return "medicalAppointments/medicalAppointmentList";
//    }


    // 마이페이지 회원 예약내역 Dto 사용
    @GetMapping("/usr/mypage/member/{memberId}/medical-appointment/{medicalAppointmentId}/medical-appointment-info")
    public String medicalAppointmentListUseDto(Model model) {

        List<MedicalAppointment> medicalAppointments = medicalAppointmentQueryService.findMedicalAppointments();


        List<MedicalAppointmentDto> simpleMedicalAppointmentDtos = medicalAppointments.stream()
                .map(o -> new MedicalAppointmentDto(o))
                .collect(Collectors.toList());

        model.addAttribute("simpleMedicalAppointmentDtos", simpleMedicalAppointmentDtos);

        return "medicalAppointments/medicalAppointmentList";
    }



    @Getter
    static class MedicalAppointmentDto {

        private Long medicalAppointmentId;
        private String memberName;
        private String animalName;
        private String hospitalName;
        private String doctorName;

        private LocalDateTime medicalAppointmentDate;
        private MedicalAppointmentStatus medicalAppointmentStatus;


        public MedicalAppointmentDto(MedicalAppointment medicalAppointment) {
            medicalAppointmentId = medicalAppointment.getId();
            memberName = medicalAppointment.getMember().getName();
            animalName = medicalAppointment.getAnimal().getName();
            hospitalName = medicalAppointment.getHospital().getName();
            doctorName = medicalAppointment.getDoctor().getName();
            medicalAppointmentDate = medicalAppointment.getMedicalAppointmentDate();
            medicalAppointmentStatus = medicalAppointment.getMedicalAppointmentStatus();
        }
    }

    // 마이페이지 회원 예약정보 취소
    @PostMapping("/usr/mypage/member/{memberId}/medical-appointment/medical-appointment-info/{medicalAppointmentId}/cancel")
    public String cancelMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId) {
        medicalAppointmentService.cancelMedicalAppointment(medicalAppointmentId);
        return "redirect:/usr/mypage/member/{memberId}/medical-appointment/medical-appointment-info";
    }


    // 마이페이지 회원 예약정보 수정
//    @GetMapping("/usr/mypage/member/{memberId}/medical-appointment/{medicalAppointmentId}/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable("medicalAppointmentId") Long medicalAppointmentId, Model model) {
//        return "redirect:/usr/mypage/member/medical-appoint/medical-appointment-info";
//    }
//
//    @PostMapping("/usr/mypage/member/medical-appoint/medical-appointment-info/{medicalAppointmentId}/edit")
//    public String updateMedicalAppointment(@PathVariable Long medicalAppointmentId, LocalDateTime date) {
//
//        medicalAppointmentService.updateMedicalAppointment(medicalAppointmentId, date);
//
//        return "redirect:/usr/mypage/member/medical-appoint/medical-appointment-info";
//    }
}