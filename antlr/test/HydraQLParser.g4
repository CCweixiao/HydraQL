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
    : UPSERT INTO table_ref ('(' column_ref_list ')')?
        VALUES '(' insert_values? ')'
        (',' '(' insert_values? ')')*
    ;

insert_values
    : literal (',' literal)*
    ;

column_ref_list
    : column_ref (',' column_ref)*
    ;

column_def_list
    : column_def (',' column_def)*
    ;

delete_command
    : DELETE select_expression (',' select_expression)* FROM table_name
        where_clause?
        timestamp_range_clause?
    ;

timestamp_range_clause
    : LP STARTTS gtOper tsExp COMMA ENDTS leOper tsExp RP                  # tsrange_startAndEnd
    | STARTTS gtOper tsExp                                                 # tsrange_start
    | ENDTS leOper tsExp                                                   # tsrange_end
    | TS EQ tsExp                                                          # tsequal
    ;

tsExp: timestamp ;
timestamp: integer;

gtOper: GT | GE;
leOper: LT | LE;

versions_clause
    : VERSIONS number
    ;

limit_clause
    : LIMIT number
    ;

where_clause
    : WHERE expression
    ;

select_command
    : select_statement
      versions_clause?
      timestamp_range_clause?
      limit_clause?
    ;

select_statement
    : SELECT select_expression (',' select_expression)*
      FROM table_ref
      where_clause?
    ;

number
    : DECIMAL_LITERAL
    ;

select_expression : '*'                                 # selectAllFamilyAndCol
                  | family_name '.' '*'                 # selectOneFamilyAllCol
                  | (family_name ':')? column_name      # selectFamilyAndCol
                  | functionCall (AS? column_alias)?    # selectWithFuncCall
                  ;

functionCall
    : funcName '(' functionArgs? ')'                                  #udfFunctionCall
    ;

funcName
    : name
    ;

functionArgs
    : ( fullColumnName | expression )
    (
      ','
      ( fullColumnName | expression )
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

expression
    : literal                                                         # expressionConstantValue
    | VAR_LP variable VAR_RP                                          # expressionVariable
    | (family_name COLON)? column_name                                # expressionColName
    | functionCall                                                    # expressionWithFunc
    | expression comp_op expression                                   # expressionCompOp
    | expression (LIKE | NOT LIKE) expression                         # expressionLikeOrNot
    | expression IS NOT? NULL_                                        # expressionIsNullOrNot
    | expression NOT? IN LP expression_list RP                        # expressionIn
    | expression NOT? BETWEEN expression AND expression               # expressionBetweenOrNot
    | LP expression RP                                                # expressionWrapper
    | expression AND expression                                       # expressionAnd
    | expression OR expression                                        # expressionOr
    ;

comp_op
    : EQ | GT | GE | LT | LE | NE | NE2
    ;

expression_list
    : expression (COMMA expression)*
    ;

variable
   : varString
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
    : '-'? number ('.' number)?
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