package com.codelion.animalcare.global.config;

import com.codelion.animalcare.domain.appointment.interceptor.HasAnimalsInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    private final HasAnimalsInterceptor hasAnimalsInterceptor;

    // 요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/login/authority").setViewName("login/main");
//        registry.addViewController("/login").setViewName("login/login");
        registry.addViewController("/admin").setViewName("login/admin");
        registry.addViewController("/signup").setViewName("login/signup");
//        registry.addViewController("/test").setViewName("test");

        //error
        registry.addViewController("/error/not-animals").setViewName("error/not-animals");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(hasAnimalsInterceptor)
                .addPathPatterns("/usr/member/appointment");
    }
}
