# FAQs

## What is this project for?

[Advent on Code](https://adventofcode.com/) - event for software engineers that happens every year.

This event is a set of new puzzles every day in December.

This project is designed to remove setup boilerplate
and start coding in feature rich environment immediately.

- Automatic task input downloads.
- OneLiner unit tests.

To start:

Create solution file.

```
public record Day1Puzzle1() implements Solution {
    @Override
    public Object solve(String input) {
        return "Implement Me";
    }
```

Run

> make fix

Run

> make solve

### Step 1

#### Fork / Clone this Repository

### Step 2

#### Set up your environment.

See: IDE: VS Code section

### Step 2

#### Create a branch for your solution

> git checkout -b solution

### Step 3

#### [Optional] Add you token to .env file

Create .env file in root directory and add following line:

> AOC_TOKEN="<YOUR_COOKIE>"

After that run

> make download

You have downloaded task input to: src/resources/puzzle/2024_01_1.txt

#### Alternative

Create empty .env file.
Create src/resources/puzzle/2024_01_1.txt
Copy and paste your first puzzle input in that file.

### Step 4

#### Run make test

> make test

```
ExampleTest.testTutorial:19->check:1->SolutionBaseTest.check:33 expected: <11> but was: <Not Implemented>
```

### Step 5

#### Implement solution

First puzzle is in src/main/java/dev/aoc/starter/solution/year2024/Day1Puzzle1.java

### Step 6

#### Run your solution with actual input

> make run

and submit your solution

## More Details

## To start working on next task

1. Create a java class in dev.aoc.starter.solution
1. Java class full name must contain year, day and puzzle number separated by non-digits.
1. Example: dev.aoc.starter.solution.year2024.Day1Task1.java
1. Example: dev.aoc.starter.solution.Y24D1T1.java
1. Extend class from dev.aoc.starter.solution.Solution
1. Implement solver

## How to download task input

Find you cookie for AoC in your browser.
Put you cookie in .env file under AOC_TOKEN="<YOU COOKIE>"

> $ make download

It will download puzzle input to ./src/resources/puzzle/{YYYY}\_{dd}\_{id}.txt

## Can I download manually?

Sure.
Create empty .env file.
Create a file ./src/resources/puzzle/{YYYY}\_{dd}\_{n}.txt and copy/paste your input there.

## How to make and run jar

> $ make uberjar

> $ java -jar ./target/starter-0.0.1-SNAPSHOT.jar --help

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
1. use helper functions defined in base class to make tests as oneliner.

```
     @Test
     void testSolutionScenario() {
        check(of(2024, 15, 2, "suffix"), "1982");
     }
```

This will test solution for puzzle:

> Year = 2024, day=15, puzzle number 2.

> Input file src/test/resources/puzzle/2024_15_2_suffix.txt

> Expected result "1982"

## How to format

> $ make format

## How to run tests

> $ make test

## How to disable all that formatting and tests

Delete relevant plugin sections in pom.xml

# IDE: VS Code

> npm install

> Install prettier extension

```
{
  "[java]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode",
  },
  "editor.formatOnSave": true,
  "prettier.requireConfig": false
}
```

## How to mirror repo (If Fork doesn't work)

See: [How to Mirror](https://docs.github.com/en/repositories/creating-and-managing-repositories/duplicating-a-repository#mirroring-a-repository-in-another-location)

If you want to mirror a repository in another location, including getting updates from the original, you can clone a mirror and periodically push the changes.

Open Terminal.

Create a bare mirrored clone of the repository.

```
    git clone --mirror https://github.com/EXAMPLE-USER/REPOSITORY-TO-MIRROR.git
```

Set the push location to your mirror.

```
cd REPOSITORY-TO-MIRROR

git remote set-url --push origin https://github.com/EXAMPLE-USER/MIRRORED
```

As with a bare clone, a mirrored clone includes all remote branches and tags, but all local references will be overwritten each time you fetch, so it will always be the same as the original repository. Setting the URL for pushes simplifies pushing to your mirror.

To update your mirror, fetch updates and push.

```
git fetch -p origin
git push --mirror
```

NOTE

```
$ git push - this will delete any custom code you had in the mirrored repo.
```

You can disable 1 to 1 mirroring using

```
$ git config remote.origin.mirror false

$ git fetch -p origin

$ git push -u origin main
```
