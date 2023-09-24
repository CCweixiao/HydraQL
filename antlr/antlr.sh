#!/bin/sh
java -cp .:./lib/antlr4-4.13.1-complete.jar:$CLASSPATH org.antlr.v4.Tool $*  -no-listener -visitor