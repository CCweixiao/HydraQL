package com.github.CCweixiao.hbase.sdk.common;

import com.github.CCweixiao.hbase.sdk.common.mapper.RowMapper;
import com.github.CCweixiao.hbase.sdk.common.model.data.HBaseColData;
import com.github.CCweixiao.hbase.sdk.common.model.data.HBaseRowData;
import com.github.CCweixiao.hbase.sdk.common.model.data.HBaseRowDataWithMultiVersions;
import com.github.CCweixiao.hbase.sdk.common.query.ScanParams;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 定义HBase的数据操作接口
 *
 * @author leojie 2020/9/26 11:04 上午
 */
public interface IHBaseTableOperations {

    /**
     * 保存数据，需构造Map类型的数据参数<br/>
     * 入参data的数据格式举例如下： <br/>
     * {"f1:name": "leo", "f2:age": 18} <br/>
     * <h3>tip:</h3>
     * <p>1. HBase数据都以字节形成存储，存取都需要字段值的类型，才能对值做正确的类型转换，<br/>
     * 因此在此API的封装中，非字符串的基础类型数据，底层会被自动序列化成字符串类型。</p>
     * <p>2. 复杂数据类型，如：List/Map/Java对象等，会先被格式化成JSON字符串后再进行存储，默认使用FastJson处理json格式数据<br/></p>
     * <p>如果对数据类型有要求，请使用方法: {@link IHBaseTableOperations#save(Object)} <br/><p/>
     *
     * @param tableName HBase表名
     * @param rowKey    指定row key，row key默认被限定必须使用String类型
     * @param data      需要保存的数据，自行构造Map<String, Object>结构，样例数据如：入参data的数据格式举例
     */
    void save(String tableName, String rowKey, Map<String, Object> data);

    /**
     * 保存数据，需构造一个Java数据实体对象来绑定属性与HBase列族、字段的对应关系。<br/>
     * 例如：{@link com.github.CCweixiao.hbase.sdk.common.model.example.CityModel} <br/>
     * @see <a href="https://github.com/CCweixiao/hbase-manager/wiki/hbase%E2%80%90sdk-ORM%E7%89%B9%E6%80%A7%E4%BD%BF%E7%94%A8">ORM特性使用文档</a>
     *
     * @param t   数据实体对象
     */
    <T> void save(T t);

    /**
     * 批量保存数据，需构造嵌套Map类型的入参<br/>
     * 例如：<br/>
     * {  <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"row_key1": {"f1:name": "leojie1", "f2:age": 18}, <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"row_key2": {"f1:name": "leojie2", "f2:age": 17} <br/>
     * } <br/>
     * <h3>tip:</h3>
     * <p>1. HBase数据都以字节形成存储，存取都需要字段值的类型，才能对值做正确的类型转换，<br/>
     * 因此在此API的封装中，非字符串的基础类型数据，底层会被自动序列化成字符串类型。</p>
     * <p>2. 复杂数据类型，如：List/Map/Java对象等，会先被格式化成JSON字符串后再进行存储，默认使用FastJson处理json格式数据<br/></p>
     * <p>如果对数据类型有要求，请使用方法: {@link IHBaseTableOperations#save(Object)} <br/><p/>
     *
     * @param tableName 表名
     * @param data      需要保存的数据. 如样例格式数据
     * @return 成功保存数据的条数
     */
    int saveBatch(String tableName, Map<String, Map<String, Object>> data);

    /**
     * 批量保存数据，需构造一个Java数据实体对象来绑定属性与HBase列族、字段的对应关系。<br/>
     * 例如：{@link com.github.CCweixiao.hbase.sdk.common.model.example.CityModel} <br/>
     *
     * @param list 数据实体对象列表
     * @param <T>  泛型类型
     * @return 返回保存成功的数据条数
     * @see <a href="https://github.com/CCweixiao/hbase-manager/wiki/hbase%E2%80%90sdk-ORM%E7%89%B9%E6%80%A7%E4%BD%BF%E7%94%A8">ORM特性使用文档</a>
     */
    <T> int saveBatch(List<T> list);

    /**
     * 根据rowKey查询数据，查询结果映射为一个Java数据实体对象，默认查询所有列簇，所有列
     *
     * @param rowKey rowKey
     * @param clazz  数据实体类型，如：CityModel.class
     * @param <T>    泛型类型
     * @return 结果数据，使用Optional来避免空指针异常
     */
    <T> Optional<T> getRow(String rowKey, Class<T> clazz);

