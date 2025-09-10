#!/bin/bash

echo "========================================"
echo "   🎯 TEST VOCACIONAL - EJECUTAR"
echo "========================================"
echo

echo "🔧 Compilando aplicación..."
mkdir -p bin
javac -d bin -cp src src/Controlador/TestVocationalGUI.java

if [ $? -ne 0 ]; then
    echo "❌ Error en la compilación"
    exit 1
fi

echo "✅ Compilación exitosa"
echo
echo "🚀 Iniciando Test Vocacional..."
echo

java -cp bin Controlador.TestVocationalGUI

echo
echo "👋 Aplicación cerrada"
read -p "Presiona Enter para continuar..."
