

# FAQs

## Start Working
This is a 

## To start working on next task
1. Create a java class in dev.aoc.starter.solution
1. Java class full name must contain year, day and puzzle number separated by non-digits.
1. Example: dev.aoc.starter.solution.year2024.Day1Task1.java
1. Example: dev.aoc.starter.solution.Y24D1T1.java
1. Extend class from dev.aoc.starter.solution.Solution
1. Implement solver

## How to download task input
> $ make download

It will download it to ./src/resources/puzzle/{YYYY}\_{dd}\_{id}.txt

## How to make and run jar
> $ make uberjar

> $ java -jar ./target/starter-0.0.1-SNAPSHOT.jar

## How to solve specific puzzle
> ./mvnw spring-boot:run -Dspring-boot.run.arguments="solve -y 2024 -d 01 -p 1"

> java -jar ./target/starter-0.0.1-SNAPSHOT.jar solve -y 2024 -d 01 -p 1

## How to solve last puzzle available
> make solve

> ./mvnw spring-boot:run -Dspring-boot.run.arguments="solve"

> java -jar ./target/starter-0.0.1-SNAPSHOT.jar solve

## How to create test for solver

1. Create test inputs in src/test/resources/puzzle directory
1. Create a test class in src/test/java/dev/aoc/starter/solution/
1. Example: src/test/java/dev/aoc/starter/solution/ExampleTest.java
1. Use
```
    @SpringBootTest
    @ActiveProfiles("test")
    @ContextConfiguration(classes =     {TestConfiguration.class})
```
1. Extend from src/test/java/dev/aoc/starter/solution/SolutionBaseTest.java
1. use 
```
     @Test
     void testSolutionScenario() {
        check(of(2024, 15, 2, "suffix"), "1982");
     }
```
to test solution for puzzle 
Year = 2024, day=15, puzzle number 2.
Input file src/test/resources/puzzle/1001_01_1_suffix.txt
Expected result "1982"


## How to format

> $ make format

## How to run tests

> $ make test
