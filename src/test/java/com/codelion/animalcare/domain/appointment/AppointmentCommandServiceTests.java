package com.codelion.animalcare.domain.appointment;

import com.codelion.animalcare.domain.appointment.dto.AppointmentFormDto;
import com.codelion.animalcare.domain.appointment.entity.Appointment;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.appointment.service.AppointmentCommandService;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class AppointmentCommandServiceTests {

    @Autowired
    private AppointmentCommandService appointmentCommandService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DoctorRepository doctorRepository;


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


    // 2. 병원 시간 안에 있는지, 예약 날짜의 병원 운영시간
    @Test
    void appointmentCheckHospitalTime() throws Exception {

        //given
        AppointmentFormDto appointmentFormDto = new AppointmentFormDto();
        appointmentFormDto.setDate("2022-11-07T14:10:00");
        String inputDate = appointmentFormDto.getDate();
        LocalDateTime date = LocalDateTime.parse(inputDate);

        Hospital hospital = hospitalRepository.findById(1L).get();


        //when
        String inputDateHourMinute = date.getHour() + ":" + date.getMinute();

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String timeOfHospital = hospital.getOpeningHours().split("/")[dayOfWeek.getValue() -1];
        String[] times = timeOfHospital.split("~", 2);


        //then
        assertThat(inputDateHourMinute).isBetween(times[0], times[1]);
    }


    // 3. 병원에 예약한 사람이 있을 때
    @Test
    void appointmentWhenAlreadyAppointment() throws Exception {

        //given
        Doctor doctor = doctorRepository.findById(6L).get();
        AppointmentFormDto appointmentFormDto = new AppointmentFormDto();
        appointmentFormDto.setDate("2022-10-03T10:30:00");
        String inputDate = appointmentFormDto.getDate();
        LocalDateTime date = LocalDateTime.parse(inputDate);


        //when
        Optional<Appointment> appointment = appointmentRepository.findOneByDateAndDoctorId(date, doctor.getId());


        //then
        assertThat(appointment.isPresent()).isTrue();
    }

}
