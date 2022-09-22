package com.codelion.animalcare.domain.animal;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class AnimalServiceTest {
    @Autowired
    private AnimalService animalService;

//    @Test
//    public void 고객이_갖고있는_동물들_구하기(){
//        Long memberId1 = 3L;
//        List<Animal> animalList = animalService.findByMemberId(memberId1); // 2마리 있다 가정.
//
//        System.out.println(animalList.size());
//        assertThat(animalList.size(), greaterThan(0));
//
//    }

}
