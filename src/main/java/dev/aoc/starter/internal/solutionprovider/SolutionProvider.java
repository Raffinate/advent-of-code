/* (C) 2024 */
package dev.aoc.starter.internal.solutionprovider;

import dev.aoc.starter.solution.Solution;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = { "dev.aoc.starter.solution" },
    includeFilters = {
        @Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = { Solution.class }
        ),
    }
)
public class SolutionProvider {}
