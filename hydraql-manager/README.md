## 1. 编译插件

```shell
cd /项目根目录

mvn -T 2C clean -U install -pl hydraql-manager/plugins/ -Dmaven.test.skip=true -Dhydraql-hbase.profile=1.2 -Dhydraql.hbase.version=1.2.0
mvn -T 2C clean -U install -pl hydraql-manager/plugins/ -Dmaven.test.skip=true -Dhydraql-hbase.profile=1.4 -Dhydraql.hbase.version=1.4.3
mvn -T 2C clean -U install -pl hydraql-manager/plugins/ -Dmaven.test.skip=true -Dhydraql-hbase.profile=2.2 -Dhydraql.hbase.version=2.2.6
```