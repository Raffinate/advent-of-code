/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.command;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import dev.aoc.starter.internal.aocapi.PuzzleAnswerSubmitter;
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
        description = "Puzzle year."
    )
    int year;

    @Option(
        names = { "-d", "--day" },
        required = false,
        description = "Puzzle day."
    )
    int day;

    @Option(
        names = { "-l", "--level" },
        required = false,
        description = "Level number. 1 or 2."
    )
    int level;

    @Option(
        names = { "-s", "--submit" },
        required = false,
        negatable = true,
        description = "Submit puzzle output to AoC. Requires TOKEN."
    )
    boolean submit;

    Solver solver;

    PuzzleAnswerSubmitter submitter;

    public SolveCommand(Solver solver, PuzzleAnswerSubmitter submitter) {
        this.solver = solver;
        this.submitter = submitter;
        this.year = 0;
        this.day = 0;
        this.level = 0;
    }

    @Override
    public Integer call() {
        var puzzle = new Puzzle(year, day, level, Optional.empty());

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
                return (this.level <= 0 || p.level() == puzzleFilter.level());
            })
            .toList();

        var latest = filteredPuzzles.stream().findFirst();

        Preconditions.checkState(
            latest.isPresent(),
            "Could not find solution for: " + puzzleFilter.toString()
        );

        var output = solver.apply(latest.get().toPuzzle());

        System.out.println("Answer: " + output);

        if (submit) {
            System.out.println(
                String.format(
                    "Submitting %s for %s",
                    output,
                    latest.get().toPuzzle()
                )
            );

            System.out.println(submitter.submit(latest.get(), output));
        }

        return 0;
    }
}
