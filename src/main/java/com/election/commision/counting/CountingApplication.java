package com.election.commision.counting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CountingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountingApplication.class, args);
	}

}
