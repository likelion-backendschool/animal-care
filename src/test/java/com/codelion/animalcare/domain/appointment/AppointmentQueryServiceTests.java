package com.codelion.animalcare.domain.appointment;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.AppointmentModifyDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class AppointmentQueryServiceTests {

    @Autowired
    private AppointmentQueryService appointmentQueryService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MemberRepository memberRepository;



    /**
     * 비대면 진료
     * Doctor가 예약내역 확인
     */
    @Test
    void findAllAppointmentEntityListTest() throws Exception {

        //given
        Doctor doctor = doctorRepository.findById(5L).get();

        //when
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctor.getId());

        //then
        assertThat(appointments.size()).isEqualTo(9);
    }



    /**
     * 비대면 진료
     * Doctor가 예약내역 확인
     */
    @Test
    void findAllAppointmentDtoListTest() throws Exception {

        //given
        Doctor doctor = doctorRepository.findById(5L).get();

        //when
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctor.getId());
        List<AppointmentDto> appointmentDtos = appointments
                .stream()
                .map(o -> new AppointmentDto(o))
                .collect(Collectors.toList());

        //then
        assertThat(appointmentDtos.size()).isEqualTo(9);
    }



    /**
     * 비대면 진료
     * Doctor가 예약내역 확인
     */
    @Test
    void findAllAppointmentTest() throws Exception {

        //given
        Doctor doctor = doctorRepository.findById(5L).get();

        //when
        List<AppointmentDto> appointmentDtos = appointmentQueryService.findAllAppointment(doctor.getEmail());

        //then
        assertThat(appointmentDtos.size()).isEqualTo(9);
    }


    /**
     * DoctorMyPage
     * 환자 예약  관리
     */
    @Test
    void findAllByDoctorEmailTest() throws Exception {

        //given
        Doctor doctor = doctorRepository.findById(5L).get();

        //when
        List<LoadMyPageDoctorAppointment.ResponseDto> result = appointmentQueryService.findAllByDoctorEmail(doctor.getEmail());

        //then
        assertThat(result.size()).isEqualTo(9);
    }


    @Test
    void findDoctorTest() throws Exception {

        //given


        //when
        Doctor doctor = doctorRepository.findById(5L).get();


        //then
        assertThat(doctor.getName()).isEqualTo("김닥터");
    }

    @Test
    void findMemberTest() throws Exception {

        //given


        //when
        Member member = memberRepository.findById(1L).get();


        //then
        assertThat(member.getName()).isEqualTo("김멤버");
    }


    @Test
    void findAppointmentByIdTest() throws Exception {

        //given


        //when
        Appointment appointment = appointmentRepository.findByAppointmentId(1L).get();


        //then
        assertThat(appointment.getContent()).isEqualTo("기침을 많이 합니다");
    }

    @Test
    void findAppointmentDtoByIdTest() throws Exception {

        //given
        Appointment appointment = appointmentRepository.findByAppointmentId(1L).get();

        //when
        LoadMyPageDoctorAppointment.ResponseDto result = new LoadMyPageDoctorAppointment.ResponseDto(appointment);

        //then
        assertThat(result.getContent()).isEqualTo("기침을 많이 합니다");
    }


    @Test
    void findEntityAppointmentByIdTest() throws Exception {

        //given
        Appointment appointment = appointmentRepository.findByAppointmentId(1L).get();

        //when
        Appointment appointment1 = appointmentRepository
                .findByAppointmentId(appointment.getId())
                .orElseThrow(() -> new RuntimeException("Appointment id " + appointment.getId() + " is not found."));

        //then
        assertThat(appointment1.getContent()).isEqualTo("기침을 많이 합니다");
    }


    @Test
    void findDtoAppointmentByIdTest() throws Exception {

        //given
        Appointment appointment = appointmentRepository.findByAppointmentId(1L).get();

        //when
        Appointment appointment1 = appointmentRepository
                .findByAppointmentId(appointment.getId())
                .orElseThrow(() -> new RuntimeException("Appointment id " + appointment.getId() + " is not found."));

        LoadMyPageDoctorAppointment.ResponseDto result = new LoadMyPageDoctorAppointment.ResponseDto(appointment1);


        //then
        assertThat(result.getContent()).isEqualTo("기침을 많이 합니다");
    }



    @Test
    void findAppointmentModifyDtoById() throws Exception {

        //given
        Appointment appointment = appointmentRepository.findByAppointmentId(2L).get();

        //when
        AppointmentModifyDto appointmentModifyDto = appointmentQueryService.findAppointmentModifyDtoById(appointment.getId());

        //then
        assertThat(appointmentModifyDto.getDate().getHour()).isEqualTo(1);
        assertThat(appointmentModifyDto.getDate().getMinute()).isEqualTo(52);
    }


}
