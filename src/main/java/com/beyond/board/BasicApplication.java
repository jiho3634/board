package com.beyond.board;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//	세션 스토리지로 레디스를 사용하겠다는 설정
@EnableRedisHttpSession
//	BATCH
@EnableBatchProcessing
//	스케쥴링
@EnableScheduling
@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
}
