package dev.aoc.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import dev.aoc.starter.internal.apprunner.AppRunner;

@SpringBootApplication
public class StarterApplication implements CommandLineRunner {

	@Autowired
	AppRunner appRunner;

	public static void main(String[] args) {

		new SpringApplicationBuilder()
				.bannerMode(Mode.OFF)
				.logStartupInfo(false)
				.sources(StarterApplication.class)
				.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		appRunner.run();
	}

}
