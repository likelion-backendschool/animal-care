package com.codelion.animalcare.global.common;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.repository.AnimalRepository;
import com.codelion.animalcare.domain.appointment.repository.AppointmentRepository;
import com.codelion.animalcare.domain.diagnosis.repository.DiagnosisRepository;
import com.codelion.animalcare.domain.doctorqna.entity.Answer;
import com.codelion.animalcare.domain.doctorqna.entity.Question;
import com.codelion.animalcare.domain.doctorqna.repository.AnswerRepository;
import com.codelion.animalcare.domain.doctorqna.repository.QuestionRepository;
import com.codelion.animalcare.domain.hospital.entity.Hospital;
import com.codelion.animalcare.domain.hospital.repository.HospitalRepository;
import com.codelion.animalcare.domain.user.entity.Address;
import com.codelion.animalcare.domain.user.entity.Doctor;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.repository.DoctorRepository;
import com.codelion.animalcare.domain.user.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDateTime;

@Configuration
@Profile("test")
public class TestInitData {

    @Bean
    CommandLineRunner init(MemberRepository memberRepository, AnimalRepository animalRepository, DoctorRepository doctorRepository, HospitalRepository hospitalRepository, AppointmentRepository appointmentRepository, DiagnosisRepository diagnosisRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        return args -> {
            /*
            멤버 추가하는 부분입니다.
            강남역 근처로 2명
            상도 근처로 2명 잡았습니다.
             */
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Member member1 = memberRepository.save(Member.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .email("member1@test.com")
                    .password(encoder.encode("member1"))
                    .name("김멤버")
                    .birthday(new Date(95, 2, 24))
                    .address(new Address("서울 강남구 도곡동 953-11", "서울 강남구 강남대로 238", "06267", "멤버1상세주소",
                            37.4849665053325, 127.034757121285))
                    .phoneNum("01011110000")
                    .auth("ROLE_MEMBER")
                    .genderId(0)
                    .build());

            memberRepository.save(member1);

            //member1에 대한 애완동물 추가
            Animal animal1 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(122, 8, 10))
                    .genderId(1)
                    .name("멍멍이")
                    .registrationNum("410000000000001")
                    .member(member1)
                    .breedingPlace("집")
                    .animalType("고양이")
                    .animalBreed("먼치킨")
                    .animalCoatColor("검은색")
                    .animalSpecial("꼬리가 짧음")
                    .build();

            animalRepository.save(animal1);

            Animal animal2 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(121, 1, 1))
                    .genderId(1)
                    .name("땅콩")
                    .registrationNum("410000000000002")
                    .member(member1)
                    .breedingPlace("야외")
                    .animalType("강아지")
                    .animalBreed("잡종")
                    .animalCoatColor("흰색")
                    .animalSpecial("꼬리가 검은색")
                    .build();

            animalRepository.save(animal2);

            Member member2 = memberRepository.save(Member.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .email("member2@test.com")
                    .password(encoder.encode("member2"))
                    .name("이멤버")
                    .birthday(new Date(99, 5, 21))
                    .address(new Address("서울 강남구 도곡동 953-3", "서울 강남구 강남대로 242", "06267", "멤버2상세주소",
                            37.4853735070328, 127.034082861809))
                    .phoneNum("01022220000")
                    .auth("ROLE_MEMBER")
                    .genderId(1)
                    .build());
            memberRepository.save(member2);

