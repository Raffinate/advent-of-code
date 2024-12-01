/* (C) 2024 */
package dev.aoc.starter.solution;

import java.util.Optional;

@FunctionalInterface
public interface Solution {

    public static record Puzzle(int year, int day, int puzzleNumber, Optional<String> inputPath) {}

    /**
     * Allows to override solution metadata that is extracted from name. Format is ((year, day,
     * puzzle), input_path).
     *
     * If empty, details are extractef from class name. Non-negative numbers are extracted from class
     * name. It is expected that last 3 numbers will be year, day, puzzle. If year is less that 1000,
     * 2000 will be added to year. If input path is empty or metadata is empty, path to input is
     * constructed from year, day and puzzle.
     *
     * For example:
     *
     * Class: some.package.2024.Day1Puzzle2.java Result: ((2024, 1, 2), "puzzle/2024_01_2.txt")
     *
     * Class: another.package1.subwith1234numbers.Y24D2P1 Result: ((2024, 2, 1),
     * "puzzle/2024_02_1.txt")
     *
     * @return Triple of ((year, day, puzzle), input path) or Empty. In latter case details will be
     *         extracted from class name.
     */
    public default Optional<Puzzle> puzzle() {
        return Optional.empty();
    }

    public Object solve(String input);
}
