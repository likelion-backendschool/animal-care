package com.codelion.animalcare;


import com.codelion.animalcare.domain.animal.Animal;
import com.codelion.animalcare.domain.doctorTmp.Doctor;
import com.codelion.animalcare.domain.hospitalTmp.Hospital;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointment;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointmentStatus;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointmentRepository;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointmentService;
import com.codelion.animalcare.domain.member.Address;
import com.codelion.animalcare.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MedicalAppointmentServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    MedicalAppointmentService medicalAppointmentService;
    @Autowired
    MedicalAppointmentRepository medicalAppointmentRepository;

    @Test
    public void 예약취소() throws Exception {
        //given
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date personBirthDay1 = simpleDateFormat.parse("1111-01-01");
        Member member = createMember("brightGarden02@gmail.com", "1234", "이주원",
                personBirthDay1,
                "서울", "1", "1111",
                "010-1111-1111");
        em.persist(member);


        Date animalBirthDay1 = simpleDateFormat.parse("2022-01-01");
        Animal animal = createAnimal("댕댕이", animalBirthDay1, "410111111111111");
//        AnimalHealthStatus.valueOf("GOOD"));
        em.persist(animal);


        Date doctorBirthDay1 = simpleDateFormat.parse("1112-02-02");
        Doctor doctor = createDoctor("kimDoctor.gmail.com", "2222", "김닥터", animalBirthDay1, "외과", "동물을 사랑하는 수의사입니다.", "010-2222-2222");
        em.persist(doctor);

        Hospital hospital = createHospital("힘찬동물병원", "서울", "3", "3333", "02)1234-1234", "주중 10:00~20:00, 주말 10:00~16:00");
        em.persist(hospital);

        MedicalAppointment medicalAppointment = MedicalAppointment.createMedicalAppointment(member, animal, hospital, doctor);
        em.persist(medicalAppointment);
//        int orderCount = 2;


        Long medicalAppointmentId = medicalAppointmentService.medicalAppointment(member.getId(), animal.getId(), hospital.getId(), doctor.getId());

        //when
        medicalAppointmentService.cancelMedicalAppointment(medicalAppointmentId);

        //then
        MedicalAppointment getMedicalAppointment = medicalAppointmentRepository.findOne(medicalAppointmentId);

        assertEquals("주문 취소시 상태는 CANCEL 이다.", MedicalAppointmentStatus.CANCEL, getMedicalAppointment.getMedicalAppointmentStatus());

    }

    private Member createMember(String login_email, String login_pwd,
                                String name, Date birthday,
                                String city, String street, String zipcode,
                                String phone_num) {

        Member member = new Member();
        member.setLogin_email(login_email);
        member.setLogin_pwd(login_pwd);
        member.setName(name);
        member.setBirthday(birthday);
        member.setAddress(new Address(city, street, zipcode));
        member.setPhone_num(phone_num);
        return member;
    }

    private Animal createAnimal(String name, Date birthday,
                                String registration_num){
//                                AnimalHealthStatus animalHealthStatus)


        Animal animal = new Animal();
        animal.setName(name);
        animal.setBirthday(birthday);
        animal.setRegistration_num(registration_num);
//        animal.setAnimalHealthStatus(animalHealthStatus);
        return animal;
    }

    private Doctor createDoctor(String login_email, String login_pwd,
                                String name, Date birthday,
                                String major,
                                String introduce,
                                String phone_num) {

        Doctor doctor = new Doctor();
        doctor.setLogin_email(login_email);
        doctor.setLogin_pwd(login_pwd);
        doctor.setName(name);
        doctor.setBirthday(birthday);
        doctor.setMajor(major);
        doctor.setIntroduce(introduce);
        doctor.setPhone_num(phone_num);
        return doctor;
    }

    private Hospital createHospital(String name,
                                    String city, String street, String zipcode,
                                    String phone_num,
                                    String opening_hours) {

        Hospital hospital = new Hospital();
        hospital.setName(name);
        hospital.setAddress(new Address(city, street, zipcode));
        hospital.setPhone_num(phone_num);
        hospital.setOpening_hours(opening_hours);
        return hospital;
    }
}