    /**
     * 根据rowKey查询数据，查询结果映射为一个Java数据实体对象，支持指定列簇
     *
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @param clazz      数据实体类型，如：CityModel.class
     * @param <T>        泛型类型
     * @return 结果数据，使用Optional来避免空指针异常
     */
    <T> Optional<T> getRow(String rowKey, String familyName, Class<T> clazz);

    /**
     * 根据rowKey查询数据，查询结果映射为一个Java数据实体对象，支持指定列簇和字段列表
     *
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @param qualifiers 需要筛选的字段名列表
     * @param clazz      数据实体类型，如：CityModel.class
     * @param <T>        泛型类型
     * @return 结果数据，使用Optional来避免空指针异常
     */
    <T> Optional<T> getRow(String rowKey, String familyName, List<String> qualifiers, Class<T> clazz);

    /**
     * 通过row key查询数据，支持指定{@link com.github.CCweixiao.hbase.sdk.common.mapper.RowMapper}实现，
     * 来实现自定义的字段绑定到java对象属性的规则，默认查询所有的列簇和字段
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @param rowMapper 自定义的RowMapper
     * @param <T>       泛型类型
     * @return 查询结果
     * @see <a href="https://github.com/CCweixiao/hbase-manager/wiki/hbase%E2%80%90sdk-ORM%E7%89%B9%E6%80%A7%E4%BD%BF%E7%94%A8">ORM特性使用文档</a>
     */
    <T> Optional<T> getRow(String tableName, String rowKey, RowMapper<T> rowMapper);

    /**
     * 通过row key查询数据，支持指定{@link com.github.CCweixiao.hbase.sdk.common.mapper.RowMapper}实现，
     * 来实现自定义的字段绑定到java对象属性的规则，可以指定列簇名称
     *
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @param rowMapper  自定义的RowMapper
     * @param <T>        泛型类型
     * @return get查询结果
     * @see <a href="https://github.com/CCweixiao/hbase-manager/wiki/hbase%E2%80%90sdk-ORM%E7%89%B9%E6%80%A7%E4%BD%BF%E7%94%A8">ORM特性使用文档</a>
     */
    <T> Optional<T> getRow(String tableName, String rowKey, String familyName, RowMapper<T> rowMapper);

    /**
     * 通过row key查询数据，支持指定{@link com.github.CCweixiao.hbase.sdk.common.mapper.RowMapper}实现，
     * 来实现自定义的字段绑定到java对象属性的规则，可以指定列簇名称和需要的字段列表
     *
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @param qualifiers 需要筛选的字段列表
     * @param rowMapper  自定义的RowMapper
     * @param <T>        泛型类型
     * @see <a href="https://github.com/CCweixiao/hbase-manager/wiki/hbase%E2%80%90sdk-ORM%E7%89%B9%E6%80%A7%E4%BD%BF%E7%94%A8">ORM特性使用文档</a>
     * @return get查询结果
     */
    <T> Optional<T> getRow(String tableName, String rowKey, String familyName, List<String> qualifiers, RowMapper<T> rowMapper);

    /**
     * get查询某一列簇下的数据，支持绑定一个或多个字段，支持指定时间戳，如果指定的时间戳 <= 0，则默认查询最新数据，
     * 使用 自定义的Row Mapper来处理HBase数据字段的解析
     *
     * @param tableName  表名
     * @param rowKey     row key
     * @param familyName 列簇名称
     * @param qualifiers 字段列表
     * @param ts         查询字段的时间戳
     * @param rowMapper  自定义的RowMapper
     * @param <T>        泛型类型
     * @return 查询结果
     */
    // <T> Optional<T> getRowWithTs(String tableName, String rowKey, String familyName, List<String> qualifiers, long ts, RowMapper<T> rowMapper);

    /**
     * get查询数据，返回Map数据类型，<br/>
     * 例如：<br/>
     * {<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp; "f1:name": {"value": "leo", "timestamp": 1667568422619},<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp; "f1:age": {"value": "18", "timestamp": 1667568422619}
     * <br/>}
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     * @return 获取查询结果
     */
    HBaseRowData getToRowData(String tableName, String rowKey);

    /**
     * get查询数据，返回Map数据类型，支持指定一个列簇<br/>
     * 例如：<br/>
     * {<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp; "f1:name": {"value": "leo", "timestamp": 1667568422619},<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp; "f1:age": {"value": "18", "timestamp": 1667568422619}
     * <br/>}
     *
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @return 获取查询结果
     */
    HBaseRowData getToRowData(String tableName, String rowKey, String familyName);

