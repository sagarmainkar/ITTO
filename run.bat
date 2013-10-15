@echo off
set CLASSPATH=%CLASSPATH%;lib/itto.jar;lib/hsqldb.jar;lib/sqltool.jar;lib/log4j-1.2.17.jar;lib/swingx-all-1.6.3.jar;lib/jxl.jar;

set PATH=%PATH%;%JAVA_HOME%/bin;

start javaw -Dlog4j.configuration=log4j.properties com.pmpmaverick.itto.ui.ITTOExplorer

#pause




