#!/bin/bash

hydraqlConsoleBasePath=$(cd `dirname $0`; cd ..; pwd)
hydraqlConsoleBasePath="$hydraqlConsoleBasePath/lib"
jarFile=$(ls "$hydraqlConsoleBasePath" | grep 'hydraql-console_' | grep -v 'sources.jar' | grep -v 'javadoc.jar')

if [ "$jarFile" = "" ];
then
    echo "\033[0;31m No executable jar package found under path $hydraqlConsoleBasePath \033[0m  \033[0;34m \033[0m"
    exit 1
fi

appName="$hydraqlConsoleBasePath/$jarFile"

DEFAULT_OPTS="-XX:+UseConcMarkSweepGC -XX:ParallelGCThreads=20 -XX:+CMSClassUnloadingEnabled -XX:+CMSParallelRemarkEnabled -XX:+CMSScavengeBeforeRemark -Djava.net.preferIPv4Stack=true"


#JVM参数
JVM_OPTS="-Dname=HydraQLConsole -Duser.timezone=Asia/Shanghai $DEFAULT_OPTS -Xmx2g -Xms2g -Xmn768m -XX:CMSInitiatingOccupancyFraction=65 -XX:MaxGCPauseMillis=100"

java -jar  $JVM_OPTS $appName