package com.codelion.animalcare.domain.medical_appointment.repository;

import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {

    @Query("select ma from MedicalAppointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where d.id = :doctorId ")
    List<MedicalAppointment> findAllByDoctorId(@Param("doctorId") long doctorId);

    @Query("select ma from MedicalAppointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h")
    List<MedicalAppointment> findAllByMemberAndAnimalAndHospitalAndDoctor();


}
