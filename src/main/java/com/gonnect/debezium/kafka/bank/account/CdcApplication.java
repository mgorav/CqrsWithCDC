package com.gonnect.debezium.kafka.bank.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdcApplication.class, args);
	}

}

