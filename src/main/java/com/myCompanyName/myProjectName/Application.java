package com.myCompanyName.myProjectName;

import org.moduliths.Modulith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Modulith
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