    /**
     * get查询数据，返回Map数据类型，支持指定特定列簇下的字段名称<br/>
     * 例如：<br/>
     * {<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp; "f1:name": {"value": "leo", "timestamp": 1667568422619},<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp; "f1:age": {"value": "18", "timestamp": 1667568422619}
     * <br/>}
     *
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @param qualifiers 需要筛选的字段名
     * @return 获取查询结果
     */
    HBaseRowData getToRowData(String tableName, String rowKey, String familyName, List<String> qualifiers);

    /**
     * 根据row key查询数据，支持指定最大版本号，数据返回格式如下： <br/>
     * {<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"f1:name": [{"value": "值1", timestamp: 2}, {"value": "值2", timestamp: 1}],<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"f2:age": [{"value": "值1", timestamp: 2}, {"value": "值2", timestamp: 1}]<br/>
     * }<br/>
     * tag: 列表中数据，按照时间戳的新旧排序
     *
     * @param tableName  HBase表名
     * @param rowKey     row key
     * @param version    需要查询的版本数
     * @return 查询结果集
     */
    HBaseRowDataWithMultiVersions getToRowDataWithMultiVersions(String tableName, String rowKey, int version);

    /**
     * 根据row key查询数据，支持指定特定列簇，并支持指定最大版本号，数据返回格式如下： <br/>
     * {<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"f1:name": [{"value": "值1", timestamp: 2}, {"value": "值2", timestamp: 1}],<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"f2:age": [{"value": "值1", timestamp: 2}, {"value": "值2", timestamp: 1}]<br/>
     * }<br/>
     * tag: 列表中数据，按照时间戳的新旧排序
     *
     * @param tableName  HBase表名
     * @param rowKey     row key
     * @param familyName 列簇名称，可为空
     * @param version    需要查询的版本数
     * @return 查询结果集
     */
    HBaseRowDataWithMultiVersions getToRowDataWithMultiVersions(String tableName, String rowKey, String familyName, int version);

    /**
     * 根据row key查询数据，支持指定特定列簇下的多个字段名，并支持指定最大版本号，数据返回格式如下： <br/>
     * {<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"f1:name": [{"value": "值1", timestamp: 2}, {"value": "值2", timestamp: 1}],<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;"f2:age": [{"value": "值1", timestamp: 2}, {"value": "值2", timestamp: 1}]<br/>
     * }<br/>
     * tag: 列表中数据，按照时间戳的新旧排序
     *
     * @param tableName  HBase表名
     * @param rowKey     row key
     * @param familyName 列簇名称，可为空
     * @param qualifiers 需要筛选的字段名，可为空
     * @param versions    需要查询的版本数
     * @return 查询结果集
     */
    HBaseRowDataWithMultiVersions getToRowDataWithMultiVersions(String tableName, String rowKey, String familyName, List<String> qualifiers, int versions);

    /**
     * 根据一批RowKey查询所有列簇和字段下的数据，查询结果映射为一个Java Bean列表.
     *
     * @param rowKeys row key list
     * @param clazz   java bean class type
     * @param <T>     泛型类型
     * @return 范型结果集合
     */
    <T> List<T> getRows(List<String> rowKeys, Class<T> clazz);

    /**
     * 根据一批RowKey查询某一个所属列簇的数据，查询结果映射为一个Java Bean列表.
     *
     * @param rowKeys    row key list
     * @param familyName family name
     * @param clazz      java bean class type
     * @param <T>        泛型类型
     * @return 范型结果集合
     */
    <T> List<T> getRows(List<String> rowKeys, String familyName, Class<T> clazz);

    /**
     * 根据一批RowKey查询某一个所属列簇以及某些字段的数据，查询结果映射为一个Java Bean列表.
     *
     * @param rowKeys    row key list
     * @param familyName family name
     * @param qualifiers qualifiers
     * @param clazz      java bean class type
     * @param <T>        泛型类型
     * @return 范型结果集合
     */
    <T> List<T> getRows(List<String> rowKeys, String familyName, List<String> qualifiers, Class<T> clazz);

    /**
     * get查询数据，可以指定自定义的Row Mapper
     *
     * @param tableName 表名
     * @param rowKeys   rowKey列表
     * @param rowMapper 自定义的RowMapper
     * @param <T>       泛型类型
     * @return get查询结果
     */
    <T> List<T> getRows(String tableName, List<String> rowKeys, RowMapper<T> rowMapper);

