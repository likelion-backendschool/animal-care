package com.codelion.animalcare.domain.diagnosis;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class DiagnosisForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    String title;

    // @Lob: 예약하는 content에 대해서 필드 길이 제한을 두지 않음
    // 데이터베이스 VARCHAR 타입이 아닌 CLOB 타입으로 저장해야함
    // @Lob를 사용하면 CLOB, BLOB 타입을 매핑할 수 있음
    @Lob
    String content;

    String cost;

}
