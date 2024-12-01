/* (C) 2024 */
package dev.aoc.starter.solution;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { TestConfiguration.class })
public class ExampleTest extends SolutionBaseTest {

    @Test
    void test1001011() {
        check(of(1001, 1, 1, ""), "hello from test");
    }

    @Test
    void test1001011SecondTest() {
        check(of(1001, 1, 1, "2"), "hello from test 2");
    }
}
