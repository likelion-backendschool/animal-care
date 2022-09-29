package com.codelion.animalcare;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test") // 테스트 모드 활성화
@Transactional
class AnimalCareApplicationTests {

	@Test
	void 전체_디비_생성(){
		// 실행시 원래 디비에 있던 내용 다 없어집니다.
		
	}

}
