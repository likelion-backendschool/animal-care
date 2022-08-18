//package com.codelion.animalcare.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//import static javax.persistence.FetchType.LAZY;
//
//@Entity
//@Getter @Setter
//public class Gender {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "gender_id")
//    private Long id;
//
//
//    @JsonIgnore
//    @OneToOne(mappedBy = "gender", fetch = LAZY)
//    private Member member;
//
//}
