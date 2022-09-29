//package com.codelion.animalcare;

//import com.codelion.animalcare.domain.animal.entity.Animal;
//import com.codelion.animalcare.domain.doctor.entity.Doctor;
//import com.codelion.animalcare.domain.hospital.entity.Hospital;
//import com.codelion.animalcare.domain.medical_appointment.entity.MedicalAppointment;
//import com.codelion.animalcare.domain.member.Address;
//import com.codelion.animalcare.domain.member.entity.Member;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.sql.Date;
//
///**
// * 종 예약 2개
// * * 이명호
// * * 홍길동
// */
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() throws ParseException {
//        initService.dbInit1();
//        initService.dbInit2();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final EntityManager em;
//
//        public void dbInit1() throws ParseException {
//            System.out.println("Init1" + this.getClass());
//
//            Date memberBirthDay1 = transformDate("20030416");
//
//            Member member = createMember("leemyngho0@gmail.com", "1234", "이명호",
//                    memberBirthDay1,
//                    "서울", "1", "1111",
//                    "010-1111-1111");
//            em.persist(member);
//
//
//            Date animalBirthDay1 = transformDate("20200925");
//            Animal animal = createAnimal("댕댕이", animalBirthDay1, "410111111111111");
////            , AnimalHealthStatus.valueOf("GOOD"));
//            em.persist(animal);
//
//
//            Date doctorBirthDay1 = transformDate("19851012");
//            Doctor doctor = createDoctor("kimDoctor.gmail.com", "2222", "김닥터", doctorBirthDay1, "외과", "동물을 사랑하는 수의사입니다.", "010-2222-2222");
//            em.persist(doctor);
//
//
//            Hospital hospital = createHospital("힘찬동물병원", "서울", "3", "3333", "02)1234-1234", "주중 10:00~20:00, 주말 10:00~16:00");
//            em.persist(hospital);
//
//            MedicalAppointment medicalAppointment = MedicalAppointment.createMedicalAppointment(member, animal, hospital, doctor);
//            em.persist(medicalAppointment);
//
//        }
//
//        public void dbInit2() throws ParseException {
//            System.out.println("Init2" + this.getClass());
//
//
//            Date memberBirthDay2 = transformDate("19990201");
//
//            Member member = createMember("honggildong1@gmail.com", "1234", "홍길동",
//                    memberBirthDay2,
//                    "경기도", "1", "1111",
//                    "010-1111-1111");
//            em.persist(member);
//
//
//            Date animalBirthDay2 = transformDate("20200110");
//            Animal animal = createAnimal("폴짝이", animalBirthDay2, "410111111111111");
////                    , AnimalHealthStatus.valueOf("GOOD"));
//            em.persist(animal);
//
//
//            Date doctorBirthDay2 = transformDate("19800506");
//            Doctor doctor = createDoctor("ChoiDoctor.gmail.com", "2222", "최닥터", doctorBirthDay2, "외과", "동물을 사랑하는 수의사입니다.", "010-2222-2222");
//            em.persist(doctor);
//
//            Hospital hospital = createHospital("관절전문동물병원", "경기도", "3", "3333", "02)1234-1234", "주중 10:00~20:00, 주말 10:00~16:00");
//            em.persist(hospital);
//
//            // 리스트로 만들어야할 듯
//            // 계속 추가해야하니
//            MedicalAppointment medicalAppointment = MedicalAppointment.createMedicalAppointment(member, animal, hospital, doctor);
//            em.persist(medicalAppointment);
//
//        }
//
//        private Member createMember(String login_email, String login_pwd,
//                                    String name, Date birthday,
//                                    String city, String street, String zipcode,
//                                    String phone_num) {
//
//
//            Member member = new Member();
//            member.setLoginEmail(login_email);
//            member.setLoginPwd(login_pwd);
//            member.setName(name);
//            member.setBirthday((java.sql.Date) birthday);
//            member.setAddress(new Address(city, street, zipcode));
//            member.setPhoneNum(phone_num);
//            return member;
//        }
//
//
//        private Animal createAnimal(String name, Date birthday,
//                                    String registration_num){
////                                    AnimalHealthStatus animalHealthStatus)
//
//
//            Animal animal = new Animal();
//            animal.setName(name);
//            animal.setBirthday((java.sql.Date) birthday);
//            animal.setRegistrationNum(registration_num);
////            animal.setAnimalHealthStatus(animalHealthStatus);
//            return animal;
//        }
//
//        private Doctor createDoctor(String login_email, String login_pwd,
//                                    String name, Date birthday,
//                                    String major,
//                                    String introduce,
//                                    String phone_num) {
//
//            Doctor doctor = new Doctor();
//            doctor.setLoginEmail(login_email);
//            doctor.setLoginPwd(login_pwd);
//            doctor.setName(name);
//            doctor.setBirthday((java.sql.Date) birthday);
//            doctor.setMajor(major);
//            doctor.setIntroduce(introduce);
//            doctor.setPhoneNum(phone_num);
//            return doctor;
//        }
//
//        private Hospital createHospital(String name,
//                                        String city, String street, String zipcode,
//                                        String phone_num,
//                                        String opening_hours) {
//
//            Hospital hospital = new Hospital();
//            hospital.setName(name);
//            hospital.setAddress(new Address(city, street, zipcode));
//            hospital.setPhoneNum(phone_num);
//            hospital.setOpeningHours(opening_hours);
//            return hospital;
//        }
//
//        // 년, 월, 일이 각각 입력되었을 경우 Date로 변경하는 메서드
//        public Date transformDate(String year, String month, String day)
//        {
//            String date = year+"-"+month+"-"+day;
//            Date d = Date.valueOf(date);
//
//            return d;
//        }
//
//        // 날짜가 yyyymmdd 형식으로 입력되었을 경우 Date로 변경하는 메서드
//        public Date transformDate(String date)
//        {
//            SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
//
//            // Date로 변경하기 위해서는 날짜 형식을 yyyy-mm-dd로 변경해야 한다.
//            SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
//
//            java.util.Date tempDate = null;
//
//            try {
//                // 현재 yyyymmdd로된 날짜 형식으로 java.util.Date객체를 만든다.
//                tempDate = beforeFormat.parse(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            // java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
//            String transDate = afterFormat.format(tempDate);
//
//            // 반환된 String 값을 Date로 변경한다.
//            Date d = Date.valueOf(transDate);
//
//            return d;
//        }
//    }
//}
//
