package com.codelion.animalcare.domain.appointment.repository;

import com.codelion.animalcare.domain.appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    /**
     * 1. 비대면 진료시 Doctor가 예약내역 조회
     * 2. DoctorMyPage 환자 예약  관리
     */
    @Query("select ap from Appointment ap join fetch  ap.member m join fetch  ap.animal a join fetch ap.doctor d join fetch ap.hospital h where d.id = :doctorId ")
    List<Appointment> findByDoctorId(@Param("doctorId") long doctorId);

    /**
     * Member가 MemberMyPage에서 예약내역 조회
     */
    @Query("select ap from Appointment ap join fetch  ap.member m join fetch  ap.animal a join fetch ap.doctor d join fetch ap.hospital h where m.id = :memberId")
    List<Appointment> findByMemberId(@Param("memberId") long memberId);


    @Query("select ap from Appointment ap join fetch  ap.member m join fetch  ap.animal a join fetch ap.doctor d join fetch ap.hospital h where ap.id = :appointmentId")
    Optional<Appointment> findByAppointmentId(@Param("appointmentId") long appointmentId);


    @Query("select ap.date from Appointment ap where ap.doctor.id = :doctorId and :utcDateTimeFront <= ap.date and ap.date < :utcDateTimeEnd")
    List<LocalDateTime> findDateTimesByDateAndDoctor(
            @Param("utcDateTimeFront")LocalDateTime utcDateTimeFront,
            @Param("utcDateTimeEnd")LocalDateTime utcDateTimeEnd,
            @Param("doctorId") long doctorId);

    Optional<Appointment> findOneByDateAndDoctorId(LocalDateTime date, Long doctorId);
}
