@echo off
REM Apaga build anterior
rmdir /s /q out

REM Compila para Java 8 e suprime warnings de opções obsoletas
javac --release 8 -Xlint:-options -d out ^
    src\game\*.java src\network\*.java src\ui\*.java

if errorlevel 1 (
    echo Erro na compilação.
    pause
    exit /b 1
)

echo Compilação concluída.
java -cp out ui.UI
pause
