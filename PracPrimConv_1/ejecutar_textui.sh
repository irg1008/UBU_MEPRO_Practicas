#!/bin/bash

echo "Ajecutando ajedrez en consola."
echo "Autor: Iván Ruiz Gázquez."
echo ""
java -cp "./bin" juego.textui.Ajedrez \
     --classpath ./bin \
     --disable-ansi-colors \
     --exclude-engine junit-vintage \
     --scan-class-path
