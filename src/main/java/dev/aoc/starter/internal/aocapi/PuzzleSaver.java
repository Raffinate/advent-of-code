/* (C) 2024 Aleksey Mokhovikov */
package dev.aoc.starter.internal.aocapi;

import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import lombok.SneakyThrows;

public record PuzzleSaver() {
    @SneakyThrows
    public void save(PuzzleDetails puzzleDetails, String puzzleData) {
        var root = Paths.get(this.getClass().getResource("/").getPath())
            .toAbsolutePath()
            .normalize();
        var puzzleDataPath = root
            .toAbsolutePath()
            .resolve("main/resources")
            .resolve(puzzleDetails.inputPath());
        var puzzleDataFile = puzzleDataPath.toFile();

        puzzleDataFile.createNewFile();
        puzzleDataFile.setWritable(true);
        puzzleDataFile.setReadable(true);

        System.out.println(puzzleDataPath);

        try (
            var writer = new FileWriter(
                puzzleDataFile,
                Charset.defaultCharset()
            )
        ) {
            writer.append(puzzleData);
        }
    }
}
