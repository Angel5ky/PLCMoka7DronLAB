@echo off
java -version
echo Iniciando programa
set /p IP="Introduca IP del PLC: "
set /p Rack="Introduce Rack del PLC (normalemte 0): "
set /p Slot="Introduce Slot del PLC (normalemte 0): "
java -jar PLCMoka7.jar %IP% %Rack% %Slot%
set /p id="Pulsa una tecla para salir"

