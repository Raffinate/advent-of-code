/* (C) 2024 */
package dev.aoc.starter.solution;

import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.internal.solutionrunner.Solver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = {"dev.aoc.starter.internal.solutionprovider", "dev.aoc.starter.internal.example"})
@Profile("test")
public class TestConfiguration {
    @Bean
    public Solver solver(List<Solution> solutions) {
        return new Solver(solutions.stream().map(SolutionContainer::create).toList());
    }
}
