package com.codelion.animalcare.domain.hospitalTmp;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 임시 만듦
 */
@Controller
@RequiredArgsConstructor
public class HospitalController {

    // api 수정해야함
    @GetMapping("/usr/mypage/member/hospital/info")
    public String createForm(Model model) {
        model.addAttribute("hospitalForm", new HospitalForm());
        return "hospital/info";
    }
}
