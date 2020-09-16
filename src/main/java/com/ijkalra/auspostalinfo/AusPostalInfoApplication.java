package com.ijkalra.auspostalinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AusPostalInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AusPostalInfoApplication.class, args);
	}

}
