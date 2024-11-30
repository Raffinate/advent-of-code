/* (C) 2024 */
package dev.aoc.starter.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest(args = {"solve", "-y", "1001", "-d", "1", "-p", "1"})
@ExtendWith(OutputCaptureExtension.class)
class StarterApplicationTests {

    @Test
    public void thatCommandLineRunnerDoesStuff(CapturedOutput output) throws Exception {
        // this.clr.run("solve", "-y", "1001", "-d", "1", "-p", "1");
        // throw new RuntimeException(output.getOut());
        // assertEquals(0, clr.getExitCode());

        assertEquals(true, output.getOut().contains("Result: "));

        // verify changes...
    }
}
