package com.evaluacion.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal que contiene el inicio de la aplicacion
 * @author cvalenzm
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}
}
