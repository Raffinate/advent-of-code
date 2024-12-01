/* (C) 2024 */
package dev.aoc.starter.internal.command;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.internal.solutionrunner.Solver;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "solve", mixinStandardHelpOptions = true)
public class SolveCommand implements Callable<Integer> {

    @Option(names = "-y", required = false)
    int year;

    @Option(names = "-d", required = false)
    int day;

    @Option(names = "-p", required = false)
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
        var puzzle = new Puzzle(year, day, puzzleNumber, Optional.empty());
        if (year > 0 && day > 0 && puzzleNumber > 0) {
            solver.apply(puzzle);
            return 0;
        }

        var puzzles = Lists.newArrayList(solver.solutions().stream()
                .map(SolutionContainer::puzzleDetails)
                .toList());
        Collections.sort(puzzles, Collections.reverseOrder());

        var puzzleFilter = PuzzleDetails.fromPuzzle(puzzle);

        var filteredPuzzles = puzzles.stream()
                .filter(p -> {
                    return puzzleFilter.year() <= 2000 || p.year() == this.year;
                })
                .filter(p -> {
                    return puzzleFilter.day() <= 0 || p.day() == this.day;
                })
                .filter(p -> {
                    return puzzleFilter.puzzleNumber() <= 0 || p.puzzleNumber() == this.day;
                })
                .toList();

        var latest = filteredPuzzles.stream().findFirst();

        Preconditions.checkState(latest.isPresent(), "Could not find solution for: " + puzzleFilter.toString());

        solver.apply(latest.get().toPuzzle());

        return 0;
    }
}
