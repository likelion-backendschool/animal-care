package com.codelion.animalcare.domain.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 임시 만듦
 */
@Controller
@RequiredArgsConstructor
public class DeliveryInfoController {

    @GetMapping("/usr/mypage/member/delivery-info")
    public String createForm(Model model) {
        model.addAttribute("deliveryForm", new DeliveryDto());
        return "delivery/delivery";
    }
}
