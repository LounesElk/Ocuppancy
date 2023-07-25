package com.itso.occupancy.occupancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = "com.itso.occupancy.occupancy.model")
@EnableJpaRepositories("com.itso.occupancy.occupancy.repository")
public class OccupancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OccupancyApplication.class, args);
	}

}
