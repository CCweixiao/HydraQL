<p align="center">
	<a href="https://hydraql.com"><img src="https://leo-jie-pic.oss-cn-beijing.aliyuncs.com/hydraql.jpg" width="45%"></a>
</p>
<p align="center">
	<strong>🍬Designed to simplify the use of HBase's client API.</strong>
</p>
<p align="center">
	👉 <a href="https://hydraql.com">https://hydraql.com/</a> 👈
</p>
<p align="center">
	<a target="_blank" href="https://search.maven.org/artifact/com.hydraql/hydraql">
		<img src="https://img.shields.io/maven-central/v/com.hydraql/hydraql.svg?label=Maven%20Central" />
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

`HydraQL`是基于HBase的client API设计的一款SQL查询器，专为简化HBase原生API的使用而打造。

`HydraQL`旨在提供一种更直观、易用的方式来查询和操作HBase数据库。通过使用SQL语法或更精简的API，用户可以通过简单的查询语句访问和操作HBase中的数据，而无需深入了解和编写复杂的`hbase-client`API调用。


### 🎁HydraQL的名称由来

`HydraQL`由Hydra + SQL 拼接而来，其名称**Hydra**引用了“九头蛇”，象征着在处理HBase数据时的灵活性、多功能性和适应性。
**hql** 是其简称，一种类SQL的语言，在执行时，会被翻译成`hbase-client`的原生API与HBase集群进行数据读写。


### 🍺HydraQL理念

`HydraQL` 是HBase的衍生工具集，它融合了hql、hbtis、hbase-shell、hbase-thrift等功能，你可以按需引用，也可以**拷贝**和修改使用，这并不会有什么限制，只希望能及时反馈bug，或提供更好的建议。


-------------------------------------------------------------------------------

## 🛠️模块信息

项目结构：

```shell
├── LICENSE
├── README.md
├── antlr
├── bin
├── build
├── hydraql-adapter
│   ├── hydraql-adapter-common
│   ├── hydraql-adapter_1.2
│   ├── hydraql-adapter_1.4
│   ├── hydraql-adapter_2.2
├── hydraql-common
├── hydraql-console
├── hydraql-dsl
├── hydraql-examples
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

| 模块                |     介绍                                                                          |
| -------------------|---------------------------------------------------------------------------------- |
| hydraql-common        |     一些公共逻辑的封装                                            |
| hydraql-dsl | hql定义，antr4解析hql，并转换hbase-client的调用 |
| hydraql-adapter | 统一HBase数据读写接口，并针对不同版本的`hbase-client` api进行适配和增强，屏蔽了多版本hbase-client api不兼容 |
| hydraql-template |     基于hydraql-adapter，对外统一暴露为模版类和模版方法                            |
| spring-boot-starter-hydraql |     可以利用spring-boot-starter-hydraql与Spring Boot轻松集成     |
| hydraql-thrift |     对HBase thrift API的池化和封装                 |
| hydraql-shell |     对HBase Shell的封装，支持直接在java进程中执行hbase-shell的JRuby环境，可以利用该模块，封装web-hbase-shell     |
| hydraql-console |     hql的命令行工具                                 |


## 🛠️功能特性

对`hbase-client`各版本的原生API进行了统一的接口定义，消除了跨HBase版本升级过程中API不兼容的隐患，屏蔽了底层API的复杂调用方式。
在保留原有功能的同时，额外扩展了其他特性，如下：

* [x] 定义了统一的接口规范，消除了HBase不同版本原生API之间的差异
* [x] HQL，以类SQL的形式读写HBase的表中数据
* [x] 以类MyBatis的方式读写HBase，提供**ORM**的特性，以注解的方式快速实现表、列簇、字段模型与java实体类中属性进行绑定
* [x] 对HBase的原生thrift API进行池化封装，提供了HBaseThriftPool的功能
* [x] 利用spring-boot-starter-hydraql可与SpringBoot无缝集成
* [x] 内置kerberos认证方式，支持kerberos用户代理
* [x] hedged.read功能，支持读主集群超时或异常，自动降级读备集群
* [x] hydraql-shell，把hbase-shell封装到一个jar包中，可被其他项目通过maven等方式引用，这在你想开发hbase-web-shell时非常有用
* [x] hydraql-console，命令行交互程序，可以同时执行hql和hbase-shell的指令
* [ ] HBatis，类似于myBatis，提供配置文件管理HQL的功能（规划中）
* [ ] thrift 连接池中连接数的动态扩所容能力（规划中）



-------------------------------------------------------------------------------

## 📝文档

[📘中文文档](https://www.hydraql.com/docs/)

[📙参考API](https://apidoc.gitee.com/weixiaotome/HydraQL/)

[🎬视频介绍](https://www.bilibili.com/video/B)


-------------------------------------------------------------------------------

## 🪙支持HydraQL

### 💳捐赠

如果你觉得HydraQL不错，可以捐赠请维护者吃包辣条~，在此表示感谢^_^。


-------------------------------------------------------------------------------

## 📦安装

### 🍊Maven
在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>com.hydraql</groupId>
    <artifactId>hydraql</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 🍐Gradle
```
implementation 'com.hydraql:hydraql:1.0.0'
```

- [Maven中央库](https://repo1.maven.org/maven2/com/hydraql/hydraql/1.0.0/)

> 🔔️注意
> 最新版本依赖包可能更新不及时，可以选用编译安装后，再在项目中引入

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

根据所需HBase的版本，激活不同的profile，然后就可以使用Maven引入了。

-------------------------------------------------------------------------------

## 🏗️添砖加瓦

### 🎋分支说明

HydraQL的源码分为两个分支，功能如下：

| 分支       | 作用                                                          |
|-----------|---------------------------------------------------------------|
| master | 主分支，release版本使用的分支，与中央库提交的jar一致，不接收任何pr或修改 |
| dev    | 开发分支，默认为下个版本的SNAPSHOT版本，接受修改或pr                 |

### 🐞提供bug反馈或建议

提交问题反馈请说明正在使用的JDK版本呢、HydraQL版本和相关依赖库版本。

- [Gitee issue](https://gitee.com/weixiaotome/HydraQL/issues)
- [Github issue](https://github.com/CCweixiao/HydraQL/issues)


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

