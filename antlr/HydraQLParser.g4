parser grammar HydraQLParser;

@header {
package com.hydraql.dsl.antlr;
}

options { tokenVocab=HydraQLLexer; }

root
    : batch* EOF
    ;

batch
    : sql_command SEMI?
    ;

sql_command
    : ddl_command
    | dml_command
    ;

ddl_command
    : create_virtual_table_command
    | show_tables_command
    | show_table_command
    | drop_table_command
    ;

dml_command
    : select_command
    | upsert_values_command
    | delete_command
    ;

create_virtual_table_command
    : CREATE VIRTUAL TABLE if_not_exists? table_ref '(' column_def_list ')'
        table_options?
    ;

table_options
    : WITH PROPERTIES LP options_ RP
    ;

options_
    : option (',' option)*
    ;

option
    : name '=' literal
    ;

drop_table_command
    : DROP VIRTUAL TABLE if_exists? table_ref
    ;

show_tables_command
    : SHOW VIRTUAL TABLES
    ;

show_table_command
    : SHOW CREATE VIRTUAL TABLE if_exists? table_ref
    ;

if_exists
    : IF EXISTS
    ;

if_not_exists
    : IF NOT EXISTS
    ;

table_ref
    : (namespace_name ':')? table_name
    ;

upsert_values_command
    : UPSERT INTO table_ref ('(' upsert_column_def_list ')')?
        VALUES '(' insert_values ')'
        (',' '(' insert_values ')')*
    ;

insert_values
    : literal (',' literal)*
    ;

upsert_column_def_list
    : column_ref (',' column_ref)*
    ;

column_def_list
    : column_def (',' column_def)*
    ;

delete_column_def_list
    : delete_column_def (',' delete_column_def)*
    ;

delete_command
    : DELETE delete_column_def_list? FROM table_ref
      WHERE whereRow
      (AND whereCol)?
      timestamp_range_clause?
    ;

timestamp_range_clause
    : LP STARTTS gtOper tsExp COMMA ENDTS leOper tsExp RP                  # tsRangeStartAndEnd
    | STARTTS gtOper tsExp                                                 # tsRangeStart
    | ENDTS leOper tsExp                                                   # tsRangeEnd
    | TS EQ tsExp                                                          # tsRangeEq
    ;

tsExp: timestamp ;
timestamp: integer;

gtOper: GT | GE;
leOper: LT | LE;

versions_clause
    : VERSIONS integer
    ;

limit_clause
    : LIMIT integer
    ;

select_command
    : select_statement
      versions_clause?
      timestamp_range_clause?
      limit_clause?
    ;

select_statement
    : SELECT select_column_def (',' select_column_def)*
      FROM table_ref
      WHERE whereRow
      (AND whereCol)?
    ;

delete_column_def : family_name '.' '*'                # deleteOneFamilyAllCol
                  | (family_name ':')? column_name     # deleteFamilyAndCol
                  ;

select_column_def : '*'                                                    # selectAllFamilyAndCol
                  | family_name '.' '*'                                    # selectOneFamilyAllCol
                  | (family_name ':')? column_name (AS? column_alias)?     # selectFamilyAndCol
                  | functionCall (AS? column_alias)?                       # selectWithFuncCall
                  ;

functionCall
    : funcName '(' functionArgs? ')'                                  # udfFunctionCall
    ;

funcName
    : name
    ;

functionArgs
    : ( fullColumnName | literal )
    (
      ','
      ( fullColumnName | literal )
    )*
    ;

fullColumnName
    : (family_name ':')? column_name
    ;

family_name
    : name
    ;

quoted_name
    : DOUBLE_QUOTE_ID
    ;

column_alias
    : alias
    ;

alias
    : name
    ;

name
    : ID
    | quoted_name
    ;

namespace_name
    : name
    ;

table_name
    : name
    ;

column_def
    : column_ref data_type (NOT? NULL_)? (DEFAULT literal)? (PRIMARY KEY)?
    ;

column_ref
    : (family_name ':')? column_name
    ;

column_name
    : name
    ;

data_type
    : (sql_data_type | hbase_data_type) (ARRAY ('[' dimension_int ']')?)?
    ;

conditionVal
    : constant
    | var
    ;

conditionValList
    : conditionVal (COMMA conditionVal)*
    ;

constant
    : literal
    ;

variable
   : varString
   ;

var
    : VAR_LP variable VAR_RP
    ;

column
    : (family_name COLON)? column_name
    ;

rowKey
    : string
    | numeric
    ;

whereRow
    : STARTKEY gtOper rowKey AND ENDKEY leOper rowKey                   # rowKeyStartAndEnd
    | STARTKEY gtOper rowKey                                            # rowKeyStart
    | ENDKEY leOper rowKey		                                        # rowKeyEnd
    | ROWKEY EQ rowKey 			                                        # rowKeyEqOne
    | ROWKEY IN LP rowKey (COMMA rowKey)* RP                            # rowKeyInSomeKeys
    | ROWKEY LIKE rowKey                                                # rowKeyLike
    ;

whereCol: colCondition;
colCondition
    : LP colCondition RP                                                # colConditionWrapper
    | colCondition AND colCondition                                     # colConditionAnd
    | colCondition OR colCondition                                      # colConditionOr
    | column comp_op conditionVal                                       # colConditionCompOp
    | column (LIKE | NOT LIKE) conditionVal                             # colConditionLikeOrNot
    | column IS NOT? NULL_                                              # colConditionIsNullOrNot
    | column NOT? IN LP conditionValList RP                             # colConditionInOrNotIn
    | column NOT? BETWEEN conditionVal AND conditionVal                 # colConditionBetweenOrNot
    ;

comp_op
    : EQ | GT | GE | LT | LE | NE | NE2
    ;

literal
    : string
    | numeric
    | true_false
    | NULL_
    ;

string
    : STRING_LITERAL
    ;

varString
    : ID
    ;

numeric
    : integer
    | decimal
    ;

integer
    : '-'? DECIMAL_LITERAL
    ;

decimal
    : FLOAT_LITERAL
    ;

true_false
    : TRUE
    | FALSE
    ;

dimension_int
    : integer
    ;

precision_int
    : integer
    ;

scale_int
    : integer
    ;

sql_data_type
    : CHAR '(' precision_int ')'
    | VARCHAR ('(' precision_int ')')?
    | DECIMAL (precision_int ',' scale_int)?
    | TINYINT
    | SMALLINT
    | INTEGER
    | BIGINT
    | FLOAT
    | DOUBLE
    | TIMESTAMP
    | DATE
    | TIME
    | BINARY '(' precision_int ')'
    | VARBINARY '(' precision_int ')'
    ;

hbase_data_type
    : UNSIGNED_TIMESTAMP
    | UNSIGNED_DATE
    | UNSIGNED_TIME
    | UNSIGNED_TINYINT
    | UNSIGNED_SMALLINT
    | UNSIGNED_INT
    | UNSIGNED_LONG
    | UNSIGNED_FLOAT
    | UNSIGNED_DOUBLE
    ;