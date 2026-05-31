package io.github.torres;

import io.github.torres.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ApiInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiInventarioApplication.class, args);
	}

}
