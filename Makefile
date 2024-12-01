

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
	java -jar ./target/starter-0.0.1-SNAPSHOT.jar $$(java -jar ./target/starter-0.0.1-SNAPSHOT.jar missing -c) > src/main/resources/$$(java -jar ./target/starter-0.0.1-SNAPSHOT.jar missing -p)

.PHONY: solve
solve: uberjar
	java -jar ./target/starter-0.0.1-SNAPSHOT.jar solve