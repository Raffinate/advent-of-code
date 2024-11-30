package dev.aoc.starter.internal.apprunner;

import java.util.List;
import java.util.concurrent.Callable;

import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.internal.solutionrunner.SolutionRunner;
import lombok.AllArgsConstructor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@AllArgsConstructor
@Command(name = "solve", mixinStandardHelpOptions = true)
public class SolveCommand implements Callable<Integer> {

    @Option(names = "-y", required = true)
    int year;

    @Option(names = "-d", required = true)
    int day;

    @Option(names = "-p", required = true)
    int puzzleNumber;

    List<SolutionContainer> solutions;

    @Override
    public Integer call() {
        var solution = solutions.stream().filter(s -> {
            return year == s.puzzleDetails().year() && day == s.puzzleDetails().day()
                    && puzzleNumber == s.puzzleDetails().puzzleNumber();
        }).findFirst().orElseThrow();

        var runner = new SolutionRunner(solution);

        runner.run();

        return 0;
    }

}
