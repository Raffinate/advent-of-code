

.PHONY: format
format:
	./mvnw spotless:apply

.PHONY: format-check
format-check:
	./mvnw spotless:check

.PHONY: test
test:
	./mvnw verify

.PHONY: clean
clean:
	./mvnw clean

.PHONY: uberjar
uberjar: clean
	./mvnw install

.PHONY: fix
fix: uberjar mk-puzzle-dir
	java -jar ./target/solver-1.0.0.jar check -a -f

.PHONY: redownload
redownload: uberjar mk-puzzle-dir
	java -jar ./target/solver-1.0.0.jar check -a -c -f

.PHONY: mk-puzzle-dir
	mkdir src/main/resources/puzzle

.PHONY: solve
solve: uberjar
	java -jar ./target/solver-1.0.0.jar solve