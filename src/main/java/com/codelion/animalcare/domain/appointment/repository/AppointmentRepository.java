package com.codelion.animalcare.domain.appointment.repository;

import com.codelion.animalcare.domain.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where d.id = :doctorId ")
    List<Appointment> findAllByDoctorId(@Param("doctorId") long doctorId);

    /**
     * Member가 MemberMyPage에서 예약내역 조회
     */
    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where m.id = :memberId")
    List<Appointment> findByMemberId(long memberId);


    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where ma.id = :appointmentId")
    Optional<Appointment> findByIdWithMemberAndAnimalAndHospitalAndDoctor(@Param("appointmentId") long appointmentId);


    /**
     * 비대면 진료시
     * Doctor가 예약내역 조회
     */
    @Query("select ma from Appointment ma join fetch  ma.member m join fetch  ma.animal a join fetch ma.doctor d join fetch ma.hospital h where d.id = :doctorId")
    List<Appointment> findByDoctorId(Long doctorId);


    @Query("select ma.date from Appointment ma where ma.doctor.id = :doctorId and :utcDateTimeFront <= ma.date and ma.date < :utcDateTimeEnd")
    List<LocalDateTime> findDateTimesByDateAndDoctor(
            @Param("utcDateTimeFront")LocalDateTime utcDateTimeFront,
            @Param("utcDateTimeEnd")LocalDateTime utcDateTimeEnd,
            @Param("doctorId") long doctorId);

    Optional<Appointment> findOneByDateAndDoctorId(LocalDateTime date, Long doctorId);
}
