#!/bin/bash

if [ ! -d ./bin ]; then
    mkdir bin
fi

# Compilar los tests. Los tests dependen de la implementación de las clases testadas para compilar correctamente.

javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d bin \
-sourcepath ./src:./test \
./src/juego/util/*.java \
./src/juego/modelo/*.java \
./src/juego/control/*.java \
./src/juego/textui/*.java \
./test/juego/modelo/*.java \
./test/juego/control/*.java
