package com.hydraql.adapter;

import com.hydraql.adapter.schema.BaseColumnFamilyDesc;
import com.hydraql.adapter.schema.BaseHTableDesc;
import com.hydraql.common.model.NamespaceDesc;
import com.hydraql.common.model.SnapshotDesc;
import com.hydraql.common.util.SplitGoEnum;
import org.apache.hadoop.hbase.TableName;
import org.apache.yetus.audience.InterfaceAudience;

import java.util.List;
import java.util.Map;

/**
 * <p>该接口对管理员操作的API进行了统一的包装</p>
 *
 * @author leojie 2020/9/25 10:44 下午
 */
@InterfaceAudience.Private
public interface AdminService {
    /**
     * 判断表是否存在
     *
     * @param tableName 表名
     * @return 是否存在
     */
    boolean tableExists(String tableName);

    boolean tableExists(TableName tableName);

    /**
     * 获取所有的HBase表及其描述
     *
     * @return 所有的HBase表及其描述
     */
    <HTD extends BaseHTableDesc> List<HTD> listTableDesc();

    /**
     * 获取所有的HBase表及其描述
     *
     * @param includeSysTables 是否包含系统表
     * @return 所有的HBase表及其描述
     */
    <HTD extends BaseHTableDesc> List<HTD> listTableDesc(boolean includeSysTables);

    /**
     * 正则查询HBase表及其描述
     *
     * @param regex            查询正则
     * @param includeSysTables 是否包含系统表
     * @return 筛选出的HBase表及其描述
     */
    <HTD extends BaseHTableDesc> List<HTD> listTableDesc(String regex, boolean includeSysTables);

    /**
     * 获取某一命名空间下的所有表信息
     *
     * @param namespaceName 命名空间名称
     * @return 所有表信息
     */
    <HTD extends BaseHTableDesc> List<HTD> listTableDescByNamespace(final String namespaceName);

    /**
     * 获取所有表名
     *
     * @return 所有表名
     */
    List<String> listTableNames();

    /**
     * 获取所有表名
     *
     * @param includeSysTables 是否包含系统表
     * @return 所有表名
     */
    List<String> listTableNames(boolean includeSysTables);

    /**
     * 获取所有表名
     *
     * @param regex            查询正则
     * @param includeSysTables 是否包含系统表
     * @return 所有表名
     */
    List<String> listTableNames(String regex, boolean includeSysTables);

    /**
     * 获取某一命名空间下的所有表名
     *
     * @param namespaceName 命名空间名称
     * @return 所有表名
     */
    List<String> listTableNamesByNamespace(final String namespaceName);

    /**
     * 获取某张表所有的列簇信息
     *
     * @param tableName HBase表名
     * @return 所有的列簇信息
     */
    <CF extends BaseColumnFamilyDesc> List<CF> listFamilyDescOfTable(String tableName);

    /**
     * 获取某一张表的描述信息
     *
     * @param tableName 表名
     * @return 表描述
     */
    <HTD extends BaseHTableDesc> HTD getTableDesc(final String tableName);

    /**
     * 创建表，以默认的方式
     *
     * @param tableDesc 表的描述信息
     * @return 表是否被创建成功
     */
    <HTD extends BaseHTableDesc> boolean createTable(final HTD tableDesc);

    /**
     * 创建表，预分区
     *
     * @param tableDesc  表的描述信息
     * @param startKey   预分区开始的key
     * @param endKey     预分区结束的key
     * @param numRegions 需要的预分区个数
     * @param isAsync    是否是异步的方式
     * @return 表是否被创建成功
     */
    <HTD extends BaseHTableDesc> boolean createTable(final HTD tableDesc, String startKey, String endKey, int numRegions, boolean isAsync);

