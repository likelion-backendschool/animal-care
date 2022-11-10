package com.codelion.animalcare.domain.appointment;

import com.codelion.animalcare.domain.appointment.dto.AppointmentFormDto;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.appointment.service.AppointmentCommandService;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class AppointmentCommandServiceTests {

    @Autowired
    private AppointmentCommandService appointmentCommandService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    void appointmentTest() throws Exception {

        //given
        Appointment appointment1 = appointmentRepository.findById(1L).get();

        //when


        //then
        assertThat(appointment1.getMember().getName()).isEqualTo("김멤버");
    }


    @Test
    void appointmentStatusTest() throws Exception {

        //given
        Appointment appointment1 = appointmentRepository.findById(1L).get();

        //when
        appointmentCommandService.updateAppointmentStatus(appointment1.getId(), AppointmentStatus.COMPLETE);

        //then
        assertThat(appointment1.getStatus()).isEqualTo(AppointmentStatus.COMPLETE);
    }


    @Test
    void appointmentCancelStatusTest() throws Exception {

        //given
        Appointment appointment1 = appointmentRepository.findById(1L).get();

        //when
        appointmentCommandService.updateAppointmentStatus(appointment1.getId(), AppointmentStatus.CANCEL);

        //then
        assertThat(appointment1.getStatus()).isEqualTo(AppointmentStatus.CANCEL);
    }


    /* 올바른 date인지 확인.*/
    // 1. 10분 단위로 예약 가능.
    @Test
    void appointmentCheckRightDate() throws Exception {

        //given
        AppointmentFormDto appointmentFormDto = new AppointmentFormDto();

        //when
        appointmentFormDto.setDate("2022-11-10T14:00:00");
        LocalDateTime date = LocalDateTime.parse(appointmentFormDto.getDate());


        //then
        assertThat(date.getMinute() % 10).isEqualTo(0);
    }



}
