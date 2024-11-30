package dev.aoc.starter.solution;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Triple;

@FunctionalInterface
public interface Solution {

    /**
     * Allows to override solution metadata that is extracted from name.
     * Format is (year, day, puzzle).
     * 
     * @return Triple of (year, day, puzzle) or Empty if details must be extracted
     *         from class name.
     */
    default public Optional<Triple<Integer, Integer, Integer>> metadata() {
        return Optional.empty();
    }

    public Object solve(String input);

}
