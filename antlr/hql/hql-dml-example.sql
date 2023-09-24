select * from test:test_sql where
                -- rowKey = a1000
                startkey >= 'a1000' and endKey < 'a1009'
                and (f1:name = 'leo_a100_0' or f1:age > 12 or (f1:pay between 1000 and 200000));
