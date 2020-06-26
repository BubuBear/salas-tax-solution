package it.bububear.plutus.themis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import it.bububear.plutus.themis.fe.ConsoleController;

/**
 * Entry point of the application
 * 
 * @author BubuBear
 *
 */
@SpringBootApplication
public class ThemisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemisApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(@Autowired ConsoleController consoleController) {
		return args -> consoleController.startAppConsole();

	}

}
