package com.codelion.animalcare.domain.diagnosis.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
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

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;

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
    public Long diagnosis(FindOneDiagnosis newForm) {

//        //엔티티 조회
//        Member member = memberRepository.findById(memberDto.getId()).get();
//        Animal animal = animalRepository.findById(animalDtoId).get();
//        Hospital hospital = hospitalRepository.findById(hospitalDtosId).get();
//        Doctor doctor = doctorRepository.findById(doctorDtosId).get();

        //진단서 생성
        Diagnosis diagnosis = Diagnosis.createDiagnosis(newForm);

        diagnosisRepository.save(diagnosis);

        return diagnosis.getId();
    }
}
