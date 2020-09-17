package com.ijkalra.auspostalinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication
@EnableScheduling
public class AusPostalInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AusPostalInfoApplication.class, args);
	}

}