    /**
     * 创建表，预分区
     *
     * @param tableDesc 表的描述信息
     * @param splitKeys 需要划分的预分区key
     * @param isAsync   是否是异步的方式
     * @return 表是否被创建成功
     */
    <HTD extends BaseHTableDesc> boolean createTable(final HTD tableDesc, String[] splitKeys, boolean isAsync);

    /**
     * 创建表，预分区
     *
     * @param tableDesc   表的描述信息
     * @param splitGoEnum 预分区方式
     * @param numRegions  需要的预分区个数
     * @param isAsync     是否是异步的方式
     * @return 表是否被创建成功
     */
    <HTD extends BaseHTableDesc> boolean createTable(final HTD tableDesc, SplitGoEnum splitGoEnum, int numRegions, boolean isAsync);

    /**
     * 修改表属性
     *
     * @param tableName  表名
     * @param attributes 属性集合
     * @param isAsync    是否异步
     * @return 修改是否成功
     */
    boolean modifyTableAttributes(final String tableName, Map<String, String> attributes, boolean isAsync);

    /**
     * 异步修改表属性
     *
     * @param tableName  表名
     * @param attributes 属性集合
     * @return 修改是否成功
     */
    boolean modifyTableAttributesAsync(final String tableName, Map<String, String> attributes);

    /**
     * 移除表属性
     *
     * @param tableName     表名
     * @param attributeKeys 属性键列表
     * @param isAsync       是否异步
     * @return 是否移除成功
     */
    boolean removeTableAttributes(final String tableName, List<String> attributeKeys, boolean isAsync);

    /**
     * 异步移除表属性
     *
     * @param tableName     表名
     * @param attributeKeys 属性键列表
     * @return 是否移除成功
     */
    boolean removeTableAttributesAsync(final String tableName, List<String> attributeKeys);

    /**
     * 修改表配置
     *
     * @param tableName     表名
     * @param configuration 配置集合
     * @param isAsync       是否异步
     * @return 修改是否成功
     */
    boolean modifyTableConfiguration(final String tableName, Map<String, String> configuration, boolean isAsync);

    /**
     * 异步修改表配置
     *
     * @param tableName     表名
     * @param configuration 配置集合
     * @return 修改是否成功
     */
    boolean modifyTableConfigurationAsync(final String tableName, Map<String, String> configuration);

    /**
     * 移除表配置
     *
     * @param tableName  表名
     * @param configKeys 配置键列表
     * @param isAsync    是否异步
     * @return 是否移除成功
     */
    boolean removeTableConfiguration(final String tableName, List<String> configKeys, boolean isAsync);

    /**
     * 异步移除表配置
     *
     * @param tableName  表名
     * @param configKeys 配置键列表
     * @return 是否移除成功
     */
    boolean removeTableConfigurationAsync(final String tableName, List<String> configKeys);

    /**
     * 修改表列簇属性，例如：COMPRESSION，TTL，VERSIONS等
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @param attributes 属性列表
     * @param isAsync    是否异步
     * @return 修改是否成功
     */
    boolean modifyFamilyAttributes(final String tableName, String familyName, Map<String, String> attributes, boolean isAsync);

    /**
     * 异步修改表列簇属性，例如：COMPRESSION，TTL，VERSIONS等
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @param attributes 属性列表
     * @return 修改是否成功
     */
    boolean modifyFamilyAttributesAsync(final String tableName, String familyName, Map<String, String> attributes);

    /**
     * 移除列簇属性
     *
     * @param tableName     表名
     * @param familyName    列簇名
     * @param attributeKeys 待移除属性的键列表
     * @param isAsync       是否异步
     * @return 是否操作成功
     */
    boolean removeFamilyAttributes(String tableName, String familyName, List<String> attributeKeys, boolean isAsync);

    /**
     * 异步移除列簇属性
     *
     * @param tableName     表名
     * @param familyName    列簇名
     * @param attributeKeys 待移除属性的键列表
     * @return 是否操作成功
     */
    boolean removeFamilyAttributesAsync(String tableName, String familyName, List<String> attributeKeys);

