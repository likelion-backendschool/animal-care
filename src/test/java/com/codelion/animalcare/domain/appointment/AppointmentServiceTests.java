package com.codelion.animalcare.domain.appointment;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class AppointmentServiceTests {

    @Autowired
    EntityManager em;

    @Autowired
    private AppointmentQueryService appointmentQueryService;

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


}
