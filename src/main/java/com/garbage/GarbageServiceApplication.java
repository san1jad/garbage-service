package com.garbage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GarbageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarbageServiceApplication.class, args);
	}

}
