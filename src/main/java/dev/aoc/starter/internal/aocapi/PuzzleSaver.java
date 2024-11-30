package dev.aoc.starter.internal.aocapi;

import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import com.google.common.base.Preconditions;

import dev.aoc.starter.internal.solutionrunner.PuzzleDetails;
import lombok.SneakyThrows;

public record PuzzleSaver() {

    @SneakyThrows
    public void save(PuzzleDetails puzzleDetails, String puzzleData) {
        var root = findRootOrThrow();
        var puzzleDataPath = root.toAbsolutePath().resolve("main/resources").resolve(puzzleDetails.inputPath());
        var puzzleDataFile = puzzleDataPath.toFile();

        puzzleDataFile.createNewFile();
        puzzleDataFile.setWritable(true);
        puzzleDataFile.setReadable(true);

        System.out.println(puzzleDataPath);

        try (var writer = new FileWriter(puzzleDataFile, Charset.defaultCharset())) {
            writer.append(puzzleData);
        }

    }

    private Path findRootOrThrow() {
        var origRoot = Paths.get(this.getClass().getResource("/").getPath()).toAbsolutePath().normalize();

        var maxAttempts = 100;
        var root = origRoot;
        var hasParent = Objects.equals(root, root.resolve(".."));

        for (int i = 0; i < maxAttempts && !isRoot(root) && hasParent; ++i) {
            root = Preconditions.checkNotNull(root.resolve(".."),
                    "Can't find source root directory in path: " + origRoot);
        }

        Preconditions.checkState(isRoot(root), "No attempt left to find root directory in path: " + origRoot);

        return root;
    }

    private boolean isRoot(Path path) {
        System.out.println(path.toString());
        var puzzleDir = path.resolve("src/main/resources/puzzle");

        return puzzleDir.toFile().isDirectory();
    }
}
