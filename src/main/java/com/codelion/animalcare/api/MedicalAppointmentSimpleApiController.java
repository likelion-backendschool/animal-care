package com.codelion.animalcare.api;


import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointment;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointmentStatus;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointmentSearch;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointmentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * xToOne(ManyToOne)
 * MedicalAppointment
 * MedicalAppointment -> Member
 * MedicalAppointment -> Animal
 * MedicalAppointment -> Doctor
 */

@RestController
@RequiredArgsConstructor
public class MedicalAppointmentSimpleApiController {


    private final MedicalAppointmentRepository medicalAppointmentRepository;


    @GetMapping("/api/v1/simple-medicalAppointments")
    public List<MedicalAppointment> medicalAppointmentsV1() {

        List<MedicalAppointment> all = medicalAppointmentRepository.findAllByString(new MedicalAppointmentSearch());
        for (MedicalAppointment medicalAppointment : all) {
            medicalAppointment.getMember().getName(); // Lazy 강제 초기화
            medicalAppointment.getAnimal().getName(); // Lazy 강제 초기화
            medicalAppointment.getDoctor().getName(); // Lazy 강제 초기화
        }

        return all;
    }

    @GetMapping("/api/v2/simple-medicalAppointments")
    public List<SimpleMedicalAppointmentDto> medicalAppointmentsV2() {

        List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findAllByString(new MedicalAppointmentSearch());
        List<SimpleMedicalAppointmentDto> result = medicalAppointments.stream()
                .map(o -> new SimpleMedicalAppointmentDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v3/simple-medicalAppointments")
    public List<SimpleMedicalAppointmentDto> medicalAppointmentsV3() {
        List<MedicalAppointment> medicalAppointments = medicalAppointmentRepository.findAllWithMemberAnimalDoctorHospital();


        List<SimpleMedicalAppointmentDto> result = medicalAppointments.stream()
                .map(o -> new SimpleMedicalAppointmentDto(o))
                .collect(Collectors.toList());
        return result;
    }


    @Data
    static class SimpleMedicalAppointmentDto {

        private Long medicalAppointmentId;
        private String memberName;
        private String animalName;

        private LocalDateTime medicalAppointmentDate;
        private MedicalAppointmentStatus medicalAppointmentStatus;


        public SimpleMedicalAppointmentDto(MedicalAppointment medicalAppointment) {
            medicalAppointmentId = medicalAppointment.getId();
            memberName = medicalAppointment.getMember().getName();
            animalName = medicalAppointment.getAnimal().getName();
            medicalAppointmentStatus = medicalAppointment.getMedicalAppointmentStatus();
        }
    }
}
