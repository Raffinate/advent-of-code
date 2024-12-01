

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

.PHONY: download
download: uberjar mk-puzzle-dir
	java -jar ./target/solver-1.0.0.jar $$(java -jar ./target/solver-1.0.0.jar check -d) > src/main/resources/$$(java -jar ./target/solver-1.0.0.jar check -p)

.PHONY: download
redownload: uberjar mk-puzzle-dir
	java -jar ./target/solver-1.0.0.jar $$(java -jar ./target/solver-1.0.0.jar check -d -c) > src/main/resources/$$(java -jar ./target/solver-1.0.0.jar check -c -p)

.PHONY: mk-puzzle-dir
	mkdir src/main/resources/puzzle

.PHONY: solve
solve: uberjar
	java -jar ./target/solver-1.0.0.jar solve