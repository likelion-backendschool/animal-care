package com.codelion.animalcare.domain.item_tmp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 임시 만듦
 */
@Controller
@RequiredArgsConstructor
public class ItemOrderListController {


    @GetMapping("/usr/mypage/member/{memberId}/itemOrder/list")
    public String createForm(Model model) {
        model.addAttribute("orderListForm", new ItemOrderListDto());
        return "itemOrder/list";
    }

}
