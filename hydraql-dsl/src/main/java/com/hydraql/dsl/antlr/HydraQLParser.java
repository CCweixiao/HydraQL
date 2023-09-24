// Generated from ./HydraQLParser.g4 by ANTLR 4.13.1

package com.hydraql.dsl.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class HydraQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

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
		RULE_delete_column_def = 30, RULE_select_column_def = 31, RULE_functionCall = 32, 
		RULE_funcName = 33, RULE_functionArgs = 34, RULE_fullColumnName = 35, 
		RULE_family_name = 36, RULE_quoted_name = 37, RULE_column_alias = 38, 
		RULE_alias = 39, RULE_name = 40, RULE_namespace_name = 41, RULE_table_name = 42, 
		RULE_column_def = 43, RULE_column_ref = 44, RULE_column_name = 45, RULE_data_type = 46, 
		RULE_conditionVal = 47, RULE_conditionValList = 48, RULE_constant = 49, 
		RULE_variable = 50, RULE_var = 51, RULE_column = 52, RULE_rowKey = 53, 
		RULE_whereRow = 54, RULE_whereCol = 55, RULE_colCondition = 56, RULE_comp_op = 57, 
		RULE_literal = 58, RULE_string = 59, RULE_varString = 60, RULE_numeric = 61, 
		RULE_integer = 62, RULE_decimal = 63, RULE_true_false = 64, RULE_dimension_int = 65, 
		RULE_precision_int = 66, RULE_scale_int = 67, RULE_sql_data_type = 68, 
		RULE_hbase_data_type = 69;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "batch", "sql_command", "ddl_command", "dml_command", "create_virtual_table_command", 
			"table_options", "options_", "option", "drop_table_command", "show_tables_command", 
			"show_table_command", "if_exists", "if_not_exists", "table_ref", "upsert_values_command", 
			"insert_values", "upsert_column_def_list", "column_def_list", "delete_column_def_list", 
			"delete_command", "timestamp_range_clause", "tsExp", "timestamp", "gtOper", 
			"leOper", "versions_clause", "limit_clause", "select_command", "select_statement", 
			"delete_column_def", "select_column_def", "functionCall", "funcName", 
			"functionArgs", "fullColumnName", "family_name", "quoted_name", "column_alias", 
			"alias", "name", "namespace_name", "table_name", "column_def", "column_ref", 
			"column_name", "data_type", "conditionVal", "conditionValList", "constant", 
			"variable", "var", "column", "rowKey", "whereRow", "whereCol", "colCondition", 
			"comp_op", "literal", "string", "varString", "numeric", "integer", "decimal", 
			"true_false", "dimension_int", "precision_int", "scale_int", "sql_data_type", 
			"hbase_data_type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'AND'", "'ARRAY'", "'AS'", "'BETWEEN'", "'BINARY'", "'CHAR'", 
			"'CREATE'", "'DATE'", "'DECIMAL'", "'DEFAULT'", "'DELETE'", "'DISABLE'", 
			"'DISTINCT'", "'DOUBLE'", "'DROP'", "'EXISTS'", "'FALSE'", "'FLOAT'", 
			"'FROM'", "'IN'", "'IF'", "'INTEGER'", "'BIGINT'", "'INTO'", "'IS'", 
			"'KEY'", "'STARTKEY'", "'ENDKEY'", "'ROWKEY'", "'LIKE'", "'VERSIONS'", 
			"'STARTTS'", "'ENDTS'", "'TS'", "'LIMIT'", "'NOT'", "'NULL'", "'OR'", 
			"'PRIMARY'", "'SELECT'", "'SHOW'", "'SMALLINT'", "'VIRTUAL'", "'TABLE'", 
			"'TABLES'", "'TIME'", "'TIMESTAMP'", "'TINYINT'", "'TRUE'", "'UNSIGNED_DATE'", 
			"'UNSIGNED_DOUBLE'", "'UNSIGNED_FLOAT'", "'UNSIGNED_INT'", "'UNSIGNED_LONG'", 
			"'UNSIGNED_SMALLINT'", "'UNSIGNED_TIME'", "'UNSIGNED_TIMESTAMP'", "'UNSIGNED_TINYINT'", 
			"'UPDATE'", "'UPSERT'", "'VALUES'", "'VARBINARY'", "'VARCHAR'", "'WHERE'", 
			"'WITH'", "'PROPERTIES'", "';'", "':'", "','", "'.'", "'('", "')'", "'${'", 
			"'}'", "'*'", "'/'", "'%'", "'+'", "'-'", "'||'", "'['", "']'", "'='", 
			"'<>'", "'!='", "'>'", "'>='", "'<'", "'<='", "'?'", null, null, null, 
			"'/*+'", "'*/'", null, "'''"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "ARRAY", "AS", "BETWEEN", "BINARY", "CHAR", "CREATE", "DATE", 
			"DECIMAL", "DEFAULT", "DELETE", "DISABLE", "DISTINCT", "DOUBLE", "DROP", 
			"EXISTS", "FALSE", "FLOAT", "FROM", "IN", "IF", "INTEGER", "BIGINT", 
			"INTO", "IS", "KEY", "STARTKEY", "ENDKEY", "ROWKEY", "LIKE", "VERSIONS", 
			"STARTTS", "ENDTS", "TS", "LIMIT", "NOT", "NULL_", "OR", "PRIMARY", "SELECT", 
			"SHOW", "SMALLINT", "VIRTUAL", "TABLE", "TABLES", "TIME", "TIMESTAMP", 
			"TINYINT", "TRUE", "UNSIGNED_DATE", "UNSIGNED_DOUBLE", "UNSIGNED_FLOAT", 
			"UNSIGNED_INT", "UNSIGNED_LONG", "UNSIGNED_SMALLINT", "UNSIGNED_TIME", 
			"UNSIGNED_TIMESTAMP", "UNSIGNED_TINYINT", "UPDATE", "UPSERT", "VALUES", 
			"VARBINARY", "VARCHAR", "WHERE", "WITH", "PROPERTIES", "SEMI", "COLON", 
			"COMMA", "DOT", "LP", "RP", "VAR_LP", "VAR_RP", "STAR", "DIV", "MOD", 
			"PLUS", "MINUS", "PIPEPIPE", "LSB", "RSC", "EQ", "NE", "NE2", "GT", "GE", 
			"LT", "LE", "QM", "WHITE_SPACE", "SQL_COMMENT", "LINE_COMMENT", "HINT_START", 
			"HINT_END", "DOUBLE_QUOTE_ID", "SINGLE_QUOTE", "ID", "STRING_LITERAL", 
			"DECIMAL_LITERAL", "FLOAT_LITERAL", "REAL_LITERAL", "CHAR_LITERAL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1152924803141765248L) != 0)) {
				{
				{
				setState(140);
				batch();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(148);
			sql_command();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(149);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case SHOW:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				ddl_command();
				}
				break;
			case DELETE:
			case SELECT:
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				create_virtual_table_command();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				show_tables_command();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
				show_table_command();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(159);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				select_command();
				}
				break;
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				upsert_values_command();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Create_virtual_table_commandContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(HydraQLParser.CREATE, 0); }
		public TerminalNode VIRTUAL() { return getToken(HydraQLParser.VIRTUAL, 0); }
		public TerminalNode TABLE() { return getToken(HydraQLParser.TABLE, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public Column_def_listContext column_def_list() {
			return getRuleContext(Column_def_listContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
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
			setState(167);
			match(CREATE);
			setState(168);
			match(VIRTUAL);
			setState(169);
			match(TABLE);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(170);
				if_not_exists();
				}
			}

			setState(173);
			table_ref();
			setState(174);
			match(LP);
			setState(175);
			column_def_list();
			setState(176);
			match(RP);
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(177);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(180);
			match(WITH);
			setState(181);
			match(PROPERTIES);
			setState(182);
			match(LP);
			setState(183);
			options_();
			setState(184);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Options_Context extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(186);
			option();
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(187);
				match(COMMA);
				setState(188);
				option();
				}
				}
				setState(193);
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

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode EQ() { return getToken(HydraQLParser.EQ, 0); }
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
			setState(194);
			name();
			setState(195);
			match(EQ);
			setState(196);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(198);
			match(DROP);
			setState(199);
			match(VIRTUAL);
			setState(200);
			match(TABLE);
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(201);
				if_exists();
				}
			}

			setState(204);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(206);
			match(SHOW);
			setState(207);
			match(VIRTUAL);
			setState(208);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(210);
			match(SHOW);
			setState(211);
			match(CREATE);
			setState(212);
			match(VIRTUAL);
			setState(213);
			match(TABLE);
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(214);
				if_exists();
				}
			}

			setState(217);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(219);
			match(IF);
			setState(220);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(222);
			match(IF);
			setState(223);
			match(NOT);
			setState(224);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Table_refContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Namespace_nameContext namespace_name() {
			return getRuleContext(Namespace_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
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
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(226);
				namespace_name();
				setState(227);
				match(COLON);
				}
				break;
			}
			setState(231);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Upsert_values_commandContext extends ParserRuleContext {
		public TerminalNode UPSERT() { return getToken(HydraQLParser.UPSERT, 0); }
		public TerminalNode INTO() { return getToken(HydraQLParser.INTO, 0); }
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public TerminalNode VALUES() { return getToken(HydraQLParser.VALUES, 0); }
		public List<TerminalNode> LP() { return getTokens(HydraQLParser.LP); }
		public TerminalNode LP(int i) {
			return getToken(HydraQLParser.LP, i);
		}
		public List<Insert_valuesContext> insert_values() {
			return getRuleContexts(Insert_valuesContext.class);
		}
		public Insert_valuesContext insert_values(int i) {
			return getRuleContext(Insert_valuesContext.class,i);
		}
		public List<TerminalNode> RP() { return getTokens(HydraQLParser.RP); }
		public TerminalNode RP(int i) {
			return getToken(HydraQLParser.RP, i);
		}
		public Upsert_column_def_listContext upsert_column_def_list() {
			return getRuleContext(Upsert_column_def_listContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(233);
			match(UPSERT);
			setState(234);
			match(INTO);
			setState(235);
			table_ref();
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(236);
				match(LP);
				setState(237);
				upsert_column_def_list();
				setState(238);
				match(RP);
				}
			}

			setState(242);
			match(VALUES);
			setState(243);
			match(LP);
			setState(244);
			insert_values();
			setState(245);
			match(RP);
			setState(253);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(246);
				match(COMMA);
				setState(247);
				match(LP);
				setState(248);
				insert_values();
				setState(249);
				match(RP);
				}
				}
				setState(255);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Insert_valuesContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(256);
			literal();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(257);
				match(COMMA);
				setState(258);
				literal();
				}
				}
				setState(263);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Upsert_column_def_listContext extends ParserRuleContext {
		public List<Column_refContext> column_ref() {
			return getRuleContexts(Column_refContext.class);
		}
		public Column_refContext column_ref(int i) {
			return getRuleContext(Column_refContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(264);
			column_ref();
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(265);
				match(COMMA);
				setState(266);
				column_ref();
				}
				}
				setState(271);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Column_def_listContext extends ParserRuleContext {
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(272);
			column_def();
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(273);
				match(COMMA);
				setState(274);
				column_def();
				}
				}
				setState(279);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Delete_column_def_listContext extends ParserRuleContext {
		public List<Delete_column_defContext> delete_column_def() {
			return getRuleContexts(Delete_column_defContext.class);
		}
		public Delete_column_defContext delete_column_def(int i) {
			return getRuleContext(Delete_column_defContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(280);
			delete_column_def();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(281);
				match(COMMA);
				setState(282);
				delete_column_def();
				}
				}
				setState(287);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(288);
			match(DELETE);
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOUBLE_QUOTE_ID || _la==ID) {
				{
				setState(289);
				delete_column_def_list();
				}
			}

			setState(292);
			match(FROM);
			setState(293);
			table_ref();
			setState(294);
			match(WHERE);
			setState(295);
			whereRow();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(296);
				match(AND);
				setState(297);
				whereCol();
				}
			}

			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & 549755813895L) != 0)) {
				{
				setState(300);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
			setState(324);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LP:
				_localctx = new TsRangeStartAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				match(LP);
				setState(304);
				match(STARTTS);
				setState(305);
				gtOper();
				setState(306);
				tsExp();
				setState(307);
				match(COMMA);
				setState(308);
				match(ENDTS);
				setState(309);
				leOper();
				setState(310);
				tsExp();
				setState(311);
				match(RP);
				}
				break;
			case STARTTS:
				_localctx = new TsRangeStartContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				match(STARTTS);
				setState(314);
				gtOper();
				setState(315);
				tsExp();
				}
				break;
			case ENDTS:
				_localctx = new TsRangeEndContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(317);
				match(ENDTS);
				setState(318);
				leOper();
				setState(319);
				tsExp();
				}
				break;
			case TS:
				_localctx = new TsRangeEqContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(321);
				match(TS);
				setState(322);
				match(EQ);
				setState(323);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(326);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(328);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(330);
			_la = _input.LA(1);
			if ( !(_la==GT || _la==GE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(332);
			_la = _input.LA(1);
			if ( !(_la==LT || _la==LE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Versions_clauseContext extends ParserRuleContext {
		public TerminalNode VERSIONS() { return getToken(HydraQLParser.VERSIONS, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
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
			setState(334);
			match(VERSIONS);
			setState(335);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Limit_clauseContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(HydraQLParser.LIMIT, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
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
			setState(337);
			match(LIMIT);
			setState(338);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(340);
			select_statement();
			setState(342);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VERSIONS) {
				{
				setState(341);
				versions_clause();
				}
			}

			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & 549755813895L) != 0)) {
				{
				setState(344);
				timestamp_range_clause();
				}
			}

			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(347);
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

	@SuppressWarnings("CheckReturnValue")
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
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(350);
			match(SELECT);
			setState(351);
			select_column_def();
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(352);
				match(COMMA);
				setState(353);
				select_column_def();
				}
				}
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(359);
			match(FROM);
			setState(360);
			table_ref();
			setState(361);
			match(WHERE);
			setState(362);
			whereRow();
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(363);
				match(AND);
				setState(364);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
	public static class DeleteOneFamilyAllColContext extends Delete_column_defContext {
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(HydraQLParser.DOT, 0); }
		public TerminalNode STAR() { return getToken(HydraQLParser.STAR, 0); }
		public DeleteOneFamilyAllColContext(Delete_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDeleteOneFamilyAllCol(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeleteFamilyAndColContext extends Delete_column_defContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
		public DeleteFamilyAndColContext(Delete_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDeleteFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_column_defContext delete_column_def() throws RecognitionException {
		Delete_column_defContext _localctx = new Delete_column_defContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_delete_column_def);
		try {
			setState(377);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new DeleteOneFamilyAllColContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(367);
				family_name();
				setState(368);
				match(DOT);
				setState(369);
				match(STAR);
				}
				break;
			case 2:
				_localctx = new DeleteFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(371);
					family_name();
					setState(372);
					match(COLON);
					}
					break;
				}
				setState(376);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
	public static class SelectAllFamilyAndColContext extends Select_column_defContext {
		public TerminalNode STAR() { return getToken(HydraQLParser.STAR, 0); }
		public SelectAllFamilyAndColContext(Select_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectAllFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelectOneFamilyAllColContext extends Select_column_defContext {
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(HydraQLParser.DOT, 0); }
		public TerminalNode STAR() { return getToken(HydraQLParser.STAR, 0); }
		public SelectOneFamilyAllColContext(Select_column_defContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectOneFamilyAllCol(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
	public static class SelectFamilyAndColContext extends Select_column_defContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
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
		enterRule(_localctx, 62, RULE_select_column_def);
		int _la;
		try {
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				_localctx = new SelectAllFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(379);
				match(STAR);
				}
				break;
			case 2:
				_localctx = new SelectOneFamilyAllColContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(380);
				family_name();
				setState(381);
				match(DOT);
				setState(382);
				match(STAR);
				}
				break;
			case 3:
				_localctx = new SelectFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(387);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(384);
					family_name();
					setState(385);
					match(COLON);
					}
					break;
				}
				setState(389);
				column_name();
				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
					{
					setState(391);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(390);
						match(AS);
						}
					}

					setState(393);
					column_alias();
					}
				}

				}
				break;
			case 4:
				_localctx = new SelectWithFuncCallContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(396);
				functionCall();
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
					{
					setState(398);
					_errHandler.sync(this);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
	public static class UdfFunctionCallContext extends FunctionCallContext {
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
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
			setState(405);
			funcName();
			setState(406);
			match(LP);
			setState(408);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 563087392505856L) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & 7995393L) != 0)) {
				{
				setState(407);
				functionArgs();
				}
			}

			setState(410);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(412);
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

	@SuppressWarnings("CheckReturnValue")
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
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
			setState(416);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOUBLE_QUOTE_ID:
			case ID:
				{
				setState(414);
				fullColumnName();
				}
				break;
			case FALSE:
			case NULL_:
			case TRUE:
			case MINUS:
			case STRING_LITERAL:
			case DECIMAL_LITERAL:
			case FLOAT_LITERAL:
				{
				setState(415);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(425);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(418);
				match(COMMA);
				setState(421);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DOUBLE_QUOTE_ID:
				case ID:
					{
					setState(419);
					fullColumnName();
					}
					break;
				case FALSE:
				case NULL_:
				case TRUE:
				case MINUS:
				case STRING_LITERAL:
				case DECIMAL_LITERAL:
				case FLOAT_LITERAL:
					{
					setState(420);
					literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(427);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FullColumnNameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
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
			setState(431);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(428);
				family_name();
				setState(429);
				match(COLON);
				}
				break;
			}
			setState(433);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(435);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(437);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(439);
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

	@SuppressWarnings("CheckReturnValue")
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(445);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(443);
				match(ID);
				}
				break;
			case DOUBLE_QUOTE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(444);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(447);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(449);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(451);
			column_ref();
			setState(452);
			data_type();
			setState(457);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT || _la==NULL_) {
				{
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(453);
					match(NOT);
					}
				}

				setState(456);
				match(NULL_);
				}
			}

			setState(461);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(459);
				match(DEFAULT);
				setState(460);
				literal();
				}
			}

			setState(465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIMARY) {
				{
				setState(463);
				match(PRIMARY);
				setState(464);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Column_refContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
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
			setState(470);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(467);
				family_name();
				setState(468);
				match(COLON);
				}
				break;
			}
			setState(472);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(474);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Data_typeContext extends ParserRuleContext {
		public Sql_data_typeContext sql_data_type() {
			return getRuleContext(Sql_data_typeContext.class,0);
		}
		public Hbase_data_typeContext hbase_data_type() {
			return getRuleContext(Hbase_data_typeContext.class,0);
		}
		public TerminalNode ARRAY() { return getToken(HydraQLParser.ARRAY, 0); }
		public TerminalNode LSB() { return getToken(HydraQLParser.LSB, 0); }
		public Dimension_intContext dimension_int() {
			return getRuleContext(Dimension_intContext.class,0);
		}
		public TerminalNode RSC() { return getToken(HydraQLParser.RSC, 0); }
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
			setState(478);
			_errHandler.sync(this);
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
				setState(476);
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
				setState(477);
				hbase_data_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARRAY) {
				{
				setState(480);
				match(ARRAY);
				setState(485);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(481);
					match(LSB);
					setState(482);
					dimension_int();
					setState(483);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 94, RULE_conditionVal);
		try {
			setState(491);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FALSE:
			case NULL_:
			case TRUE:
			case MINUS:
			case STRING_LITERAL:
			case DECIMAL_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(489);
				constant();
				}
				break;
			case VAR_LP:
				enterOuterAlt(_localctx, 2);
				{
				setState(490);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 96, RULE_conditionValList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			conditionVal();
			setState(498);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(494);
				match(COMMA);
				setState(495);
				conditionVal();
				}
				}
				setState(500);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 98, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(503);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(505);
			match(VAR_LP);
			setState(506);
			variable();
			setState(507);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(512);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(509);
				family_name();
				setState(510);
				match(COLON);
				}
				break;
			}
			setState(514);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(518);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(516);
				string();
				}
				break;
			case MINUS:
			case DECIMAL_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(517);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
			setState(555);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				_localctx = new RowKeyStartAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(520);
				match(STARTKEY);
				setState(521);
				gtOper();
				setState(522);
				rowKey();
				setState(523);
				match(AND);
				setState(524);
				match(ENDKEY);
				setState(525);
				leOper();
				setState(526);
				rowKey();
				}
				break;
			case 2:
				_localctx = new RowKeyStartContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(528);
				match(STARTKEY);
				setState(529);
				gtOper();
				setState(530);
				rowKey();
				}
				break;
			case 3:
				_localctx = new RowKeyEndContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(532);
				match(ENDKEY);
				setState(533);
				leOper();
				setState(534);
				rowKey();
				}
				break;
			case 4:
				_localctx = new RowKeyEqOneContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(536);
				match(ROWKEY);
				setState(537);
				match(EQ);
				setState(538);
				rowKey();
				}
				break;
			case 5:
				_localctx = new RowKeyInSomeKeysContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(539);
				match(ROWKEY);
				setState(540);
				match(IN);
				setState(541);
				match(LP);
				setState(542);
				rowKey();
				setState(547);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(543);
					match(COMMA);
					setState(544);
					rowKey();
					}
					}
					setState(549);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(550);
				match(RP);
				}
				break;
			case 6:
				_localctx = new RowKeyLikeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(552);
				match(ROWKEY);
				setState(553);
				match(LIKE);
				setState(554);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(557);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
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
			setState(601);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				_localctx = new ColConditionWrapperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(560);
				match(LP);
				setState(561);
				colCondition(0);
				setState(562);
				match(RP);
				}
				break;
			case 2:
				{
				_localctx = new ColConditionCompOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(564);
				column();
				setState(565);
				comp_op();
				setState(566);
				conditionVal();
				}
				break;
			case 3:
				{
				_localctx = new ColConditionLikeOrNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(568);
				column();
				setState(572);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LIKE:
					{
					setState(569);
					match(LIKE);
					}
					break;
				case NOT:
					{
					setState(570);
					match(NOT);
					setState(571);
					match(LIKE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(574);
				conditionVal();
				}
				break;
			case 4:
				{
				_localctx = new ColConditionIsNullOrNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(576);
				column();
				setState(577);
				match(IS);
				setState(579);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(578);
					match(NOT);
					}
				}

				setState(581);
				match(NULL_);
				}
				break;
			case 5:
				{
				_localctx = new ColConditionInOrNotInContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(583);
				column();
				setState(585);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(584);
					match(NOT);
					}
				}

				setState(587);
				match(IN);
				setState(588);
				match(LP);
				setState(589);
				conditionValList();
				setState(590);
				match(RP);
				}
				break;
			case 6:
				{
				_localctx = new ColConditionBetweenOrNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(592);
				column();
				setState(594);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(593);
					match(NOT);
					}
				}

				setState(596);
				match(BETWEEN);
				setState(597);
				conditionVal();
				setState(598);
				match(AND);
				setState(599);
				conditionVal();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(611);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(609);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						_localctx = new ColConditionAndContext(new ColConditionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_colCondition);
						setState(603);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(604);
						match(AND);
						setState(605);
						colCondition(8);
						}
						break;
					case 2:
						{
						_localctx = new ColConditionOrContext(new ColConditionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_colCondition);
						setState(606);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(607);
						match(OR);
						setState(608);
						colCondition(7);
						}
						break;
					}
					} 
				}
				setState(613);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(614);
			_la = _input.LA(1);
			if ( !(((((_la - 83)) & ~0x3f) == 0 && ((1L << (_la - 83)) & 127L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 116, RULE_literal);
		try {
			setState(620);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(616);
				string();
				}
				break;
			case MINUS:
			case DECIMAL_LITERAL:
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(617);
				numeric();
				}
				break;
			case FALSE:
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(618);
				true_false();
				}
				break;
			case NULL_:
				enterOuterAlt(_localctx, 4);
				{
				setState(619);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 118, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 120, RULE_varString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(624);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 122, RULE_numeric);
		try {
			setState(628);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(626);
				integer();
				}
				break;
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(627);
				decimal();
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

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(HydraQLParser.DECIMAL_LITERAL, 0); }
		public TerminalNode MINUS() { return getToken(HydraQLParser.MINUS, 0); }
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
		enterRule(_localctx, 124, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(630);
				match(MINUS);
				}
			}

			setState(633);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DecimalContext extends ParserRuleContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(HydraQLParser.FLOAT_LITERAL, 0); }
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
		enterRule(_localctx, 126, RULE_decimal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(635);
			match(FLOAT_LITERAL);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 128, RULE_true_false);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			_la = _input.LA(1);
			if ( !(_la==FALSE || _la==TRUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 130, RULE_dimension_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 132, RULE_precision_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(641);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 134, RULE_scale_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643);
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

	@SuppressWarnings("CheckReturnValue")
	public static class Sql_data_typeContext extends ParserRuleContext {
		public TerminalNode CHAR() { return getToken(HydraQLParser.CHAR, 0); }
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public Precision_intContext precision_int() {
			return getRuleContext(Precision_intContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public TerminalNode VARCHAR() { return getToken(HydraQLParser.VARCHAR, 0); }
		public TerminalNode DECIMAL() { return getToken(HydraQLParser.DECIMAL, 0); }
		public TerminalNode COMMA() { return getToken(HydraQLParser.COMMA, 0); }
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
		enterRule(_localctx, 136, RULE_sql_data_type);
		int _la;
		try {
			setState(683);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(645);
				match(CHAR);
				setState(646);
				match(LP);
				setState(647);
				precision_int();
				setState(648);
				match(RP);
				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(650);
				match(VARCHAR);
				setState(655);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LP) {
					{
					setState(651);
					match(LP);
					setState(652);
					precision_int();
					setState(653);
					match(RP);
					}
				}

				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(657);
				match(DECIMAL);
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==DECIMAL_LITERAL) {
					{
					setState(658);
					precision_int();
					setState(659);
					match(COMMA);
					setState(660);
					scale_int();
					}
				}

				}
				break;
			case TINYINT:
				enterOuterAlt(_localctx, 4);
				{
				setState(664);
				match(TINYINT);
				}
				break;
			case SMALLINT:
				enterOuterAlt(_localctx, 5);
				{
				setState(665);
				match(SMALLINT);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 6);
				{
				setState(666);
				match(INTEGER);
				}
				break;
			case BIGINT:
				enterOuterAlt(_localctx, 7);
				{
				setState(667);
				match(BIGINT);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 8);
				{
				setState(668);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 9);
				{
				setState(669);
				match(DOUBLE);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 10);
				{
				setState(670);
				match(TIMESTAMP);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 11);
				{
				setState(671);
				match(DATE);
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 12);
				{
				setState(672);
				match(TIME);
				}
				break;
			case BINARY:
				enterOuterAlt(_localctx, 13);
				{
				setState(673);
				match(BINARY);
				setState(674);
				match(LP);
				setState(675);
				precision_int();
				setState(676);
				match(RP);
				}
				break;
			case VARBINARY:
				enterOuterAlt(_localctx, 14);
				{
				setState(678);
				match(VARBINARY);
				setState(679);
				match(LP);
				setState(680);
				precision_int();
				setState(681);
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

	@SuppressWarnings("CheckReturnValue")
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
		enterRule(_localctx, 138, RULE_hbase_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(685);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 575334852396580864L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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
		"\u0004\u0001g\u02b0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0001"+
		"\u0000\u0005\u0000\u008e\b\u0000\n\u0000\f\u0000\u0091\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001\u0097\b\u0001\u0001\u0002"+
		"\u0001\u0002\u0003\u0002\u009b\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u00a1\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004\u00a6\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005\u00ac\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u00b3\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007\u00be\b\u0007\n\u0007\f\u0007\u00c1\t\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00cb\b\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00d8\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u00e6\b\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u00f1\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0005\u000f\u00fc\b\u000f\n\u000f\f\u000f\u00ff\t\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0005\u0010\u0104\b\u0010\n\u0010\f\u0010\u0107\t\u0010"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u010c\b\u0011\n\u0011"+
		"\f\u0011\u010f\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u0114\b\u0012\n\u0012\f\u0012\u0117\t\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0005\u0013\u011c\b\u0013\n\u0013\f\u0013\u011f\t\u0013\u0001\u0014"+
		"\u0001\u0014\u0003\u0014\u0123\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u012b\b\u0014\u0001\u0014"+
		"\u0003\u0014\u012e\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u0145\b\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0003\u001c"+
		"\u0157\b\u001c\u0001\u001c\u0003\u001c\u015a\b\u001c\u0001\u001c\u0003"+
		"\u001c\u015d\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0005"+
		"\u001d\u0163\b\u001d\n\u001d\f\u001d\u0166\t\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u016e\b\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0003\u001e\u0177\b\u001e\u0001\u001e\u0003\u001e\u017a\b"+
		"\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0184\b\u001f\u0001\u001f\u0001"+
		"\u001f\u0003\u001f\u0188\b\u001f\u0001\u001f\u0003\u001f\u018b\b\u001f"+
		"\u0001\u001f\u0001\u001f\u0003\u001f\u018f\b\u001f\u0001\u001f\u0003\u001f"+
		"\u0192\b\u001f\u0003\u001f\u0194\b\u001f\u0001 \u0001 \u0001 \u0003 \u0199"+
		"\b \u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0003\"\u01a1\b\"\u0001"+
		"\"\u0001\"\u0001\"\u0003\"\u01a6\b\"\u0005\"\u01a8\b\"\n\"\f\"\u01ab\t"+
		"\"\u0001#\u0001#\u0001#\u0003#\u01b0\b#\u0001#\u0001#\u0001$\u0001$\u0001"+
		"%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0003(\u01be\b(\u0001"+
		")\u0001)\u0001*\u0001*\u0001+\u0001+\u0001+\u0003+\u01c7\b+\u0001+\u0003"+
		"+\u01ca\b+\u0001+\u0001+\u0003+\u01ce\b+\u0001+\u0001+\u0003+\u01d2\b"+
		"+\u0001,\u0001,\u0001,\u0003,\u01d7\b,\u0001,\u0001,\u0001-\u0001-\u0001"+
		".\u0001.\u0003.\u01df\b.\u0001.\u0001.\u0001.\u0001.\u0001.\u0003.\u01e6"+
		"\b.\u0003.\u01e8\b.\u0001/\u0001/\u0003/\u01ec\b/\u00010\u00010\u0001"+
		"0\u00050\u01f1\b0\n0\f0\u01f4\t0\u00011\u00011\u00012\u00012\u00013\u0001"+
		"3\u00013\u00013\u00014\u00014\u00014\u00034\u0201\b4\u00014\u00014\u0001"+
		"5\u00015\u00035\u0207\b5\u00016\u00016\u00016\u00016\u00016\u00016\u0001"+
		"6\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u0001"+
		"6\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00056\u0222"+
		"\b6\n6\f6\u0225\t6\u00016\u00016\u00016\u00016\u00016\u00036\u022c\b6"+
		"\u00017\u00017\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u0001"+
		"8\u00018\u00018\u00018\u00018\u00018\u00038\u023d\b8\u00018\u00018\u0001"+
		"8\u00018\u00018\u00038\u0244\b8\u00018\u00018\u00018\u00018\u00038\u024a"+
		"\b8\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00038\u0253\b8\u0001"+
		"8\u00018\u00018\u00018\u00018\u00038\u025a\b8\u00018\u00018\u00018\u0001"+
		"8\u00018\u00018\u00058\u0262\b8\n8\f8\u0265\t8\u00019\u00019\u0001:\u0001"+
		":\u0001:\u0001:\u0003:\u026d\b:\u0001;\u0001;\u0001<\u0001<\u0001=\u0001"+
		"=\u0003=\u0275\b=\u0001>\u0003>\u0278\b>\u0001>\u0001>\u0001?\u0001?\u0001"+
		"@\u0001@\u0001A\u0001A\u0001B\u0001B\u0001C\u0001C\u0001D\u0001D\u0001"+
		"D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0003D\u0290\bD\u0001"+
		"D\u0001D\u0001D\u0001D\u0001D\u0003D\u0297\bD\u0001D\u0001D\u0001D\u0001"+
		"D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001D\u0001"+
		"D\u0001D\u0001D\u0001D\u0001D\u0001D\u0003D\u02ac\bD\u0001E\u0001E\u0001"+
		"E\u0000\u0001pF\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfh"+
		"jlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u0000\u0005\u0001\u0000"+
		"VW\u0001\u0000XY\u0001\u0000SY\u0002\u0000\u0011\u001111\u0001\u00002"+
		":\u02c9\u0000\u008f\u0001\u0000\u0000\u0000\u0002\u0094\u0001\u0000\u0000"+
		"\u0000\u0004\u009a\u0001\u0000\u0000\u0000\u0006\u00a0\u0001\u0000\u0000"+
		"\u0000\b\u00a5\u0001\u0000\u0000\u0000\n\u00a7\u0001\u0000\u0000\u0000"+
		"\f\u00b4\u0001\u0000\u0000\u0000\u000e\u00ba\u0001\u0000\u0000\u0000\u0010"+
		"\u00c2\u0001\u0000\u0000\u0000\u0012\u00c6\u0001\u0000\u0000\u0000\u0014"+
		"\u00ce\u0001\u0000\u0000\u0000\u0016\u00d2\u0001\u0000\u0000\u0000\u0018"+
		"\u00db\u0001\u0000\u0000\u0000\u001a\u00de\u0001\u0000\u0000\u0000\u001c"+
		"\u00e5\u0001\u0000\u0000\u0000\u001e\u00e9\u0001\u0000\u0000\u0000 \u0100"+
		"\u0001\u0000\u0000\u0000\"\u0108\u0001\u0000\u0000\u0000$\u0110\u0001"+
		"\u0000\u0000\u0000&\u0118\u0001\u0000\u0000\u0000(\u0120\u0001\u0000\u0000"+
		"\u0000*\u0144\u0001\u0000\u0000\u0000,\u0146\u0001\u0000\u0000\u0000."+
		"\u0148\u0001\u0000\u0000\u00000\u014a\u0001\u0000\u0000\u00002\u014c\u0001"+
		"\u0000\u0000\u00004\u014e\u0001\u0000\u0000\u00006\u0151\u0001\u0000\u0000"+
		"\u00008\u0154\u0001\u0000\u0000\u0000:\u015e\u0001\u0000\u0000\u0000<"+
		"\u0179\u0001\u0000\u0000\u0000>\u0193\u0001\u0000\u0000\u0000@\u0195\u0001"+
		"\u0000\u0000\u0000B\u019c\u0001\u0000\u0000\u0000D\u01a0\u0001\u0000\u0000"+
		"\u0000F\u01af\u0001\u0000\u0000\u0000H\u01b3\u0001\u0000\u0000\u0000J"+
		"\u01b5\u0001\u0000\u0000\u0000L\u01b7\u0001\u0000\u0000\u0000N\u01b9\u0001"+
		"\u0000\u0000\u0000P\u01bd\u0001\u0000\u0000\u0000R\u01bf\u0001\u0000\u0000"+
		"\u0000T\u01c1\u0001\u0000\u0000\u0000V\u01c3\u0001\u0000\u0000\u0000X"+
		"\u01d6\u0001\u0000\u0000\u0000Z\u01da\u0001\u0000\u0000\u0000\\\u01de"+
		"\u0001\u0000\u0000\u0000^\u01eb\u0001\u0000\u0000\u0000`\u01ed\u0001\u0000"+
		"\u0000\u0000b\u01f5\u0001\u0000\u0000\u0000d\u01f7\u0001\u0000\u0000\u0000"+
		"f\u01f9\u0001\u0000\u0000\u0000h\u0200\u0001\u0000\u0000\u0000j\u0206"+
		"\u0001\u0000\u0000\u0000l\u022b\u0001\u0000\u0000\u0000n\u022d\u0001\u0000"+
		"\u0000\u0000p\u0259\u0001\u0000\u0000\u0000r\u0266\u0001\u0000\u0000\u0000"+
		"t\u026c\u0001\u0000\u0000\u0000v\u026e\u0001\u0000\u0000\u0000x\u0270"+
		"\u0001\u0000\u0000\u0000z\u0274\u0001\u0000\u0000\u0000|\u0277\u0001\u0000"+
		"\u0000\u0000~\u027b\u0001\u0000\u0000\u0000\u0080\u027d\u0001\u0000\u0000"+
		"\u0000\u0082\u027f\u0001\u0000\u0000\u0000\u0084\u0281\u0001\u0000\u0000"+
		"\u0000\u0086\u0283\u0001\u0000\u0000\u0000\u0088\u02ab\u0001\u0000\u0000"+
		"\u0000\u008a\u02ad\u0001\u0000\u0000\u0000\u008c\u008e\u0003\u0002\u0001"+
		"\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000"+
		"\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0005\u0000\u0000\u0001\u0093\u0001\u0001\u0000\u0000"+
		"\u0000\u0094\u0096\u0003\u0004\u0002\u0000\u0095\u0097\u0005C\u0000\u0000"+
		"\u0096\u0095\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000"+
		"\u0097\u0003\u0001\u0000\u0000\u0000\u0098\u009b\u0003\u0006\u0003\u0000"+
		"\u0099\u009b\u0003\b\u0004\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a"+
		"\u0099\u0001\u0000\u0000\u0000\u009b\u0005\u0001\u0000\u0000\u0000\u009c"+
		"\u00a1\u0003\n\u0005\u0000\u009d\u00a1\u0003\u0014\n\u0000\u009e\u00a1"+
		"\u0003\u0016\u000b\u0000\u009f\u00a1\u0003\u0012\t\u0000\u00a0\u009c\u0001"+
		"\u0000\u0000\u0000\u00a0\u009d\u0001\u0000\u0000\u0000\u00a0\u009e\u0001"+
		"\u0000\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a1\u0007\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a6\u00038\u001c\u0000\u00a3\u00a6\u0003\u001e"+
		"\u000f\u0000\u00a4\u00a6\u0003(\u0014\u0000\u00a5\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a4\u0001\u0000\u0000"+
		"\u0000\u00a6\t\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005\u0007\u0000\u0000"+
		"\u00a8\u00a9\u0005+\u0000\u0000\u00a9\u00ab\u0005,\u0000\u0000\u00aa\u00ac"+
		"\u0003\u001a\r\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ae\u0003"+
		"\u001c\u000e\u0000\u00ae\u00af\u0005G\u0000\u0000\u00af\u00b0\u0003$\u0012"+
		"\u0000\u00b0\u00b2\u0005H\u0000\u0000\u00b1\u00b3\u0003\f\u0006\u0000"+
		"\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b3\u000b\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005A\u0000\u0000\u00b5"+
		"\u00b6\u0005B\u0000\u0000\u00b6\u00b7\u0005G\u0000\u0000\u00b7\u00b8\u0003"+
		"\u000e\u0007\u0000\u00b8\u00b9\u0005H\u0000\u0000\u00b9\r\u0001\u0000"+
		"\u0000\u0000\u00ba\u00bf\u0003\u0010\b\u0000\u00bb\u00bc\u0005E\u0000"+
		"\u0000\u00bc\u00be\u0003\u0010\b\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000"+
		"\u00be\u00c1\u0001\u0000\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000"+
		"\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u000f\u0001\u0000\u0000\u0000"+
		"\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c2\u00c3\u0003P(\u0000\u00c3\u00c4"+
		"\u0005S\u0000\u0000\u00c4\u00c5\u0003t:\u0000\u00c5\u0011\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0005\u000f\u0000\u0000\u00c7\u00c8\u0005+\u0000"+
		"\u0000\u00c8\u00ca\u0005,\u0000\u0000\u00c9\u00cb\u0003\u0018\f\u0000"+
		"\u00ca\u00c9\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00cd\u0003\u001c\u000e\u0000"+
		"\u00cd\u0013\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005)\u0000\u0000\u00cf"+
		"\u00d0\u0005+\u0000\u0000\u00d0\u00d1\u0005-\u0000\u0000\u00d1\u0015\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d3\u0005)\u0000\u0000\u00d3\u00d4\u0005\u0007"+
		"\u0000\u0000\u00d4\u00d5\u0005+\u0000\u0000\u00d5\u00d7\u0005,\u0000\u0000"+
		"\u00d6\u00d8\u0003\u0018\f\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9"+
		"\u00da\u0003\u001c\u000e\u0000\u00da\u0017\u0001\u0000\u0000\u0000\u00db"+
		"\u00dc\u0005\u0015\u0000\u0000\u00dc\u00dd\u0005\u0010\u0000\u0000\u00dd"+
		"\u0019\u0001\u0000\u0000\u0000\u00de\u00df\u0005\u0015\u0000\u0000\u00df"+
		"\u00e0\u0005$\u0000\u0000\u00e0\u00e1\u0005\u0010\u0000\u0000\u00e1\u001b"+
		"\u0001\u0000\u0000\u0000\u00e2\u00e3\u0003R)\u0000\u00e3\u00e4\u0005D"+
		"\u0000\u0000\u00e4\u00e6\u0001\u0000\u0000\u0000\u00e5\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e8\u0003T*\u0000\u00e8\u001d\u0001\u0000\u0000\u0000"+
		"\u00e9\u00ea\u0005<\u0000\u0000\u00ea\u00eb\u0005\u0018\u0000\u0000\u00eb"+
		"\u00f0\u0003\u001c\u000e\u0000\u00ec\u00ed\u0005G\u0000\u0000\u00ed\u00ee"+
		"\u0003\"\u0011\u0000\u00ee\u00ef\u0005H\u0000\u0000\u00ef\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f0\u00ec\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005"+
		"=\u0000\u0000\u00f3\u00f4\u0005G\u0000\u0000\u00f4\u00f5\u0003 \u0010"+
		"\u0000\u00f5\u00fd\u0005H\u0000\u0000\u00f6\u00f7\u0005E\u0000\u0000\u00f7"+
		"\u00f8\u0005G\u0000\u0000\u00f8\u00f9\u0003 \u0010\u0000\u00f9\u00fa\u0005"+
		"H\u0000\u0000\u00fa\u00fc\u0001\u0000\u0000\u0000\u00fb\u00f6\u0001\u0000"+
		"\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd\u00fb\u0001\u0000"+
		"\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u001f\u0001\u0000"+
		"\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u0100\u0105\u0003t:\u0000"+
		"\u0101\u0102\u0005E\u0000\u0000\u0102\u0104\u0003t:\u0000\u0103\u0101"+
		"\u0001\u0000\u0000\u0000\u0104\u0107\u0001\u0000\u0000\u0000\u0105\u0103"+
		"\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106!\u0001"+
		"\u0000\u0000\u0000\u0107\u0105\u0001\u0000\u0000\u0000\u0108\u010d\u0003"+
		"X,\u0000\u0109\u010a\u0005E\u0000\u0000\u010a\u010c\u0003X,\u0000\u010b"+
		"\u0109\u0001\u0000\u0000\u0000\u010c\u010f\u0001\u0000\u0000\u0000\u010d"+
		"\u010b\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e"+
		"#\u0001\u0000\u0000\u0000\u010f\u010d\u0001\u0000\u0000\u0000\u0110\u0115"+
		"\u0003V+\u0000\u0111\u0112\u0005E\u0000\u0000\u0112\u0114\u0003V+\u0000"+
		"\u0113\u0111\u0001\u0000\u0000\u0000\u0114\u0117\u0001\u0000\u0000\u0000"+
		"\u0115\u0113\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000"+
		"\u0116%\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0118"+
		"\u011d\u0003<\u001e\u0000\u0119\u011a\u0005E\u0000\u0000\u011a\u011c\u0003"+
		"<\u001e\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011c\u011f\u0001\u0000"+
		"\u0000\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011d\u011e\u0001\u0000"+
		"\u0000\u0000\u011e\'\u0001\u0000\u0000\u0000\u011f\u011d\u0001\u0000\u0000"+
		"\u0000\u0120\u0122\u0005\u000b\u0000\u0000\u0121\u0123\u0003&\u0013\u0000"+
		"\u0122\u0121\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000"+
		"\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0125\u0005\u0013\u0000\u0000"+
		"\u0125\u0126\u0003\u001c\u000e\u0000\u0126\u0127\u0005@\u0000\u0000\u0127"+
		"\u012a\u0003l6\u0000\u0128\u0129\u0005\u0001\u0000\u0000\u0129\u012b\u0003"+
		"n7\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012a\u012b\u0001\u0000\u0000"+
		"\u0000\u012b\u012d\u0001\u0000\u0000\u0000\u012c\u012e\u0003*\u0015\u0000"+
		"\u012d\u012c\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000"+
		"\u012e)\u0001\u0000\u0000\u0000\u012f\u0130\u0005G\u0000\u0000\u0130\u0131"+
		"\u0005 \u0000\u0000\u0131\u0132\u00030\u0018\u0000\u0132\u0133\u0003,"+
		"\u0016\u0000\u0133\u0134\u0005E\u0000\u0000\u0134\u0135\u0005!\u0000\u0000"+
		"\u0135\u0136\u00032\u0019\u0000\u0136\u0137\u0003,\u0016\u0000\u0137\u0138"+
		"\u0005H\u0000\u0000\u0138\u0145\u0001\u0000\u0000\u0000\u0139\u013a\u0005"+
		" \u0000\u0000\u013a\u013b\u00030\u0018\u0000\u013b\u013c\u0003,\u0016"+
		"\u0000\u013c\u0145\u0001\u0000\u0000\u0000\u013d\u013e\u0005!\u0000\u0000"+
		"\u013e\u013f\u00032\u0019\u0000\u013f\u0140\u0003,\u0016\u0000\u0140\u0145"+
		"\u0001\u0000\u0000\u0000\u0141\u0142\u0005\"\u0000\u0000\u0142\u0143\u0005"+
		"S\u0000\u0000\u0143\u0145\u0003,\u0016\u0000\u0144\u012f\u0001\u0000\u0000"+
		"\u0000\u0144\u0139\u0001\u0000\u0000\u0000\u0144\u013d\u0001\u0000\u0000"+
		"\u0000\u0144\u0141\u0001\u0000\u0000\u0000\u0145+\u0001\u0000\u0000\u0000"+
		"\u0146\u0147\u0003.\u0017\u0000\u0147-\u0001\u0000\u0000\u0000\u0148\u0149"+
		"\u0003|>\u0000\u0149/\u0001\u0000\u0000\u0000\u014a\u014b\u0007\u0000"+
		"\u0000\u0000\u014b1\u0001\u0000\u0000\u0000\u014c\u014d\u0007\u0001\u0000"+
		"\u0000\u014d3\u0001\u0000\u0000\u0000\u014e\u014f\u0005\u001f\u0000\u0000"+
		"\u014f\u0150\u0003|>\u0000\u01505\u0001\u0000\u0000\u0000\u0151\u0152"+
		"\u0005#\u0000\u0000\u0152\u0153\u0003|>\u0000\u01537\u0001\u0000\u0000"+
		"\u0000\u0154\u0156\u0003:\u001d\u0000\u0155\u0157\u00034\u001a\u0000\u0156"+
		"\u0155\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157"+
		"\u0159\u0001\u0000\u0000\u0000\u0158\u015a\u0003*\u0015\u0000\u0159\u0158"+
		"\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015c"+
		"\u0001\u0000\u0000\u0000\u015b\u015d\u00036\u001b\u0000\u015c\u015b\u0001"+
		"\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000\u0000\u015d9\u0001\u0000"+
		"\u0000\u0000\u015e\u015f\u0005(\u0000\u0000\u015f\u0164\u0003>\u001f\u0000"+
		"\u0160\u0161\u0005E\u0000\u0000\u0161\u0163\u0003>\u001f\u0000\u0162\u0160"+
		"\u0001\u0000\u0000\u0000\u0163\u0166\u0001\u0000\u0000\u0000\u0164\u0162"+
		"\u0001\u0000\u0000\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165\u0167"+
		"\u0001\u0000\u0000\u0000\u0166\u0164\u0001\u0000\u0000\u0000\u0167\u0168"+
		"\u0005\u0013\u0000\u0000\u0168\u0169\u0003\u001c\u000e\u0000\u0169\u016a"+
		"\u0005@\u0000\u0000\u016a\u016d\u0003l6\u0000\u016b\u016c\u0005\u0001"+
		"\u0000\u0000\u016c\u016e\u0003n7\u0000\u016d\u016b\u0001\u0000\u0000\u0000"+
		"\u016d\u016e\u0001\u0000\u0000\u0000\u016e;\u0001\u0000\u0000\u0000\u016f"+
		"\u0170\u0003H$\u0000\u0170\u0171\u0005F\u0000\u0000\u0171\u0172\u0005"+
		"K\u0000\u0000\u0172\u017a\u0001\u0000\u0000\u0000\u0173\u0174\u0003H$"+
		"\u0000\u0174\u0175\u0005D\u0000\u0000\u0175\u0177\u0001\u0000\u0000\u0000"+
		"\u0176\u0173\u0001\u0000\u0000\u0000\u0176\u0177\u0001\u0000\u0000\u0000"+
		"\u0177\u0178\u0001\u0000\u0000\u0000\u0178\u017a\u0003Z-\u0000\u0179\u016f"+
		"\u0001\u0000\u0000\u0000\u0179\u0176\u0001\u0000\u0000\u0000\u017a=\u0001"+
		"\u0000\u0000\u0000\u017b\u0194\u0005K\u0000\u0000\u017c\u017d\u0003H$"+
		"\u0000\u017d\u017e\u0005F\u0000\u0000\u017e\u017f\u0005K\u0000\u0000\u017f"+
		"\u0194\u0001\u0000\u0000\u0000\u0180\u0181\u0003H$\u0000\u0181\u0182\u0005"+
		"D\u0000\u0000\u0182\u0184\u0001\u0000\u0000\u0000\u0183\u0180\u0001\u0000"+
		"\u0000\u0000\u0183\u0184\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000"+
		"\u0000\u0000\u0185\u018a\u0003Z-\u0000\u0186\u0188\u0005\u0003\u0000\u0000"+
		"\u0187\u0186\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000\u0000"+
		"\u0188\u0189\u0001\u0000\u0000\u0000\u0189\u018b\u0003L&\u0000\u018a\u0187"+
		"\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000\u0000\u018b\u0194"+
		"\u0001\u0000\u0000\u0000\u018c\u0191\u0003@ \u0000\u018d\u018f\u0005\u0003"+
		"\u0000\u0000\u018e\u018d\u0001\u0000\u0000\u0000\u018e\u018f\u0001\u0000"+
		"\u0000\u0000\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0192\u0003L&\u0000"+
		"\u0191\u018e\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000\u0000\u0000"+
		"\u0192\u0194\u0001\u0000\u0000\u0000\u0193\u017b\u0001\u0000\u0000\u0000"+
		"\u0193\u017c\u0001\u0000\u0000\u0000\u0193\u0183\u0001\u0000\u0000\u0000"+
		"\u0193\u018c\u0001\u0000\u0000\u0000\u0194?\u0001\u0000\u0000\u0000\u0195"+
		"\u0196\u0003B!\u0000\u0196\u0198\u0005G\u0000\u0000\u0197\u0199\u0003"+
		"D\"\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0198\u0199\u0001\u0000"+
		"\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a\u019b\u0005H\u0000"+
		"\u0000\u019bA\u0001\u0000\u0000\u0000\u019c\u019d\u0003P(\u0000\u019d"+
		"C\u0001\u0000\u0000\u0000\u019e\u01a1\u0003F#\u0000\u019f\u01a1\u0003"+
		"t:\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a0\u019f\u0001\u0000\u0000"+
		"\u0000\u01a1\u01a9\u0001\u0000\u0000\u0000\u01a2\u01a5\u0005E\u0000\u0000"+
		"\u01a3\u01a6\u0003F#\u0000\u01a4\u01a6\u0003t:\u0000\u01a5\u01a3\u0001"+
		"\u0000\u0000\u0000\u01a5\u01a4\u0001\u0000\u0000\u0000\u01a6\u01a8\u0001"+
		"\u0000\u0000\u0000\u01a7\u01a2\u0001\u0000\u0000\u0000\u01a8\u01ab\u0001"+
		"\u0000\u0000\u0000\u01a9\u01a7\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001"+
		"\u0000\u0000\u0000\u01aaE\u0001\u0000\u0000\u0000\u01ab\u01a9\u0001\u0000"+
		"\u0000\u0000\u01ac\u01ad\u0003H$\u0000\u01ad\u01ae\u0005D\u0000\u0000"+
		"\u01ae\u01b0\u0001\u0000\u0000\u0000\u01af\u01ac\u0001\u0000\u0000\u0000"+
		"\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000"+
		"\u01b1\u01b2\u0003Z-\u0000\u01b2G\u0001\u0000\u0000\u0000\u01b3\u01b4"+
		"\u0003P(\u0000\u01b4I\u0001\u0000\u0000\u0000\u01b5\u01b6\u0005`\u0000"+
		"\u0000\u01b6K\u0001\u0000\u0000\u0000\u01b7\u01b8\u0003N\'\u0000\u01b8"+
		"M\u0001\u0000\u0000\u0000\u01b9\u01ba\u0003P(\u0000\u01baO\u0001\u0000"+
		"\u0000\u0000\u01bb\u01be\u0005b\u0000\u0000\u01bc\u01be\u0003J%\u0000"+
		"\u01bd\u01bb\u0001\u0000\u0000\u0000\u01bd\u01bc\u0001\u0000\u0000\u0000"+
		"\u01beQ\u0001\u0000\u0000\u0000\u01bf\u01c0\u0003P(\u0000\u01c0S\u0001"+
		"\u0000\u0000\u0000\u01c1\u01c2\u0003P(\u0000\u01c2U\u0001\u0000\u0000"+
		"\u0000\u01c3\u01c4\u0003X,\u0000\u01c4\u01c9\u0003\\.\u0000\u01c5\u01c7"+
		"\u0005$\u0000\u0000\u01c6\u01c5\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001"+
		"\u0000\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01ca\u0005"+
		"%\u0000\u0000\u01c9\u01c6\u0001\u0000\u0000\u0000\u01c9\u01ca\u0001\u0000"+
		"\u0000\u0000\u01ca\u01cd\u0001\u0000\u0000\u0000\u01cb\u01cc\u0005\n\u0000"+
		"\u0000\u01cc\u01ce\u0003t:\u0000\u01cd\u01cb\u0001\u0000\u0000\u0000\u01cd"+
		"\u01ce\u0001\u0000\u0000\u0000\u01ce\u01d1\u0001\u0000\u0000\u0000\u01cf"+
		"\u01d0\u0005\'\u0000\u0000\u01d0\u01d2\u0005\u001a\u0000\u0000\u01d1\u01cf"+
		"\u0001\u0000\u0000\u0000\u01d1\u01d2\u0001\u0000\u0000\u0000\u01d2W\u0001"+
		"\u0000\u0000\u0000\u01d3\u01d4\u0003H$\u0000\u01d4\u01d5\u0005D\u0000"+
		"\u0000\u01d5\u01d7\u0001\u0000\u0000\u0000\u01d6\u01d3\u0001\u0000\u0000"+
		"\u0000\u01d6\u01d7\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001\u0000\u0000"+
		"\u0000\u01d8\u01d9\u0003Z-\u0000\u01d9Y\u0001\u0000\u0000\u0000\u01da"+
		"\u01db\u0003P(\u0000\u01db[\u0001\u0000\u0000\u0000\u01dc\u01df\u0003"+
		"\u0088D\u0000\u01dd\u01df\u0003\u008aE\u0000\u01de\u01dc\u0001\u0000\u0000"+
		"\u0000\u01de\u01dd\u0001\u0000\u0000\u0000\u01df\u01e7\u0001\u0000\u0000"+
		"\u0000\u01e0\u01e5\u0005\u0002\u0000\u0000\u01e1\u01e2\u0005Q\u0000\u0000"+
		"\u01e2\u01e3\u0003\u0082A\u0000\u01e3\u01e4\u0005R\u0000\u0000\u01e4\u01e6"+
		"\u0001\u0000\u0000\u0000\u01e5\u01e1\u0001\u0000\u0000\u0000\u01e5\u01e6"+
		"\u0001\u0000\u0000\u0000\u01e6\u01e8\u0001\u0000\u0000\u0000\u01e7\u01e0"+
		"\u0001\u0000\u0000\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8]\u0001"+
		"\u0000\u0000\u0000\u01e9\u01ec\u0003b1\u0000\u01ea\u01ec\u0003f3\u0000"+
		"\u01eb\u01e9\u0001\u0000\u0000\u0000\u01eb\u01ea\u0001\u0000\u0000\u0000"+
		"\u01ec_\u0001\u0000\u0000\u0000\u01ed\u01f2\u0003^/\u0000\u01ee\u01ef"+
		"\u0005E\u0000\u0000\u01ef\u01f1\u0003^/\u0000\u01f0\u01ee\u0001\u0000"+
		"\u0000\u0000\u01f1\u01f4\u0001\u0000\u0000\u0000\u01f2\u01f0\u0001\u0000"+
		"\u0000\u0000\u01f2\u01f3\u0001\u0000\u0000\u0000\u01f3a\u0001\u0000\u0000"+
		"\u0000\u01f4\u01f2\u0001\u0000\u0000\u0000\u01f5\u01f6\u0003t:\u0000\u01f6"+
		"c\u0001\u0000\u0000\u0000\u01f7\u01f8\u0003x<\u0000\u01f8e\u0001\u0000"+
		"\u0000\u0000\u01f9\u01fa\u0005I\u0000\u0000\u01fa\u01fb\u0003d2\u0000"+
		"\u01fb\u01fc\u0005J\u0000\u0000\u01fcg\u0001\u0000\u0000\u0000\u01fd\u01fe"+
		"\u0003H$\u0000\u01fe\u01ff\u0005D\u0000\u0000\u01ff\u0201\u0001\u0000"+
		"\u0000\u0000\u0200\u01fd\u0001\u0000\u0000\u0000\u0200\u0201\u0001\u0000"+
		"\u0000\u0000\u0201\u0202\u0001\u0000\u0000\u0000\u0202\u0203\u0003Z-\u0000"+
		"\u0203i\u0001\u0000\u0000\u0000\u0204\u0207\u0003v;\u0000\u0205\u0207"+
		"\u0003z=\u0000\u0206\u0204\u0001\u0000\u0000\u0000\u0206\u0205\u0001\u0000"+
		"\u0000\u0000\u0207k\u0001\u0000\u0000\u0000\u0208\u0209\u0005\u001b\u0000"+
		"\u0000\u0209\u020a\u00030\u0018\u0000\u020a\u020b\u0003j5\u0000\u020b"+
		"\u020c\u0005\u0001\u0000\u0000\u020c\u020d\u0005\u001c\u0000\u0000\u020d"+
		"\u020e\u00032\u0019\u0000\u020e\u020f\u0003j5\u0000\u020f\u022c\u0001"+
		"\u0000\u0000\u0000\u0210\u0211\u0005\u001b\u0000\u0000\u0211\u0212\u0003"+
		"0\u0018\u0000\u0212\u0213\u0003j5\u0000\u0213\u022c\u0001\u0000\u0000"+
		"\u0000\u0214\u0215\u0005\u001c\u0000\u0000\u0215\u0216\u00032\u0019\u0000"+
		"\u0216\u0217\u0003j5\u0000\u0217\u022c\u0001\u0000\u0000\u0000\u0218\u0219"+
		"\u0005\u001d\u0000\u0000\u0219\u021a\u0005S\u0000\u0000\u021a\u022c\u0003"+
		"j5\u0000\u021b\u021c\u0005\u001d\u0000\u0000\u021c\u021d\u0005\u0014\u0000"+
		"\u0000\u021d\u021e\u0005G\u0000\u0000\u021e\u0223\u0003j5\u0000\u021f"+
		"\u0220\u0005E\u0000\u0000\u0220\u0222\u0003j5\u0000\u0221\u021f\u0001"+
		"\u0000\u0000\u0000\u0222\u0225\u0001\u0000\u0000\u0000\u0223\u0221\u0001"+
		"\u0000\u0000\u0000\u0223\u0224\u0001\u0000\u0000\u0000\u0224\u0226\u0001"+
		"\u0000\u0000\u0000\u0225\u0223\u0001\u0000\u0000\u0000\u0226\u0227\u0005"+
		"H\u0000\u0000\u0227\u022c\u0001\u0000\u0000\u0000\u0228\u0229\u0005\u001d"+
		"\u0000\u0000\u0229\u022a\u0005\u001e\u0000\u0000\u022a\u022c\u0003j5\u0000"+
		"\u022b\u0208\u0001\u0000\u0000\u0000\u022b\u0210\u0001\u0000\u0000\u0000"+
		"\u022b\u0214\u0001\u0000\u0000\u0000\u022b\u0218\u0001\u0000\u0000\u0000"+
		"\u022b\u021b\u0001\u0000\u0000\u0000\u022b\u0228\u0001\u0000\u0000\u0000"+
		"\u022cm\u0001\u0000\u0000\u0000\u022d\u022e\u0003p8\u0000\u022eo\u0001"+
		"\u0000\u0000\u0000\u022f\u0230\u00068\uffff\uffff\u0000\u0230\u0231\u0005"+
		"G\u0000\u0000\u0231\u0232\u0003p8\u0000\u0232\u0233\u0005H\u0000\u0000"+
		"\u0233\u025a\u0001\u0000\u0000\u0000\u0234\u0235\u0003h4\u0000\u0235\u0236"+
		"\u0003r9\u0000\u0236\u0237\u0003^/\u0000\u0237\u025a\u0001\u0000\u0000"+
		"\u0000\u0238\u023c\u0003h4\u0000\u0239\u023d\u0005\u001e\u0000\u0000\u023a"+
		"\u023b\u0005$\u0000\u0000\u023b\u023d\u0005\u001e\u0000\u0000\u023c\u0239"+
		"\u0001\u0000\u0000\u0000\u023c\u023a\u0001\u0000\u0000\u0000\u023d\u023e"+
		"\u0001\u0000\u0000\u0000\u023e\u023f\u0003^/\u0000\u023f\u025a\u0001\u0000"+
		"\u0000\u0000\u0240\u0241\u0003h4\u0000\u0241\u0243\u0005\u0019\u0000\u0000"+
		"\u0242\u0244\u0005$\u0000\u0000\u0243\u0242\u0001\u0000\u0000\u0000\u0243"+
		"\u0244\u0001\u0000\u0000\u0000\u0244\u0245\u0001\u0000\u0000\u0000\u0245"+
		"\u0246\u0005%\u0000\u0000\u0246\u025a\u0001\u0000\u0000\u0000\u0247\u0249"+
		"\u0003h4\u0000\u0248\u024a\u0005$\u0000\u0000\u0249\u0248\u0001\u0000"+
		"\u0000\u0000\u0249\u024a\u0001\u0000\u0000\u0000\u024a\u024b\u0001\u0000"+
		"\u0000\u0000\u024b\u024c\u0005\u0014\u0000\u0000\u024c\u024d\u0005G\u0000"+
		"\u0000\u024d\u024e\u0003`0\u0000\u024e\u024f\u0005H\u0000\u0000\u024f"+
		"\u025a\u0001\u0000\u0000\u0000\u0250\u0252\u0003h4\u0000\u0251\u0253\u0005"+
		"$\u0000\u0000\u0252\u0251\u0001\u0000\u0000\u0000\u0252\u0253\u0001\u0000"+
		"\u0000\u0000\u0253\u0254\u0001\u0000\u0000\u0000\u0254\u0255\u0005\u0004"+
		"\u0000\u0000\u0255\u0256\u0003^/\u0000\u0256\u0257\u0005\u0001\u0000\u0000"+
		"\u0257\u0258\u0003^/\u0000\u0258\u025a\u0001\u0000\u0000\u0000\u0259\u022f"+
		"\u0001\u0000\u0000\u0000\u0259\u0234\u0001\u0000\u0000\u0000\u0259\u0238"+
		"\u0001\u0000\u0000\u0000\u0259\u0240\u0001\u0000\u0000\u0000\u0259\u0247"+
		"\u0001\u0000\u0000\u0000\u0259\u0250\u0001\u0000\u0000\u0000\u025a\u0263"+
		"\u0001\u0000\u0000\u0000\u025b\u025c\n\u0007\u0000\u0000\u025c\u025d\u0005"+
		"\u0001\u0000\u0000\u025d\u0262\u0003p8\b\u025e\u025f\n\u0006\u0000\u0000"+
		"\u025f\u0260\u0005&\u0000\u0000\u0260\u0262\u0003p8\u0007\u0261\u025b"+
		"\u0001\u0000\u0000\u0000\u0261\u025e\u0001\u0000\u0000\u0000\u0262\u0265"+
		"\u0001\u0000\u0000\u0000\u0263\u0261\u0001\u0000\u0000\u0000\u0263\u0264"+
		"\u0001\u0000\u0000\u0000\u0264q\u0001\u0000\u0000\u0000\u0265\u0263\u0001"+
		"\u0000\u0000\u0000\u0266\u0267\u0007\u0002\u0000\u0000\u0267s\u0001\u0000"+
		"\u0000\u0000\u0268\u026d\u0003v;\u0000\u0269\u026d\u0003z=\u0000\u026a"+
		"\u026d\u0003\u0080@\u0000\u026b\u026d\u0005%\u0000\u0000\u026c\u0268\u0001"+
		"\u0000\u0000\u0000\u026c\u0269\u0001\u0000\u0000\u0000\u026c\u026a\u0001"+
		"\u0000\u0000\u0000\u026c\u026b\u0001\u0000\u0000\u0000\u026du\u0001\u0000"+
		"\u0000\u0000\u026e\u026f\u0005c\u0000\u0000\u026fw\u0001\u0000\u0000\u0000"+
		"\u0270\u0271\u0005b\u0000\u0000\u0271y\u0001\u0000\u0000\u0000\u0272\u0275"+
		"\u0003|>\u0000\u0273\u0275\u0003~?\u0000\u0274\u0272\u0001\u0000\u0000"+
		"\u0000\u0274\u0273\u0001\u0000\u0000\u0000\u0275{\u0001\u0000\u0000\u0000"+
		"\u0276\u0278\u0005O\u0000\u0000\u0277\u0276\u0001\u0000\u0000\u0000\u0277"+
		"\u0278\u0001\u0000\u0000\u0000\u0278\u0279\u0001\u0000\u0000\u0000\u0279"+
		"\u027a\u0005d\u0000\u0000\u027a}\u0001\u0000\u0000\u0000\u027b\u027c\u0005"+
		"e\u0000\u0000\u027c\u007f\u0001\u0000\u0000\u0000\u027d\u027e\u0007\u0003"+
		"\u0000\u0000\u027e\u0081\u0001\u0000\u0000\u0000\u027f\u0280\u0003|>\u0000"+
		"\u0280\u0083\u0001\u0000\u0000\u0000\u0281\u0282\u0003|>\u0000\u0282\u0085"+
		"\u0001\u0000\u0000\u0000\u0283\u0284\u0003|>\u0000\u0284\u0087\u0001\u0000"+
		"\u0000\u0000\u0285\u0286\u0005\u0006\u0000\u0000\u0286\u0287\u0005G\u0000"+
		"\u0000\u0287\u0288\u0003\u0084B\u0000\u0288\u0289\u0005H\u0000\u0000\u0289"+
		"\u02ac\u0001\u0000\u0000\u0000\u028a\u028f\u0005?\u0000\u0000\u028b\u028c"+
		"\u0005G\u0000\u0000\u028c\u028d\u0003\u0084B\u0000\u028d\u028e\u0005H"+
		"\u0000\u0000\u028e\u0290\u0001\u0000\u0000\u0000\u028f\u028b\u0001\u0000"+
		"\u0000\u0000\u028f\u0290\u0001\u0000\u0000\u0000\u0290\u02ac\u0001\u0000"+
		"\u0000\u0000\u0291\u0296\u0005\t\u0000\u0000\u0292\u0293\u0003\u0084B"+
		"\u0000\u0293\u0294\u0005E\u0000\u0000\u0294\u0295\u0003\u0086C\u0000\u0295"+
		"\u0297\u0001\u0000\u0000\u0000\u0296\u0292\u0001\u0000\u0000\u0000\u0296"+
		"\u0297\u0001\u0000\u0000\u0000\u0297\u02ac\u0001\u0000\u0000\u0000\u0298"+
		"\u02ac\u00050\u0000\u0000\u0299\u02ac\u0005*\u0000\u0000\u029a\u02ac\u0005"+
		"\u0016\u0000\u0000\u029b\u02ac\u0005\u0017\u0000\u0000\u029c\u02ac\u0005"+
		"\u0012\u0000\u0000\u029d\u02ac\u0005\u000e\u0000\u0000\u029e\u02ac\u0005"+
		"/\u0000\u0000\u029f\u02ac\u0005\b\u0000\u0000\u02a0\u02ac\u0005.\u0000"+
		"\u0000\u02a1\u02a2\u0005\u0005\u0000\u0000\u02a2\u02a3\u0005G\u0000\u0000"+
		"\u02a3\u02a4\u0003\u0084B\u0000\u02a4\u02a5\u0005H\u0000\u0000\u02a5\u02ac"+
		"\u0001\u0000\u0000\u0000\u02a6\u02a7\u0005>\u0000\u0000\u02a7\u02a8\u0005"+
		"G\u0000\u0000\u02a8\u02a9\u0003\u0084B\u0000\u02a9\u02aa\u0005H\u0000"+
		"\u0000\u02aa\u02ac\u0001\u0000\u0000\u0000\u02ab\u0285\u0001\u0000\u0000"+
		"\u0000\u02ab\u028a\u0001\u0000\u0000\u0000\u02ab\u0291\u0001\u0000\u0000"+
		"\u0000\u02ab\u0298\u0001\u0000\u0000\u0000\u02ab\u0299\u0001\u0000\u0000"+
		"\u0000\u02ab\u029a\u0001\u0000\u0000\u0000\u02ab\u029b\u0001\u0000\u0000"+
		"\u0000\u02ab\u029c\u0001\u0000\u0000\u0000\u02ab\u029d\u0001\u0000\u0000"+
		"\u0000\u02ab\u029e\u0001\u0000\u0000\u0000\u02ab\u029f\u0001\u0000\u0000"+
		"\u0000\u02ab\u02a0\u0001\u0000\u0000\u0000\u02ab\u02a1\u0001\u0000\u0000"+
		"\u0000\u02ab\u02a6\u0001\u0000\u0000\u0000\u02ac\u0089\u0001\u0000\u0000"+
		"\u0000\u02ad\u02ae\u0007\u0004\u0000\u0000\u02ae\u008b\u0001\u0000\u0000"+
		"\u0000C\u008f\u0096\u009a\u00a0\u00a5\u00ab\u00b2\u00bf\u00ca\u00d7\u00e5"+
		"\u00f0\u00fd\u0105\u010d\u0115\u011d\u0122\u012a\u012d\u0144\u0156\u0159"+
		"\u015c\u0164\u016d\u0176\u0179\u0183\u0187\u018a\u018e\u0191\u0193\u0198"+
		"\u01a0\u01a5\u01a9\u01af\u01bd\u01c6\u01c9\u01cd\u01d1\u01d6\u01de\u01e5"+
		"\u01e7\u01eb\u01f2\u0200\u0206\u0223\u022b\u023c\u0243\u0249\u0252\u0259"+
		"\u0261\u0263\u026c\u0274\u0277\u028f\u0296\u02ab";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}