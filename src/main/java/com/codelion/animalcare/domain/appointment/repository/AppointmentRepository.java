package com.codelion.animalcare.domain.appointment.repository;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where d.id = :doctorId ")
    List<Appointment> findAllByDoctorId(@Param("doctorId") long doctorId);

    List<Appointment> findByMemberId(long memberId);

    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h left join fetch ma.diagnosis where ma.id = :appointmentId")
    Optional<Appointment> findByIdWithMemberAndAnimalAndHospitalAndDoctorAndDiagnosis(@Param("appointmentId") long appointmentId);

}
