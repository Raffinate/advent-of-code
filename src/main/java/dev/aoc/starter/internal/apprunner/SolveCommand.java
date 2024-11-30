/* (C) 2024 */
package dev.aoc.starter.internal.apprunner;

import dev.aoc.starter.internal.solutionrunner.Solver;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Optional;
import java.util.concurrent.Callable;
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

    Solver solver;

    @Override
    public Integer call() {

        solver.apply(new Puzzle(year, day, puzzleNumber, Optional.empty()));

        return 0;
    }
}