    /**
     * get查询某一列簇下的数据，可以指定自定义的Row Mapper
     *
     * @param tableName  表名
     * @param rowKeys    rowKey列表
     * @param familyName 列簇名
     * @param rowMapper  自定义的RowMapper
     * @param <T>        泛型类型
     * @return get查询结果
     */
    <T> List<T> getRows(String tableName, List<String> rowKeys, String familyName, RowMapper<T> rowMapper);

    /**
     * get查询某一列簇下的数据，可以指定多个字段，可以指定自定义的Row Mapper
     *
     * @param tableName  表名
     * @param rowKeys    rowKey列表
     * @param familyName 列簇名
     * @param qualifiers 需要筛选的字段列表
     * @param rowMapper  自定义的RowMapper
     * @param <T>        泛型类型
     * @return get查询结果
     */
    <T> List<T> getRows(String tableName, List<String> rowKeys, String familyName, List<String> qualifiers, RowMapper<T> rowMapper);


    /**
     * scan HBase表数据，查询结果集映射为JavaBean列表
     *
     * @param scanParams      scan查询参数
     * @param clazz           结果集映射的JavaBean类型
     * @param <T>             泛型类型
     * @return 结果数据
     */
    <T> List<T> scan(ScanParams scanParams, Class<T> clazz);

    /**
     * scan 所有数据
     *
     * @param tableName       表名
     * @param scanParams scan查询参数
     * @param rowMapper       自定义的RowMapper
     * @param <T>             泛型类型
     * @return 查询结果集
     */
    <T> List<T> scan(String tableName, ScanParams scanParams, RowMapper<T> rowMapper);


    /**
     * scan查询数据，返回Map列表类型，例如：["1001": {"info:name": "leo", "info:age": 18}]
     *
     * @param tableName  表名
     * @param scanParams scan查询参数
     * @return 查询结果
     */
    List<HBaseRowData> scan(String tableName, ScanParams scanParams);

    /**
     * 传入模型类，删除数据
     *
     * @param t   数据模型类
     * @param <T> 泛型类型
     */
    <T> void delete(T t);

    /**
     * 根据RowKey删除数据
     *
     * @param tableName 表名
     * @param rowKey    rowKey
     */
    void delete(String tableName, String rowKey);

    /**
     * 根据RowKey删除某一列簇下的数据
     *
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇名
     */
    void delete(String tableName, String rowKey, String familyName);

    /**
     * 根据RowKey删除某一列簇下的数据，同时可以指定字段名
     *
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @param qualifiers 多个字段名
     */
    void delete(String tableName, String rowKey, String familyName, List<String> qualifiers);

    /**
     * 根据RowKey删除某一列簇下的数据，同时可以指定字段名
     *
     * @param tableName  表名
     * @param rowKey     rowKey
     * @param familyName 列簇名
     * @param qualifiers 多个字段名
     */
    void delete(String tableName, String rowKey, String familyName, String... qualifiers);

    /**
     * 传入模型类，删除数据
     *
     * @param list 数据模型类列表
     * @param <T>  泛型类型
     */
    <T> void deleteBatch(List<T> list);

    /**
     * 根据RowKey 批量删除数据
     *
     * @param tableName 表名
     * @param rowKeys   rowKey列表
     */
    void deleteBatch(String tableName, List<String> rowKeys);

    /**
     * 根据RowKey批量删除某一列簇下的数据
     *
     * @param tableName  表名
     * @param rowKeys    rowKey列表
     * @param familyName 列簇名
     */
    void deleteBatch(String tableName, List<String> rowKeys, String familyName);

    /**
     * 根据RowKey批量删除某一列簇以及指定字段下的数据
     *
     * @param tableName  表名
     * @param rowKeys    rowKey列表
     * @param familyName 列簇名
     * @param qualifiers 多个字段名
     */
    void deleteBatch(String tableName, List<String> rowKeys, String familyName, List<String> qualifiers);

    /**
     * 根据RowKey批量删除某一列簇以及指定字段下的数据
     *
     * @param tableName  表名
     * @param rowKeys    rowKey列表
     * @param familyName 列簇名
     * @param qualifiers 多个字段名
     */
    void deleteBatch(String tableName, List<String> rowKeys, String familyName, String... qualifiers);

}
