@echo off
chcp 65001 >nul
echo ========================================
echo    🎯 TEST VOCACIONAL - EJECUTAR
echo ========================================
echo.

echo 🔧 Compilando aplicación...
if not exist "bin" mkdir bin
javac -d bin -cp src src/Controlador/TestVocationalGUI.java 2>nul

if %ERRORLEVEL% neq 0 (
    echo ❌ Error en la compilación
    echo Compilando con detalles de errores...
    javac -d bin -cp src src/Controlador/TestVocationalGUI.java
    pause
    exit /b 1
)

echo ✅ Compilación exitosa
echo.
echo 🚀 Iniciando Test Vocacional...
echo.

java -cp bin Controlador.TestVocationalGUI

echo.
echo 👋 Aplicación cerrada
pause
