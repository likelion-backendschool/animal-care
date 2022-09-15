package com.codelion.animalcare;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test") // 테스트 모드 활성화
@Transactional
class AnimalCareApplicationTests {

	@Test
	void contextLoads() {
	}

}