    /**
     * 修改表列簇配置，如：hbase.hstore.engine.class等
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @param configs    配置列表
     * @param isAsync    是否异步
     * @return 修改是否成功
     */
    boolean modifyFamilyConfiguration(final String tableName, String familyName, Map<String, String> configs, boolean isAsync);

    /**
     * 异步修改表列簇配置，如：hbase.hstore.engine.class等
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @param configs    属性列表
     * @return 修改是否成功
     */
    boolean modifyFamilyConfigurationAsync(final String tableName, String familyName, Map<String, String> configs);

    /**
     * 移除列簇配置
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @param configKeys 待移除属性的配置键列表
     * @param isAsync    是否异步
     * @return 是否操作成功
     */
    boolean removeFamilyConfiguration(String tableName, String familyName, List<String> configKeys, boolean isAsync);

    /**
     * 异步移除列簇属性
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @param configKeys 待移除属性的配置键列表
     * @return 是否操作成功
     */
    boolean removeFamilyConfigurationAsync(String tableName, String familyName, List<String> configKeys);

    /**
     * 修改表名
     *
     * @param oldTableName   旧表名
     * @param newTableName   新表名
     * @param deleteOldTable 是否删除旧表
     * @param isAsync        是否是异步的
     * @return 修改表名结果
     */
    boolean renameTable(String oldTableName, String newTableName, boolean deleteOldTable, boolean isAsync);

    /**
     * 删除表
     *
     * @param tableName 表名
     * @param isAsync   异步删除
     * @return 表是否被删除成功
     */
    boolean deleteTable(final String tableName, boolean isAsync);

    /**
     * 异步删除表
     *
     * @param tableName 表名
     * @return 表是否被删除成功
     */
    boolean deleteTableAsync(final String tableName);

    /**
     * 清空表
     *
     * @param tableName      表名
     * @param preserveSplits 是否保留预分区信息
     * @param isAsync        是否为异步操作
     * @return 表是否被成功清空
     */
    boolean truncateTable(final String tableName, final boolean preserveSplits, boolean isAsync);

    /**
     * 异步清空表
     *
     * @param tableName      表名
     * @param preserveSplits 是否保留预分区信息
     * @return 表是否被成功清空
     */
    boolean truncateTableAsync(final String tableName, final boolean preserveSplits);

    /**
     * 启用表
     *
     * @param tableName 表名
     * @param isAsync   是否是异步的
     * @return 启用表是否成功
     */
    boolean enableTable(String tableName, boolean isAsync);

    /**
     * 启用表
     *
     * @param tableName 表名
     * @return 启用表是否成功
     */
    boolean enableTableAsync(String tableName);

    /**
     * 禁用表
     *
     * @param tableName 表名
     * @param isAsync   是否是异步的
     * @return 禁用表是否成功
     */
    boolean disableTable(String tableName, boolean isAsync);


    /**
     * 禁用表
     *
     * @param tableName 表名
     * @return 禁用表是否成功
     */
    boolean disableTableAsync(String tableName);


    /**
     * 表是否被启用
     *
     * @param tableName 表名
     * @return 表是否被启用
     */
    boolean isTableEnabled(String tableName);

    /**
     * 表是否被禁用
     *
     * @param tableName 表名
     * @return 表是否被禁用
     */
    boolean isTableDisabled(String tableName);

    /**
     * 表是否可用，即该表所有的region是否可用
     *
     * @param tableName 表名
     * @return 表是否可用
     */
    boolean isTableAvailable(String tableName);


    /**
     * 为某张表新增一个列簇
     *
     * @param tableName  表名
     * @param familyDesc 列簇定义信息
     * @param isAsync    异步操作
     * @return 新增列簇是否成功
     */
    <CF extends BaseColumnFamilyDesc> boolean addFamily(final String tableName, final CF familyDesc, boolean isAsync);