            //member2 애완동물 추가
            Animal animal3 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(120, 4, 12))
                    .genderId(1)
                    .name("냥냥이")
                    .registrationNum("410000000000003")
                    .member(member2)
                    .breedingPlace("집3")
                    .animalType("고양이3")
                    .animalBreed("먼치킨3")
                    .animalCoatColor("검은색3")
                    .animalSpecial("꼬리가 짧음3")
                    .build();

            animalRepository.save(animal3);

            Animal animal4 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(110, 9, 9))
                    .genderId(1)
                    .name("슈퍼도그")
                    .registrationNum("410000000000004")
                    .member(member2)
                    .breedingPlace("집4")
                    .animalType("고양이4")
                    .animalBreed("먼치킨4")
                    .animalCoatColor("검은색4")
                    .animalSpecial("꼬리가 짧음4")
                    .build();

            animalRepository.save(animal4);

            Animal animal5 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(114, 10, 3))
                    .genderId(1)
                    .name("슈카")
                    .registrationNum("410000000000005")
                    .member(member2)
                    .breedingPlace("집5")
                    .animalType("고양이5")
                    .animalBreed("먼치킨5")
                    .animalCoatColor("검은색5")
                    .animalSpecial("꼬리가 짧음5")
                    .build();

            animalRepository.save(animal5);

            Member member3 = memberRepository.save(Member.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .email("member3@test.com")
                    .password(encoder.encode("member3"))
                    .name("최멤버")
                    .birthday(new Date(50, 12, 1))
                    .address(new Address("서울 동작구 상도동 321-4", "서울 동작구 국사봉길 21", "07042", "멤버3상세주소",
                            37.4983449739223, 126.929376868639))
                    .phoneNum("01033330000")
                    .auth("ROLE_MEMBER")
                    .genderId(0)
                    .build());
            memberRepository.save(member3);

            //member3 애완동물 추가
            Animal animal6 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(121, 5, 5))
                    .genderId(1)
                    .name("복덩이")
                    .registrationNum("410000000000006")
                    .member(member3)
                    .breedingPlace("집6")
                    .animalType("고양이6")
                    .animalBreed("먼치킨6")
                    .animalCoatColor("검은색6")
                    .animalSpecial("꼬리가 짧음6")
                    .build();

            animalRepository.save(animal6);

            Animal animal7 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(121, 8, 20))
                    .genderId(1)
                    .name("별이")
                    .registrationNum("410000000000007")
                    .member(member3)
                    .breedingPlace("집7")
                    .animalType("고양이7")
                    .animalBreed("먼치킨7")
                    .animalCoatColor("검은색7")
                    .animalSpecial("꼬리가 짧음7")
                    .build();

            animalRepository.save(animal7);

            Member member4 = memberRepository.save(Member.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .email("member4@test.com")
                    .password(encoder.encode("member4"))
                    .name("강멤버")
                    .birthday(new Date(84, 7, 2))
                    .address(new Address("서울 동작구 상도동 321-30", "서울 동작구 국사봉길 41", "07042", "멤버4상세주소",
                            37.4978834274108, 126.930048131868))
                    .phoneNum("01044440000")
                    .auth("ROLE_MEMBER")
                    .genderId(0)
                    .build());
            memberRepository.save(member4);

            //member4 에완동물 추가
            Animal animal8 = Animal.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .birthday(new Date(121, 7, 10))
                    .genderId(1)
                    .name("호이")
                    .registrationNum("410000000000008")
                    .member(member4)
                    .breedingPlace("집8")
                    .animalType("고양이8")
                    .animalBreed("먼치킨8")
                    .animalCoatColor("검은색8")
                    .animalSpecial("꼬리가 짧음8")
                    .build();

            animalRepository.save(animal8);

            Doctor doctor1 = doctorRepository.save(Doctor.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .email("doctor1@test.com")
                    .password(encoder.encode("doctor1"))
                    .name("김닥터")
                    .doctorLicense("3532")
                    .birthday(new Date(94, 9, 5))
                    .address(new Address("서울 강남구 도곡동 946-1", "서울 강남구 강남대로 284", "06258", "닥터1상세주소",
                            37.4887802184047, 127.032484091622))
                    .phoneNum("01011111111")
                    .auth("ROLE_DOCTOR")
                    .genderId(0)
                    .major("소동물")
                    .introduce("소동물의사 김의사입니다.")
                    .build());
            doctorRepository.save(doctor1);

            Doctor doctor2 = doctorRepository.save(Doctor.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .email("doctor2@test.com")
                    .password(encoder.encode("doctor2"))
                    .name("이닥터")
                    .doctorLicense("3533")
                    .birthday(new Date(90, 10, 11))
                    .address(new Address("서울 강남구 도곡동 946-14", "서울 강남구 강남대로 282", "06258", "닥터2상세주소",
                            37.4885664092435, 127.03248411211))
                    .phoneNum("01022221111")
                    .auth("ROLE_DOCTOR")
                    .genderId(1)
                    .major("대동물")
                    .introduce("대동물의사 이의사입니다.")
                    .build());
            doctorRepository.save(doctor2);

            // 병원
            Hospital hospital1 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("제주 대학 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("","제주특별자치도 서귀포시 가가로 14", "63534","101동",33.2700064530738, 126.375422332008))
                    .build();
            hospitalRepository.save(hospital1);

            Hospital hospital2 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("대박 병원")
                    .openingHours("02:57~02:57/02:57~02:57/02:57~02:58/02:58~02:58/02:58~02:58/02:58~02:58/02:58~02:58/")
                    .phoneNum("01033334444")
                    .address(new Address("경기 용인시 기흥구 구갈동 593-3", "경기 용인시 기흥구 강남동로 7", "16979","101동",37.2716680153549, 127.128387356766))
                    .build();
            hospitalRepository.save(hospital2);

            Hospital hospital3 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("제주 행복 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 서귀포시 서귀동 292-2", "제주특별자치도 서귀포시 중앙로48번길 8", "63591","101동", 33.2489347597471, 126.562437759077))
                    .build();
            hospitalRepository.save(hospital3);

            Hospital hospital4 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("제주 인생 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 서귀포시 남원읍 위미리 1967-1", "제주특별자치도 서귀포시 남원읍 일주동로 7624", "63591","101동", 33.2782319182411, 126.665854414504))
                    .build();
            hospitalRepository.save(hospital4);

            Hospital hospital5 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("제주 엄마 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 서귀포시 상예동 4580","제주특별자치도 서귀포시 가가로 28-25", "63591","101동", 33.2709012191365, 126.375638139271))
                    .build();
            hospitalRepository.save(hospital5);

            Hospital hospital6 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("제주 아빠 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 서귀포시 표선면 가시리 3665-80","제주특별자치도 서귀포시 표선면 녹산로 679-257", "63591","101동", 33.4151646324555, 126.695960005462))
                    .build();
            hospitalRepository.save(hospital6);

            Hospital hospital7 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("제주 한라 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 서귀포시 남원읍 하례리 1864-7","제주특별자치도 서귀포시 남원읍 하례광장로 25", "63607","101동", 33.3011089937279, 126.603119926935))
                    .build();
            hospitalRepository.save(hospital7);

            Hospital hospital8 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("하나 제주 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 제주시 일도이동 378-12","제주특별자치도 제주시 가령골길 1", "63275","101동", 33.5058859316727, 126.540234055807))
                    .build();
            hospitalRepository.save(hospital8);

            Hospital hospital9 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("이 제주 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 제주시 이도이동 314-4","제주특별자치도 제주시 가령로 1", "63214","101동", 33.5040742710791, 126.536724239959))
                    .build();
            hospitalRepository.save(hospital9);

            Hospital hospital10 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("삼 제주 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("","제주특별자치도 제주시 한림읍 가린내길 8", "63021","101동", 33.3615581463323, 126.307720806537))
                    .build();
            hospitalRepository.save(hospital10);

            Hospital hospital11 = Hospital.builder()
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .name("삼 제주 병원")
                    .openingHours("11:00~15:00/~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/10:00~10:00/")
                    .phoneNum("01011112222")
                    .address(new Address("제주특별자치도 제주시 한림읍 한림리 968","제주특별자치도 제주시 한림읍 강구로 1", "63028","101동", 33.412091796865, 126.269067194346))
                    .build();
            hospitalRepository.save(hospital11);

            Question question1 = Question.builder()
                    .title("title1")
                    .content("content1")
                    .view(0)
                    .member(member1)
                    .likeCount(0)
                    .build();

            questionRepository.save(question1);

            Question question2 = Question.builder()
                    .title("title2")
                    .content("content2")
                    .view(0)
                    .member(member2)
                    .likeCount(0)
                    .build();

            questionRepository.save(question2);

            Question question3 = Question.builder()
                    .title("How to learn Spring boot?")
                    .content("go hard.")
                    .view(0)
                    .member(member3)
                    .likeCount(0)
                    .build();

            questionRepository.save(question3);

            Answer answer1 = Answer.builder()
                    .content("answer1")
                    .question(question1)
                    .doctor(doctor1)
                    .build();

            answerRepository.save(answer1);

            Answer answer2 = Answer.builder()
                    .content("answer2")
                    .question(question2)
                    .doctor(doctor2)
                    .build();

            answerRepository.save(answer2);

            // 예약서
//            Appointment appointment1 = Appointment.builder()
//                    .createdAt(LocalDateTime.now())
//                    .updatedAt(LocalDateTime.now())
//                    .content("강아지가 많이 기침합니다.")
//                    .date(LocalDateTime.of(2022,9,30, 14,30,00))
//                    .status(AppointmentStatus.READY)
//                    .member(member1)
//                    .animal(animal1)
//                    .doctor(doctor1)
//                    .hospital(hospital1)
//                    .build();
//
//            appointmentRepository.save(appointment1);

            // 진단서

//            Diagnosis diagnosis1 = Diagnosis.builder()
//                    .createdAt(LocalDateTime.now())
//                    .updatedAt(LocalDateTime.now())
//                    .memberName("바다")
//                    .addressCity("경기도 강남시")
//                    .addressStreet("경기도 강남시 강남구 강남대로 1")
//                    .breedingPlace("주택")
//                    .animalType("강아지")
//                    .animalBreed("요크셔테리어")
//                    .animalName("야옹이")
//                    .animalGenderId((short) 1)
//                    .animalAge((short) 5)
//                    .animalCoatColor("검정")
//                    .animalSpecial("")
//                    .diseaseName("다리 통증")
//                    .diseaseDate(Date.valueOf("2022-09-14"))
//                    .diagnosisDate(Date.valueOf("2022-09-15"))
//                    .opinion("다리가 많이 아픔. 약 처방. 5일뒤에 또 방문 바람.")
//                    .otherMatter("없음")
//                    .hospitalName("제주 대박 병원")
//                    .hospitalStreet("제주특별자치도 서귀포시 가가로 14")
//                    .doctorName("경호")
//                    .doctorLicense("0101010101")
//                    // TODO .appointment() 필요하지만 아직 예약 테스트 안만들었음. 추가 예정
//                    .build();
//
//            diagnosisRepository.save(diagnosis1);
//
//            Diagnosis diagnosis2 = Diagnosis.builder()
//                    .createdAt(LocalDateTime.now())
//                    .updatedAt(LocalDateTime.now())
//                    .memberName(member1.getName())
//                    .addressCity(member1.getAddress().getCity())
//                    .addressStreet(member1.getAddress().getStreet())
//                    .breedingPlace("주택")
//                    .animalType("강아지")
//                    .animalBreed("리트리버")
//                    .animalName(animal1.getName())
//                    .animalGenderId((short) 1)
//                    .animalAge((short) 5)
//                    .animalCoatColor("황색")
//                    .animalSpecial("")
//                    .diseaseName("기침 많이함")
//                    .diseaseDate(Date.valueOf("2022-09-29"))
//                    .diagnosisDate(Date.valueOf("2022-09-30"))
//                    .opinion("기침을 많이함. 약 처방. 5일뒤에 또 방문 바람.")
//                    .otherMatter("없음")
//                    .hospitalName(hospital1.getName())
//                    .hospitalStreet(hospital1.getAddress().getStreet())
//                    .doctorName(doctor1.getName())
//                    .doctorLicense("0101010102")
//                    .appointment(appointment1)
//                    .build();
//
//            diagnosisRepository.save(diagnosis2);

        };
    }
}
