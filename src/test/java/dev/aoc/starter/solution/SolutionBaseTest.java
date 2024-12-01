/* (C) 2024 */
package dev.aoc.starter.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.base.Preconditions;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.internal.solutionrunner.Solver;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

class SolutionBaseTest {

    @Autowired private Solver solver;

    public void check(Puzzle puzzle, String expected) {
        var result = solver.apply(puzzle);

        assertEquals(expected, result);
    }

    public Puzzle of(int year, int day, int puzzle, String suffix) {

        var details = PuzzleDetails.fromPuzzle(new Puzzle(year, day, puzzle, Optional.empty()));

        if (suffix.isBlank()) {
            return new Puzzle(
                    details.year(),
                    details.day(),
                    details.puzzleNumber(),
                    Optional.of(details.inputPath()));
        }

        var defaultFile = details.inputPath();
        var testInput = defaultFile.replace(".txt", "_" + suffix + ".txt");

        Preconditions.checkState(
                !testInput.equals(defaultFile),
                "Failed to add suffix " + suffix + " to file " + defaultFile);

        return new Puzzle(year, day, puzzle, Optional.of(testInput));
    }
}
