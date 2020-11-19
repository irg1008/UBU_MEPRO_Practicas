#!/bin/bash

if [ ! -d ./bin ]; then
    mkdir bin
fi
javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d ./bin \
-sourcepath ./src:./test \
./src/musica/*.java \
./src/genericidad/*.java \
./test/genericidad/*.java 