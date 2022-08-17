package com.codelion.animalcare.domain.medicalDeliveryTmp;

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

    @GetMapping("/usr/mypage/member/medicine-delivery-info")
    public String createForm(Model model) {
        model.addAttribute("medicalDeliveryForm", new MedicalDeliveryForm());
        return "medicalDelivery/medicalDelivery";
    }
}
