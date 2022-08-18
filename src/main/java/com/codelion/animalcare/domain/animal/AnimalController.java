package com.codelion.animalcare.domain.animal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class AnimalController {

    // api 수정해야함
    @GetMapping("/usr/mypage/member/animal/info")
    public String createForm(Model model) {
        model.addAttribute("animalForm", new AnimalForm());
        return "animal/info";
    }
}
