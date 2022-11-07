package com.codelion.animalcare.domain.user.controller;


import com.codelion.animalcare.domain.user.dto.UserInfoDto;
import com.codelion.animalcare.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(UserInfoDto infoDto) { // 회원 추가
        userService.save(infoDto);
        return "redirect:/login";
    }

    // 추가
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String signIn(){

        return "login/signin";
    }


//    @GetMapping("test")
//    public void test(){
//        UserInfo userInfo = userService.findPatientAndDoctor();
//        System.out.println(userInfo.getId());
//    }
//    @PostMapping()

//    @GetMapping("test")
//    public String test( ){
//// 시큐리티 컨텍스트 객체를 얻습니다.
//        SecurityContext context = SecurityContextHolder.getContext();
//
//// 인증 객체를 얻습니다.
//        Authentication authentication = context.getAuthentication();
//
//// 로그인한 사용자정보를 가진 객체를 얻습니다.
//        Object principal = authentication.getPrincipal();
//        UserInfo userInfo = (UserInfo) principal;
//        System.out.println("userInfo.getAuthorities() = " + userInfo.getAuthorities());
//        return "/test";
//    }
}