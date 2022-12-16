package com.conest.conest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ConestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConestApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
