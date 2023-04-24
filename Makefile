install:
	mvn clean install

verify:
	mvn clean verify

run:
	mvn spring-boot:run

jar:
	java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
