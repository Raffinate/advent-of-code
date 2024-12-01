/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ApplicationMain {

    public static void main(String[] args) {
        var context = new SpringApplicationBuilder()
            .bannerMode(Mode.OFF)
            .logStartupInfo(false)
            .sources(MainConfiguration.class)
            .run(args);

        System.exit(SpringApplication.exit(context));
    }
}
