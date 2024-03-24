#!/bin/bash
version=$1

if [[ -n "$version" ]];then
  echo "Start building HydraQL $version ......"
else
  echo "Please enter the HBase profile version you want to build."
  exit 1
fi

if [ "$version" == "1.2" ]; then
  mvn clean install -Dmaven.test.skip=true
elif [ "$version" == "1.4" ]; then
   mvn -T 4C clean -U install -Dmaven.test.skip=true -Dhydraql.hbase.profile=1.4 -Dhydraql.hbase.version=1.4.3
elif [ "$version" == "2.2" ]; then
   mvn -T 4C clean -U install -Dmaven.test.skip=true -Dhydraql.hbase.profile=2.2 -Dhydraql.hbase.version=2.2.6
elif [ "$version" == "2.5" ]; then
   mvn -T 4C clean -U install -Dmaven.test.skip=true -Dhydraql.hbase.profile=2.5 -Dhydraql.hbase.version=2.5.3
else
  echo "The temporarily supported HBase profile versions are [1.2, 1.4, 2.2, 2.5]"
fi

