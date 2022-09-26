package com.codelion.animalcare.domain.appointment.service;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.AppointmentFormDto;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import com.codelion.animalcare.domain.appointment.AppointmentStatus;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import com.codelion.animalcare.global.util.OpeningHour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final MemberRepository memberRepository;
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;


    public List<LoadMyPageDoctorAppointment.ResponseDto> findAllByDoctorEmail(String email) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor " + email + "is not found."));

        List<Appointment> appointmentList = appointmentRepository.findAllByDoctorId(doctor.getId());

        List< LoadMyPageDoctorAppointment.ResponseDto> result = appointmentList.stream()
                .map(LoadMyPageDoctorAppointment.ResponseDto::new).toList();

        return result;
    }


//    public List<LoadMyPageDoctorAppointment.ResponseDto> findAppointmentByDoctorEmail(String email) {
//        Doctor doctor = doctorRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Doctor " + email + "is not found."));
//
//        List<Appointment> appointmentList = appointmentRepository.findAllByDoctorId(doctor.getId());
//
//        List< LoadMyPageDoctorAppointment.ResponseDto> result = appointmentList.stream()
//                .map(LoadMyPageDoctorAppointment.ResponseDto::new).toList();
//
//        return result;
//    }

    public List<Appointment> findByMemberId(long id) {
        return appointmentRepository.findByMemberId(id);
    }


    /**
     * 예약
     */
    @Transactional
    public Long appointment(MemberDto memberDto, Long animalDtoId, Long hospitalDtosId, Long doctorDtosId, LocalDateTime appointmentDate) {

        //엔티티 조회
        Member member = memberRepository.findById(memberDto.getId()).get();
        Animal animal = animalRepository.findById(animalDtoId).get();
        Hospital hospital = hospitalRepository.findById(hospitalDtosId).get();
        Doctor doctor = doctorRepository.findById(doctorDtosId).get();

        //예약 생성
        Appointment appointment = Appointment.createAppointment(member, animal, hospital, doctor, appointmentDate);

        appointmentRepository.save(appointment);

        return appointment.getId();
    }

    /**
     * 예약(메세지 추가)
     */
    @Transactional
    public Long appointment(MemberDto memberDto, AppointmentFormDto appointmentFormDto) {
        //엔티티 조회
        Member member = memberRepository.findById(memberDto.getId()).get();
        Animal animal = animalRepository.findById(appointmentFormDto.getAnimalId()).get();
        Hospital hospital = hospitalRepository.findById(appointmentFormDto.getHospitalId()).get();
        Doctor doctor = doctorRepository.findById(appointmentFormDto.getDoctorId()).get();

        /* 올바른 date인지 확인.*/
        LocalDateTime date = appointmentFormDto.getDateToLocalDateTime();
        // 1. 10분 단위로 예약 가능.
        if(date.getMinute() % 10 != 0) throw new RuntimeException("올바른 예약날짜 형식이 아닙니다.(10분 단위)");

        // 2. 병원 시간 안에 있는지
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        // 예약 날자의 병원 운영시간
        ////////////////////// 여기 체크
        String timeOfHospital = hospital.getOpeningHours().split("/")[dayOfWeek.getValue() -1];
        System.out.println(dayOfWeek.getValue() - 1);
        System.out.println(timeOfHospital);
        String[] times = timeOfHospital.split("~", 2);
        if(times[1].trim().equals("")) throw new RuntimeException("병원의 정기 휴무일 입니다.");
        System.out.println(times[1] + " " + times[1].trim().equals(""));

        // 2-1 병원 시작 시간보다 빠를 때.
        int[] openTime = Arrays.stream(times[0].split(":")).mapToInt(Integer::parseInt).toArray();
        if(openTime[0] > date.getHour() || (openTime[0] == date.getHour() && openTime[1] > date.getMinute()))
            throw new RuntimeException("병원 시작 시간보다 빠릅니다.");

        // 2-2 병원 마감 시간보다 느릴 때
        int[] closeTime = Arrays.stream(times[1].split(":")).mapToInt(Integer::parseInt).toArray();
        if(closeTime[0] < date.getHour() || (closeTime[0] == date.getHour() && closeTime[1] < date.getMinute()))
            throw new RuntimeException("병원 마감 시간보다 늦습니다.");

        // 3 병원에 예약한 사람이 있을 때
        Optional<Appointment> appointments =appointmentRepository.findOneByDateAndDoctorId(date, doctor.getId());
        if(appointments.isPresent()) throw new RuntimeException("예약이 마감되었습니다.");

        //예약 생성
        // TODO builder 숨기기
        Appointment appointment = Appointment.builder()
                .animal(animal)
                .member(member)
                .hospital(hospital)
                .doctor(doctor)
                .content(appointmentFormDto.getContent())
                .date(date)
                .status(AppointmentStatus.READY)
                .build();

        appointmentRepository.save(appointment);

        return appointment.getId();
    }


    /**
     * 예약내역에서 예약취소
     */
    @Transactional
    public void cancelAppointment(Long appointmentId) {
        //예약 엔티티 조회
        Appointment appointment = appointmentRepository.findById(appointmentId).get();

        //에약 취소
        appointment.cancel();
    }

    /**
     * 예약 거절
     * @param appointmentId 예약서id
     * @param status 상태
     */
    @Transactional
    public void updateAppointmentStatus(Long appointmentId, AppointmentStatus status) {
        // 예약 엔티티 조회
        Appointment appointment = appointmentRepository.getReferenceById(appointmentId);
        // 예약 변경.
        appointment.updateStatus(status);
    }



    /**
     * 예약내역에서 예약수정
     */
    @Transactional
    public Long updateAppointment(Long appointmentId, MemberDto memberDto, Long animalDtoId, Long hospitalDtosId, Long doctorDtosId, LocalDateTime appointmentDate) {

        //예약 엔티티 조회
        Appointment appointment = appointmentRepository.findById(appointmentId).get();

        //엔티티 조회
        Member member = memberRepository.findById(memberDto.getId()).get();
        Animal animal = animalRepository.findById(animalDtoId).get();
        Hospital hospital = hospitalRepository.findById(hospitalDtosId).get();
        Doctor doctor = doctorRepository.findById(doctorDtosId).get();

        //예약 수정
        appointment = appointment.updateAppointment(appointment, member, animal, hospital, doctor, appointmentDate);

        appointmentRepository.save(appointment);

        return appointment.getId();
    }


    public LoadMyPageDoctorAppointment.ResponseDto findById(long appointmentId) {
        Appointment appointment = appointmentRepository
                .findByIdWithMemberAndAnimalAndHospitalAndDoctor(appointmentId)
                        .orElseThrow(() -> new RuntimeException("Appointment id " + appointmentId + " is not found."));

        return new LoadMyPageDoctorAppointment.ResponseDto(appointment);
    }


    public Optional<AppointmentDto> findById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        Optional<AppointmentDto> appointmentDto = appointmentOptional.map(o -> new AppointmentDto(o));

        return appointmentDto;
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

}
