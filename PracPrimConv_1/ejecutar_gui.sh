#!/bin/bash

echo "Ajecutando ajedrez en interfaz gráfica."
echo "Autor: Iván Ruiz Gázquez."
echo ""
java -cp "./bin:./lib/*" juego.gui.Ajedrez \
     --classpath ./bin \
     --disable-ansi-colors \
     --exclude-engine junit-vintage \
     --scan-class-path
