lexer grammar HydraQLLexer;

options { caseInsensitive = true; }

@header {
package com.hydraql.dsl.antlr;
}

AND:                                'AND';
ARRAY:                              'ARRAY';
AS:                                 'AS';
BETWEEN:                            'BETWEEN';
BINARY:                             'BINARY';
CHAR:                               'CHAR';
CREATE:                             'CREATE';
DATE:                               'DATE';
DECIMAL:                            'DECIMAL';
DEFAULT:                            'DEFAULT';
DELETE:                             'DELETE';
DISABLE:                            'DISABLE';
DISTINCT:                           'DISTINCT';
DOUBLE:                             'DOUBLE';
DROP:                               'DROP';
EXISTS:                             'EXISTS';
FALSE:                              'FALSE';
FLOAT:                              'FLOAT';
FROM:                               'FROM';
IN:                                 'IN';
IF:                                 'IF';
INTEGER:                            'INTEGER';
BIGINT:                             'BIGINT';
INTO:                               'INTO';
IS:                                 'IS';
KEY:                                'KEY';
STARTKEY:                           'STARTKEY';
ENDKEY:                             'ENDKEY';
ROWKEY:                             'ROWKEY';
LIKE:                               'LIKE';
VERSIONS:                           'VERSIONS';
STARTTS:                            'STARTTS';
ENDTS:                              'ENDTS';
TS:                                 'TS';
LIMIT:                              'LIMIT';
NOT:                                'NOT';
NULL_:                              'NULL';
OR:                                 'OR';
PRIMARY:                            'PRIMARY';
SELECT:                             'SELECT';
SHOW:                               'SHOW';
SMALLINT:                           'SMALLINT';
VIRTUAL:                            'VIRTUAL';
TABLE:                              'TABLE';
TABLES:                             'TABLES';
TIME:                               'TIME';
TIMESTAMP:                          'TIMESTAMP';
TINYINT:                            'TINYINT';
TRUE:                               'TRUE';
UNSIGNED_DATE:                      'UNSIGNED_DATE';
UNSIGNED_DOUBLE:                    'UNSIGNED_DOUBLE';
UNSIGNED_FLOAT:                     'UNSIGNED_FLOAT';
UNSIGNED_INT:                       'UNSIGNED_INT';
UNSIGNED_LONG:                      'UNSIGNED_LONG';
UNSIGNED_SMALLINT:                  'UNSIGNED_SMALLINT';
UNSIGNED_TIME:                      'UNSIGNED_TIME';
UNSIGNED_TIMESTAMP:                 'UNSIGNED_TIMESTAMP';
UNSIGNED_TINYINT:                   'UNSIGNED_TINYINT';
UPDATE:                             'UPDATE';
UPSERT:                             'UPSERT';
VALUES:                             'VALUES';
VARBINARY:                          'VARBINARY';
VARCHAR:                            'VARCHAR';
WHERE:                              'WHERE';
WITH:                               'WITH';
PROPERTIES:                         'PROPERTIES';


SEMI: ';';
COLON: ':';
COMMA: ',';
DOT: '.';
LP: '(';
RP: ')';
VAR_LP: '${';
VAR_RP: '}';
STAR: '*';
DIV: '/';
MOD: '%';
PLUS: '+';
MINUS: '-';
PIPEPIPE: '||';
LSB: '[';
RSC: ']';
EQ: '=';
NE: '<>';
NE2: '!=';
GT: '>';
GE: '>=';
LT: '<';
LE: '<=';
QM: '?';


WHITE_SPACE:             [ \t\r\n]+                   -> channel(HIDDEN);

SQL_COMMENT:            '/*' (SQL_COMMENT | .)*? '*/' -> channel(HIDDEN);
LINE_COMMENT:           '--' ~[\r\n]*                 -> channel(HIDDEN);
HINT_START:             '/*+';
HINT_END:               '*/';

DOUBLE_QUOTE_ID:        '"' ~'"'+ '"';
SINGLE_QUOTE:           '\'';

ID:                     [A-Z_] [A-Z0-9_]*;


STRING_LITERAL:              '\'' ~'\''* '\'';
DECIMAL_LITERAL:             DEC_DIGIT+;
FLOAT_LITERAL:               DEC_DOT_DEC;
REAL_LITERAL:                (DECIMAL_LITERAL | DEC_DOT_DEC) ('E' [+-]? DEC_DIGIT+);
CHAR_LITERAL:                '\'' (~['\\\r\n]) '\'';

fragment LETTER:       [A-Z_];

fragment DEC_DOT_DEC:  DEC_DIGIT+ '.' DEC_DIGIT* | '.' DEC_DIGIT+;

fragment DEC_DIGIT:    [0-9];