    /**
     * 为某张表新增一个列簇， 异步
     *
     * @param tableName  表名
     * @param familyDesc 列簇定义信息
     * @return 新增列簇是否成功
     */
    <CF extends BaseColumnFamilyDesc> boolean addFamilyAsync(final String tableName, final CF familyDesc);


    /**
     * 删除一个列簇
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @param isAsync    是否异步
     * @return 删除列簇是否成功
     */
    boolean deleteFamily(final String tableName, final String familyName, boolean isAsync);

    /**
     * 删除一个列簇，异步
     *
     * @param tableName  表名
     * @param familyName 列簇名
     * @return 删除列簇是否成功
     */
    boolean deleteFamilyAsync(final String tableName, final String familyName);

    /**
     * 修改一个列簇
     *
     * @param tableName  表名
     * @param familyDesc 列簇描述
     * @param isAsync    是否异步
     * @return 修改列簇是否成功
     */
    <CF extends BaseColumnFamilyDesc> boolean modifyFamily(final String tableName, final CF familyDesc, boolean isAsync);

    /**
     * 修改一个列簇，异步
     *
     * @param tableName  表名
     * @param familyDesc 列簇描述
     * @return 修改列簇是否成功
     */
    <CF extends BaseColumnFamilyDesc> boolean modifyFamilyAsync(final String tableName, final CF familyDesc);

    /**
     * 启用replication
     *
     * @param tableName 表名
     * @param families  列簇名
     * @param isAsync   是否异步
     * @return 启用replication是否成功
     */
    boolean enableReplicationScope(String tableName, List<String> families, boolean isAsync);

    /**
     * 启用replication，异步
     *
     * @param tableName 表名
     * @param families  列簇名
     * @return 启用replication是否成功
     */
    boolean enableReplicationScopeAsync(String tableName, List<String> families);


    /**
     * 禁用replication
     *
     * @param tableName 表名
     * @param families  列簇名
     * @param isAsync   是否异步
     * @return 禁用replication是否成功
     */
    boolean disableReplicationScope(String tableName, List<String> families, boolean isAsync);

    /**
     * 禁用replication，异步
     *
     * @param tableName 表名
     * @param families  列簇名
     * @return 禁用replication是否成功
     */
    boolean disableReplicationScopeAsync(String tableName, List<String> families);

    /**
     * 刷新表，异步操作
     *
     * @param tableName 表名
     * @return 刷新表命令是否执行成功
     */
    boolean flush(final String tableName);

    /**
     * compact操作
     *
     * @param tableName 表名
     * @return compact命令是否执行成功
     */
    boolean compact(final String tableName);

    /**
     * major compact操作
     *
     * @param tableName 表名
     * @return major compact命令是否执行成功
     */
    boolean majorCompact(final String tableName);


    /**
     * 创建一个命名空间
     *
     * @param namespaceDesc 该命名空间的描述
     * @param isAsync       是否异步
     * @return namespace是否创建成功
     */
    boolean createNamespace(final NamespaceDesc namespaceDesc, boolean isAsync);

    /**
     * 创建一个命名空间
     *
     * @param namespaceDesc 该命名空间的描述
     * @return namespace是否创建成功
     */
    boolean createNamespaceAsync(final NamespaceDesc namespaceDesc);

    /**
     * 判断命名空间是否存在
     *
     * @param namespaceName 命名空间名称
     * @return 是否存在
     */
    boolean namespaceIsExists(final String namespaceName);

    /**
     * 删除命名空间
     *
     * @param namespaceName 命名空间名称
     * @param isAsync       是否异步
     * @return namespace是否删除成功
     */
    boolean deleteNamespace(final String namespaceName, boolean isAsync);

    /**
     * 删除命名空间
     *
     * @param namespaceName 命名空间名称
     * @return namespace是否删除成功
     */
    boolean deleteNamespaceAsync(final String namespaceName);

