package com.github.CCweixiao;

import com.github.CCweixiao.model.FamilyDesc;
import com.github.CCweixiao.model.NamespaceDesc;
import com.github.CCweixiao.model.TableDesc;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

/**
 * HBase admin template test
 *
 * @author leojie 2020/8/15 10:28 上午
 */
public class HBaseAdminTemplateTest {
    private HBaseAdminTemplate hBaseTemplate;

    @Before
    public void initHBaseTemplate() {
        Properties properties = new Properties();
        properties.setProperty("java.security.krb5.conf", "/Users/mac/leo_project/hbase-sdk/hbase-sdk-core_1.4/src/test/resources/conf/krb5.conf");
        properties.setProperty("hadoop.security.authentication", "kerberos");
        properties.setProperty("hbase.security.authentication", "kerberos");

        properties.setProperty("keytab.file", "/Users/mac/leo_project/hbase-sdk/hbase-sdk-core_1.4/src/test/resources/conf/hadoop.keytab");
        properties.setProperty("kerberos.principal", "hadoop@LEO.COM");

        properties.setProperty("hbase.master.kerberos.principal","hbase/_HOST@LEO.COM");
        properties.setProperty("hbase.regionserver.kerberos.principal","hbase/_HOST@LEO.COM");

        properties.setProperty("hbase.zookeeper.quorum", "node2.bigdata.leo.com,node1.bigdata.leo.com,node3.bigdata.leo.com");
        properties.setProperty("hbase.zookeeper.property.clientPort", "2181");

        hBaseTemplate = new HBaseAdminTemplate(properties);
    }

    @Test
    public void testListNamespace() {
        List<String> namespaces = hBaseTemplate.listNamespaces();
        System.out.println(namespaces);
    }

    @Test
    public void testCreateNamespace() {
        String namespaceName = "LEO_NS2";
        if (hBaseTemplate.namespaceIsExists(namespaceName)) {
            hBaseTemplate.deleteNamespace(namespaceName);
        }

        NamespaceDesc namespaceDesc = new NamespaceDesc();
        namespaceDesc.setNamespaceName(namespaceName);

        namespaceDesc = namespaceDesc.addNamespaceProp("tag", "测试命名空间")
                .addNamespaceProp("createBy", "leo").addNamespaceProp("updateBy", "");

        hBaseTemplate.createNamespace(namespaceDesc);
    }

    @Test
    public void testGetNamespace() {
        String namespaceName = "LEO_NS2";
        final NamespaceDesc namespaceDesc = hBaseTemplate.getNamespaceDesc(namespaceName);
        System.out.println(namespaceDesc.getNamespaceName());
        System.out.println(namespaceDesc.getNamespaceProps());
    }

    @Test
    public void testDeleteNamespace() {
        String namespaceName = "LEO_NS2";
        hBaseTemplate.deleteNamespace(namespaceName);
    }

    @Test
    public void testCreateTable() {
        String tableName = "LEO_NS2:USER";

        TableDesc tableDesc = new TableDesc();
        tableDesc.setTableName(tableName);

        tableDesc = tableDesc.addProp("tag", "测试用户表").addProp("createUser", "leo");

        FamilyDesc familyDesc1 = new FamilyDesc.Builder()
                .familyName("INFO")
                .replicationScope(1)
                .compressionType("NONE")
                .timeToLive(2147483647)
                .maxVersions(3).build();

        FamilyDesc familyDesc2 = new FamilyDesc.Builder()
                .familyName("INFO2")
                .replicationScope(0)
                .compressionType("NONE")
                .timeToLive(864000)
                .maxVersions(3).build();

        tableDesc = tableDesc.addFamilyDesc(familyDesc1)
                .addFamilyDesc(familyDesc2);


        //hBaseTemplate.createTable(tableDesc, false);
    }

    @Test
    public void getTableDesc() {
        String tableName = "TEST:LEO_USER";
        final TableDesc tableDesc = hBaseTemplate.getTableDesc(tableName);
        System.out.println(tableDesc.getProp("lastUpdateBy"));
        System.out.println(tableDesc.getProp("remark"));
        System.out.println(tableDesc.getTableDesc());
    }

    @Test
    public void deleteTable() {
        String tableName = "LEO_NS2:USER";
        boolean disabled = hBaseTemplate.isTableDisabled(tableName);
        if (!disabled) {
            hBaseTemplate.disableTable(tableName, false);
        }
        boolean res = hBaseTemplate.deleteTable(tableName);
        System.out.println(res);
    }
}
