package com.management.CompanyManagementSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.management.CompanyManagementSystem.Mapper")
@EnableWebMvc
public class CompanyManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementSystemApplication.class, args);
	}

}
