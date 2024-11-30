/* (C) 2024 */
package dev.aoc.starter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.aoc.starter.internal.solutionrunner.SolutionContainer;
import dev.aoc.starter.internal.solutionrunner.Solver;
import dev.aoc.starter.solution.Solution;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith({OutputCaptureExtension.class})
class SolutionBaseTests {

    @Configuration
    @ComponentScan(basePackages = {"dev.aoc.starter.internal.solutionprovider"})
    @Profile("test")
    static class TestConfiguration {

        @Bean
        public Solver solver(List<Solution> solutions) {
            return new Solver(solutions.stream().map(SolutionContainer::create).toList());
        }
    }

    @Autowired private Solver solver;

    @Test
    public void thatCommandLineRunnerDoesStuff(CapturedOutput output) throws Exception {
        // this.clr.run("solve", "-y", "1001", "-d", "1", "-p", "1");
        // throw new RuntimeException(output.getOut());
        // assertEquals("haha", output.getOut());

        assertEquals(1, solver.solutions().size());

        // verify changes...
    }
}
