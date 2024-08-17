// Generated from ./HydraQLParser.g4 by ANTLR 4.5.1

package com.hydraql.dsl.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HydraQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AND=1, ARRAY=2, AS=3, BETWEEN=4, BINARY=5, CHAR=6, CREATE=7, DATE=8, DECIMAL=9, 
		DEFAULT=10, DELETE=11, DISABLE=12, DISTINCT=13, DOUBLE=14, DROP=15, EXISTS=16, 
		FALSE=17, FLOAT=18, FROM=19, IN=20, INTEGER=21, INTO=22, IS=23, KEY=24, 
		LIKE=25, VERSIONS=26, STARTTS=27, ENDTS=28, TS=29, LIMIT=30, NOT=31, NULL_=32, 
		OR=33, PRIMARY=34, SELECT=35, SMALLINT=36, VIRTUAL=37, TABLE=38, TABLES=39, 
		TIME=40, TIMESTAMP=41, TINYINT=42, TRUE=43, UNSIGNED_DATE=44, UNSIGNED_DOUBLE=45, 
		UNSIGNED_FLOAT=46, UNSIGNED_INT=47, UNSIGNED_LONG=48, UNSIGNED_SMALLINT=49, 
		UNSIGNED_TIME=50, UNSIGNED_TIMESTAMP=51, UNSIGNED_TINYINT=52, UPDATE=53, 
		UPSERT=54, VALUES=55, VARBINARY=56, VARCHAR=57, WHERE=58, WITH=59, PROPERTIES=60, 
		SEMI=61, COLON=62, COMMA=63, DOT=64, LP=65, RP=66, VAR_LP=67, VAR_RP=68, 
		STAR=69, DIV=70, MOD=71, PLUS=72, MINUS=73, PIPEPIPE=74, LSB=75, RSC=76, 
		EQ=77, NE=78, NE2=79, GT=80, GE=81, LT=82, LE=83, QM=84, WHITE_SPACE=85, 
		SQL_COMMENT=86, LINE_COMMENT=87, HINT_START=88, HINT_END=89, DOUBLE_QUOTE_ID=90, 
		SINGLE_QUOTE=91, ID=92, STRING_LITERAL=93, DECIMAL_LITERAL=94, FLOAT_LITERAL=95, 
		REAL_LITERAL=96, CHAR_LITERAL=97, SHOW=98, IF=99, BIGINT=100;
	public static final int
		RULE_root = 0, RULE_batch = 1, RULE_sql_command = 2, RULE_ddl_command = 3, 
		RULE_dml_command = 4, RULE_create_virtual_table_command = 5, RULE_table_options = 6, 
		RULE_options_ = 7, RULE_option = 8, RULE_drop_table_command = 9, RULE_show_tables_command = 10, 
		RULE_show_table_command = 11, RULE_if_exists = 12, RULE_if_not_exists = 13, 
		RULE_table_ref = 14, RULE_upsert_values_command = 15, RULE_insert_values = 16, 
		RULE_column_ref_list = 17, RULE_column_def_list = 18, RULE_delete_command = 19, 
		RULE_timestamp_range_clause = 20, RULE_tsExp = 21, RULE_timestamp = 22, 
		RULE_gtOper = 23, RULE_leOper = 24, RULE_versions_clause = 25, RULE_limit_clause = 26, 
		RULE_where_clause = 27, RULE_select_command = 28, RULE_select_statement = 29, 
		RULE_number = 30, RULE_select_expression = 31, RULE_functionCall = 32, 
		RULE_funcName = 33, RULE_functionArgs = 34, RULE_fullColumnName = 35, 
		RULE_family_name = 36, RULE_quoted_name = 37, RULE_column_alias = 38, 
		RULE_alias = 39, RULE_name = 40, RULE_namespace_name = 41, RULE_table_name = 42, 
		RULE_column_def = 43, RULE_column_ref = 44, RULE_column_name = 45, RULE_data_type = 46, 
		RULE_expression = 47, RULE_comp_op = 48, RULE_expression_list = 49, RULE_variable = 50, 
		RULE_literal = 51, RULE_string = 52, RULE_varString = 53, RULE_numeric = 54, 
		RULE_integer = 55, RULE_decimal = 56, RULE_true_false = 57, RULE_dimension_int = 58, 
		RULE_precision_int = 59, RULE_scale_int = 60, RULE_sql_data_type = 61, 
		RULE_hbase_data_type = 62;
	public static final String[] ruleNames = {
		"root", "batch", "sql_command", "ddl_command", "dml_command", "create_virtual_table_command", 
		"table_options", "options_", "option", "drop_table_command", "show_tables_command", 
		"show_table_command", "if_exists", "if_not_exists", "table_ref", "upsert_values_command", 
		"insert_values", "column_ref_list", "column_def_list", "delete_command", 
		"timestamp_range_clause", "tsExp", "timestamp", "gtOper", "leOper", "versions_clause", 
		"limit_clause", "where_clause", "select_command", "select_statement", 
		"number", "select_expression", "functionCall", "funcName", "functionArgs", 
		"fullColumnName", "family_name", "quoted_name", "column_alias", "alias", 
		"name", "namespace_name", "table_name", "column_def", "column_ref", "column_name", 
		"data_type", "expression", "comp_op", "expression_list", "variable", "literal", 
		"string", "varString", "numeric", "integer", "decimal", "true_false", 
		"dimension_int", "precision_int", "scale_int", "sql_data_type", "hbase_data_type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'AND'", "'ARRAY'", "'AS'", "'BETWEEN'", "'BINARY'", "'CHAR'", "'CREATE'", 
		"'DATE'", "'DECIMAL'", "'DEFAULT'", "'DELETE'", "'DISABLE'", "'DISTINCT'", 
		"'DOUBLE'", "'DROP'", "'EXISTS'", "'FALSE'", "'FLOAT'", "'FROM'", "'IN'", 
		"'INTEGER'", "'INTO'", "'IS'", "'KEY'", "'LIKE'", "'VERSIONS'", "'STARTTS'", 
		"'ENDTS'", "'TS'", "'LIMIT'", "'NOT'", "'NULL'", "'OR'", "'PRIMARY'", 
		"'SELECT'", "'SMALLINT'", "'VIRTUAL'", "'TABLE'", "'TABLES'", "'TIME'", 
		"'TIMESTAMP'", "'TINYINT'", "'TRUE'", "'UNSIGNED_DATE'", "'UNSIGNED_DOUBLE'", 
		"'UNSIGNED_FLOAT'", "'UNSIGNED_INT'", "'UNSIGNED_LONG'", "'UNSIGNED_SMALLINT'", 
		"'UNSIGNED_TIME'", "'UNSIGNED_TIMESTAMP'", "'UNSIGNED_TINYINT'", "'UPDATE'", 
		"'UPSERT'", "'VALUES'", "'VARBINARY'", "'VARCHAR'", "'WHERE'", "'WITH'", 
		"'PROPERTIES'", "';'", "':'", "','", "'.'", "'('", "')'", "'${'", "'}'", 
		"'*'", "'/'", "'%'", "'+'", "'-'", "'||'", "'['", "']'", "'='", "'<>'", 
		"'!='", "'>'", "'>='", "'<'", "'<='", "'?'", null, null, null, "'/*+'", 
		"'*/'", null, "'''"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "AND", "ARRAY", "AS", "BETWEEN", "BINARY", "CHAR", "CREATE", "DATE", 
		"DECIMAL", "DEFAULT", "DELETE", "DISABLE", "DISTINCT", "DOUBLE", "DROP", 
		"EXISTS", "FALSE", "FLOAT", "FROM", "IN", "INTEGER", "INTO", "IS", "KEY", 
		"LIKE", "VERSIONS", "STARTTS", "ENDTS", "TS", "LIMIT", "NOT", "NULL_", 
		"OR", "PRIMARY", "SELECT", "SMALLINT", "VIRTUAL", "TABLE", "TABLES", "TIME", 
		"TIMESTAMP", "TINYINT", "TRUE", "UNSIGNED_DATE", "UNSIGNED_DOUBLE", "UNSIGNED_FLOAT", 
		"UNSIGNED_INT", "UNSIGNED_LONG", "UNSIGNED_SMALLINT", "UNSIGNED_TIME", 
		"UNSIGNED_TIMESTAMP", "UNSIGNED_TINYINT", "UPDATE", "UPSERT", "VALUES", 
		"VARBINARY", "VARCHAR", "WHERE", "WITH", "PROPERTIES", "SEMI", "COLON", 
		"COMMA", "DOT", "LP", "RP", "VAR_LP", "VAR_RP", "STAR", "DIV", "MOD", 
		"PLUS", "MINUS", "PIPEPIPE", "LSB", "RSC", "EQ", "NE", "NE2", "GT", "GE", 
		"LT", "LE", "QM", "WHITE_SPACE", "SQL_COMMENT", "LINE_COMMENT", "HINT_START", 
		"HINT_END", "DOUBLE_QUOTE_ID", "SINGLE_QUOTE", "ID", "STRING_LITERAL", 
		"DECIMAL_LITERAL", "FLOAT_LITERAL", "REAL_LITERAL", "CHAR_LITERAL", "SHOW", 
		"IF", "BIGINT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "HydraQLParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HydraQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(HydraQLParser.EOF, 0); }
		public List<BatchContext> batch() {
			return getRuleContexts(BatchContext.class);
		}
		public BatchContext batch(int i) {
			return getRuleContext(BatchContext.class,i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << DELETE) | (1L << DROP) | (1L << SELECT) | (1L << UPSERT))) != 0) || _la==SHOW) {
				{
				{
				setState(126);
				batch();
				}
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BatchContext extends ParserRuleContext {
		public Sql_commandContext sql_command() {
			return getRuleContext(Sql_commandContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(HydraQLParser.SEMI, 0); }
		public BatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_batch; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitBatch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BatchContext batch() throws RecognitionException {
		BatchContext _localctx = new BatchContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_batch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			sql_command();
			setState(136);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(135);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_commandContext extends ParserRuleContext {
		public Ddl_commandContext ddl_command() {
			return getRuleContext(Ddl_commandContext.class,0);
		}
		public Dml_commandContext dml_command() {
			return getRuleContext(Dml_commandContext.class,0);
		}
		public Sql_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSql_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_commandContext sql_command() throws RecognitionException {
		Sql_commandContext _localctx = new Sql_commandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sql_command);
		try {
			setState(140);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case SHOW:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				ddl_command();
				}
				break;
			case DELETE:
			case SELECT:
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				dml_command();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ddl_commandContext extends ParserRuleContext {
		public Create_virtual_table_commandContext create_virtual_table_command() {
			return getRuleContext(Create_virtual_table_commandContext.class,0);
		}
		public Show_tables_commandContext show_tables_command() {
			return getRuleContext(Show_tables_commandContext.class,0);
		}
		public Show_table_commandContext show_table_command() {
			return getRuleContext(Show_table_commandContext.class,0);
		}
		public Drop_table_commandContext drop_table_command() {
			return getRuleContext(Drop_table_commandContext.class,0);
		}
		public Ddl_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ddl_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDdl_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ddl_commandContext ddl_command() throws RecognitionException {
		Ddl_commandContext _localctx = new Ddl_commandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ddl_command);
		try {
			setState(146);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				create_virtual_table_command();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				show_tables_command();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				show_table_command();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(145);
				drop_table_command();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dml_commandContext extends ParserRuleContext {
		public Select_commandContext select_command() {
			return getRuleContext(Select_commandContext.class,0);
		}
		public Upsert_values_commandContext upsert_values_command() {
			return getRuleContext(Upsert_values_commandContext.class,0);
		}
		public Delete_commandContext delete_command() {
			return getRuleContext(Delete_commandContext.class,0);
		}
		public Dml_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dml_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDml_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dml_commandContext dml_command() throws RecognitionException {
		Dml_commandContext _localctx = new Dml_commandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dml_command);
		try {
			setState(151);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				select_command();
				}
				break;
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				upsert_values_command();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				delete_command();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_virtual_table_commandContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(HydraQLParser.CREATE, 0); }
		public TerminalNode VIRTUAL() { return getToken(HydraQLParser.VIRTUAL, 0); }
		public TerminalNode TABLE() { return getToken(HydraQLParser.TABLE, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public Column_def_listContext column_def_list() {
			return getRuleContext(Column_def_listContext.class,0);
		}
		public If_not_existsContext if_not_exists() {
			return getRuleContext(If_not_existsContext.class,0);
		}
		public Table_optionsContext table_options() {
			return getRuleContext(Table_optionsContext.class,0);
		}
		public Create_virtual_table_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_virtual_table_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitCreate_virtual_table_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_virtual_table_commandContext create_virtual_table_command() throws RecognitionException {
		Create_virtual_table_commandContext _localctx = new Create_virtual_table_commandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_create_virtual_table_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(CREATE);
			setState(154);
			match(VIRTUAL);
			setState(155);
			match(TABLE);
			setState(157);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(156);
				if_not_exists();
				}
			}

			setState(159);
			table_ref();
			setState(160);
			match(LP);
			setState(161);
			column_def_list();
			setState(162);
			match(RP);
			setState(164);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(163);
				table_options();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_optionsContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(HydraQLParser.WITH, 0); }
		public TerminalNode PROPERTIES() { return getToken(HydraQLParser.PROPERTIES, 0); }
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public Options_Context options_() {
			return getRuleContext(Options_Context.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public Table_optionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_options; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTable_options(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_optionsContext table_options() throws RecognitionException {
		Table_optionsContext _localctx = new Table_optionsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_table_options);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(WITH);
			setState(167);
			match(PROPERTIES);
			setState(168);
			match(LP);
			setState(169);
			options_();
			setState(170);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Options_Context extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public Options_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_options_; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitOptions_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Options_Context options_() throws RecognitionException {
		Options_Context _localctx = new Options_Context(_ctx, getState());
		enterRule(_localctx, 14, RULE_options_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			option();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(173);
				match(COMMA);
				setState(174);
				option();
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			name();
			setState(181);
			match(EQ);
			setState(182);
			literal();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_table_commandContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(HydraQLParser.DROP, 0); }
		public TerminalNode VIRTUAL() { return getToken(HydraQLParser.VIRTUAL, 0); }
		public TerminalNode TABLE() { return getToken(HydraQLParser.TABLE, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public If_existsContext if_exists() {
			return getRuleContext(If_existsContext.class,0);
		}
		public Drop_table_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_table_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDrop_table_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_table_commandContext drop_table_command() throws RecognitionException {
		Drop_table_commandContext _localctx = new Drop_table_commandContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_drop_table_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(DROP);
			setState(185);
			match(VIRTUAL);
			setState(186);
			match(TABLE);
			setState(188);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(187);
				if_exists();
				}
			}

			setState(190);
			table_ref();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_tables_commandContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(HydraQLParser.SHOW, 0); }
		public TerminalNode VIRTUAL() { return getToken(HydraQLParser.VIRTUAL, 0); }
		public TerminalNode TABLES() { return getToken(HydraQLParser.TABLES, 0); }
		public Show_tables_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_tables_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitShow_tables_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_tables_commandContext show_tables_command() throws RecognitionException {
		Show_tables_commandContext _localctx = new Show_tables_commandContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_show_tables_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(SHOW);
			setState(193);
			match(VIRTUAL);
			setState(194);
			match(TABLES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_table_commandContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(HydraQLParser.SHOW, 0); }
		public TerminalNode CREATE() { return getToken(HydraQLParser.CREATE, 0); }
		public TerminalNode VIRTUAL() { return getToken(HydraQLParser.VIRTUAL, 0); }
		public TerminalNode TABLE() { return getToken(HydraQLParser.TABLE, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public If_existsContext if_exists() {
			return getRuleContext(If_existsContext.class,0);
		}
		public Show_table_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_table_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitShow_table_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_table_commandContext show_table_command() throws RecognitionException {
		Show_table_commandContext _localctx = new Show_table_commandContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_show_table_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(SHOW);
			setState(197);
			match(CREATE);
			setState(198);
			match(VIRTUAL);
			setState(199);
			match(TABLE);
			setState(201);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(200);
				if_exists();
				}
			}

			setState(203);
			table_ref();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_existsContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(HydraQLParser.IF, 0); }
		public TerminalNode EXISTS() { return getToken(HydraQLParser.EXISTS, 0); }
		public If_existsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_exists; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitIf_exists(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_existsContext if_exists() throws RecognitionException {
		If_existsContext _localctx = new If_existsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_if_exists);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(IF);
			setState(206);
			match(EXISTS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_not_existsContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(HydraQLParser.IF, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(HydraQLParser.EXISTS, 0); }
		public If_not_existsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_not_exists; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitIf_not_exists(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_not_existsContext if_not_exists() throws RecognitionException {
		If_not_existsContext _localctx = new If_not_existsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_if_not_exists);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(IF);
			setState(209);
			match(NOT);
			setState(210);
			match(EXISTS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_refContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Namespace_nameContext namespace_name() {
			return getRuleContext(Namespace_nameContext.class,0);
		}
		public Table_refContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_ref; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTable_ref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_refContext table_ref() throws RecognitionException {
		Table_refContext _localctx = new Table_refContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_table_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(212);
				namespace_name();
				setState(213);
				match(COLON);
				}
				break;
			}
			setState(217);
			table_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Upsert_values_commandContext extends ParserRuleContext {
		public TerminalNode UPSERT() { return getToken(HydraQLParser.UPSERT, 0); }
		public TerminalNode INTO() { return getToken(HydraQLParser.INTO, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public TerminalNode VALUES() { return getToken(HydraQLParser.VALUES, 0); }
		public Column_ref_listContext column_ref_list() {
			return getRuleContext(Column_ref_listContext.class,0);
		}
		public List<Insert_valuesContext> insert_values() {
			return getRuleContexts(Insert_valuesContext.class);
		}
		public Insert_valuesContext insert_values(int i) {
			return getRuleContext(Insert_valuesContext.class,i);
		}
		public Upsert_values_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_upsert_values_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitUpsert_values_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Upsert_values_commandContext upsert_values_command() throws RecognitionException {
		Upsert_values_commandContext _localctx = new Upsert_values_commandContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_upsert_values_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(UPSERT);
			setState(220);
			match(INTO);
			setState(221);
			table_ref();
			setState(226);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(222);
				match(LP);
				setState(223);
				column_ref_list();
				setState(224);
				match(RP);
				}
			}

			setState(228);
			match(VALUES);
			setState(229);
			match(LP);
			setState(231);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << NULL_) | (1L << TRUE))) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (MINUS - 73)) | (1L << (STRING_LITERAL - 73)) | (1L << (DECIMAL_LITERAL - 73)))) != 0)) {
				{
				setState(230);
				insert_values();
				}
			}

			setState(233);
			match(RP);
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(234);
				match(COMMA);
				setState(235);
				match(LP);
				setState(237);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << NULL_) | (1L << TRUE))) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (MINUS - 73)) | (1L << (STRING_LITERAL - 73)) | (1L << (DECIMAL_LITERAL - 73)))) != 0)) {
					{
					setState(236);
					insert_values();
					}
				}

				setState(239);
				match(RP);
				}
				}
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_valuesContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public Insert_valuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_values; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitInsert_values(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_valuesContext insert_values() throws RecognitionException {
		Insert_valuesContext _localctx = new Insert_valuesContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_insert_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			literal();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(246);
				match(COMMA);
				setState(247);
				literal();
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_ref_listContext extends ParserRuleContext {
		public List<Column_refContext> column_ref() {
			return getRuleContexts(Column_refContext.class);
		}
		public Column_refContext column_ref(int i) {
			return getRuleContext(Column_refContext.class,i);
		}
		public Column_ref_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_ref_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_ref_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_ref_listContext column_ref_list() throws RecognitionException {
		Column_ref_listContext _localctx = new Column_ref_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_column_ref_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			column_ref();
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(254);
				match(COMMA);
				setState(255);
				column_ref();
				}
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_def_listContext extends ParserRuleContext {
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public Column_def_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_def_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_def_listContext column_def_list() throws RecognitionException {
		Column_def_listContext _localctx = new Column_def_listContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_column_def_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			column_def();
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(262);
				match(COMMA);
				setState(263);
				column_def();
				}
				}
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_commandContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(HydraQLParser.DELETE, 0); }
		public List<Select_expressionContext> select_expression() {
			return getRuleContexts(Select_expressionContext.class);
		}
		public Select_expressionContext select_expression(int i) {
			return getRuleContext(Select_expressionContext.class,i);
		}
		public TerminalNode FROM() { return getToken(HydraQLParser.FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public Timestamp_range_clauseContext timestamp_range_clause() {
			return getRuleContext(Timestamp_range_clauseContext.class,0);
		}
		public Delete_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDelete_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_commandContext delete_command() throws RecognitionException {
		Delete_commandContext _localctx = new Delete_commandContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_delete_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(DELETE);
			setState(270);
			select_expression();
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(271);
				match(COMMA);
				setState(272);
				select_expression();
				}
				}
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(278);
			match(FROM);
			setState(279);
			table_name();
			setState(281);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(280);
				where_clause();
				}
			}

			setState(284);
			_la = _input.LA(1);
			if (((((_la - 27)) & ~0x3f) == 0 && ((1L << (_la - 27)) & ((1L << (STARTTS - 27)) | (1L << (ENDTS - 27)) | (1L << (TS - 27)) | (1L << (LP - 27)))) != 0)) {
				{
				setState(283);
				timestamp_range_clause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Timestamp_range_clauseContext extends ParserRuleContext {
		public Timestamp_range_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timestamp_range_clause; }
	 
		public Timestamp_range_clauseContext() { }
		public void copyFrom(Timestamp_range_clauseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TsequalContext extends Timestamp_range_clauseContext {
		public TerminalNode TS() { return getToken(HydraQLParser.TS, 0); }
		public TerminalNode EQ() { return getToken(HydraQLParser.EQ, 0); }
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public TsequalContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsequal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Tsrange_startContext extends Timestamp_range_clauseContext {
		public TerminalNode STARTTS() { return getToken(HydraQLParser.STARTTS, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public Tsrange_startContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsrange_start(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Tsrange_endContext extends Timestamp_range_clauseContext {
		public TerminalNode ENDTS() { return getToken(HydraQLParser.ENDTS, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public Tsrange_endContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsrange_end(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Tsrange_startAndEndContext extends Timestamp_range_clauseContext {
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public TerminalNode STARTTS() { return getToken(HydraQLParser.STARTTS, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public List<TsExpContext> tsExp() {
			return getRuleContexts(TsExpContext.class);
		}
		public TsExpContext tsExp(int i) {
			return getRuleContext(TsExpContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(HydraQLParser.COMMA, 0); }
		public TerminalNode ENDTS() { return getToken(HydraQLParser.ENDTS, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public Tsrange_startAndEndContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsrange_startAndEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Timestamp_range_clauseContext timestamp_range_clause() throws RecognitionException {
		Timestamp_range_clauseContext _localctx = new Timestamp_range_clauseContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_timestamp_range_clause);
		try {
			setState(307);
			switch (_input.LA(1)) {
			case LP:
				_localctx = new Tsrange_startAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(286);
				match(LP);
				setState(287);
				match(STARTTS);
				setState(288);
				gtOper();
				setState(289);
				tsExp();
				setState(290);
				match(COMMA);
				setState(291);
				match(ENDTS);
				setState(292);
				leOper();
				setState(293);
				tsExp();
				setState(294);
				match(RP);
				}
				break;
			case STARTTS:
				_localctx = new Tsrange_startContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				match(STARTTS);
				setState(297);
				gtOper();
				setState(298);
				tsExp();
				}
				break;
			case ENDTS:
				_localctx = new Tsrange_endContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(300);
				match(ENDTS);
				setState(301);
				leOper();
				setState(302);
				tsExp();
				}
				break;
			case TS:
				_localctx = new TsequalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(304);
				match(TS);
				setState(305);
				match(EQ);
				setState(306);
				tsExp();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TsExpContext extends ParserRuleContext {
		public TimestampContext timestamp() {
			return getRuleContext(TimestampContext.class,0);
		}
		public TsExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tsExp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TsExpContext tsExp() throws RecognitionException {
		TsExpContext _localctx = new TsExpContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_tsExp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			timestamp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TimestampContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TimestampContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timestamp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTimestamp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimestampContext timestamp() throws RecognitionException {
		TimestampContext _localctx = new TimestampContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_timestamp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			integer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GtOperContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(HydraQLParser.GT, 0); }
		public TerminalNode GE() { return getToken(HydraQLParser.GE, 0); }
		public GtOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gtOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitGtOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GtOperContext gtOper() throws RecognitionException {
		GtOperContext _localctx = new GtOperContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_gtOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_la = _input.LA(1);
			if ( !(_la==GT || _la==GE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeOperContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(HydraQLParser.LT, 0); }
		public TerminalNode LE() { return getToken(HydraQLParser.LE, 0); }
		public LeOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitLeOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeOperContext leOper() throws RecognitionException {
		LeOperContext _localctx = new LeOperContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_leOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			_la = _input.LA(1);
			if ( !(_la==LT || _la==LE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Versions_clauseContext extends ParserRuleContext {
		public TerminalNode VERSIONS() { return getToken(HydraQLParser.VERSIONS, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Versions_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versions_clause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitVersions_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Versions_clauseContext versions_clause() throws RecognitionException {
		Versions_clauseContext _localctx = new Versions_clauseContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_versions_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(VERSIONS);
			setState(318);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Limit_clauseContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(HydraQLParser.LIMIT, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Limit_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit_clause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitLimit_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Limit_clauseContext limit_clause() throws RecognitionException {
		Limit_clauseContext _localctx = new Limit_clauseContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_limit_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(LIMIT);
			setState(321);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Where_clauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(HydraQLParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitWhere_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_where_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(WHERE);
			setState(324);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_commandContext extends ParserRuleContext {
		public Select_statementContext select_statement() {
			return getRuleContext(Select_statementContext.class,0);
		}
		public Versions_clauseContext versions_clause() {
			return getRuleContext(Versions_clauseContext.class,0);
		}
		public Timestamp_range_clauseContext timestamp_range_clause() {
			return getRuleContext(Timestamp_range_clauseContext.class,0);
		}
		public Limit_clauseContext limit_clause() {
			return getRuleContext(Limit_clauseContext.class,0);
		}
		public Select_commandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_command; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelect_command(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_commandContext select_command() throws RecognitionException {
		Select_commandContext _localctx = new Select_commandContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_select_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			select_statement();
			setState(328);
			_la = _input.LA(1);
			if (_la==VERSIONS) {
				{
				setState(327);
				versions_clause();
				}
			}

			setState(331);
			_la = _input.LA(1);
			if (((((_la - 27)) & ~0x3f) == 0 && ((1L << (_la - 27)) & ((1L << (STARTTS - 27)) | (1L << (ENDTS - 27)) | (1L << (TS - 27)) | (1L << (LP - 27)))) != 0)) {
				{
				setState(330);
				timestamp_range_clause();
				}
			}

			setState(334);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(333);
				limit_clause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_statementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(HydraQLParser.SELECT, 0); }
		public List<Select_expressionContext> select_expression() {
			return getRuleContexts(Select_expressionContext.class);
		}
		public Select_expressionContext select_expression(int i) {
			return getRuleContext(Select_expressionContext.class,i);
		}
		public TerminalNode FROM() { return getToken(HydraQLParser.FROM, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public Select_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelect_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_statementContext select_statement() throws RecognitionException {
		Select_statementContext _localctx = new Select_statementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_select_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(SELECT);
			setState(337);
			select_expression();
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(338);
				match(COMMA);
				setState(339);
				select_expression();
				}
				}
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(345);
			match(FROM);
			setState(346);
			table_ref();
			setState(348);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(347);
				where_clause();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(HydraQLParser.DECIMAL_LITERAL, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(DECIMAL_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_expressionContext extends ParserRuleContext {
		public Select_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_expression; }
	 
		public Select_expressionContext() { }
		public void copyFrom(Select_expressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SelectAllFamilyAndColContext extends Select_expressionContext {
		public SelectAllFamilyAndColContext(Select_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectAllFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectOneFamilyAllColContext extends Select_expressionContext {
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public SelectOneFamilyAllColContext(Select_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectOneFamilyAllCol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectWithFuncCallContext extends Select_expressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public Column_aliasContext column_alias() {
			return getRuleContext(Column_aliasContext.class,0);
		}
		public TerminalNode AS() { return getToken(HydraQLParser.AS, 0); }
		public SelectWithFuncCallContext(Select_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectWithFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectFamilyAndColContext extends Select_expressionContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public SelectFamilyAndColContext(Select_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_expressionContext select_expression() throws RecognitionException {
		Select_expressionContext _localctx = new Select_expressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_select_expression);
		int _la;
		try {
			setState(370);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new SelectAllFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(352);
				match(STAR);
				}
				break;
			case 2:
				_localctx = new SelectOneFamilyAllColContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				family_name();
				setState(354);
				match(DOT);
				setState(355);
				match(STAR);
				}
				break;
			case 3:
				_localctx = new SelectFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(360);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(357);
					family_name();
					setState(358);
					match(COLON);
					}
					break;
				}
				setState(362);
				column_name();
				}
				break;
			case 4:
				_localctx = new SelectWithFuncCallContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(363);
				functionCall();
				setState(368);
				_la = _input.LA(1);
				if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
					{
					setState(365);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(364);
						match(AS);
						}
					}

					setState(367);
					column_alias();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
	 
		public FunctionCallContext() { }
		public void copyFrom(FunctionCallContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UdfFunctionCallContext extends FunctionCallContext {
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public FunctionArgsContext functionArgs() {
			return getRuleContext(FunctionArgsContext.class,0);
		}
		public UdfFunctionCallContext(FunctionCallContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitUdfFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_functionCall);
		int _la;
		try {
			_localctx = new UdfFunctionCallContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			funcName();
			setState(373);
			match(LP);
			setState(375);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << NULL_) | (1L << TRUE))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (LP - 65)) | (1L << (VAR_LP - 65)) | (1L << (MINUS - 65)) | (1L << (DOUBLE_QUOTE_ID - 65)) | (1L << (ID - 65)) | (1L << (STRING_LITERAL - 65)) | (1L << (DECIMAL_LITERAL - 65)))) != 0)) {
				{
				setState(374);
				functionArgs();
				}
			}

			setState(377);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncNameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitFuncName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionArgsContext extends ParserRuleContext {
		public List<FullColumnNameContext> fullColumnName() {
			return getRuleContexts(FullColumnNameContext.class);
		}
		public FullColumnNameContext fullColumnName(int i) {
			return getRuleContext(FullColumnNameContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArgs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitFunctionArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionArgsContext functionArgs() throws RecognitionException {
		FunctionArgsContext _localctx = new FunctionArgsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_functionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(381);
				fullColumnName();
				}
				break;
			case 2:
				{
				setState(382);
				expression(0);
				}
				break;
			}
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(385);
				match(COMMA);
				setState(388);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(386);
					fullColumnName();
					}
					break;
				case 2:
					{
					setState(387);
					expression(0);
					}
					break;
				}
				}
				}
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FullColumnNameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public FullColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fullColumnName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitFullColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FullColumnNameContext fullColumnName() throws RecognitionException {
		FullColumnNameContext _localctx = new FullColumnNameContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_fullColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(395);
				family_name();
				setState(396);
				match(COLON);
				}
				break;
			}
			setState(400);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Family_nameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Family_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_family_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitFamily_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Family_nameContext family_name() throws RecognitionException {
		Family_nameContext _localctx = new Family_nameContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_family_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quoted_nameContext extends ParserRuleContext {
		public TerminalNode DOUBLE_QUOTE_ID() { return getToken(HydraQLParser.DOUBLE_QUOTE_ID, 0); }
		public Quoted_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitQuoted_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quoted_nameContext quoted_name() throws RecognitionException {
		Quoted_nameContext _localctx = new Quoted_nameContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_quoted_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(DOUBLE_QUOTE_ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_aliasContext extends ParserRuleContext {
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public Column_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_alias; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_alias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_aliasContext column_alias() throws RecognitionException {
		Column_aliasContext _localctx = new Column_aliasContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_column_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			alias();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AliasContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitAlias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HydraQLParser.ID, 0); }
		public Quoted_nameContext quoted_name() {
			return getRuleContext(Quoted_nameContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_name);
		try {
			setState(412);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(410);
				match(ID);
				}
				break;
			case DOUBLE_QUOTE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(411);
				quoted_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Namespace_nameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Namespace_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitNamespace_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_nameContext namespace_name() throws RecognitionException {
		Namespace_nameContext _localctx = new Namespace_nameContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_namespace_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_defContext extends ParserRuleContext {
		public Column_refContext column_ref() {
			return getRuleContext(Column_refContext.class,0);
		}
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public TerminalNode NULL_() { return getToken(HydraQLParser.NULL_, 0); }
		public TerminalNode DEFAULT() { return getToken(HydraQLParser.DEFAULT, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode PRIMARY() { return getToken(HydraQLParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(HydraQLParser.KEY, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public Column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			column_ref();
			setState(419);
			data_type();
			setState(424);
			_la = _input.LA(1);
			if (_la==NOT || _la==NULL_) {
				{
				setState(421);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(420);
					match(NOT);
					}
				}

				setState(423);
				match(NULL_);
				}
			}

			setState(428);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(426);
				match(DEFAULT);
				setState(427);
				literal();
				}
			}

			setState(432);
			_la = _input.LA(1);
			if (_la==PRIMARY) {
				{
				setState(430);
				match(PRIMARY);
				setState(431);
				match(KEY);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_refContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public Column_refContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_ref; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_ref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_refContext column_ref() throws RecognitionException {
		Column_refContext _localctx = new Column_refContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_column_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(434);
				family_name();
				setState(435);
				match(COLON);
				}
				break;
			}
			setState(439);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_typeContext extends ParserRuleContext {
		public Sql_data_typeContext sql_data_type() {
			return getRuleContext(Sql_data_typeContext.class,0);
		}
		public Hbase_data_typeContext hbase_data_type() {
			return getRuleContext(Hbase_data_typeContext.class,0);
		}
		public TerminalNode ARRAY() { return getToken(HydraQLParser.ARRAY, 0); }
		public Dimension_intContext dimension_int() {
			return getRuleContext(Dimension_intContext.class,0);
		}
		public Data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitData_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Data_typeContext data_type() throws RecognitionException {
		Data_typeContext _localctx = new Data_typeContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			switch (_input.LA(1)) {
			case BINARY:
			case CHAR:
			case DATE:
			case DECIMAL:
			case DOUBLE:
			case FLOAT:
			case INTEGER:
			case SMALLINT:
			case TIME:
			case TIMESTAMP:
			case TINYINT:
			case VARBINARY:
			case VARCHAR:
			case BIGINT:
				{
				setState(443);
				sql_data_type();
				}
				break;
			case UNSIGNED_DATE:
			case UNSIGNED_DOUBLE:
			case UNSIGNED_FLOAT:
			case UNSIGNED_INT:
			case UNSIGNED_LONG:
			case UNSIGNED_SMALLINT:
			case UNSIGNED_TIME:
			case UNSIGNED_TIMESTAMP:
			case UNSIGNED_TINYINT:
				{
				setState(444);
				hbase_data_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(454);
			_la = _input.LA(1);
			if (_la==ARRAY) {
				{
				setState(447);
				match(ARRAY);
				setState(452);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(448);
					match(LSB);
					setState(449);
					dimension_int();
					setState(450);
					match(RSC);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionColNameContext extends ExpressionContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
		public ExpressionColNameContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionColName(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionLikeOrNotContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LIKE() { return getToken(HydraQLParser.LIKE, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ExpressionLikeOrNotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionLikeOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionCompOpContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Comp_opContext comp_op() {
			return getRuleContext(Comp_opContext.class,0);
		}
		public ExpressionCompOpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionCompOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionIsNullOrNotContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(HydraQLParser.IS, 0); }
		public TerminalNode NULL_() { return getToken(HydraQLParser.NULL_, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ExpressionIsNullOrNotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionIsNullOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionAndContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(HydraQLParser.AND, 0); }
		public ExpressionAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionConstantValueContext extends ExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExpressionConstantValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionConstantValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionBetweenOrNotContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BETWEEN() { return getToken(HydraQLParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(HydraQLParser.AND, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ExpressionBetweenOrNotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionBetweenOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionOrContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(HydraQLParser.OR, 0); }
		public ExpressionOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionWrapperContext extends ExpressionContext {
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public ExpressionWrapperContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionWrapper(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionInContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IN() { return getToken(HydraQLParser.IN, 0); }
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ExpressionInContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionIn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionVariableContext extends ExpressionContext {
		public TerminalNode VAR_LP() { return getToken(HydraQLParser.VAR_LP, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode VAR_RP() { return getToken(HydraQLParser.VAR_RP, 0); }
		public ExpressionVariableContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionWithFuncContext extends ExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ExpressionWithFuncContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionWithFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 94;
		enterRecursionRule(_localctx, 94, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				_localctx = new ExpressionConstantValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(457);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExpressionVariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(458);
				match(VAR_LP);
				setState(459);
				variable();
				setState(460);
				match(VAR_RP);
				}
				break;
			case 3:
				{
				_localctx = new ExpressionColNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(465);
				switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
				case 1:
					{
					setState(462);
					family_name();
					setState(463);
					match(COLON);
					}
					break;
				}
				setState(467);
				column_name();
				}
				break;
			case 4:
				{
				_localctx = new ExpressionWithFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(468);
				functionCall();
				}
				break;
			case 5:
				{
				_localctx = new ExpressionWrapperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(469);
				match(LP);
				setState(470);
				expression(0);
				setState(471);
				match(RP);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(518);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(516);
					switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionCompOpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(475);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(476);
						comp_op();
						setState(477);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionLikeOrNotContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(479);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(483);
						switch (_input.LA(1)) {
						case LIKE:
							{
							setState(480);
							match(LIKE);
							}
							break;
						case NOT:
							{
							setState(481);
							match(NOT);
							setState(482);
							match(LIKE);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(485);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionBetweenOrNotContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(486);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(488);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(487);
							match(NOT);
							}
						}

						setState(490);
						match(BETWEEN);
						setState(491);
						expression(0);
						setState(492);
						match(AND);
						setState(493);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionAndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(495);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(496);
						match(AND);
						setState(497);
						expression(3);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionOrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(498);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(499);
						match(OR);
						setState(500);
						expression(2);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionIsNullOrNotContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(501);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(502);
						match(IS);
						setState(504);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(503);
							match(NOT);
							}
						}

						setState(506);
						match(NULL_);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionInContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(507);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(509);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(508);
							match(NOT);
							}
						}

						setState(511);
						match(IN);
						setState(512);
						match(LP);
						setState(513);
						expression_list();
						setState(514);
						match(RP);
						}
						break;
					}
					} 
				}
				setState(520);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Comp_opContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(HydraQLParser.EQ, 0); }
		public TerminalNode GT() { return getToken(HydraQLParser.GT, 0); }
		public TerminalNode GE() { return getToken(HydraQLParser.GE, 0); }
		public TerminalNode LT() { return getToken(HydraQLParser.LT, 0); }
		public TerminalNode LE() { return getToken(HydraQLParser.LE, 0); }
		public TerminalNode NE() { return getToken(HydraQLParser.NE, 0); }
		public TerminalNode NE2() { return getToken(HydraQLParser.NE2, 0); }
		public Comp_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_op; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitComp_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_opContext comp_op() throws RecognitionException {
		Comp_opContext _localctx = new Comp_opContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_comp_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			_la = _input.LA(1);
			if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (EQ - 77)) | (1L << (NE - 77)) | (1L << (NE2 - 77)) | (1L << (GT - 77)) | (1L << (GE - 77)) | (1L << (LT - 77)) | (1L << (LE - 77)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_listContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
		}
		public Expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpression_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			expression(0);
			setState(528);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(524);
				match(COMMA);
				setState(525);
				expression(0);
				}
				}
				setState(530);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public VarStringContext varString() {
			return getRuleContext(VarStringContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
			varString();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumericContext numeric() {
			return getRuleContext(NumericContext.class,0);
		}
		public True_falseContext true_false() {
			return getRuleContext(True_falseContext.class,0);
		}
		public TerminalNode NULL_() { return getToken(HydraQLParser.NULL_, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_literal);
		try {
			setState(537);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(533);
				string();
				}
				break;
			case MINUS:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(534);
				numeric();
				}
				break;
			case FALSE:
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(535);
				true_false();
				}
				break;
			case NULL_:
				enterOuterAlt(_localctx, 4);
				{
				setState(536);
				match(NULL_);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(HydraQLParser.STRING_LITERAL, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(539);
			match(STRING_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarStringContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HydraQLParser.ID, 0); }
		public VarStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varString; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitVarString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarStringContext varString() throws RecognitionException {
		VarStringContext _localctx = new VarStringContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_varString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public NumericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitNumeric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericContext numeric() throws RecognitionException {
		NumericContext _localctx = new NumericContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_numeric);
		try {
			setState(545);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(543);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(544);
				decimal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(HydraQLParser.DECIMAL_LITERAL, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(547);
				match(MINUS);
				}
			}

			setState(550);
			match(DECIMAL_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecimalContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public DecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalContext decimal() throws RecognitionException {
		DecimalContext _localctx = new DecimalContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_decimal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(553);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(552);
				match(MINUS);
				}
			}

			setState(555);
			number();
			setState(558);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(556);
				match(DOT);
				setState(557);
				number();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class True_falseContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(HydraQLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(HydraQLParser.FALSE, 0); }
		public True_falseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTrue_false(this);
			else return visitor.visitChildren(this);
		}
	}

	public final True_falseContext true_false() throws RecognitionException {
		True_falseContext _localctx = new True_falseContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_true_false);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			_la = _input.LA(1);
			if ( !(_la==FALSE || _la==TRUE) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dimension_intContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public Dimension_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension_int; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDimension_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dimension_intContext dimension_int() throws RecognitionException {
		Dimension_intContext _localctx = new Dimension_intContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_dimension_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562);
			integer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Precision_intContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public Precision_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precision_int; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitPrecision_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Precision_intContext precision_int() throws RecognitionException {
		Precision_intContext _localctx = new Precision_intContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_precision_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(564);
			integer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Scale_intContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public Scale_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scale_int; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitScale_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Scale_intContext scale_int() throws RecognitionException {
		Scale_intContext _localctx = new Scale_intContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_scale_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(566);
			integer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_data_typeContext extends ParserRuleContext {
		public TerminalNode CHAR() { return getToken(HydraQLParser.CHAR, 0); }
		public Precision_intContext precision_int() {
			return getRuleContext(Precision_intContext.class,0);
		}
		public TerminalNode VARCHAR() { return getToken(HydraQLParser.VARCHAR, 0); }
		public TerminalNode DECIMAL() { return getToken(HydraQLParser.DECIMAL, 0); }
		public Scale_intContext scale_int() {
			return getRuleContext(Scale_intContext.class,0);
		}
		public TerminalNode TINYINT() { return getToken(HydraQLParser.TINYINT, 0); }
		public TerminalNode SMALLINT() { return getToken(HydraQLParser.SMALLINT, 0); }
		public TerminalNode INTEGER() { return getToken(HydraQLParser.INTEGER, 0); }
		public TerminalNode BIGINT() { return getToken(HydraQLParser.BIGINT, 0); }
		public TerminalNode FLOAT() { return getToken(HydraQLParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(HydraQLParser.DOUBLE, 0); }
		public TerminalNode TIMESTAMP() { return getToken(HydraQLParser.TIMESTAMP, 0); }
		public TerminalNode DATE() { return getToken(HydraQLParser.DATE, 0); }
		public TerminalNode TIME() { return getToken(HydraQLParser.TIME, 0); }
		public TerminalNode BINARY() { return getToken(HydraQLParser.BINARY, 0); }
		public TerminalNode VARBINARY() { return getToken(HydraQLParser.VARBINARY, 0); }
		public Sql_data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_data_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSql_data_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_data_typeContext sql_data_type() throws RecognitionException {
		Sql_data_typeContext _localctx = new Sql_data_typeContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_sql_data_type);
		int _la;
		try {
			setState(606);
			switch (_input.LA(1)) {
			case CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(568);
				match(CHAR);
				setState(569);
				match(LP);
				setState(570);
				precision_int();
				setState(571);
				match(RP);
				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(573);
				match(VARCHAR);
				setState(578);
				_la = _input.LA(1);
				if (_la==LP) {
					{
					setState(574);
					match(LP);
					setState(575);
					precision_int();
					setState(576);
					match(RP);
					}
				}

				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(580);
				match(DECIMAL);
				setState(585);
				_la = _input.LA(1);
				if (_la==MINUS || _la==DECIMAL_LITERAL) {
					{
					setState(581);
					precision_int();
					setState(582);
					match(COMMA);
					setState(583);
					scale_int();
					}
				}

				}
				break;
			case TINYINT:
				enterOuterAlt(_localctx, 4);
				{
				setState(587);
				match(TINYINT);
				}
				break;
			case SMALLINT:
				enterOuterAlt(_localctx, 5);
				{
				setState(588);
				match(SMALLINT);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 6);
				{
				setState(589);
				match(INTEGER);
				}
				break;
			case BIGINT:
				enterOuterAlt(_localctx, 7);
				{
				setState(590);
				match(BIGINT);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 8);
				{
				setState(591);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 9);
				{
				setState(592);
				match(DOUBLE);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 10);
				{
				setState(593);
				match(TIMESTAMP);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 11);
				{
				setState(594);
				match(DATE);
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 12);
				{
				setState(595);
				match(TIME);
				}
				break;
			case BINARY:
				enterOuterAlt(_localctx, 13);
				{
				setState(596);
				match(BINARY);
				setState(597);
				match(LP);
				setState(598);
				precision_int();
				setState(599);
				match(RP);
				}
				break;
			case VARBINARY:
				enterOuterAlt(_localctx, 14);
				{
				setState(601);
				match(VARBINARY);
				setState(602);
				match(LP);
				setState(603);
				precision_int();
				setState(604);
				match(RP);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Hbase_data_typeContext extends ParserRuleContext {
		public TerminalNode UNSIGNED_TIMESTAMP() { return getToken(HydraQLParser.UNSIGNED_TIMESTAMP, 0); }
		public TerminalNode UNSIGNED_DATE() { return getToken(HydraQLParser.UNSIGNED_DATE, 0); }
		public TerminalNode UNSIGNED_TIME() { return getToken(HydraQLParser.UNSIGNED_TIME, 0); }
		public TerminalNode UNSIGNED_TINYINT() { return getToken(HydraQLParser.UNSIGNED_TINYINT, 0); }
		public TerminalNode UNSIGNED_SMALLINT() { return getToken(HydraQLParser.UNSIGNED_SMALLINT, 0); }
		public TerminalNode UNSIGNED_INT() { return getToken(HydraQLParser.UNSIGNED_INT, 0); }
		public TerminalNode UNSIGNED_LONG() { return getToken(HydraQLParser.UNSIGNED_LONG, 0); }
		public TerminalNode UNSIGNED_FLOAT() { return getToken(HydraQLParser.UNSIGNED_FLOAT, 0); }
		public TerminalNode UNSIGNED_DOUBLE() { return getToken(HydraQLParser.UNSIGNED_DOUBLE, 0); }
		public Hbase_data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hbase_data_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitHbase_data_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Hbase_data_typeContext hbase_data_type() throws RecognitionException {
		Hbase_data_typeContext _localctx = new Hbase_data_typeContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_hbase_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(608);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << UNSIGNED_DATE) | (1L << UNSIGNED_DOUBLE) | (1L << UNSIGNED_FLOAT) | (1L << UNSIGNED_INT) | (1L << UNSIGNED_LONG) | (1L << UNSIGNED_SMALLINT) | (1L << UNSIGNED_TIME) | (1L << UNSIGNED_TIMESTAMP) | (1L << UNSIGNED_TINYINT))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 47:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 2);
		case 4:
			return precpred(_ctx, 1);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3f\u0265\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\7\2\u0082\n\2\f\2\16\2\u0085\13\2\3\2\3\2\3\3\3"+
		"\3\5\3\u008b\n\3\3\4\3\4\5\4\u008f\n\4\3\5\3\5\3\5\3\5\5\5\u0095\n\5\3"+
		"\6\3\6\3\6\5\6\u009a\n\6\3\7\3\7\3\7\3\7\5\7\u00a0\n\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7\u00a7\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\7\t\u00b2\n\t\f"+
		"\t\16\t\u00b5\13\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\5\13\u00bf\n\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u00cc\n\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\5\20\u00da\n\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00e5\n\21\3\21\3\21\3\21"+
		"\5\21\u00ea\n\21\3\21\3\21\3\21\3\21\5\21\u00f0\n\21\3\21\7\21\u00f3\n"+
		"\21\f\21\16\21\u00f6\13\21\3\22\3\22\3\22\7\22\u00fb\n\22\f\22\16\22\u00fe"+
		"\13\22\3\23\3\23\3\23\7\23\u0103\n\23\f\23\16\23\u0106\13\23\3\24\3\24"+
		"\3\24\7\24\u010b\n\24\f\24\16\24\u010e\13\24\3\25\3\25\3\25\3\25\7\25"+
		"\u0114\n\25\f\25\16\25\u0117\13\25\3\25\3\25\3\25\5\25\u011c\n\25\3\25"+
		"\5\25\u011f\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0136\n\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\36\3\36\5\36\u014b\n\36\3\36\5\36\u014e\n\36\3\36\5\36\u0151"+
		"\n\36\3\37\3\37\3\37\3\37\7\37\u0157\n\37\f\37\16\37\u015a\13\37\3\37"+
		"\3\37\3\37\5\37\u015f\n\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\5!\u016b\n!\3"+
		"!\3!\3!\5!\u0170\n!\3!\5!\u0173\n!\5!\u0175\n!\3\"\3\"\3\"\5\"\u017a\n"+
		"\"\3\"\3\"\3#\3#\3$\3$\5$\u0182\n$\3$\3$\3$\5$\u0187\n$\7$\u0189\n$\f"+
		"$\16$\u018c\13$\3%\3%\3%\5%\u0191\n%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3"+
		"*\3*\5*\u019f\n*\3+\3+\3,\3,\3-\3-\3-\5-\u01a8\n-\3-\5-\u01ab\n-\3-\3"+
		"-\5-\u01af\n-\3-\3-\5-\u01b3\n-\3.\3.\3.\5.\u01b8\n.\3.\3.\3/\3/\3\60"+
		"\3\60\5\60\u01c0\n\60\3\60\3\60\3\60\3\60\3\60\5\60\u01c7\n\60\5\60\u01c9"+
		"\n\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u01d4\n\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\5\61\u01dc\n\61\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\5\61\u01e6\n\61\3\61\3\61\3\61\5\61\u01eb\n\61\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u01fb"+
		"\n\61\3\61\3\61\3\61\5\61\u0200\n\61\3\61\3\61\3\61\3\61\3\61\7\61\u0207"+
		"\n\61\f\61\16\61\u020a\13\61\3\62\3\62\3\63\3\63\3\63\7\63\u0211\n\63"+
		"\f\63\16\63\u0214\13\63\3\64\3\64\3\65\3\65\3\65\3\65\5\65\u021c\n\65"+
		"\3\66\3\66\3\67\3\67\38\38\58\u0224\n8\39\59\u0227\n9\39\39\3:\5:\u022c"+
		"\n:\3:\3:\3:\5:\u0231\n:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3?\3?\3?\3?\3?"+
		"\3?\3?\3?\5?\u0245\n?\3?\3?\3?\3?\3?\5?\u024c\n?\3?\3?\3?\3?\3?\3?\3?"+
		"\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\3?\5?\u0261\n?\3@\3@\3@\2\3`A\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT"+
		"VXZ\\^`bdfhjlnprtvxz|~\2\7\3\2RS\3\2TU\3\2OU\4\2\23\23--\3\2.\66\u0280"+
		"\2\u0083\3\2\2\2\4\u0088\3\2\2\2\6\u008e\3\2\2\2\b\u0094\3\2\2\2\n\u0099"+
		"\3\2\2\2\f\u009b\3\2\2\2\16\u00a8\3\2\2\2\20\u00ae\3\2\2\2\22\u00b6\3"+
		"\2\2\2\24\u00ba\3\2\2\2\26\u00c2\3\2\2\2\30\u00c6\3\2\2\2\32\u00cf\3\2"+
		"\2\2\34\u00d2\3\2\2\2\36\u00d9\3\2\2\2 \u00dd\3\2\2\2\"\u00f7\3\2\2\2"+
		"$\u00ff\3\2\2\2&\u0107\3\2\2\2(\u010f\3\2\2\2*\u0135\3\2\2\2,\u0137\3"+
		"\2\2\2.\u0139\3\2\2\2\60\u013b\3\2\2\2\62\u013d\3\2\2\2\64\u013f\3\2\2"+
		"\2\66\u0142\3\2\2\28\u0145\3\2\2\2:\u0148\3\2\2\2<\u0152\3\2\2\2>\u0160"+
		"\3\2\2\2@\u0174\3\2\2\2B\u0176\3\2\2\2D\u017d\3\2\2\2F\u0181\3\2\2\2H"+
		"\u0190\3\2\2\2J\u0194\3\2\2\2L\u0196\3\2\2\2N\u0198\3\2\2\2P\u019a\3\2"+
		"\2\2R\u019e\3\2\2\2T\u01a0\3\2\2\2V\u01a2\3\2\2\2X\u01a4\3\2\2\2Z\u01b7"+
		"\3\2\2\2\\\u01bb\3\2\2\2^\u01bf\3\2\2\2`\u01db\3\2\2\2b\u020b\3\2\2\2"+
		"d\u020d\3\2\2\2f\u0215\3\2\2\2h\u021b\3\2\2\2j\u021d\3\2\2\2l\u021f\3"+
		"\2\2\2n\u0223\3\2\2\2p\u0226\3\2\2\2r\u022b\3\2\2\2t\u0232\3\2\2\2v\u0234"+
		"\3\2\2\2x\u0236\3\2\2\2z\u0238\3\2\2\2|\u0260\3\2\2\2~\u0262\3\2\2\2\u0080"+
		"\u0082\5\4\3\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2"+
		"\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086"+
		"\u0087\7\2\2\3\u0087\3\3\2\2\2\u0088\u008a\5\6\4\2\u0089\u008b\7?\2\2"+
		"\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\5\3\2\2\2\u008c\u008f\5"+
		"\b\5\2\u008d\u008f\5\n\6\2\u008e\u008c\3\2\2\2\u008e\u008d\3\2\2\2\u008f"+
		"\7\3\2\2\2\u0090\u0095\5\f\7\2\u0091\u0095\5\26\f\2\u0092\u0095\5\30\r"+
		"\2\u0093\u0095\5\24\13\2\u0094\u0090\3\2\2\2\u0094\u0091\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\t\3\2\2\2\u0096\u009a\5:\36\2"+
		"\u0097\u009a\5 \21\2\u0098\u009a\5(\25\2\u0099\u0096\3\2\2\2\u0099\u0097"+
		"\3\2\2\2\u0099\u0098\3\2\2\2\u009a\13\3\2\2\2\u009b\u009c\7\t\2\2\u009c"+
		"\u009d\7\'\2\2\u009d\u009f\7(\2\2\u009e\u00a0\5\34\17\2\u009f\u009e\3"+
		"\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\5\36\20\2\u00a2"+
		"\u00a3\7C\2\2\u00a3\u00a4\5&\24\2\u00a4\u00a6\7D\2\2\u00a5\u00a7\5\16"+
		"\b\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\r\3\2\2\2\u00a8\u00a9"+
		"\7=\2\2\u00a9\u00aa\7>\2\2\u00aa\u00ab\7C\2\2\u00ab\u00ac\5\20\t\2\u00ac"+
		"\u00ad\7D\2\2\u00ad\17\3\2\2\2\u00ae\u00b3\5\22\n\2\u00af\u00b0\7A\2\2"+
		"\u00b0\u00b2\5\22\n\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\21\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"\u00b7\5R*\2\u00b7\u00b8\7O\2\2\u00b8\u00b9\5h\65\2\u00b9\23\3\2\2\2\u00ba"+
		"\u00bb\7\21\2\2\u00bb\u00bc\7\'\2\2\u00bc\u00be\7(\2\2\u00bd\u00bf\5\32"+
		"\16\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c1\5\36\20\2\u00c1\25\3\2\2\2\u00c2\u00c3\7d\2\2\u00c3\u00c4\7\'\2"+
		"\2\u00c4\u00c5\7)\2\2\u00c5\27\3\2\2\2\u00c6\u00c7\7d\2\2\u00c7\u00c8"+
		"\7\t\2\2\u00c8\u00c9\7\'\2\2\u00c9\u00cb\7(\2\2\u00ca\u00cc\5\32\16\2"+
		"\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce"+
		"\5\36\20\2\u00ce\31\3\2\2\2\u00cf\u00d0\7e\2\2\u00d0\u00d1\7\22\2\2\u00d1"+
		"\33\3\2\2\2\u00d2\u00d3\7e\2\2\u00d3\u00d4\7!\2\2\u00d4\u00d5\7\22\2\2"+
		"\u00d5\35\3\2\2\2\u00d6\u00d7\5T+\2\u00d7\u00d8\7@\2\2\u00d8\u00da\3\2"+
		"\2\2\u00d9\u00d6\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"\u00dc\5V,\2\u00dc\37\3\2\2\2\u00dd\u00de\78\2\2\u00de\u00df\7\30\2\2"+
		"\u00df\u00e4\5\36\20\2\u00e0\u00e1\7C\2\2\u00e1\u00e2\5$\23\2\u00e2\u00e3"+
		"\7D\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e0\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e7\79\2\2\u00e7\u00e9\7C\2\2\u00e8\u00ea\5\"\22"+
		"\2\u00e9\u00e8\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00f4"+
		"\7D\2\2\u00ec\u00ed\7A\2\2\u00ed\u00ef\7C\2\2\u00ee\u00f0\5\"\22\2\u00ef"+
		"\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\7D"+
		"\2\2\u00f2\u00ec\3\2\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5!\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00fc\5h\65\2"+
		"\u00f8\u00f9\7A\2\2\u00f9\u00fb\5h\65\2\u00fa\u00f8\3\2\2\2\u00fb\u00fe"+
		"\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd#\3\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00ff\u0104\5Z.\2\u0100\u0101\7A\2\2\u0101\u0103\5Z.\2"+
		"\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105"+
		"\3\2\2\2\u0105%\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u010c\5X-\2\u0108\u0109"+
		"\7A\2\2\u0109\u010b\5X-\2\u010a\u0108\3\2\2\2\u010b\u010e\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\'\3\2\2\2\u010e\u010c\3\2\2\2"+
		"\u010f\u0110\7\r\2\2\u0110\u0115\5@!\2\u0111\u0112\7A\2\2\u0112\u0114"+
		"\5@!\2\u0113\u0111\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115"+
		"\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0119\7\25"+
		"\2\2\u0119\u011b\5V,\2\u011a\u011c\58\35\2\u011b\u011a\3\2\2\2\u011b\u011c"+
		"\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u011f\5*\26\2\u011e\u011d\3\2\2\2\u011e"+
		"\u011f\3\2\2\2\u011f)\3\2\2\2\u0120\u0121\7C\2\2\u0121\u0122\7\35\2\2"+
		"\u0122\u0123\5\60\31\2\u0123\u0124\5,\27\2\u0124\u0125\7A\2\2\u0125\u0126"+
		"\7\36\2\2\u0126\u0127\5\62\32\2\u0127\u0128\5,\27\2\u0128\u0129\7D\2\2"+
		"\u0129\u0136\3\2\2\2\u012a\u012b\7\35\2\2\u012b\u012c\5\60\31\2\u012c"+
		"\u012d\5,\27\2\u012d\u0136\3\2\2\2\u012e\u012f\7\36\2\2\u012f\u0130\5"+
		"\62\32\2\u0130\u0131\5,\27\2\u0131\u0136\3\2\2\2\u0132\u0133\7\37\2\2"+
		"\u0133\u0134\7O\2\2\u0134\u0136\5,\27\2\u0135\u0120\3\2\2\2\u0135\u012a"+
		"\3\2\2\2\u0135\u012e\3\2\2\2\u0135\u0132\3\2\2\2\u0136+\3\2\2\2\u0137"+
		"\u0138\5.\30\2\u0138-\3\2\2\2\u0139\u013a\5p9\2\u013a/\3\2\2\2\u013b\u013c"+
		"\t\2\2\2\u013c\61\3\2\2\2\u013d\u013e\t\3\2\2\u013e\63\3\2\2\2\u013f\u0140"+
		"\7\34\2\2\u0140\u0141\5> \2\u0141\65\3\2\2\2\u0142\u0143\7 \2\2\u0143"+
		"\u0144\5> \2\u0144\67\3\2\2\2\u0145\u0146\7<\2\2\u0146\u0147\5`\61\2\u0147"+
		"9\3\2\2\2\u0148\u014a\5<\37\2\u0149\u014b\5\64\33\2\u014a\u0149\3\2\2"+
		"\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u014e\5*\26\2\u014d\u014c"+
		"\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u0151\5\66\34\2"+
		"\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151;\3\2\2\2\u0152\u0153\7"+
		"%\2\2\u0153\u0158\5@!\2\u0154\u0155\7A\2\2\u0155\u0157\5@!\2\u0156\u0154"+
		"\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159"+
		"\u015b\3\2\2\2\u015a\u0158\3\2\2\2\u015b\u015c\7\25\2\2\u015c\u015e\5"+
		"\36\20\2\u015d\u015f\58\35\2\u015e\u015d\3\2\2\2\u015e\u015f\3\2\2\2\u015f"+
		"=\3\2\2\2\u0160\u0161\7`\2\2\u0161?\3\2\2\2\u0162\u0175\7G\2\2\u0163\u0164"+
		"\5J&\2\u0164\u0165\7B\2\2\u0165\u0166\7G\2\2\u0166\u0175\3\2\2\2\u0167"+
		"\u0168\5J&\2\u0168\u0169\7@\2\2\u0169\u016b\3\2\2\2\u016a\u0167\3\2\2"+
		"\2\u016a\u016b\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u0175\5\\/\2\u016d\u0172"+
		"\5B\"\2\u016e\u0170\7\5\2\2\u016f\u016e\3\2\2\2\u016f\u0170\3\2\2\2\u0170"+
		"\u0171\3\2\2\2\u0171\u0173\5N(\2\u0172\u016f\3\2\2\2\u0172\u0173\3\2\2"+
		"\2\u0173\u0175\3\2\2\2\u0174\u0162\3\2\2\2\u0174\u0163\3\2\2\2\u0174\u016a"+
		"\3\2\2\2\u0174\u016d\3\2\2\2\u0175A\3\2\2\2\u0176\u0177\5D#\2\u0177\u0179"+
		"\7C\2\2\u0178\u017a\5F$\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a"+
		"\u017b\3\2\2\2\u017b\u017c\7D\2\2\u017cC\3\2\2\2\u017d\u017e\5R*\2\u017e"+
		"E\3\2\2\2\u017f\u0182\5H%\2\u0180\u0182\5`\61\2\u0181\u017f\3\2\2\2\u0181"+
		"\u0180\3\2\2\2\u0182\u018a\3\2\2\2\u0183\u0186\7A\2\2\u0184\u0187\5H%"+
		"\2\u0185\u0187\5`\61\2\u0186\u0184\3\2\2\2\u0186\u0185\3\2\2\2\u0187\u0189"+
		"\3\2\2\2\u0188\u0183\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a"+
		"\u018b\3\2\2\2\u018bG\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018e\5J&\2\u018e"+
		"\u018f\7@\2\2\u018f\u0191\3\2\2\2\u0190\u018d\3\2\2\2\u0190\u0191\3\2"+
		"\2\2\u0191\u0192\3\2\2\2\u0192\u0193\5\\/\2\u0193I\3\2\2\2\u0194\u0195"+
		"\5R*\2\u0195K\3\2\2\2\u0196\u0197\7\\\2\2\u0197M\3\2\2\2\u0198\u0199\5"+
		"P)\2\u0199O\3\2\2\2\u019a\u019b\5R*\2\u019bQ\3\2\2\2\u019c\u019f\7^\2"+
		"\2\u019d\u019f\5L\'\2\u019e\u019c\3\2\2\2\u019e\u019d\3\2\2\2\u019fS\3"+
		"\2\2\2\u01a0\u01a1\5R*\2\u01a1U\3\2\2\2\u01a2\u01a3\5R*\2\u01a3W\3\2\2"+
		"\2\u01a4\u01a5\5Z.\2\u01a5\u01aa\5^\60\2\u01a6\u01a8\7!\2\2\u01a7\u01a6"+
		"\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01ab\7\"\2\2\u01aa"+
		"\u01a7\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ae\3\2\2\2\u01ac\u01ad\7\f"+
		"\2\2\u01ad\u01af\5h\65\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2\2\2\u01af"+
		"\u01b2\3\2\2\2\u01b0\u01b1\7$\2\2\u01b1\u01b3\7\32\2\2\u01b2\u01b0\3\2"+
		"\2\2\u01b2\u01b3\3\2\2\2\u01b3Y\3\2\2\2\u01b4\u01b5\5J&\2\u01b5\u01b6"+
		"\7@\2\2\u01b6\u01b8\3\2\2\2\u01b7\u01b4\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8"+
		"\u01b9\3\2\2\2\u01b9\u01ba\5\\/\2\u01ba[\3\2\2\2\u01bb\u01bc\5R*\2\u01bc"+
		"]\3\2\2\2\u01bd\u01c0\5|?\2\u01be\u01c0\5~@\2\u01bf\u01bd\3\2\2\2\u01bf"+
		"\u01be\3\2\2\2\u01c0\u01c8\3\2\2\2\u01c1\u01c6\7\4\2\2\u01c2\u01c3\7M"+
		"\2\2\u01c3\u01c4\5v<\2\u01c4\u01c5\7N\2\2\u01c5\u01c7\3\2\2\2\u01c6\u01c2"+
		"\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c9\3\2\2\2\u01c8\u01c1\3\2\2\2\u01c8"+
		"\u01c9\3\2\2\2\u01c9_\3\2\2\2\u01ca\u01cb\b\61\1\2\u01cb\u01dc\5h\65\2"+
		"\u01cc\u01cd\7E\2\2\u01cd\u01ce\5f\64\2\u01ce\u01cf\7F\2\2\u01cf\u01dc"+
		"\3\2\2\2\u01d0\u01d1\5J&\2\u01d1\u01d2\7@\2\2\u01d2\u01d4\3\2\2\2\u01d3"+
		"\u01d0\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01dc\5\\"+
		"/\2\u01d6\u01dc\5B\"\2\u01d7\u01d8\7C\2\2\u01d8\u01d9\5`\61\2\u01d9\u01da"+
		"\7D\2\2\u01da\u01dc\3\2\2\2\u01db\u01ca\3\2\2\2\u01db\u01cc\3\2\2\2\u01db"+
		"\u01d3\3\2\2\2\u01db\u01d6\3\2\2\2\u01db\u01d7\3\2\2\2\u01dc\u0208\3\2"+
		"\2\2\u01dd\u01de\f\n\2\2\u01de\u01df\5b\62\2\u01df\u01e0\5`\61\13\u01e0"+
		"\u0207\3\2\2\2\u01e1\u01e5\f\t\2\2\u01e2\u01e6\7\33\2\2\u01e3\u01e4\7"+
		"!\2\2\u01e4\u01e6\7\33\2\2\u01e5\u01e2\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e6"+
		"\u01e7\3\2\2\2\u01e7\u0207\5`\61\n\u01e8\u01ea\f\6\2\2\u01e9\u01eb\7!"+
		"\2\2\u01ea\u01e9\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec"+
		"\u01ed\7\6\2\2\u01ed\u01ee\5`\61\2\u01ee\u01ef\7\3\2\2\u01ef\u01f0\5`"+
		"\61\7\u01f0\u0207\3\2\2\2\u01f1\u01f2\f\4\2\2\u01f2\u01f3\7\3\2\2\u01f3"+
		"\u0207\5`\61\5\u01f4\u01f5\f\3\2\2\u01f5\u01f6\7#\2\2\u01f6\u0207\5`\61"+
		"\4\u01f7\u01f8\f\b\2\2\u01f8\u01fa\7\31\2\2\u01f9\u01fb\7!\2\2\u01fa\u01f9"+
		"\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\u0207\7\"\2\2\u01fd"+
		"\u01ff\f\7\2\2\u01fe\u0200\7!\2\2\u01ff\u01fe\3\2\2\2\u01ff\u0200\3\2"+
		"\2\2\u0200\u0201\3\2\2\2\u0201\u0202\7\26\2\2\u0202\u0203\7C\2\2\u0203"+
		"\u0204\5d\63\2\u0204\u0205\7D\2\2\u0205\u0207\3\2\2\2\u0206\u01dd\3\2"+
		"\2\2\u0206\u01e1\3\2\2\2\u0206\u01e8\3\2\2\2\u0206\u01f1\3\2\2\2\u0206"+
		"\u01f4\3\2\2\2\u0206\u01f7\3\2\2\2\u0206\u01fd\3\2\2\2\u0207\u020a\3\2"+
		"\2\2\u0208\u0206\3\2\2\2\u0208\u0209\3\2\2\2\u0209a\3\2\2\2\u020a\u0208"+
		"\3\2\2\2\u020b\u020c\t\4\2\2\u020cc\3\2\2\2\u020d\u0212\5`\61\2\u020e"+
		"\u020f\7A\2\2\u020f\u0211\5`\61\2\u0210\u020e\3\2\2\2\u0211\u0214\3\2"+
		"\2\2\u0212\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213e\3\2\2\2\u0214\u0212"+
		"\3\2\2\2\u0215\u0216\5l\67\2\u0216g\3\2\2\2\u0217\u021c\5j\66\2\u0218"+
		"\u021c\5n8\2\u0219\u021c\5t;\2\u021a\u021c\7\"\2\2\u021b\u0217\3\2\2\2"+
		"\u021b\u0218\3\2\2\2\u021b\u0219\3\2\2\2\u021b\u021a\3\2\2\2\u021ci\3"+
		"\2\2\2\u021d\u021e\7_\2\2\u021ek\3\2\2\2\u021f\u0220\7^\2\2\u0220m\3\2"+
		"\2\2\u0221\u0224\5p9\2\u0222\u0224\5r:\2\u0223\u0221\3\2\2\2\u0223\u0222"+
		"\3\2\2\2\u0224o\3\2\2\2\u0225\u0227\7K\2\2\u0226\u0225\3\2\2\2\u0226\u0227"+
		"\3\2\2\2\u0227\u0228\3\2\2\2\u0228\u0229\7`\2\2\u0229q\3\2\2\2\u022a\u022c"+
		"\7K\2\2\u022b\u022a\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022d\3\2\2\2\u022d"+
		"\u0230\5> \2\u022e\u022f\7B\2\2\u022f\u0231\5> \2\u0230\u022e\3\2\2\2"+
		"\u0230\u0231\3\2\2\2\u0231s\3\2\2\2\u0232\u0233\t\5\2\2\u0233u\3\2\2\2"+
		"\u0234\u0235\5p9\2\u0235w\3\2\2\2\u0236\u0237\5p9\2\u0237y\3\2\2\2\u0238"+
		"\u0239\5p9\2\u0239{\3\2\2\2\u023a\u023b\7\b\2\2\u023b\u023c\7C\2\2\u023c"+
		"\u023d\5x=\2\u023d\u023e\7D\2\2\u023e\u0261\3\2\2\2\u023f\u0244\7;\2\2"+
		"\u0240\u0241\7C\2\2\u0241\u0242\5x=\2\u0242\u0243\7D\2\2\u0243\u0245\3"+
		"\2\2\2\u0244\u0240\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0261\3\2\2\2\u0246"+
		"\u024b\7\13\2\2\u0247\u0248\5x=\2\u0248\u0249\7A\2\2\u0249\u024a\5z>\2"+
		"\u024a\u024c\3\2\2\2\u024b\u0247\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u0261"+
		"\3\2\2\2\u024d\u0261\7,\2\2\u024e\u0261\7&\2\2\u024f\u0261\7\27\2\2\u0250"+
		"\u0261\7f\2\2\u0251\u0261\7\24\2\2\u0252\u0261\7\20\2\2\u0253\u0261\7"+
		"+\2\2\u0254\u0261\7\n\2\2\u0255\u0261\7*\2\2\u0256\u0257\7\7\2\2\u0257"+
		"\u0258\7C\2\2\u0258\u0259\5x=\2\u0259\u025a\7D\2\2\u025a\u0261\3\2\2\2"+
		"\u025b\u025c\7:\2\2\u025c\u025d\7C\2\2\u025d\u025e\5x=\2\u025e\u025f\7"+
		"D\2\2\u025f\u0261\3\2\2\2\u0260\u023a\3\2\2\2\u0260\u023f\3\2\2\2\u0260"+
		"\u0246\3\2\2\2\u0260\u024d\3\2\2\2\u0260\u024e\3\2\2\2\u0260\u024f\3\2"+
		"\2\2\u0260\u0250\3\2\2\2\u0260\u0251\3\2\2\2\u0260\u0252\3\2\2\2\u0260"+
		"\u0253\3\2\2\2\u0260\u0254\3\2\2\2\u0260\u0255\3\2\2\2\u0260\u0256\3\2"+
		"\2\2\u0260\u025b\3\2\2\2\u0261}\3\2\2\2\u0262\u0263\t\6\2\2\u0263\177"+
		"\3\2\2\2@\u0083\u008a\u008e\u0094\u0099\u009f\u00a6\u00b3\u00be\u00cb"+
		"\u00d9\u00e4\u00e9\u00ef\u00f4\u00fc\u0104\u010c\u0115\u011b\u011e\u0135"+
		"\u014a\u014d\u0150\u0158\u015e\u016a\u016f\u0172\u0174\u0179\u0181\u0186"+
		"\u018a\u0190\u019e\u01a7\u01aa\u01ae\u01b2\u01b7\u01bf\u01c6\u01c8\u01d3"+
		"\u01db\u01e5\u01ea\u01fa\u01ff\u0206\u0208\u0212\u021b\u0223\u0226\u022b"+
		"\u0230\u0244\u024b\u0260";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}