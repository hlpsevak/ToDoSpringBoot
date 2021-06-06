package com.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.mobile.model")
@ComponentScan(basePackages = "com.mobile.*")
@EnableJpaRepositories("com.mobile.repository")
public class QuickToDosApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickToDosApplication.class, args);
	}

}
