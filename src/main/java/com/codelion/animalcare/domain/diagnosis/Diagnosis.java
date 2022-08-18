package com.codelion.animalcare.domain.diagnosis;


import com.codelion.animalcare.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Diagnosis {

    @Id
    @GeneratedValue
    @Column(name = "diagnosis_id")
    private Long id;

    String title;

    // @Lob: 예약하는 content에 대해서 필드 길이 제한을 두지 않음
    // 데이터베이스 VARCHAR 타입이 아닌 CLOB 타입으로 저장해야함
    // @Lob를 사용하면 CLOB, BLOB 타입을 매핑할 수 있음
    @Lob
    String content;

    String cost;


    // Diagnosis : Customer = n : 1;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 예약 고객

}
