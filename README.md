<p align="center">
	<a href="https://hydraql.com"><img src="http://leo-jie-pic.oss-cn-beijing.aliyuncs.com/blog/4imexl.png" width="45%"></a>
</p>
<p align="center">
	<strong>🍬 HydraQL [ˈhaɪdrəQL]，是基于HBase原生客户端API设计的一款SQL查询器，专为简化HBase的使用而打造。</strong>
</p>
<p align="center">
	👉 <a href="https://hydraql.com">https://hydraql.com/</a> 👈
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/artifact/com.hydraql/hydraql">
		<img src="https://img.shields.io/:maven3+-maven-blue.svg" />
	</a>
	<a target="_blank" href="https://opensource.org/license/mit/">
		<img src="https://img.shields.io/:license-mit-blue.svg" />
	</a>
	<a target="_blank" href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
</p>


-------------------------------------------------------------------------------

[**🌎English Documentation**](README-EN.md)

-------------------------------------------------------------------------------

## 📚简介

`HydraQL` 是基于 `hbase-clinet` API设计的一款SQL查询器，专为简化HBase的使用而打造。

`HydraQL`旨在提供一种更直观和易用的方式来查询和操作HBase数据库。通过使用SQL语法或更精简的API，
用户就可以轻松读写HBase表中的数据，而无需深入了解和编写复杂的方法调用。

与Phoenix相比，`HydraQL`中的SQL语法更轻量，无需引入额外的组件和配置即可使用，但目前还不支持二级索引。


### 🎁HydraQL的名称由来

`HydraQL`由Hydra + SQL 拼接而来，其名称**Hydra**引用了“九头蛇”，象征着在处理HBase数据时的灵活性和多功能性。
**hql** 是其简称，一种类SQL的语言，其在执行时，会被翻译成`hbase-client`的原生API来读写HBase表中的数据。


### 🍺HydraQL的理念

`HydraQL` 是HBase的衍生工具集，它融合了hql、HBatis、hbase-shell、hbase-thrift等API的功能，你可以按需引用，也可以**拷贝**和修改源码使用，
这并不会有什么限制，只希望能及时反馈bug，或提供更好的建议。


-------------------------------------------------------------------------------

## ⚙️模块信息

项目结构：

```shell
├── LICENSE
├── README.md
├── antlr
├── bin
├── build
├── hydraql-adapter
│   ├── hydraql-adapter-core
│   ├── hydraql-adapter_1.2
│   ├── hydraql-adapter_1.4
│   ├── hydraql-adapter_2.2
├── hydraql-core
├── hydraql-console
├── hydraql-dsl
├── hydraql-examples
├── hydraql-tests
│   ├── hydraql-example
│   ├── hydraql-shell-example
│   └── spring-boot-starter-hydraql-example
├── hydraql-shell
│   ├── hydraql-shell-core
│   ├── hydraql-shell_1.2
│   ├── hydraql-shell_1.4
│   ├── hydraql-shell_2.2
├── hydraql-template
├── hydraql-thrift
└── spring-boot-starter-hydraql
```


核心模块介绍：

| 模块                          | 介绍                                                                          |
|-----------------------------|-----------------------------------------------------------------------------|
| hydraql-core                | 对一些公共方法的封装                                                                  |
| hydraql-dsl                 | hql的定义，以及使用antr4解析hql，并转换hbase-client的调用                                    |
| hydraql-adapter             | 统一HBase数据读写的接口，并针对不同版本的`hbase-client` api进行适配和增强，屏蔽了多版本下hbase-client api不兼容 |
| hydraql-template            | 依赖hydraql-adapter，对外统一暴露为模版类和模版方法                                           |
| hydraql-tests               | 利用HBaseMiniCluster来做单元测试                                                    |
| spring-boot-starter-hydraql | 可以利用spring-boot-starter-hydraql与Spring Boot轻松集成                             |
| hydraql-thrift              | 对HBase thrift API的池化和封装                                                     |
| hydraql-shell               | 对HBase Shell的封装，支持直接在java进程中执行hbase-shell的JRuby环境，可以利用该模块，封装web-hbase-shell |
| hydraql-console             | hql的命令行交互程序                                                                 |


