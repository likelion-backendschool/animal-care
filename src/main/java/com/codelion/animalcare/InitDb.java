package com.codelion.animalcare;

import com.codelion.animalcare.domain.animal.Animal;
import com.codelion.animalcare.domain.doctorTmp.Doctor;
import com.codelion.animalcare.domain.hospitalTmp.Hospital;
import com.codelion.animalcare.domain.medicalAppointment.MedicalAppointment;
import com.codelion.animalcare.domain.member.Address;
import com.codelion.animalcare.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 종 예약 2개
 * * 이명호
 * * 홍길동
 */
@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() throws ParseException {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() throws ParseException {
            System.out.println("Init1" + this.getClass());

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            Date personBirthDay1 = simpleDateFormat.parse("1111-01-01");
            Member member = createMember("leemyngho0@gmail.com", "1234", "이명호",
                    personBirthDay1,
                    "서울", "1", "1111",
                    "010-1111-1111");
            em.persist(member);


            Date animalBirthDay1 = simpleDateFormat.parse("2022-01-01");
            Animal animal = createAnimal("댕댕이", animalBirthDay1, "410111111111111");
//            , AnimalHealthStatus.valueOf("GOOD"));
            em.persist(animal);


            Date doctorBirthDay1 = simpleDateFormat.parse("1112-02-02");
            Doctor doctor = createDoctor("kimDoctor.gmail.com", "2222", "김닥터", animalBirthDay1, "외과", "동물을 사랑하는 수의사입니다.", "010-2222-2222");
            em.persist(doctor);


            Hospital hospital = createHospital("힘찬동물병원", "서울", "3", "3333", "02)1234-1234", "주중 10:00~20:00, 주말 10:00~16:00");
            em.persist(hospital);

            MedicalAppointment medicalAppointment = MedicalAppointment.createMedicalAppointment(member, animal, hospital, doctor);
            em.persist(medicalAppointment);

        }

        public void dbInit2() throws ParseException {
            System.out.println("Init2" + this.getClass());

            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            Date personBirthDay1 = simpleDateFormat.parse("1112-02-02");
            Member member = createMember("honggildong1@gmail.com", "1234", "홍길동",
                    personBirthDay1,
                    "경기도", "1", "1111",
                    "010-1111-1111");
            em.persist(member);


            Date animalBirthDay1 = simpleDateFormat.parse("2021-02-02");
            Animal animal = createAnimal("폴짝이", animalBirthDay1, "410111111111111");
//                    , AnimalHealthStatus.valueOf("GOOD"));
            em.persist(animal);


            Date doctorBirthDay1 = simpleDateFormat.parse("1112-02-02");
            Doctor doctor = createDoctor("ChoiDoctor.gmail.com", "2222", "최닥터", animalBirthDay1, "외과", "동물을 사랑하는 수의사입니다.", "010-2222-2222");
            em.persist(doctor);


            Hospital hospital = createHospital("관절전문동물병원", "경기도", "3", "3333", "02)1234-1234", "주중 10:00~20:00, 주말 10:00~16:00");
            em.persist(hospital);

            // 리스트로 만들어야할 듯
            // 계속 추가해야하니
            MedicalAppointment medicalAppointment = MedicalAppointment.createMedicalAppointment(member, animal, hospital, doctor);
            em.persist(medicalAppointment);

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
//                                    AnimalHealthStatus animalHealthStatus)


            Animal animal = new Animal();
            animal.setName(name);
            animal.setBirthday(birthday);
            animal.setRegistration_num(registration_num);
//            animal.setAnimalHealthStatus(animalHealthStatus);
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
}

