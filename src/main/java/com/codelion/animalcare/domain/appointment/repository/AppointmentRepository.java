package com.codelion.animalcare.domain.appointment.repository;

import com.codelion.animalcare.domain.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where d.id = :doctorId ")
    List<Appointment> findAllByDoctorId(@Param("doctorId") long doctorId);


    //TODO 쿼리 수정 구현 필요. 병원 같은 경우 여러개 쿼리를 한번에 조회하도록 구현해야함
    List<Appointment> findByMemberId(long memberId);

    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where ma.id = :appointmentId")
    Optional<Appointment> findByIdWithMemberAndAnimalAndHospitalAndDoctor(@Param("appointmentId") long appointmentId);

    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where d.id = :doctorId")
    List<Appointment> findByDoctorId(Long doctorId);

}
