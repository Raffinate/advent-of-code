/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.solution;

import java.util.Optional;

/**
 * Interface all puzzle solutiuons should implement.
 * All solutions should be located in
 * dev.aoc.starter.solution module and
 * implement Solution interface.
 */
@FunctionalInterface
public interface Solution {
    /**
     * Puzzle definition
     * @param year - Year of a puzzle.
     * @param day - Day of a puzzle.
     * @param level - Level of a puzzle (1 or 2)
     * @param inputPath - Path to file that contains input.
     */
    public static record Puzzle(
        int year,
        int day,
        int level,
        Optional<String> inputPath
    ) {}

    /**
     * Allows to override solution metadata that is extracted from name.
     *
     * If empty, Year, Day and Level are extracted from class name.
     *
     * Non-negative numbers are extracted from class name.
     * It is expected that last 3 numbers will be year, day, puzzle accordingly.
     * If the year is less that 1000, 2000 will be added to year.
     *
     * Class: some.package.2024.Day1Puzzle2.java
     * Result: Year: 2024, Day: 1, Puzzle: 2, inputPath: "puzzle/2024_01_2.txt"
     *
     * Class: another.package1.subwith1234numbers.Y24D2L1
     * Result: Year: 2024, Day: 2, Puzzle: 1, inputPath: "puzzle/2024_02_1.txt"
     *
     * If inputPath is empty, inputPath will be generated from year, day and level.
     *
     * For example:
     * Puzzle: Year: 2024, Day: 4, Level: 2,
     * Result: inputPath: "puzzle/2024_04_2.txt"
     *
     * @return Puzzle or Empty that is this solution for.
     */
    default Optional<Puzzle> puzzle() {
        return Optional.empty();
    }

    /**
     * Method that transforms puzzle input to resut.
     * @param input - input as given in Advent of Code.
     * @return - output.
     */
    Object solve(String input);
}
