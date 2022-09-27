package com.codelion.animalcare.domain.diagnosis.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.diagnosis.DiagnosisSearch;
import com.codelion.animalcare.domain.diagnosis.dto.FindOneDiagnosis;
import com.codelion.animalcare.domain.diagnosis.entity.Diagnosis;
import com.codelion.animalcare.domain.diagnosis.repository.DiagnosisRepository;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final AppointmentRepository appointmentRepository;

    public List<Diagnosis> findDiagnoses(DiagnosisSearch diagnosisSearch) {
        return diagnosisRepository.findAll();
    }

    /**
     * 예약서로 진단서 찾기
     * @param appointmentId
     * @return 존재한다면 FindOneDiagnosis로 출력. 없다면 비어있는 FindOneDiagnosis로 출력.
     */
    public FindOneDiagnosis findByAppointmentId(Long appointmentId){
         Optional<Diagnosis> diagnosisOptional = diagnosisRepository.findByAppointmentId(appointmentId);
         return diagnosisOptional.map(FindOneDiagnosis::new).orElse(null);
    }

    @Transactional
    public Long diagnosis(LoadMyPageDoctorAppointment.ResponseDto appointmentDto, FindOneDiagnosis writtenDiagnosisForm) {

        //엔티티 조회
        Member member = appointmentDto.getMember();
        Animal animal = appointmentDto.getAnimal();
        Hospital hospital = appointmentDto.getHospital();
        Doctor doctor = appointmentDto.getDoctor();

        //수정 구현 예정
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentDto.getId());
        Appointment appointment = appointmentOptional.get();

        //진단서 생성
        Diagnosis diagnosis = Diagnosis.createDiagnosis(member, animal, hospital, doctor, appointment, writtenDiagnosisForm);

        diagnosisRepository.save(diagnosis);

        return diagnosis.getId();
    }
}
