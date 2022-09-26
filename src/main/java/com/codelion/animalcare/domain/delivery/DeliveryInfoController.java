package com.codelion.animalcare.domain.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 임시 만듦
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/mypage/delivery/info")
public class DeliveryInfoController {

    @GetMapping()
    public String createForm(Model model) {
        model.addAttribute("deliveryForm", new DeliveryDto());
        return "delivery/delivery";
    }
}
