/* (C) 2024 */
package dev.aoc.starter.internal.command;

import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Optional;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "download", mixinStandardHelpOptions = true)
public class DownloadCommand implements Callable<Integer> {

    @Option(names = "-y", required = true)
    int year;

    @Option(names = "-d", required = true)
    int day;

    @Option(names = "-p", required = true)
    int puzzleNumber;

    PuzzleLoader puzzleLoader;

    public DownloadCommand(PuzzleLoader puzzleLoader) {
        this.puzzleLoader = puzzleLoader;
        this.year = 0;
        this.day = 0;
        this.puzzleNumber = 0;
    }

    @Override
    public Integer call() {
        var inputData =
                puzzleLoader.load(PuzzleDetails.fromPuzzle(new Puzzle(year, day, puzzleNumber, Optional.empty())));

        System.out.println(inputData);

        return 0;
    }
}
