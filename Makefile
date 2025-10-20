all: build run

build:
	mvn package

run:
	java -cp target/astar-on-maze-1.0-SNAPSHOT.jar App
