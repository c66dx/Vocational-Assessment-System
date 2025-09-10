@echo off
echo ========================================
echo    🔧 TEST VOCACIONAL - COMPILAR
echo ========================================
echo.

echo 🧹 Limpiando archivos anteriores...
if exist "bin" rmdir /s /q bin
mkdir bin

echo 🔧 Compilando codigo fuente...
javac -d bin -cp src src/Controlador/TestVocationalGUI.java

if %ERRORLEVEL% neq 0 (
    echo ❌ Error en la compilacion
    pause
    exit /b 1
)

echo ✅ Compilacion completada exitosamente
echo 📁 Archivos compilados en: bin/
echo.
echo 💡 Para ejecutar usa: run.bat
echo.
pause
