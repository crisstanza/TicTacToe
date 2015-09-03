@echo off

SetLocal

REM
REM Command line main menu for TicTacToe.
REM
REM Author: Cris Stanza, 03-Set-2015
REM

set CP=.

:MAIN_MENU
	cls
	echo.
	echo   =====================
	echo   =    Tic-Tac-Toe    =
	echo   =====================
	echo.

	echo  [1] Compile      [2] Run      [3] Clean
	echo  [q] quit
	echo.

	set /p OPTION=: 

	if [%OPTION%] == [1] goto OPTION_1
	if [%OPTION%] == [2] goto OPTION_2
	if [%OPTION%] == [3] goto OPTION_3
	if [%OPTION%] == [q] goto OPTION_Q
	goto MAIN_MENU

:END_OF_OPTION
	echo.
	pause
	set OPTION=
	goto MAIN_MENU

:OPTION_1
	echo.
	if not exist .\classes md .\classes
	javac -sourcepath .\src -d .\classes -cp .\src;%CP% .\src\*.java
	copy .\src\*.properties .\classes
	goto END_OF_OPTION

:OPTION_2
	echo.
	cd .\classes
	java -cp %CP% Main
	cd ..
	goto END_OF_OPTION

:OPTION_3
	if exist .\classes rmdir /s/q .\classes
	md .\classes
	goto END_OF_OPTION

:OPTION_Q
	goto END

:END
	echo.
	EndLocal
