@echo off

REM
REM Command line main menu for TicTacToe.
REM
REM Author: Cris Stanza, 03-Set-2015
REM

SetLocal

set COLOR_DEFAULT=80
set COLOR_ERROR=84
color %COLOR_DEFAULT%


set CP=.


set INVALID_OPTION=

:MAIN_MENU
	cls
	echo.
	echo          ===============================
	echo          =         Tic-Tac-Toe         =
	echo          ===============================
	echo.
	if not "%INVALID_OPTION%" == "" (
		color %COLOR_ERROR%
		echo.
		echo Invalid option: "%INVALID_OPTION%".
		echo.
		pause
		color %COLOR_DEFAULT%
		set INVALID_OPTION=
		goto MAIN_MENU
	)
	echo.
	echo   Native commands:
	echo   ----------------
	echo     [1] Compile      [2] Run          [3] Clean
	echo     [t] terminal     [q] quit
	echo.
	echo   Maven commands:
	echo   ----------------
	echo     [a] Clean        [s] Source       [k] Site
	echo     [d] Compile      [f] Test         [g] Install
	echo     [h] Run (java)   [j] Run (exec)
	echo.

	set OPTION=
	set /p OPTION="> "

	if "%OPTION%" == "1" goto OPTION_1
	if "%OPTION%" == "2" goto OPTION_2
	if "%OPTION%" == "3" goto OPTION_3

	if "%OPTION%" == "a" goto OPTION_A
	if "%OPTION%" == "s" goto OPTION_S
	if "%OPTION%" == "d" goto OPTION_D
	if "%OPTION%" == "f" goto OPTION_F
	if "%OPTION%" == "g" goto OPTION_G
	if "%OPTION%" == "h" goto OPTION_H
	if "%OPTION%" == "j" goto OPTION_J
	if "%OPTION%" == "k" goto OPTION_K

	if "%OPTION%" == "t" goto OPTION_T
	if "%OPTION%" == "q" goto OPTION_Q

	set INVALID_OPTION=%OPTION%
	goto MAIN_MENU

:END_OF_OPTION
	echo.
	pause
:END_OF_OPTION_UNPAUSED
	set OPTION=
	goto MAIN_MENU


:OPTION_1
	echo.
	if not exist .\target md .\target
	if not exist .\target\classes md .\target\classes
	dir /s /b .\src\main\java\*.java > .\target\files.txt
	javac -encoding UTF-8 -sourcepath .\src -d .\target\classes -cp .\src\main\java;%CP% @.\target\files.txt
	copy .\src\main\resources\*.properties .\target\classes
	copy .\src\main\resources\*.png .\target\classes
	goto END_OF_OPTION

:OPTION_2
	echo.
	cd .\target
	cd .\classes
	java -splash:splash.png -cp %CP% Main
	cd ..
	cd ..
	goto END_OF_OPTION

:OPTION_3
	if exist .\target rmdir /s/q .\target
	md .\target
	md .\target\classes
	goto END_OF_OPTION


:OPTION_A
	echo.
	call mvn clean
	goto END_OF_OPTION

:OPTION_S
	echo.
	call mvn source:jar
	goto END_OF_OPTION

:OPTION_D
	echo.
	call mvn compile
	goto END_OF_OPTION

:OPTION_F
	echo.
	call mvn test
	goto END_OF_OPTION

:OPTION_G
	echo.
	call mvn install
	goto END_OF_OPTION

:OPTION_H
	echo.
	call mvn exec:java -Dexec.mainClass="Main"
	goto END_OF_OPTION

:OPTION_J
	echo.
	call mvn exec:exec -Dexec.executable="java" -Dexec.args="-splash:.\target\classes\splash.png -cp .\target\classes;%CP% Main"
	goto END_OF_OPTION

:OPTION_K
	echo.
	call mvn site:site
	goto END_OF_OPTION


:OPTION_T
	cmd
	goto END_OF_OPTION_UNPAUSED

:OPTION_Q
	goto END


:END
	echo.
	EndLocal
