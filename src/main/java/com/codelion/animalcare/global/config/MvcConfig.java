package com.codelion.animalcare.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/login/authority").setViewName("login/main");
        registry.addViewController("/login").setViewName("login/login");
        registry.addViewController("/admin").setViewName("login/admin");
        registry.addViewController("/signup").setViewName("login/signup");
//        registry.addViewController("/user/mypage/member/{memberId}/medical-appointment").setViewName("medicalAppointments/medicalAppointmentForm");

//        registry.addViewController("/test").setViewName("test");

    }
}
