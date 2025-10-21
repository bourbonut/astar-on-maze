all: build run

fmt:
	mvn formatter:format

build:
	mvn package

run:
	java -cp target/astar-on-maze-1.0-SNAPSHOT.jar App
