package dev.aoc.starter.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.aoc.starter.internal.apprunner.AppRunner;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.solution.Solution;

@Configuration
public class AppConfiguration {

    @Bean
    public List<SolutionContainer> solutionContainersProvider(List<Solution> solutions) {
        return solutions.stream().map(SolutionContainer::create).toList();
    }

    @Bean
    public AppRunner appRunner(List<SolutionContainer> solutions, @Value("${dev.aoc.starter.token}") String token) {
        return new AppRunner(solutions, token);
    }

}
