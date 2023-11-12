@echo off
setlocal enabledelayedexpansion

set "folderPath=%cd%"
set "fileExtension=.java" :: Change with the prefered extension

set "hasFiles=0"

if exist "%folderPath%/*%fileExtension%" (
    set "hasFiles=1"
)

:loop
for /d /r %%D in (*) do (
    if exist "%%D\*%fileExtension%" (
        set "hasFiles=1"
        goto :break
    )
)
:break

if !hasFiles! == 0 (
    echo No files with the extension %fileExtension% found in %folderPath% and its subdirectories.
    pause
    exit
)

set "outputFolder=%folderPath%/out"
if not exist "%outputFolder%" mkdir "%outputFolder%"

for %%f in ("%folderPath%/*%fileExtension%") do (
    set "fileName=%%~nxf"
    set "fileNameWithoutExtension=%%~nf"

    copy "!fileName!" "%outputFolder%/!fileNameWithoutExtension!.txt"
)

for /d /r %%D in (*) do (
    for %%f in ("%%D\*%fileExtension%") do (
        set "fileName=%%~nxf"
        set "fileNameWithoutExtension=%%~nf"

        copy "%%D\!fileName!" "%outputFolder%/!fileNameWithoutExtension!.txt"
    )
)

endlocal