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
		FALSE=17, FLOAT=18, FROM=19, IN=20, IF=21, INTEGER=22, BIGINT=23, INTO=24, 
		IS=25, KEY=26, STARTKEY=27, ENDKEY=28, ROWKEY=29, LIKE=30, VERSIONS=31, 
		STARTTS=32, ENDTS=33, TS=34, LIMIT=35, NOT=36, NULL_=37, OR=38, PRIMARY=39, 
		SELECT=40, SHOW=41, SMALLINT=42, VIRTUAL=43, TABLE=44, TABLES=45, TIME=46, 
		TIMESTAMP=47, TINYINT=48, TRUE=49, UNSIGNED_DATE=50, UNSIGNED_DOUBLE=51, 
		UNSIGNED_FLOAT=52, UNSIGNED_INT=53, UNSIGNED_LONG=54, UNSIGNED_SMALLINT=55, 
		UNSIGNED_TIME=56, UNSIGNED_TIMESTAMP=57, UNSIGNED_TINYINT=58, UPDATE=59, 
		UPSERT=60, VALUES=61, VARBINARY=62, VARCHAR=63, WHERE=64, WITH=65, PROPERTIES=66, 
		SEMI=67, COLON=68, COMMA=69, DOT=70, LP=71, RP=72, VAR_LP=73, VAR_RP=74, 
		STAR=75, DIV=76, MOD=77, PLUS=78, MINUS=79, PIPEPIPE=80, LSB=81, RSC=82, 
		EQ=83, NE=84, NE2=85, GT=86, GE=87, LT=88, LE=89, QM=90, WHITE_SPACE=91, 
		SQL_COMMENT=92, LINE_COMMENT=93, HINT_START=94, HINT_END=95, DOUBLE_QUOTE_ID=96, 
		SINGLE_QUOTE=97, ID=98, STRING_LITERAL=99, DECIMAL_LITERAL=100, FLOAT_LITERAL=101, 
		REAL_LITERAL=102, CHAR_LITERAL=103;
	public static final int
		RULE_root = 0, RULE_batch = 1, RULE_sql_command = 2, RULE_ddl_command = 3, 
		RULE_dml_command = 4, RULE_create_virtual_table_command = 5, RULE_table_options = 6, 
		RULE_options_ = 7, RULE_option = 8, RULE_drop_table_command = 9, RULE_show_tables_command = 10, 
		RULE_show_table_command = 11, RULE_if_exists = 12, RULE_if_not_exists = 13, 
		RULE_table_ref = 14, RULE_upsert_values_command = 15, RULE_insert_values = 16, 
		RULE_upsert_column_def_list = 17, RULE_column_def_list = 18, RULE_delete_column_def_list = 19, 
		RULE_delete_command = 20, RULE_timestamp_range_clause = 21, RULE_tsExp = 22, 
		RULE_timestamp = 23, RULE_gtOper = 24, RULE_leOper = 25, RULE_versions_clause = 26, 
		RULE_limit_clause = 27, RULE_select_command = 28, RULE_select_statement = 29, 
		RULE_number = 30, RULE_delete_column_def = 31, RULE_select_column_def = 32, 
		RULE_functionCall = 33, RULE_funcName = 34, RULE_functionArgs = 35, RULE_fullColumnName = 36, 
		RULE_family_name = 37, RULE_quoted_name = 38, RULE_column_alias = 39, 
		RULE_alias = 40, RULE_name = 41, RULE_namespace_name = 42, RULE_table_name = 43, 
		RULE_column_def = 44, RULE_column_ref = 45, RULE_column_name = 46, RULE_data_type = 47, 
		RULE_conditionVal = 48, RULE_conditionValList = 49, RULE_constant = 50, 
		RULE_var = 51, RULE_column = 52, RULE_rowKey = 53, RULE_whereRow = 54, 
		RULE_whereCol = 55, RULE_colCondition = 56, RULE_comp_op = 57, RULE_variable = 58, 
		RULE_literal = 59, RULE_string = 60, RULE_varString = 61, RULE_numeric = 62, 
		RULE_integer = 63, RULE_decimal = 64, RULE_true_false = 65, RULE_dimension_int = 66, 
		RULE_precision_int = 67, RULE_scale_int = 68, RULE_sql_data_type = 69, 
		RULE_hbase_data_type = 70;
	public static final String[] ruleNames = {
		"root", "batch", "sql_command", "ddl_command", "dml_command", "create_virtual_table_command", 
		"table_options", "options_", "option", "drop_table_command", "show_tables_command", 
		"show_table_command", "if_exists", "if_not_exists", "table_ref", "upsert_values_command", 
		"insert_values", "upsert_column_def_list", "column_def_list", "delete_column_def_list", 
		"delete_command", "timestamp_range_clause", "tsExp", "timestamp", "gtOper", 
		"leOper", "versions_clause", "limit_clause", "select_command", "select_statement", 
		"number", "delete_column_def", "select_column_def", "functionCall", "funcName", 
		"functionArgs", "fullColumnName", "family_name", "quoted_name", "column_alias", 
		"alias", "name", "namespace_name", "table_name", "column_def", "column_ref", 
		"column_name", "data_type", "conditionVal", "conditionValList", "constant", 
		"var", "column", "rowKey", "whereRow", "whereCol", "colCondition", "comp_op", 
		"variable", "literal", "string", "varString", "numeric", "integer", "decimal", 
		"true_false", "dimension_int", "precision_int", "scale_int", "sql_data_type", 
		"hbase_data_type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'AND'", "'ARRAY'", "'AS'", "'BETWEEN'", "'BINARY'", "'CHAR'", "'CREATE'", 
		"'DATE'", "'DECIMAL'", "'DEFAULT'", "'DELETE'", "'DISABLE'", "'DISTINCT'", 
		"'DOUBLE'", "'DROP'", "'EXISTS'", "'FALSE'", "'FLOAT'", "'FROM'", "'IN'", 
		"'IF'", "'INTEGER'", "'BIGINT'", "'INTO'", "'IS'", "'KEY'", "'STARTKEY'", 
		"'ENDKEY'", "'ROWKEY'", "'LIKE'", "'VERSIONS'", "'STARTTS'", "'ENDTS'", 
		"'TS'", "'LIMIT'", "'NOT'", "'NULL'", "'OR'", "'PRIMARY'", "'SELECT'", 
		"'SHOW'", "'SMALLINT'", "'VIRTUAL'", "'TABLE'", "'TABLES'", "'TIME'", 
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
		"EXISTS", "FALSE", "FLOAT", "FROM", "IN", "IF", "INTEGER", "BIGINT", "INTO", 
		"IS", "KEY", "STARTKEY", "ENDKEY", "ROWKEY", "LIKE", "VERSIONS", "STARTTS", 
		"ENDTS", "TS", "LIMIT", "NOT", "NULL_", "OR", "PRIMARY", "SELECT", "SHOW", 
		"SMALLINT", "VIRTUAL", "TABLE", "TABLES", "TIME", "TIMESTAMP", "TINYINT", 
		"TRUE", "UNSIGNED_DATE", "UNSIGNED_DOUBLE", "UNSIGNED_FLOAT", "UNSIGNED_INT", 
		"UNSIGNED_LONG", "UNSIGNED_SMALLINT", "UNSIGNED_TIME", "UNSIGNED_TIMESTAMP", 
		"UNSIGNED_TINYINT", "UPDATE", "UPSERT", "VALUES", "VARBINARY", "VARCHAR", 
		"WHERE", "WITH", "PROPERTIES", "SEMI", "COLON", "COMMA", "DOT", "LP", 
		"RP", "VAR_LP", "VAR_RP", "STAR", "DIV", "MOD", "PLUS", "MINUS", "PIPEPIPE", 
		"LSB", "RSC", "EQ", "NE", "NE2", "GT", "GE", "LT", "LE", "QM", "WHITE_SPACE", 
		"SQL_COMMENT", "LINE_COMMENT", "HINT_START", "HINT_END", "DOUBLE_QUOTE_ID", 
		"SINGLE_QUOTE", "ID", "STRING_LITERAL", "DECIMAL_LITERAL", "FLOAT_LITERAL", 
		"REAL_LITERAL", "CHAR_LITERAL"
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
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << DELETE) | (1L << DROP) | (1L << SELECT) | (1L << SHOW) | (1L << UPSERT))) != 0)) {
				{
				{
				setState(142);
				batch();
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148);
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
			setState(150);
			sql_command();
			setState(152);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(151);
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
			setState(156);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case SHOW:
				enterOuterAlt(_localctx, 1);
				{
				setState(154);
				ddl_command();
				}
				break;
			case DELETE:
			case SELECT:
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
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
			setState(162);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				create_virtual_table_command();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				show_tables_command();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				show_table_command();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
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
			setState(167);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				select_command();
				}
				break;
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				upsert_values_command();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
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
			setState(169);
			match(CREATE);
			setState(170);
			match(VIRTUAL);
			setState(171);
			match(TABLE);
			setState(173);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(172);
				if_not_exists();
				}
			}

			setState(175);
			table_ref();
			setState(176);
			match(LP);
			setState(177);
			column_def_list();
			setState(178);
			match(RP);
			setState(180);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(179);
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
			setState(182);
			match(WITH);
			setState(183);
			match(PROPERTIES);
			setState(184);
			match(LP);
			setState(185);
			options_();
			setState(186);
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
			setState(188);
			option();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(189);
				match(COMMA);
				setState(190);
				option();
				}
				}
				setState(195);
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
			setState(196);
			name();
			setState(197);
			match(EQ);
			setState(198);
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
			setState(200);
			match(DROP);
			setState(201);
			match(VIRTUAL);
			setState(202);
			match(TABLE);
			setState(204);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(203);
				if_exists();
				}
			}

			setState(206);
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
			setState(208);
			match(SHOW);
			setState(209);
			match(VIRTUAL);
			setState(210);
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
			setState(212);
			match(SHOW);
			setState(213);
			match(CREATE);
			setState(214);
			match(VIRTUAL);
			setState(215);
			match(TABLE);
			setState(217);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(216);
				if_exists();
				}
			}

			setState(219);
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
			setState(221);
			match(IF);
			setState(222);
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
			setState(224);
			match(IF);
			setState(225);
			match(NOT);
			setState(226);
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
			setState(231);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(228);
				namespace_name();
				setState(229);
				match(COLON);
				}
				break;
			}
			setState(233);
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
		public Upsert_column_def_listContext upsert_column_def_list() {
			return getRuleContext(Upsert_column_def_listContext.class,0);
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
			setState(235);
			match(UPSERT);
			setState(236);
			match(INTO);
			setState(237);
			table_ref();
			setState(242);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(238);
				match(LP);
				setState(239);
				upsert_column_def_list();
				setState(240);
				match(RP);
				}
			}

			setState(244);
			match(VALUES);
			setState(245);
			match(LP);
			setState(247);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << NULL_) | (1L << TRUE))) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (MINUS - 79)) | (1L << (STRING_LITERAL - 79)) | (1L << (DECIMAL_LITERAL - 79)))) != 0)) {
				{
				setState(246);
				insert_values();
				}
			}

			setState(249);
			match(RP);
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(250);
				match(COMMA);
				setState(251);
				match(LP);
				setState(253);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << NULL_) | (1L << TRUE))) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (MINUS - 79)) | (1L << (STRING_LITERAL - 79)) | (1L << (DECIMAL_LITERAL - 79)))) != 0)) {
					{
					setState(252);
					insert_values();
					}
				}

				setState(255);
				match(RP);
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
			setState(261);
			literal();
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(262);
				match(COMMA);
				setState(263);
				literal();
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

	public static class Upsert_column_def_listContext extends ParserRuleContext {
		public List<Column_refContext> column_ref() {
			return getRuleContexts(Column_refContext.class);
		}
		public Column_refContext column_ref(int i) {
			return getRuleContext(Column_refContext.class,i);
		}
		public Upsert_column_def_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_upsert_column_def_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitUpsert_column_def_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Upsert_column_def_listContext upsert_column_def_list() throws RecognitionException {
		Upsert_column_def_listContext _localctx = new Upsert_column_def_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_upsert_column_def_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			column_ref();
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(270);
				match(COMMA);
				setState(271);
				column_ref();
				}
				}
				setState(276);
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
			setState(277);
			column_def();
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(278);
				match(COMMA);
				setState(279);
				column_def();
				}
				}
				setState(284);
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

	public static class Delete_column_def_listContext extends ParserRuleContext {
		public List<Delete_column_defContext> delete_column_def() {
			return getRuleContexts(Delete_column_defContext.class);
		}
		public Delete_column_defContext delete_column_def(int i) {
			return getRuleContext(Delete_column_defContext.class,i);
		}
		public Delete_column_def_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_column_def_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDelete_column_def_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_column_def_listContext delete_column_def_list() throws RecognitionException {
		Delete_column_def_listContext _localctx = new Delete_column_def_listContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_delete_column_def_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			delete_column_def();
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(286);
				match(COMMA);
				setState(287);
				delete_column_def();
				}
				}
				setState(292);
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
		public TerminalNode FROM() { return getToken(HydraQLParser.FROM, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(HydraQLParser.WHERE, 0); }
		public WhereRowContext whereRow() {
			return getRuleContext(WhereRowContext.class,0);
		}
		public Delete_column_def_listContext delete_column_def_list() {
			return getRuleContext(Delete_column_def_listContext.class,0);
		}
		public TerminalNode AND() { return getToken(HydraQLParser.AND, 0); }
		public WhereColContext whereCol() {
			return getRuleContext(WhereColContext.class,0);
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
		enterRule(_localctx, 40, RULE_delete_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(DELETE);
			setState(295);
			_la = _input.LA(1);
			if (_la==DOUBLE_QUOTE_ID || _la==ID) {
				{
				setState(294);
				delete_column_def_list();
				}
			}

			setState(297);
			match(FROM);
			setState(298);
			table_ref();
			setState(299);
			match(WHERE);
			setState(300);
			whereRow();
			setState(303);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(301);
				match(AND);
				setState(302);
				whereCol();
				}
			}

			setState(306);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (STARTTS - 32)) | (1L << (ENDTS - 32)) | (1L << (TS - 32)) | (1L << (LP - 32)))) != 0)) {
				{
				setState(305);
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
	public static class TsRangeStartAndEndContext extends Timestamp_range_clauseContext {
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
		public TsRangeStartAndEndContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsRangeStartAndEnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TsRangeEqContext extends Timestamp_range_clauseContext {
		public TerminalNode TS() { return getToken(HydraQLParser.TS, 0); }
		public TerminalNode EQ() { return getToken(HydraQLParser.EQ, 0); }
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public TsRangeEqContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsRangeEq(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TsRangeStartContext extends Timestamp_range_clauseContext {
		public TerminalNode STARTTS() { return getToken(HydraQLParser.STARTTS, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public TsRangeStartContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsRangeStart(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TsRangeEndContext extends Timestamp_range_clauseContext {
		public TerminalNode ENDTS() { return getToken(HydraQLParser.ENDTS, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public TsRangeEndContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsRangeEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Timestamp_range_clauseContext timestamp_range_clause() throws RecognitionException {
		Timestamp_range_clauseContext _localctx = new Timestamp_range_clauseContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_timestamp_range_clause);
		try {
			setState(329);
			switch (_input.LA(1)) {
			case LP:
				_localctx = new TsRangeStartAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(308);
				match(LP);
				setState(309);
				match(STARTTS);
				setState(310);
				gtOper();
				setState(311);
				tsExp();
				setState(312);
				match(COMMA);
				setState(313);
				match(ENDTS);
				setState(314);
				leOper();
				setState(315);
				tsExp();
				setState(316);
				match(RP);
				}
				break;
			case STARTTS:
				_localctx = new TsRangeStartContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				match(STARTTS);
				setState(319);
				gtOper();
				setState(320);
				tsExp();
				}
				break;
			case ENDTS:
				_localctx = new TsRangeEndContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(322);
				match(ENDTS);
				setState(323);
				leOper();
				setState(324);
				tsExp();
				}
				break;
			case TS:
				_localctx = new TsRangeEqContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(326);
				match(TS);
				setState(327);
				match(EQ);
				setState(328);
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
		enterRule(_localctx, 44, RULE_tsExp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
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
		enterRule(_localctx, 46, RULE_timestamp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
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
		enterRule(_localctx, 48, RULE_gtOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(335);
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
		enterRule(_localctx, 50, RULE_leOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
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
		enterRule(_localctx, 52, RULE_versions_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(VERSIONS);
			setState(340);
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
		enterRule(_localctx, 54, RULE_limit_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			match(LIMIT);
			setState(343);
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
			setState(345);
			select_statement();
			setState(347);
			_la = _input.LA(1);
			if (_la==VERSIONS) {
				{
				setState(346);
				versions_clause();
				}
			}

			setState(350);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & ((1L << (STARTTS - 32)) | (1L << (ENDTS - 32)) | (1L << (TS - 32)) | (1L << (LP - 32)))) != 0)) {
				{
				setState(349);
				timestamp_range_clause();
				}
			}

			setState(353);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(352);
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
		public List<Select_column_defContext> select_column_def() {
			return getRuleContexts(Select_column_defContext.class);
		}
		public Select_column_defContext select_column_def(int i) {
			return getRuleContext(Select_column_defContext.class,i);
		}
		public TerminalNode FROM() { return getToken(HydraQLParser.FROM, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(HydraQLParser.WHERE, 0); }
		public WhereRowContext whereRow() {
			return getRuleContext(WhereRowContext.class,0);
		}
		public TerminalNode AND() { return getToken(HydraQLParser.AND, 0); }
		public WhereColContext whereCol() {
			return getRuleContext(WhereColContext.class,0);
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
			setState(355);
			match(SELECT);
			setState(356);
			select_column_def();
			setState(361);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(357);
				match(COMMA);
				setState(358);
				select_column_def();
				}
				}
				setState(363);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(364);
			match(FROM);
			setState(365);
			table_ref();
			setState(366);
			match(WHERE);
			setState(367);
			whereRow();
			setState(370);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(368);
				match(AND);
				setState(369);
				whereCol();
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
			setState(372);
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

	public static class Delete_column_defContext extends ParserRuleContext {
		public Delete_column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_column_def; }
	 
		public Delete_column_defContext() { }
		public void copyFrom(Delete_column_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DeleteOneFamilyAllColContext extends Delete_column_defContext {
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public DeleteOneFamilyAllColContext(Delete_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDeleteOneFamilyAllCol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeleteFamilyAndColContext extends Delete_column_defContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public DeleteFamilyAndColContext(Delete_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDeleteFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_column_defContext delete_column_def() throws RecognitionException {
		Delete_column_defContext _localctx = new Delete_column_defContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_delete_column_def);
		try {
			setState(384);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				_localctx = new DeleteOneFamilyAllColContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				family_name();
				setState(375);
				match(DOT);
				setState(376);
				match(STAR);
				}
				break;
			case 2:
				_localctx = new DeleteFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(381);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(378);
					family_name();
					setState(379);
					match(COLON);
					}
					break;
				}
				setState(383);
				column_name();
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

	public static class Select_column_defContext extends ParserRuleContext {
		public Select_column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_column_def; }
	 
		public Select_column_defContext() { }
		public void copyFrom(Select_column_defContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SelectAllFamilyAndColContext extends Select_column_defContext {
		public SelectAllFamilyAndColContext(Select_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectAllFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectOneFamilyAllColContext extends Select_column_defContext {
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public SelectOneFamilyAllColContext(Select_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectOneFamilyAllCol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectWithFuncCallContext extends Select_column_defContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public Column_aliasContext column_alias() {
			return getRuleContext(Column_aliasContext.class,0);
		}
		public TerminalNode AS() { return getToken(HydraQLParser.AS, 0); }
		public SelectWithFuncCallContext(Select_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectWithFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelectFamilyAndColContext extends Select_column_defContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public Column_aliasContext column_alias() {
			return getRuleContext(Column_aliasContext.class,0);
		}
		public TerminalNode AS() { return getToken(HydraQLParser.AS, 0); }
		public SelectFamilyAndColContext(Select_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_column_defContext select_column_def() throws RecognitionException {
		Select_column_defContext _localctx = new Select_column_defContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_select_column_def);
		int _la;
		try {
			setState(410);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				_localctx = new SelectAllFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				match(STAR);
				}
				break;
			case 2:
				_localctx = new SelectOneFamilyAllColContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				family_name();
				setState(388);
				match(DOT);
				setState(389);
				match(STAR);
				}
				break;
			case 3:
				_localctx = new SelectFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(394);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(391);
					family_name();
					setState(392);
					match(COLON);
					}
					break;
				}
				setState(396);
				column_name();
				setState(401);
				_la = _input.LA(1);
				if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
					{
					setState(398);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(397);
						match(AS);
						}
					}

					setState(400);
					column_alias();
					}
				}

				}
				break;
			case 4:
				_localctx = new SelectWithFuncCallContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(403);
				functionCall();
				setState(408);
				_la = _input.LA(1);
				if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
					{
					setState(405);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(404);
						match(AS);
						}
					}

					setState(407);
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
		enterRule(_localctx, 66, RULE_functionCall);
		int _la;
		try {
			_localctx = new UdfFunctionCallContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			funcName();
			setState(413);
			match(LP);
			setState(415);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FALSE) | (1L << NULL_) | (1L << TRUE))) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (MINUS - 79)) | (1L << (DOUBLE_QUOTE_ID - 79)) | (1L << (ID - 79)) | (1L << (STRING_LITERAL - 79)) | (1L << (DECIMAL_LITERAL - 79)))) != 0)) {
				{
				setState(414);
				functionArgs();
				}
			}

			setState(417);
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
		enterRule(_localctx, 68, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
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
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
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
		enterRule(_localctx, 70, RULE_functionArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			switch (_input.LA(1)) {
			case DOUBLE_QUOTE_ID:
			case ID:
				{
				setState(421);
				fullColumnName();
				}
				break;
			case FALSE:
			case NULL_:
			case TRUE:
			case MINUS:
			case STRING_LITERAL:
			case DECIMAL_LITERAL:
				{
				setState(422);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(425);
				match(COMMA);
				setState(428);
				switch (_input.LA(1)) {
				case DOUBLE_QUOTE_ID:
				case ID:
					{
					setState(426);
					fullColumnName();
					}
					break;
				case FALSE:
				case NULL_:
				case TRUE:
				case MINUS:
				case STRING_LITERAL:
				case DECIMAL_LITERAL:
					{
					setState(427);
					literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(434);
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
		enterRule(_localctx, 72, RULE_fullColumnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(435);
				family_name();
				setState(436);
				match(COLON);
				}
				break;
			}
			setState(440);
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
		enterRule(_localctx, 74, RULE_family_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
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
		enterRule(_localctx, 76, RULE_quoted_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
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
		enterRule(_localctx, 78, RULE_column_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
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
		enterRule(_localctx, 80, RULE_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
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
		enterRule(_localctx, 82, RULE_name);
		try {
			setState(452);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(450);
				match(ID);
				}
				break;
			case DOUBLE_QUOTE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(451);
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
		enterRule(_localctx, 84, RULE_namespace_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
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
		enterRule(_localctx, 86, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456);
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
		enterRule(_localctx, 88, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			column_ref();
			setState(459);
			data_type();
			setState(464);
			_la = _input.LA(1);
			if (_la==NOT || _la==NULL_) {
				{
				setState(461);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(460);
					match(NOT);
					}
				}

				setState(463);
				match(NULL_);
				}
			}

			setState(468);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(466);
				match(DEFAULT);
				setState(467);
				literal();
				}
			}

			setState(472);
			_la = _input.LA(1);
			if (_la==PRIMARY) {
				{
				setState(470);
				match(PRIMARY);
				setState(471);
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
		enterRule(_localctx, 90, RULE_column_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(474);
				family_name();
				setState(475);
				match(COLON);
				}
				break;
			}
			setState(479);
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
		enterRule(_localctx, 92, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
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
		enterRule(_localctx, 94, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(485);
			switch (_input.LA(1)) {
			case BINARY:
			case CHAR:
			case DATE:
			case DECIMAL:
			case DOUBLE:
			case FLOAT:
			case INTEGER:
			case BIGINT:
			case SMALLINT:
			case TIME:
			case TIMESTAMP:
			case TINYINT:
			case VARBINARY:
			case VARCHAR:
				{
				setState(483);
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
				setState(484);
				hbase_data_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(494);
			_la = _input.LA(1);
			if (_la==ARRAY) {
				{
				setState(487);
				match(ARRAY);
				setState(492);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(488);
					match(LSB);
					setState(489);
					dimension_int();
					setState(490);
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

	public static class ConditionValContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public ConditionValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionVal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitConditionVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionValContext conditionVal() throws RecognitionException {
		ConditionValContext _localctx = new ConditionValContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_conditionVal);
		try {
			setState(498);
			switch (_input.LA(1)) {
			case FALSE:
			case NULL_:
			case TRUE:
			case MINUS:
			case STRING_LITERAL:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(496);
				constant();
				}
				break;
			case VAR_LP:
				enterOuterAlt(_localctx, 2);
				{
				setState(497);
				var();
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

	public static class ConditionValListContext extends ParserRuleContext {
		public List<ConditionValContext> conditionVal() {
			return getRuleContexts(ConditionValContext.class);
		}
		public ConditionValContext conditionVal(int i) {
			return getRuleContext(ConditionValContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
		}
		public ConditionValListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionValList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitConditionValList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionValListContext conditionValList() throws RecognitionException {
		ConditionValListContext _localctx = new ConditionValListContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_conditionValList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			conditionVal();
			setState(505);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(501);
				match(COMMA);
				setState(502);
				conditionVal();
				}
				}
				setState(507);
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

	public static class ConstantContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(508);
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

	public static class VarContext extends ParserRuleContext {
		public TerminalNode VAR_LP() { return getToken(HydraQLParser.VAR_LP, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode VAR_RP() { return getToken(HydraQLParser.VAR_RP, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			match(VAR_LP);
			setState(511);
			variable();
			setState(512);
			match(VAR_RP);
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

	public static class ColumnContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(514);
				family_name();
				setState(515);
				match(COLON);
				}
				break;
			}
			setState(519);
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

	public static class RowKeyContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumericContext numeric() {
			return getRuleContext(NumericContext.class,0);
		}
		public RowKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowKey; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRowKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowKeyContext rowKey() throws RecognitionException {
		RowKeyContext _localctx = new RowKeyContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_rowKey);
		try {
			setState(523);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(521);
				string();
				}
				break;
			case MINUS:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(522);
				numeric();
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

	public static class WhereRowContext extends ParserRuleContext {
		public WhereRowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereRow; }
	 
		public WhereRowContext() { }
		public void copyFrom(WhereRowContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RowKeyStartAndEndContext extends WhereRowContext {
		public TerminalNode STARTKEY() { return getToken(HydraQLParser.STARTKEY, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public List<RowKeyContext> rowKey() {
			return getRuleContexts(RowKeyContext.class);
		}
		public RowKeyContext rowKey(int i) {
			return getRuleContext(RowKeyContext.class,i);
		}
		public TerminalNode AND() { return getToken(HydraQLParser.AND, 0); }
		public TerminalNode ENDKEY() { return getToken(HydraQLParser.ENDKEY, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public RowKeyStartAndEndContext(WhereRowContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRowKeyStartAndEnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RowKeyInSomeKeysContext extends WhereRowContext {
		public TerminalNode ROWKEY() { return getToken(HydraQLParser.ROWKEY, 0); }
		public TerminalNode IN() { return getToken(HydraQLParser.IN, 0); }
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public List<RowKeyContext> rowKey() {
			return getRuleContexts(RowKeyContext.class);
		}
		public RowKeyContext rowKey(int i) {
			return getRuleContext(RowKeyContext.class,i);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
		}
		public RowKeyInSomeKeysContext(WhereRowContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRowKeyInSomeKeys(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RowKeyEndContext extends WhereRowContext {
		public TerminalNode ENDKEY() { return getToken(HydraQLParser.ENDKEY, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public RowKeyContext rowKey() {
			return getRuleContext(RowKeyContext.class,0);
		}
		public RowKeyEndContext(WhereRowContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRowKeyEnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RowKeyStartContext extends WhereRowContext {
		public TerminalNode STARTKEY() { return getToken(HydraQLParser.STARTKEY, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public RowKeyContext rowKey() {
			return getRuleContext(RowKeyContext.class,0);
		}
		public RowKeyStartContext(WhereRowContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRowKeyStart(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RowKeyLikeContext extends WhereRowContext {
		public TerminalNode ROWKEY() { return getToken(HydraQLParser.ROWKEY, 0); }
		public TerminalNode LIKE() { return getToken(HydraQLParser.LIKE, 0); }
		public RowKeyContext rowKey() {
			return getRuleContext(RowKeyContext.class,0);
		}
		public RowKeyLikeContext(WhereRowContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRowKeyLike(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RowKeyEqOneContext extends WhereRowContext {
		public TerminalNode ROWKEY() { return getToken(HydraQLParser.ROWKEY, 0); }
		public TerminalNode EQ() { return getToken(HydraQLParser.EQ, 0); }
		public RowKeyContext rowKey() {
			return getRuleContext(RowKeyContext.class,0);
		}
		public RowKeyEqOneContext(WhereRowContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitRowKeyEqOne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereRowContext whereRow() throws RecognitionException {
		WhereRowContext _localctx = new WhereRowContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_whereRow);
		int _la;
		try {
			setState(560);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				_localctx = new RowKeyStartAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(525);
				match(STARTKEY);
				setState(526);
				gtOper();
				setState(527);
				rowKey();
				setState(528);
				match(AND);
				setState(529);
				match(ENDKEY);
				setState(530);
				leOper();
				setState(531);
				rowKey();
				}
				break;
			case 2:
				_localctx = new RowKeyStartContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(533);
				match(STARTKEY);
				setState(534);
				gtOper();
				setState(535);
				rowKey();
				}
				break;
			case 3:
				_localctx = new RowKeyEndContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(537);
				match(ENDKEY);
				setState(538);
				leOper();
				setState(539);
				rowKey();
				}
				break;
			case 4:
				_localctx = new RowKeyEqOneContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(541);
				match(ROWKEY);
				setState(542);
				match(EQ);
				setState(543);
				rowKey();
				}
				break;
			case 5:
				_localctx = new RowKeyInSomeKeysContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(544);
				match(ROWKEY);
				setState(545);
				match(IN);
				setState(546);
				match(LP);
				setState(547);
				rowKey();
				setState(552);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(548);
					match(COMMA);
					setState(549);
					rowKey();
					}
					}
					setState(554);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(555);
				match(RP);
				}
				break;
			case 6:
				_localctx = new RowKeyLikeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(557);
				match(ROWKEY);
				setState(558);
				match(LIKE);
				setState(559);
				rowKey();
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

	public static class WhereColContext extends ParserRuleContext {
		public ColConditionContext colCondition() {
			return getRuleContext(ColConditionContext.class,0);
		}
		public WhereColContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereCol; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitWhereCol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereColContext whereCol() throws RecognitionException {
		WhereColContext _localctx = new WhereColContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_whereCol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562);
			colCondition(0);
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

	public static class ColConditionContext extends ParserRuleContext {
		public ColConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colCondition; }
	 
		public ColConditionContext() { }
		public void copyFrom(ColConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ColConditionIsNullOrNotContext extends ColConditionContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode IS() { return getToken(HydraQLParser.IS, 0); }
		public TerminalNode NULL_() { return getToken(HydraQLParser.NULL_, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ColConditionIsNullOrNotContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionIsNullOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColConditionInOrNotInContext extends ColConditionContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode IN() { return getToken(HydraQLParser.IN, 0); }
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public ConditionValListContext conditionValList() {
			return getRuleContext(ConditionValListContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ColConditionInOrNotInContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionInOrNotIn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColConditionCompOpContext extends ColConditionContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public Comp_opContext comp_op() {
			return getRuleContext(Comp_opContext.class,0);
		}
		public ConditionValContext conditionVal() {
			return getRuleContext(ConditionValContext.class,0);
		}
		public ColConditionCompOpContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionCompOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColConditionBetweenOrNotContext extends ColConditionContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode BETWEEN() { return getToken(HydraQLParser.BETWEEN, 0); }
		public List<ConditionValContext> conditionVal() {
			return getRuleContexts(ConditionValContext.class);
		}
		public ConditionValContext conditionVal(int i) {
			return getRuleContext(ConditionValContext.class,i);
		}
		public TerminalNode AND() { return getToken(HydraQLParser.AND, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ColConditionBetweenOrNotContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionBetweenOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColConditionLikeOrNotContext extends ColConditionContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public ConditionValContext conditionVal() {
			return getRuleContext(ConditionValContext.class,0);
		}
		public TerminalNode LIKE() { return getToken(HydraQLParser.LIKE, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ColConditionLikeOrNotContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionLikeOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColConditionAndContext extends ColConditionContext {
		public List<ColConditionContext> colCondition() {
			return getRuleContexts(ColConditionContext.class);
		}
		public ColConditionContext colCondition(int i) {
			return getRuleContext(ColConditionContext.class,i);
		}
		public TerminalNode AND() { return getToken(HydraQLParser.AND, 0); }
		public ColConditionAndContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColConditionOrContext extends ColConditionContext {
		public List<ColConditionContext> colCondition() {
			return getRuleContexts(ColConditionContext.class);
		}
		public ColConditionContext colCondition(int i) {
			return getRuleContext(ColConditionContext.class,i);
		}
		public TerminalNode OR() { return getToken(HydraQLParser.OR, 0); }
		public ColConditionOrContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColConditionWrapperContext extends ColConditionContext {
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public ColConditionContext colCondition() {
			return getRuleContext(ColConditionContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public ColConditionWrapperContext(ColConditionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColConditionWrapper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColConditionContext colCondition() throws RecognitionException {
		return colCondition(0);
	}

	private ColConditionContext colCondition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ColConditionContext _localctx = new ColConditionContext(_ctx, _parentState);
		ColConditionContext _prevctx = _localctx;
		int _startState = 112;
		enterRecursionRule(_localctx, 112, RULE_colCondition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(606);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				_localctx = new ColConditionWrapperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(565);
				match(LP);
				setState(566);
				colCondition(0);
				setState(567);
				match(RP);
				}
				break;
			case 2:
				{
				_localctx = new ColConditionCompOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(569);
				column();
				setState(570);
				comp_op();
				setState(571);
				conditionVal();
				}
				break;
			case 3:
				{
				_localctx = new ColConditionLikeOrNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(573);
				column();
				setState(577);
				switch (_input.LA(1)) {
				case LIKE:
					{
					setState(574);
					match(LIKE);
					}
					break;
				case NOT:
					{
					setState(575);
					match(NOT);
					setState(576);
					match(LIKE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(579);
				conditionVal();
				}
				break;
			case 4:
				{
				_localctx = new ColConditionIsNullOrNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(581);
				column();
				setState(582);
				match(IS);
				setState(584);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(583);
					match(NOT);
					}
				}

				setState(586);
				match(NULL_);
				}
				break;
			case 5:
				{
				_localctx = new ColConditionInOrNotInContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(588);
				column();
				setState(590);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(589);
					match(NOT);
					}
				}

				setState(592);
				match(IN);
				setState(593);
				match(LP);
				setState(594);
				conditionValList();
				setState(595);
				match(RP);
				}
				break;
			case 6:
				{
				_localctx = new ColConditionBetweenOrNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(597);
				column();
				setState(599);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(598);
					match(NOT);
					}
				}

				setState(601);
				match(BETWEEN);
				setState(602);
				conditionVal();
				setState(603);
				match(AND);
				setState(604);
				conditionVal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(616);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(614);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						_localctx = new ColConditionAndContext(new ColConditionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_colCondition);
						setState(608);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(609);
						match(AND);
						setState(610);
						colCondition(8);
						}
						break;
					case 2:
						{
						_localctx = new ColConditionOrContext(new ColConditionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_colCondition);
						setState(611);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(612);
						match(OR);
						setState(613);
						colCondition(7);
						}
						break;
					}
					} 
				}
				setState(618);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
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
		enterRule(_localctx, 114, RULE_comp_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(619);
			_la = _input.LA(1);
			if ( !(((((_la - 83)) & ~0x3f) == 0 && ((1L << (_la - 83)) & ((1L << (EQ - 83)) | (1L << (NE - 83)) | (1L << (NE2 - 83)) | (1L << (GT - 83)) | (1L << (GE - 83)) | (1L << (LT - 83)) | (1L << (LE - 83)))) != 0)) ) {
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
		enterRule(_localctx, 116, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
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
		enterRule(_localctx, 118, RULE_literal);
		try {
			setState(627);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(623);
				string();
				}
				break;
			case MINUS:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(624);
				numeric();
				}
				break;
			case FALSE:
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(625);
				true_false();
				}
				break;
			case NULL_:
				enterOuterAlt(_localctx, 4);
				{
				setState(626);
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
		enterRule(_localctx, 120, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(629);
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
		enterRule(_localctx, 122, RULE_varString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
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
		enterRule(_localctx, 124, RULE_numeric);
		try {
			setState(635);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(633);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(634);
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
		enterRule(_localctx, 126, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(638);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(637);
				match(MINUS);
				}
			}

			setState(640);
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
		enterRule(_localctx, 128, RULE_decimal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(642);
				match(MINUS);
				}
			}

			setState(645);
			number();
			setState(648);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				setState(646);
				match(DOT);
				setState(647);
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
		enterRule(_localctx, 130, RULE_true_false);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(650);
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
		enterRule(_localctx, 132, RULE_dimension_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652);
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
		enterRule(_localctx, 134, RULE_precision_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
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
		enterRule(_localctx, 136, RULE_scale_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(656);
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
		enterRule(_localctx, 138, RULE_sql_data_type);
		int _la;
		try {
			setState(696);
			switch (_input.LA(1)) {
			case CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(658);
				match(CHAR);
				setState(659);
				match(LP);
				setState(660);
				precision_int();
				setState(661);
				match(RP);
				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(663);
				match(VARCHAR);
				setState(668);
				_la = _input.LA(1);
				if (_la==LP) {
					{
					setState(664);
					match(LP);
					setState(665);
					precision_int();
					setState(666);
					match(RP);
					}
				}

				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(670);
				match(DECIMAL);
				setState(675);
				_la = _input.LA(1);
				if (_la==MINUS || _la==DECIMAL_LITERAL) {
					{
					setState(671);
					precision_int();
					setState(672);
					match(COMMA);
					setState(673);
					scale_int();
					}
				}

				}
				break;
			case TINYINT:
				enterOuterAlt(_localctx, 4);
				{
				setState(677);
				match(TINYINT);
				}
				break;
			case SMALLINT:
				enterOuterAlt(_localctx, 5);
				{
				setState(678);
				match(SMALLINT);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 6);
				{
				setState(679);
				match(INTEGER);
				}
				break;
			case BIGINT:
				enterOuterAlt(_localctx, 7);
				{
				setState(680);
				match(BIGINT);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 8);
				{
				setState(681);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 9);
				{
				setState(682);
				match(DOUBLE);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 10);
				{
				setState(683);
				match(TIMESTAMP);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 11);
				{
				setState(684);
				match(DATE);
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 12);
				{
				setState(685);
				match(TIME);
				}
				break;
			case BINARY:
				enterOuterAlt(_localctx, 13);
				{
				setState(686);
				match(BINARY);
				setState(687);
				match(LP);
				setState(688);
				precision_int();
				setState(689);
				match(RP);
				}
				break;
			case VARBINARY:
				enterOuterAlt(_localctx, 14);
				{
				setState(691);
				match(VARBINARY);
				setState(692);
				match(LP);
				setState(693);
				precision_int();
				setState(694);
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
		enterRule(_localctx, 140, RULE_hbase_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(698);
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
		case 56:
			return colCondition_sempred((ColConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean colCondition_sempred(ColConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3i\u02bf\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\3\2"+
		"\7\2\u0092\n\2\f\2\16\2\u0095\13\2\3\2\3\2\3\3\3\3\5\3\u009b\n\3\3\4\3"+
		"\4\5\4\u009f\n\4\3\5\3\5\3\5\3\5\5\5\u00a5\n\5\3\6\3\6\3\6\5\6\u00aa\n"+
		"\6\3\7\3\7\3\7\3\7\5\7\u00b0\n\7\3\7\3\7\3\7\3\7\3\7\5\7\u00b7\n\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\7\t\u00c2\n\t\f\t\16\t\u00c5\13\t\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\5\13\u00cf\n\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u00dc\n\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\5\20\u00ea\n\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\5\21\u00f5\n\21\3\21\3\21\3\21\5\21\u00fa\n\21\3\21\3"+
		"\21\3\21\3\21\5\21\u0100\n\21\3\21\7\21\u0103\n\21\f\21\16\21\u0106\13"+
		"\21\3\22\3\22\3\22\7\22\u010b\n\22\f\22\16\22\u010e\13\22\3\23\3\23\3"+
		"\23\7\23\u0113\n\23\f\23\16\23\u0116\13\23\3\24\3\24\3\24\7\24\u011b\n"+
		"\24\f\24\16\24\u011e\13\24\3\25\3\25\3\25\7\25\u0123\n\25\f\25\16\25\u0126"+
		"\13\25\3\26\3\26\5\26\u012a\n\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0132"+
		"\n\26\3\26\5\26\u0135\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u014c"+
		"\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\36\3\36\5\36\u015e\n\36\3\36\5\36\u0161\n\36\3\36\5\36\u0164\n"+
		"\36\3\37\3\37\3\37\3\37\7\37\u016a\n\37\f\37\16\37\u016d\13\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\5\37\u0175\n\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\5!"+
		"\u0180\n!\3!\5!\u0183\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u018d\n\""+
		"\3\"\3\"\5\"\u0191\n\"\3\"\5\"\u0194\n\"\3\"\3\"\5\"\u0198\n\"\3\"\5\""+
		"\u019b\n\"\5\"\u019d\n\"\3#\3#\3#\5#\u01a2\n#\3#\3#\3$\3$\3%\3%\5%\u01aa"+
		"\n%\3%\3%\3%\5%\u01af\n%\7%\u01b1\n%\f%\16%\u01b4\13%\3&\3&\3&\5&\u01b9"+
		"\n&\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\5+\u01c7\n+\3,\3,\3-\3-\3.\3"+
		".\3.\5.\u01d0\n.\3.\5.\u01d3\n.\3.\3.\5.\u01d7\n.\3.\3.\5.\u01db\n.\3"+
		"/\3/\3/\5/\u01e0\n/\3/\3/\3\60\3\60\3\61\3\61\5\61\u01e8\n\61\3\61\3\61"+
		"\3\61\3\61\3\61\5\61\u01ef\n\61\5\61\u01f1\n\61\3\62\3\62\5\62\u01f5\n"+
		"\62\3\63\3\63\3\63\7\63\u01fa\n\63\f\63\16\63\u01fd\13\63\3\64\3\64\3"+
		"\65\3\65\3\65\3\65\3\66\3\66\3\66\5\66\u0208\n\66\3\66\3\66\3\67\3\67"+
		"\5\67\u020e\n\67\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\78\u0229\n8\f8\168\u022c\138\38\38\38\38\38\58"+
		"\u0233\n8\39\39\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\5:\u0244\n:\3:"+
		"\3:\3:\3:\3:\5:\u024b\n:\3:\3:\3:\3:\5:\u0251\n:\3:\3:\3:\3:\3:\3:\3:"+
		"\5:\u025a\n:\3:\3:\3:\3:\3:\5:\u0261\n:\3:\3:\3:\3:\3:\3:\7:\u0269\n:"+
		"\f:\16:\u026c\13:\3;\3;\3<\3<\3=\3=\3=\3=\5=\u0276\n=\3>\3>\3?\3?\3@\3"+
		"@\5@\u027e\n@\3A\5A\u0281\nA\3A\3A\3B\5B\u0286\nB\3B\3B\3B\5B\u028b\n"+
		"B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u029f\nG\3"+
		"G\3G\3G\3G\3G\5G\u02a6\nG\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3"+
		"G\3G\3G\3G\3G\5G\u02bb\nG\3H\3H\3H\2\3rI\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080"+
		"\u0082\u0084\u0086\u0088\u008a\u008c\u008e\2\7\3\2XY\3\2Z[\3\2U[\4\2\23"+
		"\23\63\63\3\2\64<\u02db\2\u0093\3\2\2\2\4\u0098\3\2\2\2\6\u009e\3\2\2"+
		"\2\b\u00a4\3\2\2\2\n\u00a9\3\2\2\2\f\u00ab\3\2\2\2\16\u00b8\3\2\2\2\20"+
		"\u00be\3\2\2\2\22\u00c6\3\2\2\2\24\u00ca\3\2\2\2\26\u00d2\3\2\2\2\30\u00d6"+
		"\3\2\2\2\32\u00df\3\2\2\2\34\u00e2\3\2\2\2\36\u00e9\3\2\2\2 \u00ed\3\2"+
		"\2\2\"\u0107\3\2\2\2$\u010f\3\2\2\2&\u0117\3\2\2\2(\u011f\3\2\2\2*\u0127"+
		"\3\2\2\2,\u014b\3\2\2\2.\u014d\3\2\2\2\60\u014f\3\2\2\2\62\u0151\3\2\2"+
		"\2\64\u0153\3\2\2\2\66\u0155\3\2\2\28\u0158\3\2\2\2:\u015b\3\2\2\2<\u0165"+
		"\3\2\2\2>\u0176\3\2\2\2@\u0182\3\2\2\2B\u019c\3\2\2\2D\u019e\3\2\2\2F"+
		"\u01a5\3\2\2\2H\u01a9\3\2\2\2J\u01b8\3\2\2\2L\u01bc\3\2\2\2N\u01be\3\2"+
		"\2\2P\u01c0\3\2\2\2R\u01c2\3\2\2\2T\u01c6\3\2\2\2V\u01c8\3\2\2\2X\u01ca"+
		"\3\2\2\2Z\u01cc\3\2\2\2\\\u01df\3\2\2\2^\u01e3\3\2\2\2`\u01e7\3\2\2\2"+
		"b\u01f4\3\2\2\2d\u01f6\3\2\2\2f\u01fe\3\2\2\2h\u0200\3\2\2\2j\u0207\3"+
		"\2\2\2l\u020d\3\2\2\2n\u0232\3\2\2\2p\u0234\3\2\2\2r\u0260\3\2\2\2t\u026d"+
		"\3\2\2\2v\u026f\3\2\2\2x\u0275\3\2\2\2z\u0277\3\2\2\2|\u0279\3\2\2\2~"+
		"\u027d\3\2\2\2\u0080\u0280\3\2\2\2\u0082\u0285\3\2\2\2\u0084\u028c\3\2"+
		"\2\2\u0086\u028e\3\2\2\2\u0088\u0290\3\2\2\2\u008a\u0292\3\2\2\2\u008c"+
		"\u02ba\3\2\2\2\u008e\u02bc\3\2\2\2\u0090\u0092\5\4\3\2\u0091\u0090\3\2"+
		"\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7\2\2\3\u0097\3\3\2\2\2"+
		"\u0098\u009a\5\6\4\2\u0099\u009b\7E\2\2\u009a\u0099\3\2\2\2\u009a\u009b"+
		"\3\2\2\2\u009b\5\3\2\2\2\u009c\u009f\5\b\5\2\u009d\u009f\5\n\6\2\u009e"+
		"\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\7\3\2\2\2\u00a0\u00a5\5\f\7\2"+
		"\u00a1\u00a5\5\26\f\2\u00a2\u00a5\5\30\r\2\u00a3\u00a5\5\24\13\2\u00a4"+
		"\u00a0\3\2\2\2\u00a4\u00a1\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2"+
		"\2\2\u00a5\t\3\2\2\2\u00a6\u00aa\5:\36\2\u00a7\u00aa\5 \21\2\u00a8\u00aa"+
		"\5*\26\2\u00a9\u00a6\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa"+
		"\13\3\2\2\2\u00ab\u00ac\7\t\2\2\u00ac\u00ad\7-\2\2\u00ad\u00af\7.\2\2"+
		"\u00ae\u00b0\5\34\17\2\u00af\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1"+
		"\3\2\2\2\u00b1\u00b2\5\36\20\2\u00b2\u00b3\7I\2\2\u00b3\u00b4\5&\24\2"+
		"\u00b4\u00b6\7J\2\2\u00b5\u00b7\5\16\b\2\u00b6\u00b5\3\2\2\2\u00b6\u00b7"+
		"\3\2\2\2\u00b7\r\3\2\2\2\u00b8\u00b9\7C\2\2\u00b9\u00ba\7D\2\2\u00ba\u00bb"+
		"\7I\2\2\u00bb\u00bc\5\20\t\2\u00bc\u00bd\7J\2\2\u00bd\17\3\2\2\2\u00be"+
		"\u00c3\5\22\n\2\u00bf\u00c0\7G\2\2\u00c0\u00c2\5\22\n\2\u00c1\u00bf\3"+
		"\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\21\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\5T+\2\u00c7\u00c8\7U\2\2\u00c8"+
		"\u00c9\5x=\2\u00c9\23\3\2\2\2\u00ca\u00cb\7\21\2\2\u00cb\u00cc\7-\2\2"+
		"\u00cc\u00ce\7.\2\2\u00cd\u00cf\5\32\16\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\5\36\20\2\u00d1\25\3\2\2\2\u00d2"+
		"\u00d3\7+\2\2\u00d3\u00d4\7-\2\2\u00d4\u00d5\7/\2\2\u00d5\27\3\2\2\2\u00d6"+
		"\u00d7\7+\2\2\u00d7\u00d8\7\t\2\2\u00d8\u00d9\7-\2\2\u00d9\u00db\7.\2"+
		"\2\u00da\u00dc\5\32\16\2\u00db\u00da\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\u00de\5\36\20\2\u00de\31\3\2\2\2\u00df\u00e0\7\27"+
		"\2\2\u00e0\u00e1\7\22\2\2\u00e1\33\3\2\2\2\u00e2\u00e3\7\27\2\2\u00e3"+
		"\u00e4\7&\2\2\u00e4\u00e5\7\22\2\2\u00e5\35\3\2\2\2\u00e6\u00e7\5V,\2"+
		"\u00e7\u00e8\7F\2\2\u00e8\u00ea\3\2\2\2\u00e9\u00e6\3\2\2\2\u00e9\u00ea"+
		"\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\5X-\2\u00ec\37\3\2\2\2\u00ed"+
		"\u00ee\7>\2\2\u00ee\u00ef\7\32\2\2\u00ef\u00f4\5\36\20\2\u00f0\u00f1\7"+
		"I\2\2\u00f1\u00f2\5$\23\2\u00f2\u00f3\7J\2\2\u00f3\u00f5\3\2\2\2\u00f4"+
		"\u00f0\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\7?"+
		"\2\2\u00f7\u00f9\7I\2\2\u00f8\u00fa\5\"\22\2\u00f9\u00f8\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u0104\7J\2\2\u00fc\u00fd\7G\2"+
		"\2\u00fd\u00ff\7I\2\2\u00fe\u0100\5\"\22\2\u00ff\u00fe\3\2\2\2\u00ff\u0100"+
		"\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0103\7J\2\2\u0102\u00fc\3\2\2\2\u0103"+
		"\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105!\3\2\2\2"+
		"\u0106\u0104\3\2\2\2\u0107\u010c\5x=\2\u0108\u0109\7G\2\2\u0109\u010b"+
		"\5x=\2\u010a\u0108\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d#\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0114\5\\/\2\u0110"+
		"\u0111\7G\2\2\u0111\u0113\5\\/\2\u0112\u0110\3\2\2\2\u0113\u0116\3\2\2"+
		"\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115%\3\2\2\2\u0116\u0114"+
		"\3\2\2\2\u0117\u011c\5Z.\2\u0118\u0119\7G\2\2\u0119\u011b\5Z.\2\u011a"+
		"\u0118\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2"+
		"\2\2\u011d\'\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0124\5@!\2\u0120\u0121"+
		"\7G\2\2\u0121\u0123\5@!\2\u0122\u0120\3\2\2\2\u0123\u0126\3\2\2\2\u0124"+
		"\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125)\3\2\2\2\u0126\u0124\3\2\2\2"+
		"\u0127\u0129\7\r\2\2\u0128\u012a\5(\25\2\u0129\u0128\3\2\2\2\u0129\u012a"+
		"\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\7\25\2\2\u012c\u012d\5\36\20"+
		"\2\u012d\u012e\7B\2\2\u012e\u0131\5n8\2\u012f\u0130\7\3\2\2\u0130\u0132"+
		"\5p9\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0134\3\2\2\2\u0133"+
		"\u0135\5,\27\2\u0134\u0133\3\2\2\2\u0134\u0135\3\2\2\2\u0135+\3\2\2\2"+
		"\u0136\u0137\7I\2\2\u0137\u0138\7\"\2\2\u0138\u0139\5\62\32\2\u0139\u013a"+
		"\5.\30\2\u013a\u013b\7G\2\2\u013b\u013c\7#\2\2\u013c\u013d\5\64\33\2\u013d"+
		"\u013e\5.\30\2\u013e\u013f\7J\2\2\u013f\u014c\3\2\2\2\u0140\u0141\7\""+
		"\2\2\u0141\u0142\5\62\32\2\u0142\u0143\5.\30\2\u0143\u014c\3\2\2\2\u0144"+
		"\u0145\7#\2\2\u0145\u0146\5\64\33\2\u0146\u0147\5.\30\2\u0147\u014c\3"+
		"\2\2\2\u0148\u0149\7$\2\2\u0149\u014a\7U\2\2\u014a\u014c\5.\30\2\u014b"+
		"\u0136\3\2\2\2\u014b\u0140\3\2\2\2\u014b\u0144\3\2\2\2\u014b\u0148\3\2"+
		"\2\2\u014c-\3\2\2\2\u014d\u014e\5\60\31\2\u014e/\3\2\2\2\u014f\u0150\5"+
		"\u0080A\2\u0150\61\3\2\2\2\u0151\u0152\t\2\2\2\u0152\63\3\2\2\2\u0153"+
		"\u0154\t\3\2\2\u0154\65\3\2\2\2\u0155\u0156\7!\2\2\u0156\u0157\5> \2\u0157"+
		"\67\3\2\2\2\u0158\u0159\7%\2\2\u0159\u015a\5> \2\u015a9\3\2\2\2\u015b"+
		"\u015d\5<\37\2\u015c\u015e\5\66\34\2\u015d\u015c\3\2\2\2\u015d\u015e\3"+
		"\2\2\2\u015e\u0160\3\2\2\2\u015f\u0161\5,\27\2\u0160\u015f\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0164\58\35\2\u0163\u0162\3\2"+
		"\2\2\u0163\u0164\3\2\2\2\u0164;\3\2\2\2\u0165\u0166\7*\2\2\u0166\u016b"+
		"\5B\"\2\u0167\u0168\7G\2\2\u0168\u016a\5B\"\2\u0169\u0167\3\2\2\2\u016a"+
		"\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016e\3\2"+
		"\2\2\u016d\u016b\3\2\2\2\u016e\u016f\7\25\2\2\u016f\u0170\5\36\20\2\u0170"+
		"\u0171\7B\2\2\u0171\u0174\5n8\2\u0172\u0173\7\3\2\2\u0173\u0175\5p9\2"+
		"\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175=\3\2\2\2\u0176\u0177\7"+
		"f\2\2\u0177?\3\2\2\2\u0178\u0179\5L\'\2\u0179\u017a\7H\2\2\u017a\u017b"+
		"\7M\2\2\u017b\u0183\3\2\2\2\u017c\u017d\5L\'\2\u017d\u017e\7F\2\2\u017e"+
		"\u0180\3\2\2\2\u017f\u017c\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2"+
		"\2\2\u0181\u0183\5^\60\2\u0182\u0178\3\2\2\2\u0182\u017f\3\2\2\2\u0183"+
		"A\3\2\2\2\u0184\u019d\7M\2\2\u0185\u0186\5L\'\2\u0186\u0187\7H\2\2\u0187"+
		"\u0188\7M\2\2\u0188\u019d\3\2\2\2\u0189\u018a\5L\'\2\u018a\u018b\7F\2"+
		"\2\u018b\u018d\3\2\2\2\u018c\u0189\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e"+
		"\3\2\2\2\u018e\u0193\5^\60\2\u018f\u0191\7\5\2\2\u0190\u018f\3\2\2\2\u0190"+
		"\u0191\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0194\5P)\2\u0193\u0190\3\2\2"+
		"\2\u0193\u0194\3\2\2\2\u0194\u019d\3\2\2\2\u0195\u019a\5D#\2\u0196\u0198"+
		"\7\5\2\2\u0197\u0196\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199\3\2\2\2\u0199"+
		"\u019b\5P)\2\u019a\u0197\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019d\3\2\2"+
		"\2\u019c\u0184\3\2\2\2\u019c\u0185\3\2\2\2\u019c\u018c\3\2\2\2\u019c\u0195"+
		"\3\2\2\2\u019dC\3\2\2\2\u019e\u019f\5F$\2\u019f\u01a1\7I\2\2\u01a0\u01a2"+
		"\5H%\2\u01a1\u01a0\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3"+
		"\u01a4\7J\2\2\u01a4E\3\2\2\2\u01a5\u01a6\5T+\2\u01a6G\3\2\2\2\u01a7\u01aa"+
		"\5J&\2\u01a8\u01aa\5x=\2\u01a9\u01a7\3\2\2\2\u01a9\u01a8\3\2\2\2\u01aa"+
		"\u01b2\3\2\2\2\u01ab\u01ae\7G\2\2\u01ac\u01af\5J&\2\u01ad\u01af\5x=\2"+
		"\u01ae\u01ac\3\2\2\2\u01ae\u01ad\3\2\2\2\u01af\u01b1\3\2\2\2\u01b0\u01ab"+
		"\3\2\2\2\u01b1\u01b4\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3"+
		"I\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b5\u01b6\5L\'\2\u01b6\u01b7\7F\2\2\u01b7"+
		"\u01b9\3\2\2\2\u01b8\u01b5\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\3\2"+
		"\2\2\u01ba\u01bb\5^\60\2\u01bbK\3\2\2\2\u01bc\u01bd\5T+\2\u01bdM\3\2\2"+
		"\2\u01be\u01bf\7b\2\2\u01bfO\3\2\2\2\u01c0\u01c1\5R*\2\u01c1Q\3\2\2\2"+
		"\u01c2\u01c3\5T+\2\u01c3S\3\2\2\2\u01c4\u01c7\7d\2\2\u01c5\u01c7\5N(\2"+
		"\u01c6\u01c4\3\2\2\2\u01c6\u01c5\3\2\2\2\u01c7U\3\2\2\2\u01c8\u01c9\5"+
		"T+\2\u01c9W\3\2\2\2\u01ca\u01cb\5T+\2\u01cbY\3\2\2\2\u01cc\u01cd\5\\/"+
		"\2\u01cd\u01d2\5`\61\2\u01ce\u01d0\7&\2\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0"+
		"\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d3\7\'\2\2\u01d2\u01cf\3\2\2\2\u01d2"+
		"\u01d3\3\2\2\2\u01d3\u01d6\3\2\2\2\u01d4\u01d5\7\f\2\2\u01d5\u01d7\5x"+
		"=\2\u01d6\u01d4\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01da\3\2\2\2\u01d8"+
		"\u01d9\7)\2\2\u01d9\u01db\7\34\2\2\u01da\u01d8\3\2\2\2\u01da\u01db\3\2"+
		"\2\2\u01db[\3\2\2\2\u01dc\u01dd\5L\'\2\u01dd\u01de\7F\2\2\u01de\u01e0"+
		"\3\2\2\2\u01df\u01dc\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1"+
		"\u01e2\5^\60\2\u01e2]\3\2\2\2\u01e3\u01e4\5T+\2\u01e4_\3\2\2\2\u01e5\u01e8"+
		"\5\u008cG\2\u01e6\u01e8\5\u008eH\2\u01e7\u01e5\3\2\2\2\u01e7\u01e6\3\2"+
		"\2\2\u01e8\u01f0\3\2\2\2\u01e9\u01ee\7\4\2\2\u01ea\u01eb\7S\2\2\u01eb"+
		"\u01ec\5\u0086D\2\u01ec\u01ed\7T\2\2\u01ed\u01ef\3\2\2\2\u01ee\u01ea\3"+
		"\2\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f1\3\2\2\2\u01f0\u01e9\3\2\2\2\u01f0"+
		"\u01f1\3\2\2\2\u01f1a\3\2\2\2\u01f2\u01f5\5f\64\2\u01f3\u01f5\5h\65\2"+
		"\u01f4\u01f2\3\2\2\2\u01f4\u01f3\3\2\2\2\u01f5c\3\2\2\2\u01f6\u01fb\5"+
		"b\62\2\u01f7\u01f8\7G\2\2\u01f8\u01fa\5b\62\2\u01f9\u01f7\3\2\2\2\u01fa"+
		"\u01fd\3\2\2\2\u01fb\u01f9\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fce\3\2\2\2"+
		"\u01fd\u01fb\3\2\2\2\u01fe\u01ff\5x=\2\u01ffg\3\2\2\2\u0200\u0201\7K\2"+
		"\2\u0201\u0202\5v<\2\u0202\u0203\7L\2\2\u0203i\3\2\2\2\u0204\u0205\5L"+
		"\'\2\u0205\u0206\7F\2\2\u0206\u0208\3\2\2\2\u0207\u0204\3\2\2\2\u0207"+
		"\u0208\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020a\5^\60\2\u020ak\3\2\2\2"+
		"\u020b\u020e\5z>\2\u020c\u020e\5~@\2\u020d\u020b\3\2\2\2\u020d\u020c\3"+
		"\2\2\2\u020em\3\2\2\2\u020f\u0210\7\35\2\2\u0210\u0211\5\62\32\2\u0211"+
		"\u0212\5l\67\2\u0212\u0213\7\3\2\2\u0213\u0214\7\36\2\2\u0214\u0215\5"+
		"\64\33\2\u0215\u0216\5l\67\2\u0216\u0233\3\2\2\2\u0217\u0218\7\35\2\2"+
		"\u0218\u0219\5\62\32\2\u0219\u021a\5l\67\2\u021a\u0233\3\2\2\2\u021b\u021c"+
		"\7\36\2\2\u021c\u021d\5\64\33\2\u021d\u021e\5l\67\2\u021e\u0233\3\2\2"+
		"\2\u021f\u0220\7\37\2\2\u0220\u0221\7U\2\2\u0221\u0233\5l\67\2\u0222\u0223"+
		"\7\37\2\2\u0223\u0224\7\26\2\2\u0224\u0225\7I\2\2\u0225\u022a\5l\67\2"+
		"\u0226\u0227\7G\2\2\u0227\u0229\5l\67\2\u0228\u0226\3\2\2\2\u0229\u022c"+
		"\3\2\2\2\u022a\u0228\3\2\2\2\u022a\u022b\3\2\2\2\u022b\u022d\3\2\2\2\u022c"+
		"\u022a\3\2\2\2\u022d\u022e\7J\2\2\u022e\u0233\3\2\2\2\u022f\u0230\7\37"+
		"\2\2\u0230\u0231\7 \2\2\u0231\u0233\5l\67\2\u0232\u020f\3\2\2\2\u0232"+
		"\u0217\3\2\2\2\u0232\u021b\3\2\2\2\u0232\u021f\3\2\2\2\u0232\u0222\3\2"+
		"\2\2\u0232\u022f\3\2\2\2\u0233o\3\2\2\2\u0234\u0235\5r:\2\u0235q\3\2\2"+
		"\2\u0236\u0237\b:\1\2\u0237\u0238\7I\2\2\u0238\u0239\5r:\2\u0239\u023a"+
		"\7J\2\2\u023a\u0261\3\2\2\2\u023b\u023c\5j\66\2\u023c\u023d\5t;\2\u023d"+
		"\u023e\5b\62\2\u023e\u0261\3\2\2\2\u023f\u0243\5j\66\2\u0240\u0244\7 "+
		"\2\2\u0241\u0242\7&\2\2\u0242\u0244\7 \2\2\u0243\u0240\3\2\2\2\u0243\u0241"+
		"\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0246\5b\62\2\u0246\u0261\3\2\2\2\u0247"+
		"\u0248\5j\66\2\u0248\u024a\7\33\2\2\u0249\u024b\7&\2\2\u024a\u0249\3\2"+
		"\2\2\u024a\u024b\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u024d\7\'\2\2\u024d"+
		"\u0261\3\2\2\2\u024e\u0250\5j\66\2\u024f\u0251\7&\2\2\u0250\u024f\3\2"+
		"\2\2\u0250\u0251\3\2\2\2\u0251\u0252\3\2\2\2\u0252\u0253\7\26\2\2\u0253"+
		"\u0254\7I\2\2\u0254\u0255\5d\63\2\u0255\u0256\7J\2\2\u0256\u0261\3\2\2"+
		"\2\u0257\u0259\5j\66\2\u0258\u025a\7&\2\2\u0259\u0258\3\2\2\2\u0259\u025a"+
		"\3\2\2\2\u025a\u025b\3\2\2\2\u025b\u025c\7\6\2\2\u025c\u025d\5b\62\2\u025d"+
		"\u025e\7\3\2\2\u025e\u025f\5b\62\2\u025f\u0261\3\2\2\2\u0260\u0236\3\2"+
		"\2\2\u0260\u023b\3\2\2\2\u0260\u023f\3\2\2\2\u0260\u0247\3\2\2\2\u0260"+
		"\u024e\3\2\2\2\u0260\u0257\3\2\2\2\u0261\u026a\3\2\2\2\u0262\u0263\f\t"+
		"\2\2\u0263\u0264\7\3\2\2\u0264\u0269\5r:\n\u0265\u0266\f\b\2\2\u0266\u0267"+
		"\7(\2\2\u0267\u0269\5r:\t\u0268\u0262\3\2\2\2\u0268\u0265\3\2\2\2\u0269"+
		"\u026c\3\2\2\2\u026a\u0268\3\2\2\2\u026a\u026b\3\2\2\2\u026bs\3\2\2\2"+
		"\u026c\u026a\3\2\2\2\u026d\u026e\t\4\2\2\u026eu\3\2\2\2\u026f\u0270\5"+
		"|?\2\u0270w\3\2\2\2\u0271\u0276\5z>\2\u0272\u0276\5~@\2\u0273\u0276\5"+
		"\u0084C\2\u0274\u0276\7\'\2\2\u0275\u0271\3\2\2\2\u0275\u0272\3\2\2\2"+
		"\u0275\u0273\3\2\2\2\u0275\u0274\3\2\2\2\u0276y\3\2\2\2\u0277\u0278\7"+
		"e\2\2\u0278{\3\2\2\2\u0279\u027a\7d\2\2\u027a}\3\2\2\2\u027b\u027e\5\u0080"+
		"A\2\u027c\u027e\5\u0082B\2\u027d\u027b\3\2\2\2\u027d\u027c\3\2\2\2\u027e"+
		"\177\3\2\2\2\u027f\u0281\7Q\2\2\u0280\u027f\3\2\2\2\u0280\u0281\3\2\2"+
		"\2\u0281\u0282\3\2\2\2\u0282\u0283\7f\2\2\u0283\u0081\3\2\2\2\u0284\u0286"+
		"\7Q\2\2\u0285\u0284\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u0287\3\2\2\2\u0287"+
		"\u028a\5> \2\u0288\u0289\7H\2\2\u0289\u028b\5> \2\u028a\u0288\3\2\2\2"+
		"\u028a\u028b\3\2\2\2\u028b\u0083\3\2\2\2\u028c\u028d\t\5\2\2\u028d\u0085"+
		"\3\2\2\2\u028e\u028f\5\u0080A\2\u028f\u0087\3\2\2\2\u0290\u0291\5\u0080"+
		"A\2\u0291\u0089\3\2\2\2\u0292\u0293\5\u0080A\2\u0293\u008b\3\2\2\2\u0294"+
		"\u0295\7\b\2\2\u0295\u0296\7I\2\2\u0296\u0297\5\u0088E\2\u0297\u0298\7"+
		"J\2\2\u0298\u02bb\3\2\2\2\u0299\u029e\7A\2\2\u029a\u029b\7I\2\2\u029b"+
		"\u029c\5\u0088E\2\u029c\u029d\7J\2\2\u029d\u029f\3\2\2\2\u029e\u029a\3"+
		"\2\2\2\u029e\u029f\3\2\2\2\u029f\u02bb\3\2\2\2\u02a0\u02a5\7\13\2\2\u02a1"+
		"\u02a2\5\u0088E\2\u02a2\u02a3\7G\2\2\u02a3\u02a4\5\u008aF\2\u02a4\u02a6"+
		"\3\2\2\2\u02a5\u02a1\3\2\2\2\u02a5\u02a6\3\2\2\2\u02a6\u02bb\3\2\2\2\u02a7"+
		"\u02bb\7\62\2\2\u02a8\u02bb\7,\2\2\u02a9\u02bb\7\30\2\2\u02aa\u02bb\7"+
		"\31\2\2\u02ab\u02bb\7\24\2\2\u02ac\u02bb\7\20\2\2\u02ad\u02bb\7\61\2\2"+
		"\u02ae\u02bb\7\n\2\2\u02af\u02bb\7\60\2\2\u02b0\u02b1\7\7\2\2\u02b1\u02b2"+
		"\7I\2\2\u02b2\u02b3\5\u0088E\2\u02b3\u02b4\7J\2\2\u02b4\u02bb\3\2\2\2"+
		"\u02b5\u02b6\7@\2\2\u02b6\u02b7\7I\2\2\u02b7\u02b8\5\u0088E\2\u02b8\u02b9"+
		"\7J\2\2\u02b9\u02bb\3\2\2\2\u02ba\u0294\3\2\2\2\u02ba\u0299\3\2\2\2\u02ba"+
		"\u02a0\3\2\2\2\u02ba\u02a7\3\2\2\2\u02ba\u02a8\3\2\2\2\u02ba\u02a9\3\2"+
		"\2\2\u02ba\u02aa\3\2\2\2\u02ba\u02ab\3\2\2\2\u02ba\u02ac\3\2\2\2\u02ba"+
		"\u02ad\3\2\2\2\u02ba\u02ae\3\2\2\2\u02ba\u02af\3\2\2\2\u02ba\u02b0\3\2"+
		"\2\2\u02ba\u02b5\3\2\2\2\u02bb\u008d\3\2\2\2\u02bc\u02bd\t\6\2\2\u02bd"+
		"\u008f\3\2\2\2I\u0093\u009a\u009e\u00a4\u00a9\u00af\u00b6\u00c3\u00ce"+
		"\u00db\u00e9\u00f4\u00f9\u00ff\u0104\u010c\u0114\u011c\u0124\u0129\u0131"+
		"\u0134\u014b\u015d\u0160\u0163\u016b\u0174\u017f\u0182\u018c\u0190\u0193"+
		"\u0197\u019a\u019c\u01a1\u01a9\u01ae\u01b2\u01b8\u01c6\u01cf\u01d2\u01d6"+
		"\u01da\u01df\u01e7\u01ee\u01f0\u01f4\u01fb\u0207\u020d\u022a\u0232\u0243"+
		"\u024a\u0250\u0259\u0260\u0268\u026a\u0275\u027d\u0280\u0285\u028a\u029e"+
		"\u02a5\u02ba";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}