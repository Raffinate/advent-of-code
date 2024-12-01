/* (C) 2024 */
package dev.aoc.starter.internal.command;

import dev.aoc.starter.internal.solutionrunner.Solver;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Optional;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "solve", mixinStandardHelpOptions = true)
public class SolveCommand implements Callable<Integer> {

    @Option(names = "-y", required = true)
    int year;

    @Option(names = "-d", required = true)
    int day;

    @Option(names = "-p", required = true)
    int puzzleNumber;

    Solver solver;

    public SolveCommand(Solver solver) {
        this.solver = solver;
        this.year = 0;
        this.day = 0;
        this.puzzleNumber = 0;
    }

    @Override
    public Integer call() {

        solver.apply(new Puzzle(year, day, puzzleNumber, Optional.empty()));

        return 0;
    }
}
