package io.lemon.tree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LemonTreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LemonTreeApplication.class, args);
	}

}
