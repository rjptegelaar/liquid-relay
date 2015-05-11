echo off

if not "%JAVA_HOME%" == "" goto gotJavaHome

echo The JAVA_HOME environment variable is not defined

echo This environment variable is needed to run this program

goto exit

:gotJavaHome

if exist %JAVA_HOME%\bin\server\jvm.dll (
    set PR_JVM=%JAVA_HOME%\bin\server\jvm.dll
) else (
    set PR_JVM=%JAVA_HOME%\jre\bin\server\jvm.dll  
)

if defined LIQUID_HOME (
	echo LIQUID_HOME IS defined
) else (
	echo LIQUID_HOME is not defined

	echo This environment variable is needed to run this program

	goto exit
)

echo Using %LIQUID_HOME% as liquid home directory

set SERVICE_NAME=RelayService
set PR_INSTALL=%LIQUID_HOME%\bin\amd64\prunsrv.exe
 
REM Service log configuration
set PR_LOGPREFIX=%SERVICE_NAME%
set PR_LOGPATH=%LIQUID_HOME%\logs
set PR_STDOUTPUT=%LIQUID_HOME%\logs\stdout.log
set PR_STDERROR=%LIQUID_HOME%\logs\stderr.log
set PR_LOGLEVEL=INFO
 
REM Path to java installation

set PR_CLASSPATH=%LIQUID_HOME%/lib/relay.jar
 
REM Startup configuration
set PR_STARTUP=auto
set PR_STARTMODE=jvm
set PR_STARTCLASS=com.pte.liquid.relay.RelayService
set PR_STARTMETHOD=start
 
REM Shutdown configuration
set PR_STOPMODE=jvm
set PR_STOPCLASS=com.pte.liquid.relay.RelayService
set PR_STOPMETHOD=stop
 
REM JVM configuration
set PR_JVMMS=256
set PR_JVMMX=1024
set PR_JVMSS=4000
set PR_JVMOPTIONS=-Duser.language=NE;-Duser.region=ne;-Dlog4j.configuration="file:%LIQUID_HOME%\conf\log4j.properties"
REM Install service 
%LIQUID_HOME%\bin\amd64\prunsrv.exe //IS//%SERVICE_NAME%