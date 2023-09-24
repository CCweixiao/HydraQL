create virtual table if not exists
test:test_sql (
userId varchar(100) not null primary key,
f1:name varchar(200) null default '12121',
f1:age smallint
) with properties
( "hbase.client.scanner.caching"=100 ,
 "hbase.client.block.cache"=false);