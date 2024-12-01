package dev.aoc.starter.internal.puzzleinputmanager;

import com.google.common.base.Preconditions;
import dev.aoc.starter.internal.aocapi.PuzzleLoader;
import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;

public record PuzzleInputManager(PuzzleLoader loader) {
    public void save(PuzzleDetails puzzle) {
        var dir = findResourcesRoot();
        var content = loader.load(puzzle);
        var path = dir.resolve(puzzle.inputPath()).toFile();
        path.getParentFile().mkdirs();
        try {
            path.createNewFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (
            var writer = new FileWriter(
                dir.resolve(puzzle.inputPath()).toFile(),
                Charset.defaultCharset(),
                false
            )
        ) {
            writer.append(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Path findResourcesRoot() {
        var userDir = Path.of(System.getProperty("user.dir"))
            .toAbsolutePath()
            .normalize();

        var currentDir = userDir;
        for (int i = 0; i < 100; ++i) {
            var src = currentDir.resolve("src");
            var main = src.resolve("main");
            var resources = main.resolve("resources");

            Preconditions.checkState(currentDir.toFile().isDirectory());

            if (
                src.toFile().isDirectory() &&
                main.toFile().isDirectory() &&
                resources.toFile().isDirectory()
            ) {
                return resources;
            }
        }

        throw new RuntimeException(
            "Failed to find project root for: " + userDir
        );
    }
}
