// Generated from /Users/leojie/other_project/HydraQL/antlr/test/HydraQLParser.g4 by ANTLR 4.12.0
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
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

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
		REAL_LITERAL=96, CHAR_LITERAL=97, SHOW=98, IF=99, TABLESAMPLE=100, BIGINT=101;
	public static final int
		RULE_hql = 0, RULE_batch = 1, RULE_sql_command = 2, RULE_ddl_command = 3, 
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
		RULE_alias = 39, RULE_name = 40, RULE_table_spec = 41, RULE_aliased_table_ref = 42, 
		RULE_table_alias = 43, RULE_positive_decimal = 44, RULE_namespace_name = 45, 
		RULE_table_name = 46, RULE_column_def = 47, RULE_column_ref = 48, RULE_column_name = 49, 
		RULE_data_type = 50, RULE_expression = 51, RULE_comp_op = 52, RULE_expression_list = 53, 
		RULE_variable = 54, RULE_literal = 55, RULE_string = 56, RULE_varString = 57, 
		RULE_numeric = 58, RULE_integer = 59, RULE_decimal = 60, RULE_true_false = 61, 
		RULE_fun_name = 62, RULE_dimension_int = 63, RULE_precision_int = 64, 
		RULE_scale_int = 65, RULE_sql_data_type = 66, RULE_hbase_data_type = 67;
	private static String[] makeRuleNames() {
		return new String[] {
			"hql", "batch", "sql_command", "ddl_command", "dml_command", "create_virtual_table_command", 
			"table_options", "options_", "option", "drop_table_command", "show_tables_command", 
			"show_table_command", "if_exists", "if_not_exists", "table_ref", "upsert_values_command", 
			"insert_values", "column_ref_list", "column_def_list", "delete_command", 
			"timestamp_range_clause", "tsExp", "timestamp", "gtOper", "leOper", "versions_clause", 
			"limit_clause", "where_clause", "select_command", "select_statement", 
			"number", "select_expression", "functionCall", "funcName", "functionArgs", 
			"fullColumnName", "family_name", "quoted_name", "column_alias", "alias", 
			"name", "table_spec", "aliased_table_ref", "table_alias", "positive_decimal", 
			"namespace_name", "table_name", "column_def", "column_ref", "column_name", 
			"data_type", "expression", "comp_op", "expression_list", "variable", 
			"literal", "string", "varString", "numeric", "integer", "decimal", "true_false", 
			"fun_name", "dimension_int", "precision_int", "scale_int", "sql_data_type", 
			"hbase_data_type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'AND'", "'ARRAY'", "'AS'", "'BETWEEN'", "'BINARY'", "'CHAR'", 
			"'CREATE'", "'DATE'", "'DECIMAL'", "'DEFAULT'", "'DELETE'", "'DISABLE'", 
			"'DISTINCT'", "'DOUBLE'", "'DROP'", "'EXISTS'", "'FALSE'", "'FLOAT'", 
			"'FROM'", "'IN'", "'INTEGER'", "'INTO'", "'IS'", "'KEY'", "'LIKE'", "'VERSIONS'", 
			"'STARTTS'", "'ENDTS'", "'TS'", "'LIMIT'", "'NOT'", "'NULL'", "'OR'", 
			"'PRIMARY'", "'SELECT'", "'SMALLINT'", "'VIRTUAL'", "'TABLE'", "'TABLES'", 
			"'TIME'", "'TIMESTAMP'", "'TINYINT'", "'TRUE'", "'UNSIGNED_DATE'", "'UNSIGNED_DOUBLE'", 
			"'UNSIGNED_FLOAT'", "'UNSIGNED_INT'", "'UNSIGNED_LONG'", "'UNSIGNED_SMALLINT'", 
			"'UNSIGNED_TIME'", "'UNSIGNED_TIMESTAMP'", "'UNSIGNED_TINYINT'", "'UPDATE'", 
			"'UPSERT'", "'VALUES'", "'VARBINARY'", "'VARCHAR'", "'WHERE'", "'WITH'", 
			"'PROPERTIES'", "';'", "':'", "','", "'.'", "'('", "')'", "'${'", "'}'", 
			"'*'", "'/'", "'%'", "'+'", "'-'", "'||'", "'['", "']'", "'='", "'<>'", 
			"'!='", "'>'", "'>='", "'<'", "'<='", "'?'", null, null, null, "'/*+'", 
			"'*/'", null, "'''"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AND", "ARRAY", "AS", "BETWEEN", "BINARY", "CHAR", "CREATE", "DATE", 
			"DECIMAL", "DEFAULT", "DELETE", "DISABLE", "DISTINCT", "DOUBLE", "DROP", 
			"EXISTS", "FALSE", "FLOAT", "FROM", "IN", "INTEGER", "INTO", "IS", "KEY", 
			"LIKE", "VERSIONS", "STARTTS", "ENDTS", "TS", "LIMIT", "NOT", "NULL_", 
			"OR", "PRIMARY", "SELECT", "SMALLINT", "VIRTUAL", "TABLE", "TABLES", 
			"TIME", "TIMESTAMP", "TINYINT", "TRUE", "UNSIGNED_DATE", "UNSIGNED_DOUBLE", 
			"UNSIGNED_FLOAT", "UNSIGNED_INT", "UNSIGNED_LONG", "UNSIGNED_SMALLINT", 
			"UNSIGNED_TIME", "UNSIGNED_TIMESTAMP", "UNSIGNED_TINYINT", "UPDATE", 
			"UPSERT", "VALUES", "VARBINARY", "VARCHAR", "WHERE", "WITH", "PROPERTIES", 
			"SEMI", "COLON", "COMMA", "DOT", "LP", "RP", "VAR_LP", "VAR_RP", "STAR", 
			"DIV", "MOD", "PLUS", "MINUS", "PIPEPIPE", "LSB", "RSC", "EQ", "NE", 
			"NE2", "GT", "GE", "LT", "LE", "QM", "WHITE_SPACE", "SQL_COMMENT", "LINE_COMMENT", 
			"HINT_START", "HINT_END", "DOUBLE_QUOTE_ID", "SINGLE_QUOTE", "ID", "STRING_LITERAL", 
			"DECIMAL_LITERAL", "FLOAT_LITERAL", "REAL_LITERAL", "CHAR_LITERAL", "SHOW", 
			"IF", "TABLESAMPLE", "BIGINT"
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
	public static class HqlContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(HydraQLParser.EOF, 0); }
		public List<BatchContext> batch() {
			return getRuleContexts(BatchContext.class);
		}
		public BatchContext batch(int i) {
			return getRuleContext(BatchContext.class,i);
		}
		public HqlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hql; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterHql(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitHql(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitHql(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HqlContext hql() throws RecognitionException {
		HqlContext _localctx = new HqlContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_hql);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18014432869255296L) != 0) || _la==SHOW) {
				{
				{
				setState(136);
				batch();
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(142);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterBatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitBatch(this);
		}
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
			setState(144);
			sql_command();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(145);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSql_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSql_command(this);
		}
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
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case SHOW:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				ddl_command();
				}
				break;
			case DELETE:
			case SELECT:
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterDdl_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitDdl_command(this);
		}
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
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				create_virtual_table_command();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				show_tables_command();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(154);
				show_table_command();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterDml_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitDml_command(this);
		}
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
			setState(161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				select_command();
				}
				break;
			case UPSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				upsert_values_command();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterCreate_virtual_table_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitCreate_virtual_table_command(this);
		}
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
			setState(163);
			match(CREATE);
			setState(164);
			match(VIRTUAL);
			setState(165);
			match(TABLE);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(166);
				if_not_exists();
				}
			}

			setState(169);
			table_ref();
			setState(170);
			match(LP);
			setState(171);
			column_def_list();
			setState(172);
			match(RP);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(173);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTable_options(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTable_options(this);
		}
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
			setState(176);
			match(WITH);
			setState(177);
			match(PROPERTIES);
			setState(178);
			match(LP);
			setState(179);
			options_();
			setState(180);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterOptions_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitOptions_(this);
		}
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
			setState(182);
			option();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(183);
				match(COMMA);
				setState(184);
				option();
				}
				}
				setState(189);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitOption(this);
		}
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
			setState(190);
			name();
			setState(191);
			match(EQ);
			setState(192);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterDrop_table_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitDrop_table_command(this);
		}
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
			setState(194);
			match(DROP);
			setState(195);
			match(VIRTUAL);
			setState(196);
			match(TABLE);
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(197);
				if_exists();
				}
			}

			setState(200);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterShow_tables_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitShow_tables_command(this);
		}
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
			setState(202);
			match(SHOW);
			setState(203);
			match(VIRTUAL);
			setState(204);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterShow_table_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitShow_table_command(this);
		}
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
			setState(206);
			match(SHOW);
			setState(207);
			match(CREATE);
			setState(208);
			match(VIRTUAL);
			setState(209);
			match(TABLE);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(210);
				if_exists();
				}
			}

			setState(213);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterIf_exists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitIf_exists(this);
		}
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
			setState(215);
			match(IF);
			setState(216);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterIf_not_exists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitIf_not_exists(this);
		}
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
			setState(218);
			match(IF);
			setState(219);
			match(NOT);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTable_ref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTable_ref(this);
		}
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
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(222);
				namespace_name();
				setState(223);
				match(COLON);
				}
				break;
			}
			setState(227);
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
		public List<TerminalNode> RP() { return getTokens(HydraQLParser.RP); }
		public TerminalNode RP(int i) {
			return getToken(HydraQLParser.RP, i);
		}
		public Column_ref_listContext column_ref_list() {
			return getRuleContext(Column_ref_listContext.class,0);
		}
		public List<Insert_valuesContext> insert_values() {
			return getRuleContexts(Insert_valuesContext.class);
		}
		public Insert_valuesContext insert_values(int i) {
			return getRuleContext(Insert_valuesContext.class,i);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterUpsert_values_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitUpsert_values_command(this);
		}
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
			setState(229);
			match(UPSERT);
			setState(230);
			match(INTO);
			setState(231);
			table_ref();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(232);
				match(LP);
				setState(233);
				column_ref_list();
				setState(234);
				match(RP);
				}
			}

			setState(238);
			match(VALUES);
			setState(239);
			match(LP);
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8800388120576L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 3145729L) != 0)) {
				{
				setState(240);
				insert_values();
				}
			}

			setState(243);
			match(RP);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(244);
				match(COMMA);
				setState(245);
				match(LP);
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8800388120576L) != 0) || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 3145729L) != 0)) {
					{
					setState(246);
					insert_values();
					}
				}

				setState(249);
				match(RP);
				}
				}
				setState(254);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterInsert_values(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitInsert_values(this);
		}
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
			setState(255);
			literal();
			setState(260);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(256);
				match(COMMA);
				setState(257);
				literal();
				}
				}
				setState(262);
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
	public static class Column_ref_listContext extends ParserRuleContext {
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
		public Column_ref_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_ref_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterColumn_ref_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitColumn_ref_list(this);
		}
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
			setState(263);
			column_ref();
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(264);
				match(COMMA);
				setState(265);
				column_ref();
				}
				}
				setState(270);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterColumn_def_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitColumn_def_list(this);
		}
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
			setState(271);
			column_def();
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(272);
				match(COMMA);
				setState(273);
				column_def();
				}
				}
				setState(278);
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
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterDelete_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitDelete_command(this);
		}
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
			setState(279);
			match(DELETE);
			setState(280);
			select_expression();
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(281);
				match(COMMA);
				setState(282);
				select_expression();
				}
				}
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(288);
			match(FROM);
			setState(289);
			table_name();
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(290);
				where_clause();
				}
			}

			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 27)) & ~0x3f) == 0 && ((1L << (_la - 27)) & 274877906951L) != 0)) {
				{
				setState(293);
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
	public static class TsequalContext extends Timestamp_range_clauseContext {
		public TerminalNode TS() { return getToken(HydraQLParser.TS, 0); }
		public TerminalNode EQ() { return getToken(HydraQLParser.EQ, 0); }
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public TsequalContext(Timestamp_range_clauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTsequal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTsequal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsequal(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTsrange_start(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTsrange_start(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsrange_start(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTsrange_end(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTsrange_end(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTsrange_end(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTsrange_startAndEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTsrange_startAndEnd(this);
		}
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
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LP:
				_localctx = new Tsrange_startAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				match(LP);
				setState(297);
				match(STARTTS);
				setState(298);
				gtOper();
				setState(299);
				tsExp();
				setState(300);
				match(COMMA);
				setState(301);
				match(ENDTS);
				setState(302);
				leOper();
				setState(303);
				tsExp();
				setState(304);
				match(RP);
				}
				break;
			case STARTTS:
				_localctx = new Tsrange_startContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				match(STARTTS);
				setState(307);
				gtOper();
				setState(308);
				tsExp();
				}
				break;
			case ENDTS:
				_localctx = new Tsrange_endContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(310);
				match(ENDTS);
				setState(311);
				leOper();
				setState(312);
				tsExp();
				}
				break;
			case TS:
				_localctx = new TsequalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(314);
				match(TS);
				setState(315);
				match(EQ);
				setState(316);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTsExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTsExp(this);
		}
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
			setState(319);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTimestamp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTimestamp(this);
		}
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
			setState(321);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterGtOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitGtOper(this);
		}
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
			setState(323);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterLeOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitLeOper(this);
		}
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
			setState(325);
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
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Versions_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versions_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterVersions_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitVersions_clause(this);
		}
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
			setState(327);
			match(VERSIONS);
			setState(328);
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

	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterLimit_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitLimit_clause(this);
		}
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
			setState(330);
			match(LIMIT);
			setState(331);
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

	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterWhere_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitWhere_clause(this);
		}
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
			setState(333);
			match(WHERE);
			setState(334);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSelect_command(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSelect_command(this);
		}
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
			setState(336);
			select_statement();
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VERSIONS) {
				{
				setState(337);
				versions_clause();
				}
			}

			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 27)) & ~0x3f) == 0 && ((1L << (_la - 27)) & 274877906951L) != 0)) {
				{
				setState(340);
				timestamp_range_clause();
				}
			}

			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(343);
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
		public List<TerminalNode> COMMA() { return getTokens(HydraQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HydraQLParser.COMMA, i);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public Select_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSelect_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSelect_statement(this);
		}
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
			setState(346);
			match(SELECT);
			setState(347);
			select_expression();
			setState(352);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(348);
				match(COMMA);
				setState(349);
				select_expression();
				}
				}
				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(355);
			match(FROM);
			setState(356);
			table_ref();
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(357);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(HydraQLParser.DECIMAL_LITERAL, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitNumber(this);
		}
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
			setState(360);
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
	@SuppressWarnings("CheckReturnValue")
	public static class SelectAllFamilyAndColContext extends Select_expressionContext {
		public TerminalNode STAR() { return getToken(HydraQLParser.STAR, 0); }
		public SelectAllFamilyAndColContext(Select_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSelectAllFamilyAndCol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSelectAllFamilyAndCol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectAllFamilyAndCol(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelectOneFamilyAllColContext extends Select_expressionContext {
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(HydraQLParser.DOT, 0); }
		public TerminalNode STAR() { return getToken(HydraQLParser.STAR, 0); }
		public SelectOneFamilyAllColContext(Select_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSelectOneFamilyAllCol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSelectOneFamilyAllCol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectOneFamilyAllCol(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSelectWithFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSelectWithFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSelectWithFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelectFamilyAndColContext extends Select_expressionContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Family_nameContext family_name() {
			return getRuleContext(Family_nameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(HydraQLParser.COLON, 0); }
		public SelectFamilyAndColContext(Select_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSelectFamilyAndCol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSelectFamilyAndCol(this);
		}
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
			setState(380);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new SelectAllFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				match(STAR);
				}
				break;
			case 2:
				_localctx = new SelectOneFamilyAllColContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(363);
				family_name();
				setState(364);
				match(DOT);
				setState(365);
				match(STAR);
				}
				break;
			case 3:
				_localctx = new SelectFamilyAndColContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(370);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(367);
					family_name();
					setState(368);
					match(COLON);
					}
					break;
				}
				setState(372);
				column_name();
				}
				break;
			case 4:
				_localctx = new SelectWithFuncCallContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(373);
				functionCall();
				setState(378);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
					{
					setState(375);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(374);
						match(AS);
						}
					}

					setState(377);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterUdfFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitUdfFunctionCall(this);
		}
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
			setState(382);
			funcName();
			setState(383);
			match(LP);
			setState(385);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8800388120576L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 973078789L) != 0)) {
				{
				setState(384);
				functionArgs();
				}
			}

			setState(387);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterFuncName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitFuncName(this);
		}
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
			setState(389);
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
		public FunctionArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterFunctionArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitFunctionArgs(this);
		}
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
			setState(393);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(391);
				fullColumnName();
				}
				break;
			case 2:
				{
				setState(392);
				expression(0);
				}
				break;
			}
			setState(402);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(395);
				match(COMMA);
				setState(398);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(396);
					fullColumnName();
					}
					break;
				case 2:
					{
					setState(397);
					expression(0);
					}
					break;
				}
				}
				}
				setState(404);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterFullColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitFullColumnName(this);
		}
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
			setState(408);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(405);
				family_name();
				setState(406);
				match(COLON);
				}
				break;
			}
			setState(410);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterFamily_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitFamily_name(this);
		}
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
	public static class Quoted_nameContext extends ParserRuleContext {
		public TerminalNode DOUBLE_QUOTE_ID() { return getToken(HydraQLParser.DOUBLE_QUOTE_ID, 0); }
		public Quoted_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterQuoted_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitQuoted_name(this);
		}
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
			setState(414);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterColumn_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitColumn_alias(this);
		}
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
			setState(416);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitAlias(this);
		}
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
			setState(418);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitName(this);
		}
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
			setState(422);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(420);
				match(ID);
				}
				break;
			case DOUBLE_QUOTE_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(421);
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
	public static class Table_specContext extends ParserRuleContext {
		public Aliased_table_refContext aliased_table_ref() {
			return getRuleContext(Aliased_table_refContext.class,0);
		}
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public Select_commandContext select_command() {
			return getRuleContext(Select_commandContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public TerminalNode AS() { return getToken(HydraQLParser.AS, 0); }
		public Table_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTable_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTable_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTable_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_specContext table_spec() throws RecognitionException {
		Table_specContext _localctx = new Table_specContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_table_spec);
		int _la;
		try {
			setState(434);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOUBLE_QUOTE_ID:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(424);
				aliased_table_ref();
				}
				break;
			case LP:
				enterOuterAlt(_localctx, 2);
				{
				setState(425);
				match(LP);
				setState(426);
				select_command();
				setState(427);
				match(RP);
				setState(432);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
					{
					setState(429);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(428);
						match(AS);
						}
					}

					setState(431);
					table_alias();
					}
				}

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
	public static class Aliased_table_refContext extends ParserRuleContext {
		public Table_refContext table_ref() {
			return getRuleContext(Table_refContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public List<TerminalNode> LP() { return getTokens(HydraQLParser.LP); }
		public TerminalNode LP(int i) {
			return getToken(HydraQLParser.LP, i);
		}
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public List<TerminalNode> RP() { return getTokens(HydraQLParser.RP); }
		public TerminalNode RP(int i) {
			return getToken(HydraQLParser.RP, i);
		}
		public TerminalNode TABLESAMPLE() { return getToken(HydraQLParser.TABLESAMPLE, 0); }
		public Positive_decimalContext positive_decimal() {
			return getRuleContext(Positive_decimalContext.class,0);
		}
		public TerminalNode AS() { return getToken(HydraQLParser.AS, 0); }
		public TerminalNode COMMA() { return getToken(HydraQLParser.COMMA, 0); }
		public Aliased_table_refContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliased_table_ref; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterAliased_table_ref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitAliased_table_ref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitAliased_table_ref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aliased_table_refContext aliased_table_ref() throws RecognitionException {
		Aliased_table_refContext _localctx = new Aliased_table_refContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_aliased_table_ref);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			table_ref();
			setState(441);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || _la==DOUBLE_QUOTE_ID || _la==ID) {
				{
				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(437);
					match(AS);
					}
				}

				setState(440);
				table_alias();
				}
			}

			setState(451);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LP) {
				{
				setState(443);
				match(LP);
				setState(444);
				column_def();
				setState(447);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(445);
					match(COMMA);
					setState(446);
					column_def();
					}
				}

				setState(449);
				match(RP);
				}
			}

			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TABLESAMPLE) {
				{
				setState(453);
				match(TABLESAMPLE);
				setState(454);
				match(LP);
				setState(455);
				positive_decimal();
				setState(456);
				match(RP);
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
	public static class Table_aliasContext extends ParserRuleContext {
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public Table_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTable_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTable_alias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTable_alias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_aliasContext table_alias() throws RecognitionException {
		Table_aliasContext _localctx = new Table_aliasContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_table_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
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
	public static class Positive_decimalContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public Positive_decimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_positive_decimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterPositive_decimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitPositive_decimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitPositive_decimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Positive_decimalContext positive_decimal() throws RecognitionException {
		Positive_decimalContext _localctx = new Positive_decimalContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_positive_decimal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterNamespace_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitNamespace_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitNamespace_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Namespace_nameContext namespace_name() throws RecognitionException {
		Namespace_nameContext _localctx = new Namespace_nameContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_namespace_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTable_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterColumn_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitColumn_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			column_ref();
			setState(469);
			data_type();
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT || _la==NULL_) {
				{
				setState(471);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(470);
					match(NOT);
					}
				}

				setState(473);
				match(NULL_);
				}
			}

			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(476);
				match(DEFAULT);
				setState(477);
				literal();
				}
			}

			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIMARY) {
				{
				setState(480);
				match(PRIMARY);
				setState(481);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterColumn_ref(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitColumn_ref(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_ref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_refContext column_ref() throws RecognitionException {
		Column_refContext _localctx = new Column_refContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_column_ref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(484);
				family_name();
				setState(485);
				match(COLON);
				}
				break;
			}
			setState(489);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitColumn_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterData_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitData_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitData_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Data_typeContext data_type() throws RecognitionException {
		Data_typeContext _localctx = new Data_typeContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495);
			_errHandler.sync(this);
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
				setState(493);
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
				setState(494);
				hbase_data_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(504);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARRAY) {
				{
				setState(497);
				match(ARRAY);
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LSB) {
					{
					setState(498);
					match(LSB);
					setState(499);
					dimension_int();
					setState(500);
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
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionColName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionColName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionColName(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionLikeOrNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionLikeOrNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionLikeOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionCompOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionCompOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionCompOp(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionIsNullOrNotContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(HydraQLParser.IS, 0); }
		public TerminalNode NULL_() { return getToken(HydraQLParser.NULL_, 0); }
		public TerminalNode NOT() { return getToken(HydraQLParser.NOT, 0); }
		public ExpressionIsNullOrNotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionIsNullOrNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionIsNullOrNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionIsNullOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionConstantValueContext extends ExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExpressionConstantValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionConstantValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionConstantValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionConstantValue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionBetweenOrNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionBetweenOrNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionBetweenOrNot(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionWrapperContext extends ExpressionContext {
		public TerminalNode LP() { return getToken(HydraQLParser.LP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RP() { return getToken(HydraQLParser.RP, 0); }
		public ExpressionWrapperContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionWrapper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionWrapper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionWrapper(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionIn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionIn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionIn(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionVariableContext extends ExpressionContext {
		public TerminalNode VAR_LP() { return getToken(HydraQLParser.VAR_LP, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode VAR_RP() { return getToken(HydraQLParser.VAR_RP, 0); }
		public ExpressionVariableContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpressionVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionWithFuncContext extends ExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public ExpressionWithFuncContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpressionWithFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpressionWithFunc(this);
		}
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
		int _startState = 102;
		enterRecursionRule(_localctx, 102, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				_localctx = new ExpressionConstantValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(507);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExpressionVariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(508);
				match(VAR_LP);
				setState(509);
				variable();
				setState(510);
				match(VAR_RP);
				}
				break;
			case 3:
				{
				_localctx = new ExpressionColNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(515);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
				case 1:
					{
					setState(512);
					family_name();
					setState(513);
					match(COLON);
					}
					break;
				}
				setState(517);
				column_name();
				}
				break;
			case 4:
				{
				_localctx = new ExpressionWithFuncContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(518);
				functionCall();
				}
				break;
			case 5:
				{
				_localctx = new ExpressionWrapperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(519);
				match(LP);
				setState(520);
				expression(0);
				setState(521);
				match(RP);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(568);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(566);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionCompOpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(525);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(526);
						comp_op();
						setState(527);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionLikeOrNotContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(529);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(533);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case LIKE:
							{
							setState(530);
							match(LIKE);
							}
							break;
						case NOT:
							{
							setState(531);
							match(NOT);
							setState(532);
							match(LIKE);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(535);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionBetweenOrNotContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(536);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(538);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(537);
							match(NOT);
							}
						}

						setState(540);
						match(BETWEEN);
						setState(541);
						expression(0);
						setState(542);
						match(AND);
						setState(543);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionAndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(545);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(546);
						match(AND);
						setState(547);
						expression(3);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionOrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(548);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(549);
						match(OR);
						setState(550);
						expression(2);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionIsNullOrNotContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(551);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(552);
						match(IS);
						setState(554);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(553);
							match(NOT);
							}
						}

						setState(556);
						match(NULL_);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionInContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(557);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(559);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(558);
							match(NOT);
							}
						}

						setState(561);
						match(IN);
						setState(562);
						match(LP);
						setState(563);
						expression_list();
						setState(564);
						match(RP);
						}
						break;
					}
					} 
				}
				setState(570);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterComp_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitComp_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitComp_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_opContext comp_op() throws RecognitionException {
		Comp_opContext _localctx = new Comp_opContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_comp_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			_la = _input.LA(1);
			if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & 127L) != 0)) ) {
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterExpression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitExpression_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitExpression_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(573);
			expression(0);
			setState(578);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(574);
				match(COMMA);
				setState(575);
				expression(0);
				}
				}
				setState(580);
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
	public static class VariableContext extends ParserRuleContext {
		public VarStringContext varString() {
			return getRuleContext(VarStringContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_literal);
		try {
			setState(587);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(583);
				string();
				}
				break;
			case MINUS:
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(584);
				numeric();
				}
				break;
			case FALSE:
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(585);
				true_false();
				}
				break;
			case NULL_:
				enterOuterAlt(_localctx, 4);
				{
				setState(586);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(589);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterVarString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitVarString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitVarString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarStringContext varString() throws RecognitionException {
		VarStringContext _localctx = new VarStringContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_varString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(591);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterNumeric(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitNumeric(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitNumeric(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericContext numeric() throws RecognitionException {
		NumericContext _localctx = new NumericContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_numeric);
		try {
			setState(595);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(593);
				integer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(594);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(HydraQLParser.DECIMAL_LITERAL, 0); }
		public TerminalNode MINUS() { return getToken(HydraQLParser.MINUS, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(598);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(597);
				match(MINUS);
				}
			}

			setState(600);
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
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(HydraQLParser.MINUS, 0); }
		public TerminalNode DOT() { return getToken(HydraQLParser.DOT, 0); }
		public DecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalContext decimal() throws RecognitionException {
		DecimalContext _localctx = new DecimalContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_decimal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(602);
				match(MINUS);
				}
			}

			setState(605);
			number();
			setState(608);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				{
				setState(606);
				match(DOT);
				setState(607);
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

	@SuppressWarnings("CheckReturnValue")
	public static class True_falseContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(HydraQLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(HydraQLParser.FALSE, 0); }
		public True_falseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterTrue_false(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitTrue_false(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitTrue_false(this);
			else return visitor.visitChildren(this);
		}
	}

	public final True_falseContext true_false() throws RecognitionException {
		True_falseContext _localctx = new True_falseContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_true_false);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(610);
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
	public static class Fun_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HydraQLParser.ID, 0); }
		public Fun_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterFun_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitFun_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitFun_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fun_nameContext fun_name() throws RecognitionException {
		Fun_nameContext _localctx = new Fun_nameContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_fun_name);
		try {
			setState(614);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(612);
				match(ID);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
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
	public static class Dimension_intContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public Dimension_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimension_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterDimension_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitDimension_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitDimension_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Dimension_intContext dimension_int() throws RecognitionException {
		Dimension_intContext _localctx = new Dimension_intContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_dimension_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterPrecision_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitPrecision_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitPrecision_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Precision_intContext precision_int() throws RecognitionException {
		Precision_intContext _localctx = new Precision_intContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_precision_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(618);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterScale_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitScale_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitScale_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Scale_intContext scale_int() throws RecognitionException {
		Scale_intContext _localctx = new Scale_intContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_scale_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(620);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterSql_data_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitSql_data_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitSql_data_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_data_typeContext sql_data_type() throws RecognitionException {
		Sql_data_typeContext _localctx = new Sql_data_typeContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_sql_data_type);
		int _la;
		try {
			setState(660);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CHAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(622);
				match(CHAR);
				setState(623);
				match(LP);
				setState(624);
				precision_int();
				setState(625);
				match(RP);
				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(627);
				match(VARCHAR);
				setState(632);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LP) {
					{
					setState(628);
					match(LP);
					setState(629);
					precision_int();
					setState(630);
					match(RP);
					}
				}

				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(634);
				match(DECIMAL);
				setState(639);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==DECIMAL_LITERAL) {
					{
					setState(635);
					precision_int();
					setState(636);
					match(COMMA);
					setState(637);
					scale_int();
					}
				}

				}
				break;
			case TINYINT:
				enterOuterAlt(_localctx, 4);
				{
				setState(641);
				match(TINYINT);
				}
				break;
			case SMALLINT:
				enterOuterAlt(_localctx, 5);
				{
				setState(642);
				match(SMALLINT);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 6);
				{
				setState(643);
				match(INTEGER);
				}
				break;
			case BIGINT:
				enterOuterAlt(_localctx, 7);
				{
				setState(644);
				match(BIGINT);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 8);
				{
				setState(645);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 9);
				{
				setState(646);
				match(DOUBLE);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 10);
				{
				setState(647);
				match(TIMESTAMP);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 11);
				{
				setState(648);
				match(DATE);
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 12);
				{
				setState(649);
				match(TIME);
				}
				break;
			case BINARY:
				enterOuterAlt(_localctx, 13);
				{
				setState(650);
				match(BINARY);
				setState(651);
				match(LP);
				setState(652);
				precision_int();
				setState(653);
				match(RP);
				}
				break;
			case VARBINARY:
				enterOuterAlt(_localctx, 14);
				{
				setState(655);
				match(VARBINARY);
				setState(656);
				match(LP);
				setState(657);
				precision_int();
				setState(658);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).enterHbase_data_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HydraQLParserListener ) ((HydraQLParserListener)listener).exitHbase_data_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HydraQLParserVisitor ) return ((HydraQLParserVisitor<? extends T>)visitor).visitHbase_data_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Hbase_data_typeContext hbase_data_type() throws RecognitionException {
		Hbase_data_typeContext _localctx = new Hbase_data_typeContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_hbase_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(662);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8989607068696576L) != 0)) ) {
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
		case 51:
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
		"\u0004\u0001e\u0299\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0001\u0000\u0005\u0000\u008a\b\u0000"+
		"\n\u0000\f\u0000\u008d\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u0093\b\u0001\u0001\u0002\u0001\u0002\u0003\u0002\u0097"+
		"\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u009d"+
		"\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00a2\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00a8\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"\u00af\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00ba\b\u0007"+
		"\n\u0007\f\u0007\u00bd\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0003\t\u00c7\b\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u00d4\b\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00e2\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u00ed\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00f2"+
		"\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00f8"+
		"\b\u000f\u0001\u000f\u0005\u000f\u00fb\b\u000f\n\u000f\f\u000f\u00fe\t"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0103\b\u0010\n"+
		"\u0010\f\u0010\u0106\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005"+
		"\u0011\u010b\b\u0011\n\u0011\f\u0011\u010e\t\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0005\u0012\u0113\b\u0012\n\u0012\f\u0012\u0116\t\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u011c\b\u0013\n"+
		"\u0013\f\u0013\u011f\t\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u0124\b\u0013\u0001\u0013\u0003\u0013\u0127\b\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u013e\b\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0003\u001c"+
		"\u0153\b\u001c\u0001\u001c\u0003\u001c\u0156\b\u001c\u0001\u001c\u0003"+
		"\u001c\u0159\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0005"+
		"\u001d\u015f\b\u001d\n\u001d\f\u001d\u0162\t\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0003\u001d\u0167\b\u001d\u0001\u001e\u0001\u001e\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0003\u001f\u0173\b\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0003\u001f\u0178\b\u001f\u0001\u001f\u0003\u001f\u017b\b\u001f\u0003"+
		"\u001f\u017d\b\u001f\u0001 \u0001 \u0001 \u0003 \u0182\b \u0001 \u0001"+
		" \u0001!\u0001!\u0001\"\u0001\"\u0003\"\u018a\b\"\u0001\"\u0001\"\u0001"+
		"\"\u0003\"\u018f\b\"\u0005\"\u0191\b\"\n\"\f\"\u0194\t\"\u0001#\u0001"+
		"#\u0001#\u0003#\u0199\b#\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001"+
		"&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0003(\u01a7\b(\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0003)\u01ae\b)\u0001)\u0003)\u01b1\b)\u0003)\u01b3\b"+
		")\u0001*\u0001*\u0003*\u01b7\b*\u0001*\u0003*\u01ba\b*\u0001*\u0001*\u0001"+
		"*\u0001*\u0003*\u01c0\b*\u0001*\u0001*\u0003*\u01c4\b*\u0001*\u0001*\u0001"+
		"*\u0001*\u0001*\u0003*\u01cb\b*\u0001+\u0001+\u0001,\u0001,\u0001-\u0001"+
		"-\u0001.\u0001.\u0001/\u0001/\u0001/\u0003/\u01d8\b/\u0001/\u0003/\u01db"+
		"\b/\u0001/\u0001/\u0003/\u01df\b/\u0001/\u0001/\u0003/\u01e3\b/\u0001"+
		"0\u00010\u00010\u00030\u01e8\b0\u00010\u00010\u00011\u00011\u00012\u0001"+
		"2\u00032\u01f0\b2\u00012\u00012\u00012\u00012\u00012\u00032\u01f7\b2\u0003"+
		"2\u01f9\b2\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u0001"+
		"3\u00033\u0204\b3\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u020c"+
		"\b3\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u0216"+
		"\b3\u00013\u00013\u00013\u00033\u021b\b3\u00013\u00013\u00013\u00013\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u00013\u0003"+
		"3\u022b\b3\u00013\u00013\u00013\u00033\u0230\b3\u00013\u00013\u00013\u0001"+
		"3\u00013\u00053\u0237\b3\n3\f3\u023a\t3\u00014\u00014\u00015\u00015\u0001"+
		"5\u00055\u0241\b5\n5\f5\u0244\t5\u00016\u00016\u00017\u00017\u00017\u0001"+
		"7\u00037\u024c\b7\u00018\u00018\u00019\u00019\u0001:\u0001:\u0003:\u0254"+
		"\b:\u0001;\u0003;\u0257\b;\u0001;\u0001;\u0001<\u0003<\u025c\b<\u0001"+
		"<\u0001<\u0001<\u0003<\u0261\b<\u0001=\u0001=\u0001>\u0001>\u0003>\u0267"+
		"\b>\u0001?\u0001?\u0001@\u0001@\u0001A\u0001A\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0003B\u0279\bB\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0003B\u0280\bB\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"B\u0001B\u0001B\u0001B\u0001B\u0003B\u0295\bB\u0001C\u0001C\u0001C\u0000"+
		"\u0001fD\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080"+
		"\u0082\u0084\u0086\u0000\u0005\u0001\u0000PQ\u0001\u0000RS\u0001\u0000"+
		"MS\u0002\u0000\u0011\u0011++\u0001\u0000,4\u02b8\u0000\u008b\u0001\u0000"+
		"\u0000\u0000\u0002\u0090\u0001\u0000\u0000\u0000\u0004\u0096\u0001\u0000"+
		"\u0000\u0000\u0006\u009c\u0001\u0000\u0000\u0000\b\u00a1\u0001\u0000\u0000"+
		"\u0000\n\u00a3\u0001\u0000\u0000\u0000\f\u00b0\u0001\u0000\u0000\u0000"+
		"\u000e\u00b6\u0001\u0000\u0000\u0000\u0010\u00be\u0001\u0000\u0000\u0000"+
		"\u0012\u00c2\u0001\u0000\u0000\u0000\u0014\u00ca\u0001\u0000\u0000\u0000"+
		"\u0016\u00ce\u0001\u0000\u0000\u0000\u0018\u00d7\u0001\u0000\u0000\u0000"+
		"\u001a\u00da\u0001\u0000\u0000\u0000\u001c\u00e1\u0001\u0000\u0000\u0000"+
		"\u001e\u00e5\u0001\u0000\u0000\u0000 \u00ff\u0001\u0000\u0000\u0000\""+
		"\u0107\u0001\u0000\u0000\u0000$\u010f\u0001\u0000\u0000\u0000&\u0117\u0001"+
		"\u0000\u0000\u0000(\u013d\u0001\u0000\u0000\u0000*\u013f\u0001\u0000\u0000"+
		"\u0000,\u0141\u0001\u0000\u0000\u0000.\u0143\u0001\u0000\u0000\u00000"+
		"\u0145\u0001\u0000\u0000\u00002\u0147\u0001\u0000\u0000\u00004\u014a\u0001"+
		"\u0000\u0000\u00006\u014d\u0001\u0000\u0000\u00008\u0150\u0001\u0000\u0000"+
		"\u0000:\u015a\u0001\u0000\u0000\u0000<\u0168\u0001\u0000\u0000\u0000>"+
		"\u017c\u0001\u0000\u0000\u0000@\u017e\u0001\u0000\u0000\u0000B\u0185\u0001"+
		"\u0000\u0000\u0000D\u0189\u0001\u0000\u0000\u0000F\u0198\u0001\u0000\u0000"+
		"\u0000H\u019c\u0001\u0000\u0000\u0000J\u019e\u0001\u0000\u0000\u0000L"+
		"\u01a0\u0001\u0000\u0000\u0000N\u01a2\u0001\u0000\u0000\u0000P\u01a6\u0001"+
		"\u0000\u0000\u0000R\u01b2\u0001\u0000\u0000\u0000T\u01b4\u0001\u0000\u0000"+
		"\u0000V\u01cc\u0001\u0000\u0000\u0000X\u01ce\u0001\u0000\u0000\u0000Z"+
		"\u01d0\u0001\u0000\u0000\u0000\\\u01d2\u0001\u0000\u0000\u0000^\u01d4"+
		"\u0001\u0000\u0000\u0000`\u01e7\u0001\u0000\u0000\u0000b\u01eb\u0001\u0000"+
		"\u0000\u0000d\u01ef\u0001\u0000\u0000\u0000f\u020b\u0001\u0000\u0000\u0000"+
		"h\u023b\u0001\u0000\u0000\u0000j\u023d\u0001\u0000\u0000\u0000l\u0245"+
		"\u0001\u0000\u0000\u0000n\u024b\u0001\u0000\u0000\u0000p\u024d\u0001\u0000"+
		"\u0000\u0000r\u024f\u0001\u0000\u0000\u0000t\u0253\u0001\u0000\u0000\u0000"+
		"v\u0256\u0001\u0000\u0000\u0000x\u025b\u0001\u0000\u0000\u0000z\u0262"+
		"\u0001\u0000\u0000\u0000|\u0266\u0001\u0000\u0000\u0000~\u0268\u0001\u0000"+
		"\u0000\u0000\u0080\u026a\u0001\u0000\u0000\u0000\u0082\u026c\u0001\u0000"+
		"\u0000\u0000\u0084\u0294\u0001\u0000\u0000\u0000\u0086\u0296\u0001\u0000"+
		"\u0000\u0000\u0088\u008a\u0003\u0002\u0001\u0000\u0089\u0088\u0001\u0000"+
		"\u0000\u0000\u008a\u008d\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000"+
		"\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008e\u0001\u0000"+
		"\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u008f\u0005\u0000"+
		"\u0000\u0001\u008f\u0001\u0001\u0000\u0000\u0000\u0090\u0092\u0003\u0004"+
		"\u0002\u0000\u0091\u0093\u0005=\u0000\u0000\u0092\u0091\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0003\u0001\u0000\u0000"+
		"\u0000\u0094\u0097\u0003\u0006\u0003\u0000\u0095\u0097\u0003\b\u0004\u0000"+
		"\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000"+
		"\u0097\u0005\u0001\u0000\u0000\u0000\u0098\u009d\u0003\n\u0005\u0000\u0099"+
		"\u009d\u0003\u0014\n\u0000\u009a\u009d\u0003\u0016\u000b\u0000\u009b\u009d"+
		"\u0003\u0012\t\u0000\u009c\u0098\u0001\u0000\u0000\u0000\u009c\u0099\u0001"+
		"\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009b\u0001"+
		"\u0000\u0000\u0000\u009d\u0007\u0001\u0000\u0000\u0000\u009e\u00a2\u0003"+
		"8\u001c\u0000\u009f\u00a2\u0003\u001e\u000f\u0000\u00a0\u00a2\u0003&\u0013"+
		"\u0000\u00a1\u009e\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\t\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a4\u0005\u0007\u0000\u0000\u00a4\u00a5\u0005%\u0000\u0000\u00a5"+
		"\u00a7\u0005&\u0000\u0000\u00a6\u00a8\u0003\u001a\r\u0000\u00a7\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0003\u001c\u000e\u0000\u00aa\u00ab"+
		"\u0005A\u0000\u0000\u00ab\u00ac\u0003$\u0012\u0000\u00ac\u00ae\u0005B"+
		"\u0000\u0000\u00ad\u00af\u0003\f\u0006\u0000\u00ae\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u000b\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b1\u0005;\u0000\u0000\u00b1\u00b2\u0005<\u0000\u0000\u00b2"+
		"\u00b3\u0005A\u0000\u0000\u00b3\u00b4\u0003\u000e\u0007\u0000\u00b4\u00b5"+
		"\u0005B\u0000\u0000\u00b5\r\u0001\u0000\u0000\u0000\u00b6\u00bb\u0003"+
		"\u0010\b\u0000\u00b7\u00b8\u0005?\u0000\u0000\u00b8\u00ba\u0003\u0010"+
		"\b\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bd\u0001\u0000\u0000"+
		"\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bc\u000f\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0003P(\u0000\u00bf\u00c0\u0005M\u0000\u0000\u00c0"+
		"\u00c1\u0003n7\u0000\u00c1\u0011\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005"+
		"\u000f\u0000\u0000\u00c3\u00c4\u0005%\u0000\u0000\u00c4\u00c6\u0005&\u0000"+
		"\u0000\u00c5\u00c7\u0003\u0018\f\u0000\u00c6\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c9\u0003\u001c\u000e\u0000\u00c9\u0013\u0001\u0000\u0000\u0000"+
		"\u00ca\u00cb\u0005b\u0000\u0000\u00cb\u00cc\u0005%\u0000\u0000\u00cc\u00cd"+
		"\u0005\'\u0000\u0000\u00cd\u0015\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005"+
		"b\u0000\u0000\u00cf\u00d0\u0005\u0007\u0000\u0000\u00d0\u00d1\u0005%\u0000"+
		"\u0000\u00d1\u00d3\u0005&\u0000\u0000\u00d2\u00d4\u0003\u0018\f\u0000"+
		"\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6\u0003\u001c\u000e\u0000"+
		"\u00d6\u0017\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005c\u0000\u0000\u00d8"+
		"\u00d9\u0005\u0010\u0000\u0000\u00d9\u0019\u0001\u0000\u0000\u0000\u00da"+
		"\u00db\u0005c\u0000\u0000\u00db\u00dc\u0005\u001f\u0000\u0000\u00dc\u00dd"+
		"\u0005\u0010\u0000\u0000\u00dd\u001b\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0003Z-\u0000\u00df\u00e0\u0005>\u0000\u0000\u00e0\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e1\u00de\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0003\\."+
		"\u0000\u00e4\u001d\u0001\u0000\u0000\u0000\u00e5\u00e6\u00056\u0000\u0000"+
		"\u00e6\u00e7\u0005\u0016\u0000\u0000\u00e7\u00ec\u0003\u001c\u000e\u0000"+
		"\u00e8\u00e9\u0005A\u0000\u0000\u00e9\u00ea\u0003\"\u0011\u0000\u00ea"+
		"\u00eb\u0005B\u0000\u0000\u00eb\u00ed\u0001\u0000\u0000\u0000\u00ec\u00e8"+
		"\u0001\u0000\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ee"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ef\u00057\u0000\u0000\u00ef\u00f1\u0005"+
		"A\u0000\u0000\u00f0\u00f2\u0003 \u0010\u0000\u00f1\u00f0\u0001\u0000\u0000"+
		"\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f3\u00fc\u0005B\u0000\u0000\u00f4\u00f5\u0005?\u0000\u0000\u00f5"+
		"\u00f7\u0005A\u0000\u0000\u00f6\u00f8\u0003 \u0010\u0000\u00f7\u00f6\u0001"+
		"\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fb\u0005B\u0000\u0000\u00fa\u00f4\u0001\u0000"+
		"\u0000\u0000\u00fb\u00fe\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001\u0000"+
		"\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u001f\u0001\u0000"+
		"\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00ff\u0104\u0003n7\u0000"+
		"\u0100\u0101\u0005?\u0000\u0000\u0101\u0103\u0003n7\u0000\u0102\u0100"+
		"\u0001\u0000\u0000\u0000\u0103\u0106\u0001\u0000\u0000\u0000\u0104\u0102"+
		"\u0001\u0000\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105!\u0001"+
		"\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000\u0000\u0107\u010c\u0003"+
		"`0\u0000\u0108\u0109\u0005?\u0000\u0000\u0109\u010b\u0003`0\u0000\u010a"+
		"\u0108\u0001\u0000\u0000\u0000\u010b\u010e\u0001\u0000\u0000\u0000\u010c"+
		"\u010a\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d"+
		"#\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000\u0000\u0000\u010f\u0114"+
		"\u0003^/\u0000\u0110\u0111\u0005?\u0000\u0000\u0111\u0113\u0003^/\u0000"+
		"\u0112\u0110\u0001\u0000\u0000\u0000\u0113\u0116\u0001\u0000\u0000\u0000"+
		"\u0114\u0112\u0001\u0000\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000"+
		"\u0115%\u0001\u0000\u0000\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0117"+
		"\u0118\u0005\u000b\u0000\u0000\u0118\u011d\u0003>\u001f\u0000\u0119\u011a"+
		"\u0005?\u0000\u0000\u011a\u011c\u0003>\u001f\u0000\u011b\u0119\u0001\u0000"+
		"\u0000\u0000\u011c\u011f\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000"+
		"\u0000\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u0120\u0001\u0000"+
		"\u0000\u0000\u011f\u011d\u0001\u0000\u0000\u0000\u0120\u0121\u0005\u0013"+
		"\u0000\u0000\u0121\u0123\u0003\\.\u0000\u0122\u0124\u00036\u001b\u0000"+
		"\u0123\u0122\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000"+
		"\u0124\u0126\u0001\u0000\u0000\u0000\u0125\u0127\u0003(\u0014\u0000\u0126"+
		"\u0125\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127"+
		"\'\u0001\u0000\u0000\u0000\u0128\u0129\u0005A\u0000\u0000\u0129\u012a"+
		"\u0005\u001b\u0000\u0000\u012a\u012b\u0003.\u0017\u0000\u012b\u012c\u0003"+
		"*\u0015\u0000\u012c\u012d\u0005?\u0000\u0000\u012d\u012e\u0005\u001c\u0000"+
		"\u0000\u012e\u012f\u00030\u0018\u0000\u012f\u0130\u0003*\u0015\u0000\u0130"+
		"\u0131\u0005B\u0000\u0000\u0131\u013e\u0001\u0000\u0000\u0000\u0132\u0133"+
		"\u0005\u001b\u0000\u0000\u0133\u0134\u0003.\u0017\u0000\u0134\u0135\u0003"+
		"*\u0015\u0000\u0135\u013e\u0001\u0000\u0000\u0000\u0136\u0137\u0005\u001c"+
		"\u0000\u0000\u0137\u0138\u00030\u0018\u0000\u0138\u0139\u0003*\u0015\u0000"+
		"\u0139\u013e\u0001\u0000\u0000\u0000\u013a\u013b\u0005\u001d\u0000\u0000"+
		"\u013b\u013c\u0005M\u0000\u0000\u013c\u013e\u0003*\u0015\u0000\u013d\u0128"+
		"\u0001\u0000\u0000\u0000\u013d\u0132\u0001\u0000\u0000\u0000\u013d\u0136"+
		"\u0001\u0000\u0000\u0000\u013d\u013a\u0001\u0000\u0000\u0000\u013e)\u0001"+
		"\u0000\u0000\u0000\u013f\u0140\u0003,\u0016\u0000\u0140+\u0001\u0000\u0000"+
		"\u0000\u0141\u0142\u0003v;\u0000\u0142-\u0001\u0000\u0000\u0000\u0143"+
		"\u0144\u0007\u0000\u0000\u0000\u0144/\u0001\u0000\u0000\u0000\u0145\u0146"+
		"\u0007\u0001\u0000\u0000\u01461\u0001\u0000\u0000\u0000\u0147\u0148\u0005"+
		"\u001a\u0000\u0000\u0148\u0149\u0003<\u001e\u0000\u01493\u0001\u0000\u0000"+
		"\u0000\u014a\u014b\u0005\u001e\u0000\u0000\u014b\u014c\u0003<\u001e\u0000"+
		"\u014c5\u0001\u0000\u0000\u0000\u014d\u014e\u0005:\u0000\u0000\u014e\u014f"+
		"\u0003f3\u0000\u014f7\u0001\u0000\u0000\u0000\u0150\u0152\u0003:\u001d"+
		"\u0000\u0151\u0153\u00032\u0019\u0000\u0152\u0151\u0001\u0000\u0000\u0000"+
		"\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0155\u0001\u0000\u0000\u0000"+
		"\u0154\u0156\u0003(\u0014\u0000\u0155\u0154\u0001\u0000\u0000\u0000\u0155"+
		"\u0156\u0001\u0000\u0000\u0000\u0156\u0158\u0001\u0000\u0000\u0000\u0157"+
		"\u0159\u00034\u001a\u0000\u0158\u0157\u0001\u0000\u0000\u0000\u0158\u0159"+
		"\u0001\u0000\u0000\u0000\u01599\u0001\u0000\u0000\u0000\u015a\u015b\u0005"+
		"#\u0000\u0000\u015b\u0160\u0003>\u001f\u0000\u015c\u015d\u0005?\u0000"+
		"\u0000\u015d\u015f\u0003>\u001f\u0000\u015e\u015c\u0001\u0000\u0000\u0000"+
		"\u015f\u0162\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000\u0000"+
		"\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u0163\u0001\u0000\u0000\u0000"+
		"\u0162\u0160\u0001\u0000\u0000\u0000\u0163\u0164\u0005\u0013\u0000\u0000"+
		"\u0164\u0166\u0003\u001c\u000e\u0000\u0165\u0167\u00036\u001b\u0000\u0166"+
		"\u0165\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167"+
		";\u0001\u0000\u0000\u0000\u0168\u0169\u0005^\u0000\u0000\u0169=\u0001"+
		"\u0000\u0000\u0000\u016a\u017d\u0005E\u0000\u0000\u016b\u016c\u0003H$"+
		"\u0000\u016c\u016d\u0005@\u0000\u0000\u016d\u016e\u0005E\u0000\u0000\u016e"+
		"\u017d\u0001\u0000\u0000\u0000\u016f\u0170\u0003H$\u0000\u0170\u0171\u0005"+
		">\u0000\u0000\u0171\u0173\u0001\u0000\u0000\u0000\u0172\u016f\u0001\u0000"+
		"\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173\u0174\u0001\u0000"+
		"\u0000\u0000\u0174\u017d\u0003b1\u0000\u0175\u017a\u0003@ \u0000\u0176"+
		"\u0178\u0005\u0003\u0000\u0000\u0177\u0176\u0001\u0000\u0000\u0000\u0177"+
		"\u0178\u0001\u0000\u0000\u0000\u0178\u0179\u0001\u0000\u0000\u0000\u0179"+
		"\u017b\u0003L&\u0000\u017a\u0177\u0001\u0000\u0000\u0000\u017a\u017b\u0001"+
		"\u0000\u0000\u0000\u017b\u017d\u0001\u0000\u0000\u0000\u017c\u016a\u0001"+
		"\u0000\u0000\u0000\u017c\u016b\u0001\u0000\u0000\u0000\u017c\u0172\u0001"+
		"\u0000\u0000\u0000\u017c\u0175\u0001\u0000\u0000\u0000\u017d?\u0001\u0000"+
		"\u0000\u0000\u017e\u017f\u0003B!\u0000\u017f\u0181\u0005A\u0000\u0000"+
		"\u0180\u0182\u0003D\"\u0000\u0181\u0180\u0001\u0000\u0000\u0000\u0181"+
		"\u0182\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000\u0000\u0183"+
		"\u0184\u0005B\u0000\u0000\u0184A\u0001\u0000\u0000\u0000\u0185\u0186\u0003"+
		"P(\u0000\u0186C\u0001\u0000\u0000\u0000\u0187\u018a\u0003F#\u0000\u0188"+
		"\u018a\u0003f3\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u0188\u0001"+
		"\u0000\u0000\u0000\u018a\u0192\u0001\u0000\u0000\u0000\u018b\u018e\u0005"+
		"?\u0000\u0000\u018c\u018f\u0003F#\u0000\u018d\u018f\u0003f3\u0000\u018e"+
		"\u018c\u0001\u0000\u0000\u0000\u018e\u018d\u0001\u0000\u0000\u0000\u018f"+
		"\u0191\u0001\u0000\u0000\u0000\u0190\u018b\u0001\u0000\u0000\u0000\u0191"+
		"\u0194\u0001\u0000\u0000\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0192"+
		"\u0193\u0001\u0000\u0000\u0000\u0193E\u0001\u0000\u0000\u0000\u0194\u0192"+
		"\u0001\u0000\u0000\u0000\u0195\u0196\u0003H$\u0000\u0196\u0197\u0005>"+
		"\u0000\u0000\u0197\u0199\u0001\u0000\u0000\u0000\u0198\u0195\u0001\u0000"+
		"\u0000\u0000\u0198\u0199\u0001\u0000\u0000\u0000\u0199\u019a\u0001\u0000"+
		"\u0000\u0000\u019a\u019b\u0003b1\u0000\u019bG\u0001\u0000\u0000\u0000"+
		"\u019c\u019d\u0003P(\u0000\u019dI\u0001\u0000\u0000\u0000\u019e\u019f"+
		"\u0005Z\u0000\u0000\u019fK\u0001\u0000\u0000\u0000\u01a0\u01a1\u0003N"+
		"\'\u0000\u01a1M\u0001\u0000\u0000\u0000\u01a2\u01a3\u0003P(\u0000\u01a3"+
		"O\u0001\u0000\u0000\u0000\u01a4\u01a7\u0005\\\u0000\u0000\u01a5\u01a7"+
		"\u0003J%\u0000\u01a6\u01a4\u0001\u0000\u0000\u0000\u01a6\u01a5\u0001\u0000"+
		"\u0000\u0000\u01a7Q\u0001\u0000\u0000\u0000\u01a8\u01b3\u0003T*\u0000"+
		"\u01a9\u01aa\u0005A\u0000\u0000\u01aa\u01ab\u00038\u001c\u0000\u01ab\u01b0"+
		"\u0005B\u0000\u0000\u01ac\u01ae\u0005\u0003\u0000\u0000\u01ad\u01ac\u0001"+
		"\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01af\u0001"+
		"\u0000\u0000\u0000\u01af\u01b1\u0003V+\u0000\u01b0\u01ad\u0001\u0000\u0000"+
		"\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b3\u0001\u0000\u0000"+
		"\u0000\u01b2\u01a8\u0001\u0000\u0000\u0000\u01b2\u01a9\u0001\u0000\u0000"+
		"\u0000\u01b3S\u0001\u0000\u0000\u0000\u01b4\u01b9\u0003\u001c\u000e\u0000"+
		"\u01b5\u01b7\u0005\u0003\u0000\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000\u0000\u0000"+
		"\u01b8\u01ba\u0003V+\u0000\u01b9\u01b6\u0001\u0000\u0000\u0000\u01b9\u01ba"+
		"\u0001\u0000\u0000\u0000\u01ba\u01c3\u0001\u0000\u0000\u0000\u01bb\u01bc"+
		"\u0005A\u0000\u0000\u01bc\u01bf\u0003^/\u0000\u01bd\u01be\u0005?\u0000"+
		"\u0000\u01be\u01c0\u0003^/\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000\u01bf"+
		"\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1"+
		"\u01c2\u0005B\u0000\u0000\u01c2\u01c4\u0001\u0000\u0000\u0000\u01c3\u01bb"+
		"\u0001\u0000\u0000\u0000\u01c3\u01c4\u0001\u0000\u0000\u0000\u01c4\u01ca"+
		"\u0001\u0000\u0000\u0000\u01c5\u01c6\u0005d\u0000\u0000\u01c6\u01c7\u0005"+
		"A\u0000\u0000\u01c7\u01c8\u0003X,\u0000\u01c8\u01c9\u0005B\u0000\u0000"+
		"\u01c9\u01cb\u0001\u0000\u0000\u0000\u01ca\u01c5\u0001\u0000\u0000\u0000"+
		"\u01ca\u01cb\u0001\u0000\u0000\u0000\u01cbU\u0001\u0000\u0000\u0000\u01cc"+
		"\u01cd\u0003N\'\u0000\u01cdW\u0001\u0000\u0000\u0000\u01ce\u01cf\u0003"+
		"<\u001e\u0000\u01cfY\u0001\u0000\u0000\u0000\u01d0\u01d1\u0003P(\u0000"+
		"\u01d1[\u0001\u0000\u0000\u0000\u01d2\u01d3\u0003P(\u0000\u01d3]\u0001"+
		"\u0000\u0000\u0000\u01d4\u01d5\u0003`0\u0000\u01d5\u01da\u0003d2\u0000"+
		"\u01d6\u01d8\u0005\u001f\u0000\u0000\u01d7\u01d6\u0001\u0000\u0000\u0000"+
		"\u01d7\u01d8\u0001\u0000\u0000\u0000\u01d8\u01d9\u0001\u0000\u0000\u0000"+
		"\u01d9\u01db\u0005 \u0000\u0000\u01da\u01d7\u0001\u0000\u0000\u0000\u01da"+
		"\u01db\u0001\u0000\u0000\u0000\u01db\u01de\u0001\u0000\u0000\u0000\u01dc"+
		"\u01dd\u0005\n\u0000\u0000\u01dd\u01df\u0003n7\u0000\u01de\u01dc\u0001"+
		"\u0000\u0000\u0000\u01de\u01df\u0001\u0000\u0000\u0000\u01df\u01e2\u0001"+
		"\u0000\u0000\u0000\u01e0\u01e1\u0005\"\u0000\u0000\u01e1\u01e3\u0005\u0018"+
		"\u0000\u0000\u01e2\u01e0\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000"+
		"\u0000\u0000\u01e3_\u0001\u0000\u0000\u0000\u01e4\u01e5\u0003H$\u0000"+
		"\u01e5\u01e6\u0005>\u0000\u0000\u01e6\u01e8\u0001\u0000\u0000\u0000\u01e7"+
		"\u01e4\u0001\u0000\u0000\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8"+
		"\u01e9\u0001\u0000\u0000\u0000\u01e9\u01ea\u0003b1\u0000\u01eaa\u0001"+
		"\u0000\u0000\u0000\u01eb\u01ec\u0003P(\u0000\u01ecc\u0001\u0000\u0000"+
		"\u0000\u01ed\u01f0\u0003\u0084B\u0000\u01ee\u01f0\u0003\u0086C\u0000\u01ef"+
		"\u01ed\u0001\u0000\u0000\u0000\u01ef\u01ee\u0001\u0000\u0000\u0000\u01f0"+
		"\u01f8\u0001\u0000\u0000\u0000\u01f1\u01f6\u0005\u0002\u0000\u0000\u01f2"+
		"\u01f3\u0005K\u0000\u0000\u01f3\u01f4\u0003~?\u0000\u01f4\u01f5\u0005"+
		"L\u0000\u0000\u01f5\u01f7\u0001\u0000\u0000\u0000\u01f6\u01f2\u0001\u0000"+
		"\u0000\u0000\u01f6\u01f7\u0001\u0000\u0000\u0000\u01f7\u01f9\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f1\u0001\u0000\u0000\u0000\u01f8\u01f9\u0001\u0000"+
		"\u0000\u0000\u01f9e\u0001\u0000\u0000\u0000\u01fa\u01fb\u00063\uffff\uffff"+
		"\u0000\u01fb\u020c\u0003n7\u0000\u01fc\u01fd\u0005C\u0000\u0000\u01fd"+
		"\u01fe\u0003l6\u0000\u01fe\u01ff\u0005D\u0000\u0000\u01ff\u020c\u0001"+
		"\u0000\u0000\u0000\u0200\u0201\u0003H$\u0000\u0201\u0202\u0005>\u0000"+
		"\u0000\u0202\u0204\u0001\u0000\u0000\u0000\u0203\u0200\u0001\u0000\u0000"+
		"\u0000\u0203\u0204\u0001\u0000\u0000\u0000\u0204\u0205\u0001\u0000\u0000"+
		"\u0000\u0205\u020c\u0003b1\u0000\u0206\u020c\u0003@ \u0000\u0207\u0208"+
		"\u0005A\u0000\u0000\u0208\u0209\u0003f3\u0000\u0209\u020a\u0005B\u0000"+
		"\u0000\u020a\u020c\u0001\u0000\u0000\u0000\u020b\u01fa\u0001\u0000\u0000"+
		"\u0000\u020b\u01fc\u0001\u0000\u0000\u0000\u020b\u0203\u0001\u0000\u0000"+
		"\u0000\u020b\u0206\u0001\u0000\u0000\u0000\u020b\u0207\u0001\u0000\u0000"+
		"\u0000\u020c\u0238\u0001\u0000\u0000\u0000\u020d\u020e\n\b\u0000\u0000"+
		"\u020e\u020f\u0003h4\u0000\u020f\u0210\u0003f3\t\u0210\u0237\u0001\u0000"+
		"\u0000\u0000\u0211\u0215\n\u0007\u0000\u0000\u0212\u0216\u0005\u0019\u0000"+
		"\u0000\u0213\u0214\u0005\u001f\u0000\u0000\u0214\u0216\u0005\u0019\u0000"+
		"\u0000\u0215\u0212\u0001\u0000\u0000\u0000\u0215\u0213\u0001\u0000\u0000"+
		"\u0000\u0216\u0217\u0001\u0000\u0000\u0000\u0217\u0237\u0003f3\b\u0218"+
		"\u021a\n\u0004\u0000\u0000\u0219\u021b\u0005\u001f\u0000\u0000\u021a\u0219"+
		"\u0001\u0000\u0000\u0000\u021a\u021b\u0001\u0000\u0000\u0000\u021b\u021c"+
		"\u0001\u0000\u0000\u0000\u021c\u021d\u0005\u0004\u0000\u0000\u021d\u021e"+
		"\u0003f3\u0000\u021e\u021f\u0005\u0001\u0000\u0000\u021f\u0220\u0003f"+
		"3\u0005\u0220\u0237\u0001\u0000\u0000\u0000\u0221\u0222\n\u0002\u0000"+
		"\u0000\u0222\u0223\u0005\u0001\u0000\u0000\u0223\u0237\u0003f3\u0003\u0224"+
		"\u0225\n\u0001\u0000\u0000\u0225\u0226\u0005!\u0000\u0000\u0226\u0237"+
		"\u0003f3\u0002\u0227\u0228\n\u0006\u0000\u0000\u0228\u022a\u0005\u0017"+
		"\u0000\u0000\u0229\u022b\u0005\u001f\u0000\u0000\u022a\u0229\u0001\u0000"+
		"\u0000\u0000\u022a\u022b\u0001\u0000\u0000\u0000\u022b\u022c\u0001\u0000"+
		"\u0000\u0000\u022c\u0237\u0005 \u0000\u0000\u022d\u022f\n\u0005\u0000"+
		"\u0000\u022e\u0230\u0005\u001f\u0000\u0000\u022f\u022e\u0001\u0000\u0000"+
		"\u0000\u022f\u0230\u0001\u0000\u0000\u0000\u0230\u0231\u0001\u0000\u0000"+
		"\u0000\u0231\u0232\u0005\u0014\u0000\u0000\u0232\u0233\u0005A\u0000\u0000"+
		"\u0233\u0234\u0003j5\u0000\u0234\u0235\u0005B\u0000\u0000\u0235\u0237"+
		"\u0001\u0000\u0000\u0000\u0236\u020d\u0001\u0000\u0000\u0000\u0236\u0211"+
		"\u0001\u0000\u0000\u0000\u0236\u0218\u0001\u0000\u0000\u0000\u0236\u0221"+
		"\u0001\u0000\u0000\u0000\u0236\u0224\u0001\u0000\u0000\u0000\u0236\u0227"+
		"\u0001\u0000\u0000\u0000\u0236\u022d\u0001\u0000\u0000\u0000\u0237\u023a"+
		"\u0001\u0000\u0000\u0000\u0238\u0236\u0001\u0000\u0000\u0000\u0238\u0239"+
		"\u0001\u0000\u0000\u0000\u0239g\u0001\u0000\u0000\u0000\u023a\u0238\u0001"+
		"\u0000\u0000\u0000\u023b\u023c\u0007\u0002\u0000\u0000\u023ci\u0001\u0000"+
		"\u0000\u0000\u023d\u0242\u0003f3\u0000\u023e\u023f\u0005?\u0000\u0000"+
		"\u023f\u0241\u0003f3\u0000\u0240\u023e\u0001\u0000\u0000\u0000\u0241\u0244"+
		"\u0001\u0000\u0000\u0000\u0242\u0240\u0001\u0000\u0000\u0000\u0242\u0243"+
		"\u0001\u0000\u0000\u0000\u0243k\u0001\u0000\u0000\u0000\u0244\u0242\u0001"+
		"\u0000\u0000\u0000\u0245\u0246\u0003r9\u0000\u0246m\u0001\u0000\u0000"+
		"\u0000\u0247\u024c\u0003p8\u0000\u0248\u024c\u0003t:\u0000\u0249\u024c"+
		"\u0003z=\u0000\u024a\u024c\u0005 \u0000\u0000\u024b\u0247\u0001\u0000"+
		"\u0000\u0000\u024b\u0248\u0001\u0000\u0000\u0000\u024b\u0249\u0001\u0000"+
		"\u0000\u0000\u024b\u024a\u0001\u0000\u0000\u0000\u024co\u0001\u0000\u0000"+
		"\u0000\u024d\u024e\u0005]\u0000\u0000\u024eq\u0001\u0000\u0000\u0000\u024f"+
		"\u0250\u0005\\\u0000\u0000\u0250s\u0001\u0000\u0000\u0000\u0251\u0254"+
		"\u0003v;\u0000\u0252\u0254\u0003x<\u0000\u0253\u0251\u0001\u0000\u0000"+
		"\u0000\u0253\u0252\u0001\u0000\u0000\u0000\u0254u\u0001\u0000\u0000\u0000"+
		"\u0255\u0257\u0005I\u0000\u0000\u0256\u0255\u0001\u0000\u0000\u0000\u0256"+
		"\u0257\u0001\u0000\u0000\u0000\u0257\u0258\u0001\u0000\u0000\u0000\u0258"+
		"\u0259\u0005^\u0000\u0000\u0259w\u0001\u0000\u0000\u0000\u025a\u025c\u0005"+
		"I\u0000\u0000\u025b\u025a\u0001\u0000\u0000\u0000\u025b\u025c\u0001\u0000"+
		"\u0000\u0000\u025c\u025d\u0001\u0000\u0000\u0000\u025d\u0260\u0003<\u001e"+
		"\u0000\u025e\u025f\u0005@\u0000\u0000\u025f\u0261\u0003<\u001e\u0000\u0260"+
		"\u025e\u0001\u0000\u0000\u0000\u0260\u0261\u0001\u0000\u0000\u0000\u0261"+
		"y\u0001\u0000\u0000\u0000\u0262\u0263\u0007\u0003\u0000\u0000\u0263{\u0001"+
		"\u0000\u0000\u0000\u0264\u0267\u0005\\\u0000\u0000\u0265\u0267\u0001\u0000"+
		"\u0000\u0000\u0266\u0264\u0001\u0000\u0000\u0000\u0266\u0265\u0001\u0000"+
		"\u0000\u0000\u0267}\u0001\u0000\u0000\u0000\u0268\u0269\u0003v;\u0000"+
		"\u0269\u007f\u0001\u0000\u0000\u0000\u026a\u026b\u0003v;\u0000\u026b\u0081"+
		"\u0001\u0000\u0000\u0000\u026c\u026d\u0003v;\u0000\u026d\u0083\u0001\u0000"+
		"\u0000\u0000\u026e\u026f\u0005\u0006\u0000\u0000\u026f\u0270\u0005A\u0000"+
		"\u0000\u0270\u0271\u0003\u0080@\u0000\u0271\u0272\u0005B\u0000\u0000\u0272"+
		"\u0295\u0001\u0000\u0000\u0000\u0273\u0278\u00059\u0000\u0000\u0274\u0275"+
		"\u0005A\u0000\u0000\u0275\u0276\u0003\u0080@\u0000\u0276\u0277\u0005B"+
		"\u0000\u0000\u0277\u0279\u0001\u0000\u0000\u0000\u0278\u0274\u0001\u0000"+
		"\u0000\u0000\u0278\u0279\u0001\u0000\u0000\u0000\u0279\u0295\u0001\u0000"+
		"\u0000\u0000\u027a\u027f\u0005\t\u0000\u0000\u027b\u027c\u0003\u0080@"+
		"\u0000\u027c\u027d\u0005?\u0000\u0000\u027d\u027e\u0003\u0082A\u0000\u027e"+
		"\u0280\u0001\u0000\u0000\u0000\u027f\u027b\u0001\u0000\u0000\u0000\u027f"+
		"\u0280\u0001\u0000\u0000\u0000\u0280\u0295\u0001\u0000\u0000\u0000\u0281"+
		"\u0295\u0005*\u0000\u0000\u0282\u0295\u0005$\u0000\u0000\u0283\u0295\u0005"+
		"\u0015\u0000\u0000\u0284\u0295\u0005e\u0000\u0000\u0285\u0295\u0005\u0012"+
		"\u0000\u0000\u0286\u0295\u0005\u000e\u0000\u0000\u0287\u0295\u0005)\u0000"+
		"\u0000\u0288\u0295\u0005\b\u0000\u0000\u0289\u0295\u0005(\u0000\u0000"+
		"\u028a\u028b\u0005\u0005\u0000\u0000\u028b\u028c\u0005A\u0000\u0000\u028c"+
		"\u028d\u0003\u0080@\u0000\u028d\u028e\u0005B\u0000\u0000\u028e\u0295\u0001"+
		"\u0000\u0000\u0000\u028f\u0290\u00058\u0000\u0000\u0290\u0291\u0005A\u0000"+
		"\u0000\u0291\u0292\u0003\u0080@\u0000\u0292\u0293\u0005B\u0000\u0000\u0293"+
		"\u0295\u0001\u0000\u0000\u0000\u0294\u026e\u0001\u0000\u0000\u0000\u0294"+
		"\u0273\u0001\u0000\u0000\u0000\u0294\u027a\u0001\u0000\u0000\u0000\u0294"+
		"\u0281\u0001\u0000\u0000\u0000\u0294\u0282\u0001\u0000\u0000\u0000\u0294"+
		"\u0283\u0001\u0000\u0000\u0000\u0294\u0284\u0001\u0000\u0000\u0000\u0294"+
		"\u0285\u0001\u0000\u0000\u0000\u0294\u0286\u0001\u0000\u0000\u0000\u0294"+
		"\u0287\u0001\u0000\u0000\u0000\u0294\u0288\u0001\u0000\u0000\u0000\u0294"+
		"\u0289\u0001\u0000\u0000\u0000\u0294\u028a\u0001\u0000\u0000\u0000\u0294"+
		"\u028f\u0001\u0000\u0000\u0000\u0295\u0085\u0001\u0000\u0000\u0000\u0296"+
		"\u0297\u0007\u0004\u0000\u0000\u0297\u0087\u0001\u0000\u0000\u0000G\u008b"+
		"\u0092\u0096\u009c\u00a1\u00a7\u00ae\u00bb\u00c6\u00d3\u00e1\u00ec\u00f1"+
		"\u00f7\u00fc\u0104\u010c\u0114\u011d\u0123\u0126\u013d\u0152\u0155\u0158"+
		"\u0160\u0166\u0172\u0177\u017a\u017c\u0181\u0189\u018e\u0192\u0198\u01a6"+
		"\u01ad\u01b0\u01b2\u01b6\u01b9\u01bf\u01c3\u01ca\u01d7\u01da\u01de\u01e2"+
		"\u01e7\u01ef\u01f6\u01f8\u0203\u020b\u0215\u021a\u022a\u022f\u0236\u0238"+
		"\u0242\u024b\u0253\u0256\u025b\u0260\u0266\u0278\u027f\u0294";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}