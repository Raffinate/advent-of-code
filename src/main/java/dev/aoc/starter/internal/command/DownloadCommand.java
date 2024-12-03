/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.command;

import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.puzzleinputmanager.PuzzleInputManager;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Optional;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "download",
    mixinStandardHelpOptions = true,
    description = {
        "Downloads input for given puzzle. Requires TOKEN.",
        "Use: 'make fix' to automatically download missing input files.",
        "Use: 'make redownload' to re-download all input files.",
    }
)
public class DownloadCommand implements Callable<Integer> {

    @Option(
        names = { "-y", "--year" },
        required = true,
        description = "Puzzle year."
    )
    int year;

    @Option(
        names = { "-d", "--day" },
        required = true,
        description = "Puzzle day."
    )
    int day;

    @Option(
        names = { "-l", "--level" },
        required = true,
        description = "Level number: 1 or 2."
    )
    int level;

    @Option(
        names = { "-o", "--stdout" },
        required = false,
        description = "Print to stdout instead of saving to file."
    )
    boolean out;

    PuzzleLoader puzzleLoader;

    PuzzleInputManager puzzleInputManager;

    public DownloadCommand(
        PuzzleLoader puzzleLoader,
        PuzzleInputManager puzzleInputManager
    ) {
        this.puzzleLoader = puzzleLoader;
        this.puzzleInputManager = puzzleInputManager;
        this.year = 0;
        this.day = 0;
        this.level = 0;
    }

    @Override
    public Integer call() {
        var puzzle = PuzzleDetails.fromPuzzle(
            new Puzzle(year, day, level, Optional.empty())
        );
        if (!out) {
            puzzleInputManager.save(puzzle);
            System.out.println(
                "Saved input for " + puzzle.toPuzzle().toString()
            );
            return 0;
        }

        var inputData = puzzleLoader.load(puzzle);

        System.out.println(inputData);

        return 0;
    }
}
