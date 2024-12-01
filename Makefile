

format:
	./mvnw spotless:apply

format-check:
	./mvnw spotless:check

test:
	./mvnw verify

clean:
	./mvnw clean

uberjar: clean
	./mvnw install

download: uberjar
	java -jar ./target/starter-0.0.1-SNAPSHOT.jar $$(java -jar ./target/starter-0.0.1-SNAPSHOT.jar missing -c) > src/main/resources/$$(java -jar ./target/starter-0.0.1-SNAPSHOT.jar missing -p)
