package com.codelion.animalcare.domain.medical_delivery_tmp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 임시 만듦
 */
@Controller
@RequiredArgsConstructor
public class MedicalDeliveryInfoController {

    @GetMapping("/usr/mypage/member/{memberId}/medicine-delivery-info")
    public String createForm(Model model) {
        model.addAttribute("medicalDeliveryForm", new MedicalDeliveryDto());
        return "medicalDelivery/medicalDelivery";
    }
}
