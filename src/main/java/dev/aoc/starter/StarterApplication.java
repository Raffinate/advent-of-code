package dev.aoc.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterApplication {

	static void log(Object x) {
		System.out.println(x.toString());
	}

	static void foo() {
		log(null);
	}

	public static void main(String[] args) {

		foo();

		SpringApplication.run(StarterApplication.class, args);
	}

}
