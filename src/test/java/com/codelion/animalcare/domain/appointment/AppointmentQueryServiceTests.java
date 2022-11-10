package com.codelion.animalcare.domain.appointment;

import com.codelion.animalcare.domain.appointment.dto.AppointmentDto;
import com.codelion.animalcare.domain.appointment.dto.LoadMyPageDoctorAppointment;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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


}