## 🛠️功能特性

对`hbase-client`原生API进行了统一的接口定义，屏蔽了底层API的复杂调用方式，消除了跨版本升级过程中API不兼容的问题。
在保障原有功能的同时，额外扩展了其他优秀特性，列举如下：

* [x] 定义了统一的接口规范，消除了不同版本`hbase-client`API之间的差异
* [x] HQL，以类SQL的形式读写HBase的表中数据
* [x] HBatis, 类似MyBatis，提供**ORM**的特性，支持以注解的方式快速定义表、列簇、字段的数据模型，在保存和查询数据时，底层自动做数据类型转换
* [x] 对HBase的原生thrift API进行池化封装，提供了HBaseThriftPool的功能
* [x] 利用spring-boot-starter-hydraql可与SpringBoot无缝集成
* [x] 支持kerberos认证，支持kerberos认证的超级用户代理普通用户
* [x] 支持类似hdfs的hbase.client.hedged.read功能，在读主集群达到超时阈值或异常时，自动降级读备集群数据，此功能要求HBase主备集群互相Replication
* [x] hydraql-shell，把hbase-shell封装到一个jar包中，可被其他项目通过maven等方式依赖，这在你想开发hbase-web-shell的功能时非常有用
* [x] hydraql-console，命令行交互工具，可以同时执行hql和hbase-shell的指令，可完全替代hbase-shell来使用
* [ ] HBatis，类似于myBatis，提供配置文件管理HQL的功能（规划中）
* [ ] thrift 连接池中连接数的动态扩所容能力（规划中）


-------------------------------------------------------------------------------

## 📝文档

