package com.studentassist.geekconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.studentassist"})
//@EnableJpaRepositories(basePackages = "com.yourcompany.repositories")
public class GeekconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeekconnectApplication.class, args);
	}

}
