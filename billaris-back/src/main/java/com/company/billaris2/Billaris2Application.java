package com.company.billaris2;

import org.hibernate.service.JavaServiceLoadable;
import org.hibernate.service.spi.InjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Billaris2Application {
//public class Billaris2Application implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(Billaris2Application.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//	}
}
