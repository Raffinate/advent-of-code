/* (C) 2024 Aleksey Mokhovikov */
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

@Command(
    name = "solve",
    mixinStandardHelpOptions = true,
    description = {
        "Runs solver.",
        "Picks latest puzzle that has solution implementation.",
        "Reads corresponding input from resources.",
        "If filters (-y, -d, -p) are specified,",
        "picks latest solution that matched filter.",
        "For example -p 1 will run solution for latest first puzzle.",
    }
)
public class SolveCommand implements Callable<Integer> {

    @Option(
        names = { "-y", "--year" },
        required = false,
        description = "Year puzzle is from."
    )
    int year;

    @Option(
        names = { "-d", "--day" },
        required = false,
        description = "Day puzzle is from."
    )
    int day;

    @Option(
        names = { "-p", "--puzzle" },
        required = false,
        description = "Puzzle number. 1 or 2."
    )
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
        // if (year > 0 && day > 0 && puzzleNumber > 0) {
        //     solver.apply(PuzzleDetails.fromPuzzle(puzzle).toPuzzle());
        //     return 0;
        // }

        var puzzles = Lists.newArrayList(
            solver
                .solutions()
                .stream()
                .map(SolutionContainer::puzzleDetails)
                .toList()
        );
        Collections.sort(puzzles, Collections.reverseOrder());

        var puzzleFilter = PuzzleDetails.fromPuzzle(puzzle);

        var filteredPuzzles = puzzles
            .stream()
            .filter(p -> {
                return (this.year <= 0 || p.year() == puzzleFilter.year());
            })
            .filter(p -> {
                return this.day <= 0 || p.day() == puzzleFilter.day();
            })
            .filter(p -> {
                return (
                    this.puzzleNumber <= 0 ||
                    p.puzzleNumber() == puzzleFilter.puzzleNumber()
                );
            })
            .toList();

        var latest = filteredPuzzles.stream().findFirst();

        Preconditions.checkState(
            latest.isPresent(),
            "Could not find solution for: " + puzzleFilter.toString()
        );

        solver.apply(latest.get().toPuzzle());

        return 0;
    }
}
