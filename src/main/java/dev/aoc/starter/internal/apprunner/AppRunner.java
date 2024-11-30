/* (C) 2024 */
package dev.aoc.starter.internal.apprunner;

import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.aocapi.PuzzleSaver;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import java.util.List;

public record AppRunner(List<SolutionContainer> solutions, String token) implements Runnable {

    @Override
    public void run() {

        var pl = new PuzzleLoader(token);
        var data = pl.load(new PuzzleDetails(2023, 1, 1, "test.txt"));

        var ps = new PuzzleSaver();
        ps.save(new PuzzleDetails(2023, 1, 1, "test.txt"), data);
    }
}
