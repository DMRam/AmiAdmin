package com.ami.admin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmiAdminDemoApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "AmiAdmin");
		SpringApplication.run(AmiAdminDemoApplication.class, args);
	}

}
