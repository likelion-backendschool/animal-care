package com.codelion.animalcare.domain.animal.controller;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import com.codelion.animalcare.domain.user.dto.MemberDto;
import com.codelion.animalcare.domain.user.entity.Member;
import com.codelion.animalcare.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/animal")
public class AnimalController {
    private final AnimalService animalService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model, Principal principal){
        Optional<MemberDto> memberDto = memberService.findByEmail(principal.getName());
        List<AnimalDto> animalDtoList = animalService.findByMember(memberDto.get());
        model.addAttribute("animals", animalDtoList);
        return "animal/animalList";
    }

    @GetMapping("add")
    public String saveForm(AnimalDto animalDto){

        return "animal/animalForm";
    }

    @PostMapping("add")
    public String save(@Valid AnimalDto animalDto, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "animal/animalForm";
        }
        animalService.save(animalDto, principal);
        return "redirect:/usr/animal/list";
    }
}
