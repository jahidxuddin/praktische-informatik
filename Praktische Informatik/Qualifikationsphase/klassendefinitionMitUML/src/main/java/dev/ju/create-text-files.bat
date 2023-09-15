@echo off
setlocal enabledelayedexpansion

set "folderPath=%cd%"
set "fileExtension=.java" :: Change with the prefered extension

dir /b /a-d "%folderPath%\*%fileExtension%" 2>nul
if errorlevel 1 (
    echo No files with the extension %fileExtension% found in %folderPath%.
    pause
    exit
)

set "outputFolder=%folderPath%/out"
if not exist "%outputFolder%" mkdir "%outputFolder%"

for %%f in ("%folderPath%\*%fileExtension%") do (
    if not exist %%f goto :continue

    set "fileName=%%~nxf"
    set "fileNameWithoutExtension=%%~nf"

    copy "!fileName!" "%outputFolder%/!fileNameWithoutExtension!.txt"
)

pause

endlocal