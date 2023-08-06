-- 首先创建HBase实际表

create 'test:test_hql', {NAME => 'f1', VERSIONS => 5}, {NAME => 'f2', VERSIONS => 1}

-- 定义虚拟机表

--create virtual table test:test_sql ( row_key string isrowkey,
-- f1:id string nullable,
-- f1:name string nullable,
-- f1:age int nullable,
-- f1:job string nullable,
-- f1:pay double nullable,
-- f2:address string nullable,
-- f2:commuter string nullable );

create virtual table test:test_hql (
    row_key string isrowkey,
    f1:id string nullable,
    f1:name string nullable,
    f1:age int nullable,
    f1:job string nullable,
    f1:pay double nullable,
    f2:address string nullable,
    f2:commuter string nullable
) with properties (
"hbase.client.scanner.caching"="100"
,"hbase.client.block.cache"="false"
);

insert into test:test_hql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )
 values ('a10001', 'a10001' , 'leo_a100_01' , 18 , 'Coding' , 13333.33 , 'BeiJing' , 'Car' );

 insert into test:test_hql ( row_key , f1:id , f1:name , f1:age , f1:job , f1:pay , f2:address , f2:commuter )
  values ('a10001', 'a10001' , 'leo_a100_01' , 18 , 'Coding' , 13333.33 , 'BeiJing' , 'Car' ),
  ('a10002', 'a10002' , 'leo_a100_02' , 19 , '外卖员' , 7333.33 , 'ShangHai' , '电动车' );


-- 查询多条语句

select * from test:test_hql where rowKey = 'a10002' and maxversion = 2;

delete f1:id from test:test_hql where rowKey = 'a10002';


