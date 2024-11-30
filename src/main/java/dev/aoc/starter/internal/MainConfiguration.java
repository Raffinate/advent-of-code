package dev.aoc.starter.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.aoc.starter.internal.apprunner.AppRunner;
import dev.aoc.starter.internal.apprunner.RootCommand;
import dev.aoc.starter.internal.apprunner.SolveCommand;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.solution.Solution;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class MainConfiguration {

    @Bean
    public List<SolutionContainer> solutionContainersProvider(List<Solution> solutions) {
        return solutions.stream().map(SolutionContainer::create).toList();
    }

    @Bean
    public AppRunner appRunner(List<SolutionContainer> solutions, @Value("${dev.aoc.starter.token}") String token) {
        return new AppRunner(solutions, token);
    }

    @Bean
    public RootCommand rootCommand() {
        var command = new RootCommand();

        return command;
    }

    @Bean
    public SolveCommand solveCommandCommand(List<SolutionContainer> solutions) {
        var command = new SolveCommand(0, 0, 0, solutions);

        return command;
    }

    @Bean
    public CliRunner cliRunner(IFactory cliFactory, RootCommand command) {
        return new CliRunner(cliFactory, command, 0);
    }

}
