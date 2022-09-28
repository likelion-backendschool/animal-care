package com.codelion.animalcare.domain.animal.controller;

import com.codelion.animalcare.domain.animal.dto.AnimalDto;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usr/member/animal")
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/list")
    public String list(Model model, Principal principal){
        List<AnimalDto> animalDtoList = animalService.findByMember(principal.getName());
        model.addAttribute("animals", animalDtoList);
        return "animal/animalList";
    }

    @GetMapping("/add")
    public String saveForm(AnimalDto animalDto){
        return "animal/animalForm";
    }

    @PostMapping("/add")
    public String save(@Valid AnimalDto animalDto, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "animal/animalForm";
        }
        animalService.save(animalDto, principal);
        return "redirect:/usr/member/animal/list";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable Long id, AnimalDto animalDto, Principal principal) {
        AnimalDto findAnimalDto = animalService.findById(id);
        if(animalService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");

        }
        model.addAttribute("animal", findAnimalDto);
        return "animal/animalDetailForm";
    }

    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable Long id, Principal principal){
        if(animalService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");

        }
        AnimalDto animalDto = animalService.findById(id);
        System.out.println(animalDto.getName());
        model.addAttribute("animalDto", animalDto);
        return "animal/animalModifyForm";
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable Long id, @Valid AnimalDto animalDto, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "animal/animalModifyForm";
        }
        if(animalService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }
        animalService.update(id, animalDto);

        return "redirect:/usr/member/animal/%d".formatted(id);
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Principal principal){
        if(animalService.questionAuthorized(id, principal)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한이 없습니다.");
        }
        animalService.delete(id);
        return "redirect:/usr/member/animal/list";
    }
}
