/* (C) 2024 */
package dev.aoc.starter.solution;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * Example of how test can be defined in a single like for a puzzle
 */
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { TestConfiguration.class })
public class ExampleTest extends SolutionBaseTest {

    @Test
    void testTutorial() {
        check(of(2024, 1, 1, "tutorial"), "0");
    }
}
