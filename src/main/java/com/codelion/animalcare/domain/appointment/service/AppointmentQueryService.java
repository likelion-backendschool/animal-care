package com.codelion.animalcare.domain.appointment.service;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.AppointmentModifyDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;
    private final MemberRepository memberRepository;
    private final DoctorRepository doctorRepository;


    /**
     * 비대면 진료
     * Doctor가 예약내역 확인
     */
    public List<AppointmentDto> findAllAppointment(String email) {

        Doctor doctor = findDoctor(email);
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctor.getId());
        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }


    /**
     * DoctorMyPage
     * 환자 예약  관리
     */
    public List<LoadMyPageDoctorAppointment.ResponseDto> findAllByDoctorEmail(String email) {

        Doctor doctor = findDoctor(email);

        List<Appointment> appointmentList = appointmentRepository.findByDoctorId(doctor.getId());

        List< LoadMyPageDoctorAppointment.ResponseDto> result = appointmentList.stream()
                .map(LoadMyPageDoctorAppointment.ResponseDto::new).toList();

        return result;
    }

    /**
     * 의사가 해당 날짜에 예약 되어있는 시간을 출력함.
     * @param date
     * @param doctorId
     * @return
     */
    public List<LocalDateTime> findDateTimesByDateAndDoctor(LocalDate date, Long doctorId){
        // UTC로 검색하기 위해 Java.sql.Date 대신 LocalDate 사용
        LocalDateTime utcDateTimeFront = date.atStartOfDay();
        LocalDateTime utcDateTimeEnd = date.atStartOfDay().plusDays(1);
        System.out.println(utcDateTimeFront + " " + utcDateTimeEnd);
        return appointmentRepository.findDateTimesByDateAndDoctor(utcDateTimeFront, utcDateTimeEnd, doctorId);
    }




    public List<AppointmentDto> findAppointmentByEmail(String email) {

        Member member = findMember(email);

        List<Appointment> appointments = appointmentRepository.findByMemberId(member.getId());

        List<AppointmentDto> appointmentDtos = appointments.stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        return appointmentDtos;
    }


    private Doctor findDoctor(String email) {
        return doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor " + email + "is not found."));
    }

    private Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member " + email + "is not found."));
    }


    public LoadMyPageDoctorAppointment.ResponseDto findById(long appointmentId) {
        Appointment appointment = appointmentRepository
                .findByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment id " + appointmentId + " is not found."));

        return new LoadMyPageDoctorAppointment.ResponseDto(appointment);
    }

    public AppointmentModifyDto findAppointmentModifyDtoById(Long appointmentId) {

        Optional<Appointment> appointmentOptional = appointmentRepository.findByAppointmentId(appointmentId);
        return appointmentOptional
                .map(o -> new AppointmentModifyDto(o))
                .orElseThrow(() -> new RuntimeException("Appointment id " + appointmentId + " was not found."));
    }
}
