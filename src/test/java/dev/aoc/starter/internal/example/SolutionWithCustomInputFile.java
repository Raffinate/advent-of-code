/* (C) 2024 */
package dev.aoc.starter.internal.example;

import dev.aoc.starter.solution.Solution;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * SolutionForY1001D5P2 - will search for
 * internal/custom_input.txt
 */
@Component
public class SolutionWithCustomInputFile implements Solution {

    @Override
    public Optional<Puzzle> puzzle() {
        return Optional.of(
            new Puzzle(1, 3, 2, Optional.of("internal/custom_input.txt"))
        );
    }

    @Override
    public Object solve(String input) {
        return input;
    }
}
