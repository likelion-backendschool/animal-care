// TODO : 배포 후 실행시 검사로 인한 주석처리
//package com.codelion.animalcare.doctorqna.controller;
//
//import com.codelion.animalcare.domain.doctorqna.controller.QuestionController;
//import com.codelion.animalcare.domain.doctorqna.service.QuestionService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//
//@MockBean(JpaMetamodelMappingContext.class)
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = QuestionController.class)
//public class QuestionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private QuestionService questionService;
//
//
//    @Test
//    public void 닥터QnA_리스트_보여진다() throws Exception{
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/usr/doctor-qna"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("/doctorqna/doctorQnaList"));
//
//    }
//
//}