[📘中文文档](https://www.docs.hydraql.com)

[📙参考API](https://apidoc.gitee.com/weixiaotome/HydraQL/)

[🎬视频介绍](https://www.bilibili.com/video/B)

-------------------------------------------------------------------------------

## 🪙支持HydraQL

### 💳捐赠

如果你觉得HydraQL不错，可以请维护者吃包辣条~，在此表示感谢^_^。

<p align="center">
  <a target="_blank">
     <img alt="赞助" src="http://leo-jie-pic.oss-cn-beijing.aliyuncs.com/blog/9zbk1v.png?raw=true" />
  </a>
</p>

-------------------------------------------------------------------------------

## 📦使用指南

### 🍊Maven

在普通maven项目中使用，在项目的pom.xml的dependencies中加入以下内容:

```xml
<properties>
    <hydraql.hbase.adapter.version>1.2</hydraql.hbase.adapter.version>
    <!--    <hydraql.hbase.adapter.version>1.4</hydraql.hbase.adapter.version>-->
    <!--    <hydraql.hbase.adapter.version>2.2</hydraql.hbase.adapter.version>-->
</properties>

<dependency>
    <groupId>com.hydraql</groupId>
    <artifactId>hydraql-template_${hydraql.hbase.adapter.version}</artifactId>
    <version>1.0.0</version>
</dependency>
```

在Spring Boot项目中使用，在项目的pom.xml的dependencies中加入以下内容:

```xml
<properties>
    <hydraql.hbase.adapter.version>1.2</hydraql.hbase.adapter.version>
    <!--    <hydraql.hbase.adapter.version>1.4</hydraql.hbase.adapter.version>-->
    <!--    <hydraql.hbase.adapter.version>2.2</hydraql.hbase.adapter.version>-->
</properties>

<dependency>
    <groupId>com.hydraql</groupId>
    <artifactId>spring-boot-starter-hydraql_${hydraql.hbase.adapter.version}</artifactId>
    <version>1.0.0</version>
</dependency>
```

根据所需hbase的版本来指定不同的`hydraql.hbase.adapter.version`，目前支持的hbase版本有：1.2.x，1.4.x，2.0.x ~ 2.2.x，

更多HBase版本的支持还在持续构建中 ～

- [Maven中央库](https://repo1.maven.org/maven2/com/hydraql/hydraql/1.0.0/)

> 🔔️注意
> 最新版本的发行包可能更新不及时，同时，hydraql在构建发行包时，默认把hbase--shaded-client的依赖一起打包。
> 如果对hbase的版本要求强一致，可以选择修改hbase-client的版本后，自行编译安装，再在项目中引入


### 🍐Gradle
```
implementation 'com.hydraql:hydraql-template_1.2:1.0.0'
implementation 'com.hydraql:hydraql-template_1.4:1.0.0'
implementation 'com.hydraql:hydraql-template_2.2:1.0.0'
```

### 🚽编译安装

访问`HydraQL`的GItHub主页：[https://github.com/CCweixiao/HydraQL](https://github.com/CCweixiao/HydraQL) 下载整个项目源码（master分支）然后进入HydraQL项目的根目录下执行：

```sh
sh bin/build.sh 1.2/1.4/2.2
```

或

```shell
# 默认1.2
mvn clean install -Dmaven.test.skip=true

# 1.4
mvn clean install -Dmaven.test.skip=true -Dhbase.profile=1.4

# 2.2 
mvn clean install -Dmaven.test.skip=true -Dhbase.profile=2.2
```

如果你想在本地进行开发，扩展额外的功能，请确保已经安装了Java8和maven3.6+，同时建议在本地部署一个可连通的HBase开发环境。
建议使用docker来快速搭建一个HBase的单机环境，可以参考博客：https://blog.csdn.net/feinifi/article/details/121174846

### 快速入门

```java
@Test
public void testSaveAndGet(){
    Configuration conf = HBaseConfiguration.create();
    HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);

    Map<String, Object> data = new HashMap<>(2);
    data.put("f1:name", "leo");
    data.put("f1:age", 18);
    tableTemplate.save("test_table", "1001", data);
    HBaseRowData rowData = tableTemplate.getRow("test_table", GetRowParam.of("1001").build());
}
```
更多测试用例请参考：[更多测试用例](https://github.com/CCweixiao/HydraQL/blob/master/hydraql-tests/hydraql-template-test/src/test/java/com/hydraql/template/HBaseTableTestTemplateTest.java)


## 使用手册

### 连接配置

**普通Java项目**

**普通认证**

```java
// 普通认证
Configuration conf = HBaseConfiguration.create();
conf.set("hbase.zookeeper.quorum", "myhbase");
conf.set("hbase.zookeeper.property.clientPort", "2181");
// 请按需引入一些额外所需的客户端配置
conf.set("hbase.client.retries.number", "3");
HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);
```
或者在项目的src/main/resources目录下放入`hbase-site.xml`配置文件

**Kerberos认证**
```java
Configuration conf = HBaseConfiguration.create();
conf.set("hbase.zookeeper.quorum", "zk1,zk2,zk3");
conf.set("hbase.zookeeper.property.clientPort", "2181");
conf.set("hbase.security.authentication", "kerberos");

// 下面配置是kerberos认证方式所需
conf.set("kerberos.principal", "hbase@HADOOP.LEO.COM");
conf.set("keytab.file", "/etc/hbase/conf/hbase.keytab");
// 设置kerberos代理用户，默认为空即不进行设置，需保证hbase有代理其他用户的权限
conf.set("kerberos.proxy.user", "leojie");
conf.set("hbase.regionserver.kerberos.principal", "hbase/_HOST@HADOOP.LEO.COM");
conf.set("hbase.master.kerberos.principal", "hbase/_HOST@HADOOP.LEO.COM");
// 指定kdc服务相关的配置方式有如下两种：
// 方式一：指定krb5.conf路径
conf.set("java.security.krb5.conf", "/etc/krb5.conf");
// 方式二：指定java.security.krb5.realm和java.security.krb5.kdc
// conf.set("java.security.krb5.realm", "HADOOP.LEO.COM");
// conf.set("java.security.krb5.kdc", "你自己的kdc服务地址");
// 一些额外的客户端参数
conf.set("hbase.client.retries.number", "3");
HBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);
```

**Spring Boot项目**

**普通认证**

application.yaml

```yaml
spring:
  datasource:
    hbase:
      zk-quorum: zk_host1,zk_host2,zk_host3
      zk-client-port: 2181 # (可选，默认2181)
      dfs-root-dir: /hbase # (可选，默认/hbase)
      zk-node-parent: /hbase  # (可选，默认/hbase)
      security-auth-way: simple # (可选，默认simple)
      client-properties: hbase.client.retries.number=3;key1=value2
server:
  port: 8088
```

**Kerberos认证**

```yaml
spring:
  datasource:
    hbase:
      zk-quorum: myhbase
      zk-client-port: 2181
      dfs-root-dir: /hbase
      zk-node-parent: /hbase
      security-auth-way: kerberos
      kerberos-principal: hbase@HADOOP.LEO.COM
      keytab-file-path: /etc/hbase/conf/hbase.keytab
      kerberos-proxy-user: test
      rs-kerberos-principal: hbase/_HOST@HADOOP.LEO.COM
      master-kerberos-principal: hbase/_HOST@HADOOP.LEO.COM
      krb5-conf-path: /etc/krb5.conf
      krb5-realm:
      krb5-kdc-server-addr:
      client-properties: hbase.client.retries.number=3
server:
  port: 8088
```

```java
@Service
public class HBaseAdminService {
    @Autowired
    private BaseHBaseAdminTemplate adminTemplate;

    public List<String> allTables() {
        return adminTemplate.listTableNames();
    }
}
```

### hydraql-template模块介绍

`hydraql-template`模块中封装了三类模版操作类，分别是：

- HBaseAdminTemplate: 对Admin中方法的封装，比如：创建表，创建namespace，list表等
- HBaseTableTemplate: 对Table中方法的封装，比如：put，get，scan，delete等
- HBaseSqlTemplate: 提供hql的功能

![UML](http://leo-jie-pic.oss-cn-beijing.aliyuncs.com/blog/r8hxhq.png)

**普通项目**

```java
// 数据读写API的操作模版类
Configuration conf = HBaseConfiguration.create();
BaseHBaseTableTemplate tableTemplate = HBaseTableTemplate.of(conf);

// Admin操作模版类
BaseHBaseAdminTemplate adminTemplate = HBaseAdminTemplate.of(conf);

// HQL操作模版类
BaseHBaseSqlTemplate sqlTemplate = HBaseSqlTemplate.of(conf);
```

**SpringBoot项目**

@Autowired 依赖注入

```java
@Service
public class UserService {
    @Autowired
    private BaseHBaseTableTemplate tableTemplate;
    @Autowired
    private BaseHBaseAdminTemplate adminTemplate;
    @Autowired
    private BaseHBaseSqlTemplate sqlTemplate;
}
```

### BaseHBaseAdminTemplate使用

`BaseHBaseAdminTemplate`封装了`hbase-client`中Admin的常用操作，比如namespace的创建和删除、表的创建和删除、以及快照管理等等，后续这些API将会更加完整。

![admin-api](https://leo-jie-pic.oss-cn-beijing.aliyuncs.com/leo_blog/2020-11-29-120523.jpg)

**创建namespace**

```java
@Test
public void createNameSpace() {
    NamespaceDesc namespaceDesc = new NamespaceDesc();
    namespaceDesc.setNamespaceName("test_nn");
    namespaceDesc.addNamespaceProp("createdBy", "leojie");
    adminTemplate.createNamespaceAsync(namespaceDesc);
}
```

**创建表**

```java
@Test
public void testCreateTable() {
    ColumnFamilyDesc f1 = ColumnFamilyDesc.newBuilder()
            .name("f1")
            .build();
    ColumnFamilyDesc f2 = ColumnFamilyDesc.newBuilder()
            .name("f2")
            .timeToLive(3600)
            .maxVersions(3)
            .build();
    HTableDesc tableDesc = HTableDesc.newBuilder()
            .name("leo_test_22222")
            .addFamilyDesc(f1)
            .addFamilyDesc(f2)
            .build();
    adminTemplate.createTable(tableDesc);
}
```
更多测试用例请参考 `hydraql-tests`模块


### BaseHBaseTableTemplate使用

`BaseHBaseTableTemplate`中封装了对HBase表数据的读、写、删除等操作。

**普通方式读写**

```java
@Test
public void testSaveMap() {
    Map<String, Object> data = new HashMap<>(2);
    data.put("f1:name", "leo");
    data.put("f1:age", 18);
    tableTemplate.save(TEST_TABLE, "1001", data);
    HBaseRowData rowData = tableTemplate.getRow(TEST_TABLE, GetRowParam.of("1001").build());
    Assert.assertEquals(2, rowData.getColDataContainer().size());
}
```

**ORM特性**

1. 创建数据模型类

```java
public class CityTag {
    private String tagName;

    public CityTag(String tagName) {
        this.tagName = tagName;
    }
		// 省略Getter/Setter/toString
}
```

```java
@HBaseTable(namespaceName = "default", tableName = "test_table", defaultFamilyName = "f1")
public class CityModel {
    @HBaseRowKey
    private String cityId;
    private String cityName;
    private String cityAddress;
    @HBaseColumn(familyName = "detail")
    private Integer cityArea;
    @HBaseColumn(familyName = "detail", toUpperCase = true)
    private Integer totalPopulation;
    @HBaseColumn(familyName = "detail", columnName = "cityTagList")
    private List<CityTag> cityTagList;
  	// 省略Getter/Setter/toString
}
```

`@HBaseTable`注解用于定义HBase的表信息

```java
@HBaseTable(namespaceName = "default", tableName = "t2", defaultFamilyName = "info")
```

1）namespaceName：用于指定该表的命名空间，默认：default
2）tableName：用于指定该表的表名，如果不指定，表名则为类名的组合单词拆分转小写加'_'拼接，如：CityModel对应的表名为：city_model。
3）defaultFamilyName：用于定义如果有字段不特配置（@HBaseRowKey注解中的familyName）列簇名，则使用此处配置的列簇名。

`@HBaseRowKey`注解用于定义某一个属性字段是用作存储rowKey数据的，是必须要设置的，如：

```java
@HBaseRowKey
private String cityId;
```
该注解表示cityId字段为rowKey，每一个模型类必须，也只能有一个字段被标识为rowKey。

`@HBaseColumn`注解用于定义HBase的列簇和列名信息，如：

```java
@HBaseColumn(familyName = "detail", columnName = "TOTAL_POPULATION",  toUpperCase = true)
private Integer totalPopulation;
```

1）familyName：指定列簇名，不指定，则使用defaultFamilyName配置的列簇名。
2）columnName：指定列名，不指定则默认使用字段名的组合单词拆分转小写加'_'拼接，如：isVip，对应的字段名是：is_vip
3）toUpperCase：定义字段名是否转大写，如：isVip -> IS_VIP，默认值：false，不做转换。

2. 存取数据

```java
@Test
public void testSave() {
    CityModel cityModel = CityModelUtil.createDefaultCityModel();
    tableTemplate.save(cityModel);
    GetRowParam getRowParam = GetRowParam.of(cityModel.getCityId()).build();
    Optional<CityModel> cityModelRes = tableTemplate.getRow(getRowParam, CityModel.class);
    Assert.assertNotNull(cityModelRes);
}
```

3. 批量存取数据

```java
@Test
public void testSaveBatch() {
    List<CityModel> cityModelList = CityModelUtil.createDefaultCityModelList();
    tableTemplate.saveBatch(cityModelList);
    List<String> rowKeys = cityModelList.stream().map(CityModel::getCityId).collect(Collectors.toList());
    GetRowsParam getRowsParam = GetRowsParam.of().rowKeyList(rowKeys).build();
    List<CityModel> rows = tableTemplate.getRows(getRowsParam, CityModel.class);
    Assert.assertEquals(rowKeys.size(), rows.size());
}
```

4. 查询数据使用自定义的RowMapper解析字段

```java
@Test
public void testGetByRowMapper() {
    GetRowParam getRowParam = GetRowParam.of("a10001").build();
    Map<String, String> data = tableTemplate.getRow(TEST_TABLE, getRowParam, new RowMapper<Map<String, String>>() {
        @Override
        public <R> Map<String, String> mapRow(R r, int rowNum) throws Exception {
            Result result = (Result) r;
            if (result == null) {
                return new HashMap<>(0);
            }
            Map<String, String> data = new HashMap<>(2);
            for (Cell cell : result.listCells()) {
                data.put(Bytes.toString(CellUtil.cloneFamily(cell)) + ":" +Bytes.toString(CellUtil.cloneQualifier(cell)),
                        Bytes.toString(CellUtil.cloneValue(cell)));
            }
            return data;
        }
    }).orElse(new HashMap<>(0));
    Assert.assertEquals(5, data.size());
}
```

5. scan查询，自定义Filter

```java
@Test
public void testScanWithCustomFilter() {
    testSaveBatch();
    ScanParams scanParams = ScanParams.of()
            .filter(new IHBaseFilter<Filter>() {
                @Override
                public Filter customFilter() {
                    List<Filter> filters = new ArrayList<>(2);
                    // 筛选row key 大于b20001的数据
                    Filter rowFilter = new RowFilter(CompareFilter.CompareOp.GREATER,
                            new BinaryComparator("b20001".getBytes()));
                    // 筛选列前缀city_address的数据
                    ColumnPrefixFilter columnPrefixFilter = new ColumnPrefixFilter(Bytes.toBytes("city_address"));
                    // 筛选列值与深圳市相等的数据
                    ValueFilter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("深圳市")));
                    // 多过滤器，注意顺序
                    filters.add(rowFilter);
                    filters.add(columnPrefixFilter);
                    filters.add(valueFilter);
                    // 需所有条件全部通过
                    FilterList andFilterList = new FilterList(FilterList.Operator.MUST_PASS_ALL, filters);
                    // 满足其中一个条件即可
                    FilterList orFilterList = new FilterList(FilterList.Operator.MUST_PASS_ONE, filters);
                    return andFilterList;
                }
            })
            .build();
    List<CityModel> cityModels = tableTemplate.scan(scanParams, CityModel.class);
    Assert.assertEquals(1, cityModels.size());
}
```

6. 多版本查询

使用HBaseRowDataWithMultiVersions结构来接收多版本数据

```java
@Test
public void testGetMultiVersions() throws InterruptedException {
    Map<String, Object> data1 = new HashMap<>(3);
    tableTemplate.save(TEST_TABLE, "b1", data1);
    Map<String, Object> data2 = new HashMap<>(3);
    tableTemplate.save(TEST_TABLE, "b1", data2);
    Map<String, Object> data3 = new HashMap<>(3);
    tableTemplate.save(TEST_TABLE, "b1", data3);
    Map<String, Object> data4 = new HashMap<>(3);
    tableTemplate.save(TEST_TABLE, "b1", data4);
    Map<String, Object> data5 = new HashMap<>(3);
    tableTemplate.save(TEST_TABLE, "b1", data5);
    // 省略数据构造过程
    GetRowParam getRowParam = GetRowParam.of("b1").versions(5).build();
    HBaseRowDataWithMultiVersions rowData = tableTemplate.getWithMultiVersions(TEST_TABLE, getRowParam);
    Assert.assertEquals(3, rowData.getColDataWithMultiVersions().size());
    Assert.assertEquals(4, rowData.getColDataWithMultiVersions().get("info:name").size());
}
```

### HQL

使用前首先构造`BaseHBaseSqlTemplate`

```java
Configuration conf = HBaseConfiguration.create();
BaseHBaseSqlTemplate sqlTemplate = HBaseSqlTemplate.of(conf);
```

**1. 创建表schema**

使用`create virtual table`语句来创建表的schema，如果HBase集群中没有表`HQL.META_DATA`，会先创建，然后把表解析出来的
表模型HBaseTableSchema对象，以json的格式存储在这张元数据表中，之后每次select或insert时，加载出表schema的元数据。

```java
@Test
public void testCreateVirtualTable) {
    String hql = "create virtual table test:test_sql (\n" +
            " row_key string isrowkey,\n" +
            " f1:id string nullable,\n" +
            " f1:name string nullable,\n" +
            " f1:age int nullable,\n" +
            " f1:job string nullable,\n" +
            " f1:pay double nullable,\n" +
            " f2:address string nullable,\n" +
            " f2:commuter string nullable\n" +
            " );";
    sqlTemplate.createVirtualTable(hql1);`
}
```
virtualTable的名称要与hbase的实际表名一致，且只能存在一个。
也可以选择在执行HQL前调用sqlTemplate.registerTableSchema方法来手动注册某张表的Schema，只有注册成功表的Schema之后，才能使用hql。

打印schema

![printSchema](http://leo-jie-pic.oss-cn-beijing.aliyuncs.com/blog/ela83.png)

**2. 删除表的schema**

```java
String hql = "drop virtual table test:test_sql;";
sqlTemplate.dropVirtualTable(hql2);
```
删除虚拟表的操作，不会对HBase的原表产生任何影响。

**3. Insert**

插入一条数据

```sql
insert into test:test_sql (row_key, f1:id , f1:name , f1:age ) values ('r1', 'id1_v1' , 'leo1_v1' , 11 )
```

插入多条数据

```sql
insert into test:test_sql (row_key, f1:id , f1:name , f1:age )
values ('r1', 'id1_v1' , 'leo1_v1' , 11 ),
('r2', 'id2_v1' , 'leo2_v1' , 21 ),
('r3', 'id3_v1' , 'leo3_v1' , 31 )
```

插入数据时，使用内置的rowKey自定义函数

```sql
insert into test:table ( f:id , f:name,
                 f:age, f2:pay, f3:detail) VALUES(md5('1001'),'张三', 23.32,null )
```

**4. Select**

```sql
select * from test:test_sql where rowKey = 'a10001'
```

![select_by_row](http://leo-jie-pic.oss-cn-beijing.aliyuncs.com/blog/jz8pd.png)


```sql
select * from test:test_sql where rowKey in ( 'a10001' , 'a10002' , 'a10003' )
```

![in_row_keys](http://leo-jie-pic.oss-cn-beijing.aliyuncs.com/blog/7cnqd.png)

**4. Delete**

```sql
delete * from test:test_sql where rowKey = 'b20004';
delete f1:age from test:test_sql where rowKey = 'b20004'
```

-------------------------------------------------------------------------------

## 🏗️添砖加瓦

### 🎋分支说明

HydraQL的源码分为两个分支，功能如下：

| 分支       | 作用                                                          |
|-----------|---------------------------------------------------------------|
| master | 主分支，release版本使用的分支，与中央库提交的jar一致，不接收任何pr或修改 |
| dev    | 开发分支，默认为下个版本的SNAPSHOT版本，接受修改或pr                 |

### 🐞提供bug反馈或建议

提交问题反馈时请说明正在使用的JDK版本、HydraQL版本和相关依赖库的版本。

- [Gitee issue](https://gitee.com/weixiaotome/HydraQL/issues)
- [Github issue](https://gitee.com/weixiaotome/hydra-ql/issues)


### 🧬贡献代码的步骤

1. 在Gitee或者Github上fork项目到自己的repo
2. 把fork过去的项目也就是你的项目clone到你的本地
3. 修改代码（记得一定要修改dev分支）
4. commit后push到自己的库（dev分支）
5. 登录Gitee或Github在你首页可以看到一个 pull request 按钮，点击它，填写一些说明信息，然后提交即可。
6. 等待维护者合并

### 📐PR遵照的原则

-------------------------------------------------------------------------------

## ⭐Star HydraQL



## 📌 知识星球

