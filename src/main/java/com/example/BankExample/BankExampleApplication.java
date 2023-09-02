package com.example.BankExample;

import com.example.BankExample.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankExampleApplication implements CommandLineRunner {

	@Autowired
	private AuthenticationService authenticationService;

	public static void main(String[] args) {
		SpringApplication.run(BankExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.authenticationService.createAdmin();
	}

}
