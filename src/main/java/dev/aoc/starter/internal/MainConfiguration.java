/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal;

import dev.aoc.starter.internal.aocapi.PuzzleAnswerSubmitter;
import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.command.CheckCommand;
import dev.aoc.starter.internal.command.DownloadCommand;
import dev.aoc.starter.internal.command.RootCommand;
import dev.aoc.starter.internal.command.SolveCommand;
import dev.aoc.starter.internal.puzzleinputmanager.MissingInputPuzzleProvider;
import dev.aoc.starter.internal.puzzleinputmanager.PuzzleInputManager;
import dev.aoc.starter.internal.solutionrunner.PuzzleInputReader;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.internal.solutionrunner.Solver;
import dev.aoc.starter.solution.Solution;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class MainConfiguration {

    @Bean
    public List<SolutionContainer> solutionContainersProvider(
        List<Solution> solutions
    ) {
        return solutions.stream().map(SolutionContainer::create).toList();
    }

    @Bean
    public RootCommand rootCommand() {
        var command = new RootCommand();
        return command;
    }

    @Bean
    public Solver solver(
        List<SolutionContainer> solutions,
        PuzzleInputReader puzzleInputReader
    ) {
        return new Solver(solutions, puzzleInputReader);
    }

    @Bean
    public SolveCommand solveCommandCommand(
        Solver solver,
        PuzzleAnswerSubmitter submitter
    ) {
        var command = new SolveCommand(solver, submitter);

        return command;
    }

    @Bean
    public DownloadCommand downloadCommand(
        PuzzleLoader puzzleLoader,
        PuzzleInputManager puzzleInputManager
    ) {
        return new DownloadCommand(puzzleLoader, puzzleInputManager);
    }

    @Bean
    public CheckCommand missingCommand(
        MissingInputPuzzleProvider missingInputPuzzleProvider,
        PuzzleInputManager puzzleInputManager
    ) {
        return new CheckCommand(missingInputPuzzleProvider, puzzleInputManager);
    }

    @Bean
    public PuzzleLoader puzzleLoader(
        @Value("${dev.aoc.starter.token}") String token
    ) {
        return new PuzzleLoader(token);
    }

    @Bean
    public PuzzleAnswerSubmitter puzzleandAnswerSubmitter(
        @Value("${dev.aoc.starter.token}") String token
    ) {
        return new PuzzleAnswerSubmitter(token);
    }

    @Bean
    public PuzzleInputReader puzzleInputReader() {
        return new PuzzleInputReader();
    }

    @Bean
    public MissingInputPuzzleProvider missingInputPuzzleProvider(
        Collection<SolutionContainer> solutions,
        PuzzleInputReader reader,
        PuzzleLoader loader
    ) {
        return new MissingInputPuzzleProvider(solutions, reader, loader);
    }

    @Bean
    public CliRunner cliRunner(IFactory cliFactory, RootCommand command) {
        return new CliRunner(cliFactory, command, 0);
    }

    @Bean
    public PuzzleInputManager puzzleInputManager(PuzzleLoader loader) {
        return new PuzzleInputManager(loader);
    }
}
