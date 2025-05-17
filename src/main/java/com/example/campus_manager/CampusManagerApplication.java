package com.example.campus_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CampusManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusManagerApplication.class, args);
	}

}
