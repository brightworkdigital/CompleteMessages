package com.example.Messages4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Messages4Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Messages4Application.class, args);
		Populator populator = context.getBean("populator", Populator.class);
		populator.run();
	}
}
