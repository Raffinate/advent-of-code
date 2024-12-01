/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.base.Preconditions;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import dev.aoc.starter.internal.solutionrunner.Solver;
import dev.aoc.starter.solution.Solution.Puzzle;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for testing solutions.
 * Provides check and of methods to define tests in single line.
 * SolutionBaseTest
 */
class SolutionBaseTest {

    @Autowired
    private Solver solver;

    /**
     * Finds corresponding solution to puzzle.
     * Verifies that output is expected.
     * @param puzzle puzzle to test
     *   - - see SolutionBaseTest::of for simplified constructor
     * @param expected expected output
     */
    public void check(Puzzle puzzle, String expected) {
        var result = solver.apply(puzzle);

        assertEquals(expected, result);
    }

    /**
     * Returns puzzle with expected input located under
     *     resources/puzzle/2024_02_1.txt
     * @param year - Year of a puzzle
     * @param day - Day of a puzzle
     * @param puzzle - Id of a puzzle (1 or 2)
     * @return Puzzle
     */
    public Puzzle of(int year, int day, int puzzle) {
        return of(year, day, puzzle, "");
    }

    /**
     * Returns Puzzle with expected input located under
     *     resources/puzzle/2024_02_1_suffix.txt
     *
     * @param year - Year of a puzzle
     * @param day - Day of a puzzle
     * @param puzzle - Id of a puzzle (1 or 2)
     * @param suffix - Puzzle Input file suffix
     * @return Puzzle
     */
    public Puzzle of(int year, int day, int puzzle, String suffix) {
        var details = PuzzleDetails.fromPuzzle(
            new Puzzle(year, day, puzzle, Optional.empty())
        );

        if (suffix.isBlank()) {
            return new Puzzle(
                details.year(),
                details.day(),
                details.puzzleNumber(),
                Optional.of(details.inputPath())
            );
        }

        var defaultFile = details.inputPath();
        var testInput = defaultFile.replace(".txt", "_" + suffix + ".txt");

        Preconditions.checkState(
            !testInput.equals(defaultFile),
            "Failed to add suffix " + suffix + " to file " + defaultFile
        );

        return new Puzzle(year, day, puzzle, Optional.of(testInput));
    }
}
