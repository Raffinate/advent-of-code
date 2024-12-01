

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
download: uberjar
	java -jar ./target/solver-1.0.0.jar $$(java -jar ./target/solver-1.0.0.jar missing -c) > src/main/resources/$$(java -jar ./target/solver-1.0.0.jar missing -p)

.PHONY: solve
solve: uberjar
	java -jar ./target/solver-1.0.0.jar solve