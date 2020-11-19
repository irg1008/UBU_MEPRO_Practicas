#!/bin/bash

if [ ! -d ./bin ]; then
    mkdir bin
fi

# Siento la tonteria :)
SABER='\033[0;34m'
GREY='\033[1;37m'
NC='\033[0m'

echo "O./bin Wan, eres mi única esperanza."
echo  -n -e "${GREY}=====${NC}"

for i in {1..15}
do
  echo -n -e ${SABER}=${NC}
  sleep 0.05
done


javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d bin \
-sourcepath ./src \
./src/juego/util/*.java \
./src/juego/modelo/*.java \
./src/juego/control/*.java \
./src/juego/textui/*.java

# Siento la tonteria :) Son solo 20*0.05 segundos = 1 segundito
echo
echo "El imperio ha sido compilado."