    /**
     * 获取一个命名空间的描述
     *
     * @param namespaceName 命名空间的名称
     * @return 该命名空间的描述
     */
    NamespaceDesc getNamespaceDesc(final String namespaceName);

    /**
     * 获取所有命名空间的描述
     *
     * @return 所有命名空间的描述
     */
    List<NamespaceDesc> listNamespaceDesc();

    /**
     * 获取HBase所有的命名空间名称
     *
     * @return 所有的命名空间名称
     */
    List<String> listNamespaceNames();

    /**
     * 获取某张表最后一次的major compact时间戳，如果是0则最新的HFile无法被找到
     *
     * @param tableName 表名
     * @return 时间戳
     */
    long getLastMajorCompactionTimestamp(final String tableName);

    /**
     * 获取某一个region最后major compaction的时间戳
     *
     * @param regionName 表名
     * @return 时间戳
     */
    long getLastMajorCompactionTimestampForRegion(final String regionName);

    /**
     * 查询所有快照
     *
     * @return 所有快照
     */
    List<SnapshotDesc> listSnapshots();

    /**
     * 创建快照
     *
     * @param snapshotDesc 快照信息描述
     * @param isAsync      是否异步
     * @return 创建快照是否成功
     */
    boolean snapshot(SnapshotDesc snapshotDesc, boolean isAsync);

    /**
     * 创建快照
     *
     * @param snapshotDesc 快照信息描述
     * @return 创建快照是否成功
     */
    boolean snapshotAsync(SnapshotDesc snapshotDesc);

    /**
     * 恢复快照
     *
     * @param snapshotName 快照名称
     * @param isAsync      是否异步
     * @return 恢复快照是否成功
     */
    boolean restoreSnapshot(final String snapshotName, boolean isAsync);

    /**
     * 恢复快照
     *
     * @param snapshotName 快照名称
     * @return 恢复快照是否成功
     */
    boolean restoreSnapshotAsync(final String snapshotName);


    /**
     * 克隆快照
     *
     * @param snapshotName 快照名称
     * @param tableName    表名
     * @param isAsync      是否异步
     * @return 克隆快照是否成功
     */
    boolean cloneSnapshot(final String snapshotName, final String tableName, boolean isAsync);


    /**
     * 克隆快照
     *
     * @param snapshotName 快照名称
     * @param tableName    表名
     * @return 克隆快照是否成功
     */
    boolean cloneSnapshotAsync(final String snapshotName, final String tableName);

    /**
     * 删除快照
     *
     * @param snapshotName 快照名称
     * @return 删除快照是否成功
     */
    boolean deleteSnapshot(final String snapshotName);


    /**
     * 根据正则批量删除快照
     *
     * @param regex 正则
     * @return 删除快照是否成功
     */
    boolean deleteSnapshots(final String regex);

    /**
     * 合并region
     *
     * @param firstRegion  第一个region名称
     * @param secondRegion 第二个region名称
     * @param force        是否强制合并，加true为暴力合并，即不连续的两个region合并，尽量不要使用
     * @return 合并region提交的结果
     */
    boolean mergeRegions(final byte[] firstRegion, final byte[] secondRegion, boolean force);

    /**
     * 合并多个region
     *
     * @param regions 多个region
     * @param force   是否强制合并
     * @return 合并region提交的结果
     */
    boolean mergeMultipleRegions(final byte[][] regions, boolean force);

    /**
     * 合并某张表的小region
     *
     * @param tableName       表名
     * @param limitRegionsNum 限制参与合并的region数，例如，总的region数是1000，此值设置为100，那么每次只有100个region参与合并
     * @param limitRegionSize 限制参与合并的region大小，单位是M，例如，如果每个region的大小设置为20G，那么只有小于10G的region的进行合并（此阈值可以调整）
     * @return 合并表的小region是否成功
     */
    boolean mergeTableSmallRegions(final String tableName, int limitRegionsNum, int limitRegionSize);

}
