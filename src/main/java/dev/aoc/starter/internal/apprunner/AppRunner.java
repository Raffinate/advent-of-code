package dev.aoc.starter.internal.apprunner;

import java.util.List;

import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.aocapi.PuzzleSaver;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.internal.solutionrunner.SolutionRunner;

public record AppRunner(List<SolutionContainer> solutions, String token)
        implements Runnable {

    @Override
    public void run() {

        var runner = new SolutionRunner(solutions().get(0));

        runner.run();

        var pl = new PuzzleLoader(token);
        var data = pl.load(new PuzzleDetails(2023, 1, 1, "test.txt"));

        var ps = new PuzzleSaver();
        ps.save(new PuzzleDetails(2023, 1, 1, "test.txt"), data);

    }

}
