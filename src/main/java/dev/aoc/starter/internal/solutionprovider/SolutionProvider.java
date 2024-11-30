package dev.aoc.starter.internal.solutionprovider;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

import dev.aoc.starter.solution.Solution;

@Configuration
@ComponentScan(basePackages = { "dev.aoc.starter.solution" }, includeFilters = {
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { Solution.class }) })
public class SolutionProvider {

}
