

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

.PHONY: uberjar-no-tests
uberjar-no-tests: clean
	./mvnw install -DskipTests=true

.PHONY: download
download: uberjar-no-tests
	java -jar ./target/solver-1.0.0.jar check -a -f

.PHONY: redownload
redownload: uberjar-no-tests
	java -jar ./target/solver-1.0.0.jar check -a -c -f

.PHONY: solve
solve: uberjar
	java -jar ./target/solver-1.0.0.jar solve

.PHONY: submit
submit: uberjar
	java -jar ./target/solver-1.0.0.jar solve -s