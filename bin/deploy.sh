#!/bin/bash
version=$1

if [[ -n "$version" ]];then
  echo "Start building HydraQL $version ......"
else
  echo "Please enter the HBase profile version you want to build."
  exit 1
fi
export GPG_TTY=$(tty)
if [ "$version" == "1.2" ]; then
  mvn -T 4C clean -U deploy -Dmaven.test.skip=true -Pdeploy -projects hydraql-template,hydraql-shell,spring-boot-starter-hydraql
elif [ "$version" == "1.4" ]; then
  mvn -T 4C clean -U deploy -Dmaven.test.skip=true -Pdeploy -Dhydraql.hbase.profile=1.4 -Dhydraql.hbase.version=1.4.3 -projects hydraql-template,hydraql-shell,spring-boot-starter-hydraql
elif [ "$version" == "2.2" ]; then
  mvn -T 4C clean -U deploy -Dmaven.test.skip=true -Pdeploy -Dhydraql.hbase.profile=2.2 -Dhydraql.hbase.version=2.2.7 -projects hydraql-template,hydraql-shell,spring-boot-starter-hydraql
elif [ "$version" == "2.5" ]; then
  mvn -T 4C clean -U deploy -Dmaven.test.skip=true -Pdeploy -Dhydraql.hbase.profile=2.5 -Dhydraql.hbase.version=2.5.3 -projects hydraql-template,hydraql-shell,spring-boot-starter-hydraql
elif [ "$version" == "2.6" ]; then
  mvn -T 4C clean -U deploy -Dmaven.test.skip=true -Pdeploy -Dhydraql.hbase.profile=2.6 -Dhydraql.hbase.version=2.6.0 -projects hydraql-template,hydraql-shell,spring-boot-starter-hydraql
else
  echo "The temporarily supported HBase profile versions are [1.2, 1.4, 2.2]"
fi

