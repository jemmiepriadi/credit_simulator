package com.example.credit_simulator;

import com.example.credit_simulator.controller.LoanController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditSimulatorApplication implements CommandLineRunner {

	@Autowired
	private LoanController controller;

	@Value("${app.cli.enabled:true}")
	private boolean cliEnabled;

	public static void main(String[] args) {
		SpringApplication.run(CreditSimulatorApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (cliEnabled) {
			controller.run();
		}
	}
}
