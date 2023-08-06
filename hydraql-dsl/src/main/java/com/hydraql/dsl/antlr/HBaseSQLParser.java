// Generated from HBaseSQL.g4 by ANTLR 4.5.1

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
public class HBaseSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, LR_BRACKET=5, RR_BRACKET=6, COMMA=7, SEMICOLON=8, 
		D_QUO=9, EQ=10, NOTEQ=11, GREATER=12, GREATEREQUAL=13, LESS=14, LESSEQUAL=15, 
		PLUS=16, MINUS=17, ASTERISK=18, SLASH=19, MOD=20, IS=21, NOTMATCH=22, 
		MATCH=23, BETWEEN=24, MISSING=25, LIMIT=26, TS=27, STARTTS=28, ENDTS=29, 
		CREATE=30, DROP=31, SHOW=32, VIRTUAL=33, TABLE=34, TABLES=35, WITH=36, 
		PROPERTIES=37, NULLABLE=38, INSERT=39, INTO=40, VALUES=41, SELECT=42, 
		UPDATE=43, SET=44, DELETE=45, FROM=46, COLUMNFAMILY=47, WHERE=48, AND=49, 
		OR=50, IN=51, LIKE=52, NULL=53, NOT=54, INTEGER=55, ROWKEY=56, ISROWKEY=57, 
		STARTKEY=58, ENDKEY=59, MAXVERSION=60, ID=61, STRING=62, SPACE=63, COMMENT_INPUT=64, 
		LINE_COMMENT=65;
	public static final int
		RULE_query = 0, RULE_createTableStatement = 1, RULE_dropTableStatement = 2, 
		RULE_showTableStatement = 3, RULE_showCreateTableStatement = 4, RULE_tableName = 5, 
		RULE_fields = 6, RULE_field = 7, RULE_fieldName = 8, RULE_fieldType = 9, 
		RULE_properties = 10, RULE_keyValue = 11, RULE_column = 12, RULE_funcname = 13, 
		RULE_selectColList = 14, RULE_columnList = 15, RULE_insertStatement = 16, 
		RULE_multiValueList = 17, RULE_valueList = 18, RULE_value = 19, RULE_selectStatement = 20, 
		RULE_deleteStatement = 21, RULE_rowKeyRangeExp = 22, RULE_rowKeyExp = 23, 
		RULE_funcParamsList = 24, RULE_funcCol = 25, RULE_gtOper = 26, RULE_leOper = 27, 
		RULE_tsRange = 28, RULE_constant = 29, RULE_constantList = 30, RULE_var = 31, 
		RULE_varList = 32, RULE_multiVersionExp = 33, RULE_maxVersionExp = 34, 
		RULE_integer = 35, RULE_tsExp = 36, RULE_timestamp = 37, RULE_wherec = 38, 
		RULE_conditionc = 39, RULE_limitExp = 40;
	public static final String[] ruleNames = {
		"query", "createTableStatement", "dropTableStatement", "showTableStatement", 
		"showCreateTableStatement", "tableName", "fields", "field", "fieldName", 
		"fieldType", "properties", "keyValue", "column", "funcname", "selectColList", 
		"columnList", "insertStatement", "multiValueList", "valueList", "value", 
		"selectStatement", "deleteStatement", "rowKeyRangeExp", "rowKeyExp", "funcParamsList", 
		"funcCol", "gtOper", "leOper", "tsRange", "constant", "constantList", 
		"var", "varList", "multiVersionExp", "maxVersionExp", "integer", "tsExp", 
		"timestamp", "wherec", "conditionc", "limitExp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'`'", "'''", "'${'", "'}'", "'('", "')'", "','", "';'", "'\"'", 
		"'='", "'!='", "'>'", "'>='", "'<'", "'<='", "'+'", "'-'", "'*'", "'/'", 
		"'%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "LR_BRACKET", "RR_BRACKET", "COMMA", "SEMICOLON", 
		"D_QUO", "EQ", "NOTEQ", "GREATER", "GREATEREQUAL", "LESS", "LESSEQUAL", 
		"PLUS", "MINUS", "ASTERISK", "SLASH", "MOD", "IS", "NOTMATCH", "MATCH", 
		"BETWEEN", "MISSING", "LIMIT", "TS", "STARTTS", "ENDTS", "CREATE", "DROP", 
		"SHOW", "VIRTUAL", "TABLE", "TABLES", "WITH", "PROPERTIES", "NULLABLE", 
		"INSERT", "INTO", "VALUES", "SELECT", "UPDATE", "SET", "DELETE", "FROM", 
		"COLUMNFAMILY", "WHERE", "AND", "OR", "IN", "LIKE", "NULL", "NOT", "INTEGER", 
		"ROWKEY", "ISROWKEY", "STARTKEY", "ENDKEY", "MAXVERSION", "ID", "STRING", 
		"SPACE", "COMMENT_INPUT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "HBaseSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HBaseSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public CreateTableStatementContext createTableStatement() {
			return getRuleContext(CreateTableStatementContext.class,0);
		}
		public DropTableStatementContext dropTableStatement() {
			return getRuleContext(DropTableStatementContext.class,0);
		}
		public ShowTableStatementContext showTableStatement() {
			return getRuleContext(ShowTableStatementContext.class,0);
		}
		public ShowCreateTableStatementContext showCreateTableStatement() {
			return getRuleContext(ShowCreateTableStatementContext.class,0);
		}
		public InsertStatementContext insertStatement() {
			return getRuleContext(InsertStatementContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public DeleteStatementContext deleteStatement() {
			return getRuleContext(DeleteStatementContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			setState(89);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				createTableStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				dropTableStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(84);
				showTableStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(85);
				showCreateTableStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(86);
				insertStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(87);
				selectStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(88);
				deleteStatement();
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

	public static class CreateTableStatementContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(HBaseSQLParser.CREATE, 0); }
		public TerminalNode VIRTUAL() { return getToken(HBaseSQLParser.VIRTUAL, 0); }
		public TerminalNode TABLE() { return getToken(HBaseSQLParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public FieldsContext fields() {
			return getRuleContext(FieldsContext.class,0);
		}
		public TerminalNode WITH() { return getToken(HBaseSQLParser.WITH, 0); }
		public TerminalNode PROPERTIES() { return getToken(HBaseSQLParser.PROPERTIES, 0); }
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public CreateTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitCreateTableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableStatementContext createTableStatement() throws RecognitionException {
		CreateTableStatementContext _localctx = new CreateTableStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_createTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(CREATE);
			setState(92);
			match(VIRTUAL);
			setState(93);
			match(TABLE);
			setState(94);
			tableName();
			setState(95);
			match(LR_BRACKET);
			setState(96);
			fields();
			setState(97);
			match(RR_BRACKET);
			setState(104);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(98);
				match(WITH);
				setState(99);
				match(PROPERTIES);
				setState(100);
				match(LR_BRACKET);
				setState(101);
				properties();
				setState(102);
				match(RR_BRACKET);
				}
			}

			setState(107);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(106);
				match(SEMICOLON);
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

	public static class DropTableStatementContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(HBaseSQLParser.DROP, 0); }
		public TerminalNode VIRTUAL() { return getToken(HBaseSQLParser.VIRTUAL, 0); }
		public TerminalNode TABLE() { return getToken(HBaseSQLParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public DropTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTableStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitDropTableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropTableStatementContext dropTableStatement() throws RecognitionException {
		DropTableStatementContext _localctx = new DropTableStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dropTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(DROP);
			setState(110);
			match(VIRTUAL);
			setState(111);
			match(TABLE);
			setState(112);
			tableName();
			setState(114);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(113);
				match(SEMICOLON);
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

	public static class ShowTableStatementContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(HBaseSQLParser.SHOW, 0); }
		public TerminalNode VIRTUAL() { return getToken(HBaseSQLParser.VIRTUAL, 0); }
		public TerminalNode TABLES() { return getToken(HBaseSQLParser.TABLES, 0); }
		public ShowTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showTableStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitShowTableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowTableStatementContext showTableStatement() throws RecognitionException {
		ShowTableStatementContext _localctx = new ShowTableStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_showTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(SHOW);
			setState(117);
			match(VIRTUAL);
			setState(118);
			match(TABLES);
			setState(120);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(119);
				match(SEMICOLON);
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

	public static class ShowCreateTableStatementContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(HBaseSQLParser.SHOW, 0); }
		public TerminalNode CREATE() { return getToken(HBaseSQLParser.CREATE, 0); }
		public TerminalNode VIRTUAL() { return getToken(HBaseSQLParser.VIRTUAL, 0); }
		public TerminalNode TABLE() { return getToken(HBaseSQLParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ShowCreateTableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showCreateTableStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitShowCreateTableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowCreateTableStatementContext showCreateTableStatement() throws RecognitionException {
		ShowCreateTableStatementContext _localctx = new ShowCreateTableStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_showCreateTableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(SHOW);
			setState(123);
			match(CREATE);
			setState(124);
			match(VIRTUAL);
			setState(125);
			match(TABLE);
			setState(126);
			tableName();
			setState(128);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(127);
				match(SEMICOLON);
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

	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
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

	public static class FieldsContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public FieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitFields(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldsContext fields() throws RecognitionException {
		FieldsContext _localctx = new FieldsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fields);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			field();
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(133);
				match(COMMA);
				setState(134);
				field();
				}
				}
				setState(139);
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

	public static class FieldContext extends ParserRuleContext {
		public FieldNameContext fieldName() {
			return getRuleContext(FieldNameContext.class,0);
		}
		public FieldTypeContext fieldType() {
			return getRuleContext(FieldTypeContext.class,0);
		}
		public TerminalNode ISROWKEY() { return getToken(HBaseSQLParser.ISROWKEY, 0); }
		public TerminalNode NULLABLE() { return getToken(HBaseSQLParser.NULLABLE, 0); }
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			fieldName();
			setState(141);
			fieldType();
			setState(143);
			_la = _input.LA(1);
			if (_la==ISROWKEY) {
				{
				setState(142);
				match(ISROWKEY);
				}
			}

			setState(146);
			_la = _input.LA(1);
			if (_la==NULLABLE) {
				{
				setState(145);
				match(NULLABLE);
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

	public static class FieldNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public FieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
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

	public static class FieldTypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public FieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitFieldType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldTypeContext fieldType() throws RecognitionException {
		FieldTypeContext _localctx = new FieldTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_fieldType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
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

	public static class PropertiesContext extends ParserRuleContext {
		public List<KeyValueContext> keyValue() {
			return getRuleContexts(KeyValueContext.class);
		}
		public KeyValueContext keyValue(int i) {
			return getRuleContext(KeyValueContext.class,i);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			keyValue();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(153);
				match(COMMA);
				setState(154);
				keyValue();
				}
				}
				setState(159);
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

	public static class KeyValueContext extends ParserRuleContext {
		public List<TerminalNode> D_QUO() { return getTokens(HBaseSQLParser.D_QUO); }
		public TerminalNode D_QUO(int i) {
			return getToken(HBaseSQLParser.D_QUO, i);
		}
		public List<TerminalNode> ID() { return getTokens(HBaseSQLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(HBaseSQLParser.ID, i);
		}
		public KeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitKeyValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(D_QUO);
			setState(161);
			match(ID);
			setState(162);
			match(D_QUO);
			setState(163);
			match(EQ);
			setState(164);
			match(D_QUO);
			setState(165);
			match(ID);
			setState(166);
			match(D_QUO);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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

	public static class FuncnameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public FuncnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcname; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitFuncname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncnameContext funcname() throws RecognitionException {
		FuncnameContext _localctx = new FuncnameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funcname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
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

	public static class SelectColListContext extends ParserRuleContext {
		public SelectColListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectColList; }
	 
		public SelectColListContext() { }
		public void copyFrom(SelectColListContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ColList_StarContext extends SelectColListContext {
		public ColList_StarContext(SelectColListContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitColList_Star(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ColList_ColListContext extends SelectColListContext {
		public ColumnListContext columnList() {
			return getRuleContext(ColumnListContext.class,0);
		}
		public ColList_ColListContext(SelectColListContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitColList_ColList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectColListContext selectColList() throws RecognitionException {
		SelectColListContext _localctx = new SelectColListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_selectColList);
		try {
			setState(174);
			switch (_input.LA(1)) {
			case ASTERISK:
				_localctx = new ColList_StarContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				match(ASTERISK);
				}
				break;
			case ID:
				_localctx = new ColList_ColListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				columnList();
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

	public static class ColumnListContext extends ParserRuleContext {
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HBaseSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HBaseSQLParser.COMMA, i);
		}
		public ColumnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitColumnList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnListContext columnList() throws RecognitionException {
		ColumnListContext _localctx = new ColumnListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_columnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			column();
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(177);
				match(COMMA);
				setState(178);
				column();
				}
				}
				setState(183);
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

	public static class InsertStatementContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(HBaseSQLParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(HBaseSQLParser.INTO, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public ColumnListContext columnList() {
			return getRuleContext(ColumnListContext.class,0);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public TerminalNode VALUES() { return getToken(HBaseSQLParser.VALUES, 0); }
		public MultiValueListContext multiValueList() {
			return getRuleContext(MultiValueListContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(HBaseSQLParser.WHERE, 0); }
		public TerminalNode TS() { return getToken(HBaseSQLParser.TS, 0); }
		public TerminalNode EQ() { return getToken(HBaseSQLParser.EQ, 0); }
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public InsertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitInsertStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStatementContext insertStatement() throws RecognitionException {
		InsertStatementContext _localctx = new InsertStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(INSERT);
			setState(185);
			match(INTO);
			setState(186);
			tableName();
			setState(187);
			match(LR_BRACKET);
			setState(188);
			columnList();
			setState(189);
			match(RR_BRACKET);
			setState(190);
			match(VALUES);
			setState(191);
			multiValueList();
			setState(196);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(192);
				match(WHERE);
				setState(193);
				match(TS);
				setState(194);
				match(EQ);
				setState(195);
				tsExp();
				}
			}

			setState(199);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(198);
				match(SEMICOLON);
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

	public static class MultiValueListContext extends ParserRuleContext {
		public List<TerminalNode> LR_BRACKET() { return getTokens(HBaseSQLParser.LR_BRACKET); }
		public TerminalNode LR_BRACKET(int i) {
			return getToken(HBaseSQLParser.LR_BRACKET, i);
		}
		public List<ValueListContext> valueList() {
			return getRuleContexts(ValueListContext.class);
		}
		public ValueListContext valueList(int i) {
			return getRuleContext(ValueListContext.class,i);
		}
		public List<TerminalNode> RR_BRACKET() { return getTokens(HBaseSQLParser.RR_BRACKET); }
		public TerminalNode RR_BRACKET(int i) {
			return getToken(HBaseSQLParser.RR_BRACKET, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HBaseSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HBaseSQLParser.COMMA, i);
		}
		public MultiValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiValueList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitMultiValueList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiValueListContext multiValueList() throws RecognitionException {
		MultiValueListContext _localctx = new MultiValueListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_multiValueList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(LR_BRACKET);
			setState(202);
			valueList();
			setState(203);
			match(RR_BRACKET);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(204);
				match(COMMA);
				setState(205);
				match(LR_BRACKET);
				setState(206);
				valueList();
				setState(207);
				match(RR_BRACKET);
				}
				}
				setState(213);
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

	public static class ValueListContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(HBaseSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HBaseSQLParser.COMMA, i);
		}
		public ValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitValueList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueListContext valueList() throws RecognitionException {
		ValueListContext _localctx = new ValueListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_valueList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			value();
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(215);
				match(COMMA);
				setState(216);
				value();
				}
				}
				setState(221);
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

	public static class ValueContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(HBaseSQLParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HBaseSQLParser.STRING, i);
		}
		public List<TerminalNode> ID() { return getTokens(HBaseSQLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(HBaseSQLParser.ID, i);
		}
		public List<TerminalNode> NULL() { return getTokens(HBaseSQLParser.NULL); }
		public TerminalNode NULL(int i) {
			return getToken(HBaseSQLParser.NULL, i);
		}
		public List<TerminalNode> ROWKEY() { return getTokens(HBaseSQLParser.ROWKEY); }
		public TerminalNode ROWKEY(int i) {
			return getToken(HBaseSQLParser.ROWKEY, i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_value);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(223); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(222);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << ROWKEY) | (1L << ID) | (1L << STRING))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(225); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(HBaseSQLParser.SELECT, 0); }
		public SelectColListContext selectColList() {
			return getRuleContext(SelectColListContext.class,0);
		}
		public TerminalNode FROM() { return getToken(HBaseSQLParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(HBaseSQLParser.WHERE, 0); }
		public RowKeyRangeExpContext rowKeyRangeExp() {
			return getRuleContext(RowKeyRangeExpContext.class,0);
		}
		public List<TerminalNode> AND() { return getTokens(HBaseSQLParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(HBaseSQLParser.AND, i);
		}
		public WherecContext wherec() {
			return getRuleContext(WherecContext.class,0);
		}
		public MultiVersionExpContext multiVersionExp() {
			return getRuleContext(MultiVersionExpContext.class,0);
		}
		public LimitExpContext limitExp() {
			return getRuleContext(LimitExpContext.class,0);
		}
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(SELECT);
			setState(228);
			selectColList();
			setState(229);
			match(FROM);
			setState(230);
			tableName();
			setState(231);
			match(WHERE);
			setState(232);
			rowKeyRangeExp();
			setState(235);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(233);
				match(AND);
				setState(234);
				wherec();
				}
				break;
			}
			setState(239);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(237);
				match(AND);
				setState(238);
				multiVersionExp();
				}
			}

			setState(242);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(241);
				limitExp();
				}
			}

			setState(245);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(244);
				match(SEMICOLON);
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

	public static class DeleteStatementContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(HBaseSQLParser.DELETE, 0); }
		public SelectColListContext selectColList() {
			return getRuleContext(SelectColListContext.class,0);
		}
		public TerminalNode FROM() { return getToken(HBaseSQLParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(HBaseSQLParser.WHERE, 0); }
		public RowKeyRangeExpContext rowKeyRangeExp() {
			return getRuleContext(RowKeyRangeExpContext.class,0);
		}
		public List<TerminalNode> AND() { return getTokens(HBaseSQLParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(HBaseSQLParser.AND, i);
		}
		public WherecContext wherec() {
			return getRuleContext(WherecContext.class,0);
		}
		public TerminalNode TS() { return getToken(HBaseSQLParser.TS, 0); }
		public TerminalNode EQ() { return getToken(HBaseSQLParser.EQ, 0); }
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public DeleteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitDeleteStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteStatementContext deleteStatement() throws RecognitionException {
		DeleteStatementContext _localctx = new DeleteStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(DELETE);
			setState(248);
			selectColList();
			setState(249);
			match(FROM);
			setState(250);
			tableName();
			setState(251);
			match(WHERE);
			setState(252);
			rowKeyRangeExp();
			setState(255);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(253);
				match(AND);
				setState(254);
				wherec();
				}
				break;
			}
			setState(261);
			_la = _input.LA(1);
			if (_la==AND) {
				{
				setState(257);
				match(AND);
				setState(258);
				match(TS);
				setState(259);
				match(EQ);
				setState(260);
				tsExp();
				}
			}

			setState(264);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(263);
				match(SEMICOLON);
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

	public static class RowKeyRangeExpContext extends ParserRuleContext {
		public RowKeyRangeExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowKeyRangeExp; }
	 
		public RowKeyRangeExpContext() { }
		public void copyFrom(RowKeyRangeExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Rowkeyrange_startAndEndContext extends RowKeyRangeExpContext {
		public TerminalNode STARTKEY() { return getToken(HBaseSQLParser.STARTKEY, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public List<RowKeyExpContext> rowKeyExp() {
			return getRuleContexts(RowKeyExpContext.class);
		}
		public RowKeyExpContext rowKeyExp(int i) {
			return getRuleContext(RowKeyExpContext.class,i);
		}
		public TerminalNode AND() { return getToken(HBaseSQLParser.AND, 0); }
		public TerminalNode ENDKEY() { return getToken(HBaseSQLParser.ENDKEY, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public Rowkeyrange_startAndEndContext(RowKeyRangeExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkeyrange_startAndEnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Rowkeyrange_endContext extends RowKeyRangeExpContext {
		public TerminalNode ENDKEY() { return getToken(HBaseSQLParser.ENDKEY, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public RowKeyExpContext rowKeyExp() {
			return getRuleContext(RowKeyExpContext.class,0);
		}
		public Rowkeyrange_endContext(RowKeyRangeExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkeyrange_end(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Rowkeyrange_onerowkeyContext extends RowKeyRangeExpContext {
		public TerminalNode ROWKEY() { return getToken(HBaseSQLParser.ROWKEY, 0); }
		public TerminalNode EQ() { return getToken(HBaseSQLParser.EQ, 0); }
		public RowKeyExpContext rowKeyExp() {
			return getRuleContext(RowKeyExpContext.class,0);
		}
		public Rowkeyrange_onerowkeyContext(RowKeyRangeExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkeyrange_onerowkey(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Rowkeyrange_insomekeysContext extends RowKeyRangeExpContext {
		public TerminalNode ROWKEY() { return getToken(HBaseSQLParser.ROWKEY, 0); }
		public TerminalNode IN() { return getToken(HBaseSQLParser.IN, 0); }
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public List<RowKeyExpContext> rowKeyExp() {
			return getRuleContexts(RowKeyExpContext.class);
		}
		public RowKeyExpContext rowKeyExp(int i) {
			return getRuleContext(RowKeyExpContext.class,i);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(HBaseSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HBaseSQLParser.COMMA, i);
		}
		public Rowkeyrange_insomekeysContext(RowKeyRangeExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkeyrange_insomekeys(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Rowkeyrange_startContext extends RowKeyRangeExpContext {
		public TerminalNode STARTKEY() { return getToken(HBaseSQLParser.STARTKEY, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public RowKeyExpContext rowKeyExp() {
			return getRuleContext(RowKeyExpContext.class,0);
		}
		public Rowkeyrange_startContext(RowKeyRangeExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkeyrange_start(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Rowkeyrange_prefixContext extends RowKeyRangeExpContext {
		public TerminalNode ROWKEY() { return getToken(HBaseSQLParser.ROWKEY, 0); }
		public TerminalNode LIKE() { return getToken(HBaseSQLParser.LIKE, 0); }
		public RowKeyExpContext rowKeyExp() {
			return getRuleContext(RowKeyExpContext.class,0);
		}
		public Rowkeyrange_prefixContext(RowKeyRangeExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkeyrange_prefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowKeyRangeExpContext rowKeyRangeExp() throws RecognitionException {
		RowKeyRangeExpContext _localctx = new RowKeyRangeExpContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_rowKeyRangeExp);
		int _la;
		try {
			setState(301);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				_localctx = new Rowkeyrange_startAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(STARTKEY);
				setState(267);
				gtOper();
				setState(268);
				rowKeyExp();
				setState(269);
				match(AND);
				setState(270);
				match(ENDKEY);
				setState(271);
				leOper();
				setState(272);
				rowKeyExp();
				}
				break;
			case 2:
				_localctx = new Rowkeyrange_startContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				match(STARTKEY);
				setState(275);
				gtOper();
				setState(276);
				rowKeyExp();
				}
				break;
			case 3:
				_localctx = new Rowkeyrange_endContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(278);
				match(ENDKEY);
				setState(279);
				leOper();
				setState(280);
				rowKeyExp();
				}
				break;
			case 4:
				_localctx = new Rowkeyrange_onerowkeyContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(282);
				match(ROWKEY);
				setState(283);
				match(EQ);
				setState(284);
				rowKeyExp();
				}
				break;
			case 5:
				_localctx = new Rowkeyrange_insomekeysContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(285);
				match(ROWKEY);
				setState(286);
				match(IN);
				setState(287);
				match(LR_BRACKET);
				setState(288);
				rowKeyExp();
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(289);
					match(COMMA);
					setState(290);
					rowKeyExp();
					}
					}
					setState(295);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(296);
				match(RR_BRACKET);
				}
				break;
			case 6:
				_localctx = new Rowkeyrange_prefixContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(298);
				match(ROWKEY);
				setState(299);
				match(LIKE);
				setState(300);
				rowKeyExp();
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

	public static class RowKeyExpContext extends ParserRuleContext {
		public RowKeyExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowKeyExp; }
	 
		public RowKeyExpContext() { }
		public void copyFrom(RowKeyExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Rowkey_FuncConstantContext extends RowKeyExpContext {
		public FuncnameContext funcname() {
			return getRuleContext(FuncnameContext.class,0);
		}
		public FuncParamsListContext funcParamsList() {
			return getRuleContext(FuncParamsListContext.class,0);
		}
		public Rowkey_FuncConstantContext(RowKeyExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkey_FuncConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Rowkey_WrapperContext extends RowKeyExpContext {
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public RowKeyExpContext rowKeyExp() {
			return getRuleContext(RowKeyExpContext.class,0);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public Rowkey_WrapperContext(RowKeyExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkey_Wrapper(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Rowkey_ConstantContext extends RowKeyExpContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Rowkey_ConstantContext(RowKeyExpContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitRowkey_Constant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowKeyExpContext rowKeyExp() throws RecognitionException {
		RowKeyExpContext _localctx = new RowKeyExpContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_rowKeyExp);
		try {
			setState(311);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				_localctx = new Rowkey_WrapperContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				match(LR_BRACKET);
				setState(304);
				rowKeyExp();
				setState(305);
				match(RR_BRACKET);
				}
				break;
			case 2:
				_localctx = new Rowkey_ConstantContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				value();
				}
				break;
			case 3:
				_localctx = new Rowkey_FuncConstantContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(308);
				funcname();
				setState(309);
				funcParamsList();
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

	public static class FuncParamsListContext extends ParserRuleContext {
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public List<FuncColContext> funcCol() {
			return getRuleContexts(FuncColContext.class);
		}
		public FuncColContext funcCol(int i) {
			return getRuleContext(FuncColContext.class,i);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(HBaseSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(HBaseSQLParser.COMMA, i);
		}
		public FuncParamsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcParamsList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitFuncParamsList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncParamsListContext funcParamsList() throws RecognitionException {
		FuncParamsListContext _localctx = new FuncParamsListContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_funcParamsList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			match(LR_BRACKET);
			setState(314);
			funcCol();
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(315);
				match(COMMA);
				setState(316);
				funcCol();
				}
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(322);
			match(RR_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncColContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public FuncColContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCol; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitFuncCol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncColContext funcCol() throws RecognitionException {
		FuncColContext _localctx = new FuncColContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_funcCol);
		try {
			setState(336);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(324);
				match(T__0);
				setState(325);
				value();
				setState(326);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(328);
				match(T__1);
				setState(329);
				value();
				setState(330);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(332);
				value();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(333);
				match(T__1);
				setState(334);
				match(T__1);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
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

	public static class GtOperContext extends ParserRuleContext {
		public TerminalNode GREATER() { return getToken(HBaseSQLParser.GREATER, 0); }
		public TerminalNode GREATEREQUAL() { return getToken(HBaseSQLParser.GREATEREQUAL, 0); }
		public GtOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gtOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitGtOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GtOperContext gtOper() throws RecognitionException {
		GtOperContext _localctx = new GtOperContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_gtOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			_la = _input.LA(1);
			if ( !(_la==GREATER || _la==GREATEREQUAL) ) {
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
		public TerminalNode LESS() { return getToken(HBaseSQLParser.LESS, 0); }
		public TerminalNode LESSEQUAL() { return getToken(HBaseSQLParser.LESSEQUAL, 0); }
		public LeOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitLeOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeOperContext leOper() throws RecognitionException {
		LeOperContext _localctx = new LeOperContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_leOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			_la = _input.LA(1);
			if ( !(_la==LESS || _la==LESSEQUAL) ) {
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

	public static class TsRangeContext extends ParserRuleContext {
		public TsRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tsRange; }
	 
		public TsRangeContext() { }
		public void copyFrom(TsRangeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TsequalContext extends TsRangeContext {
		public TerminalNode TS() { return getToken(HBaseSQLParser.TS, 0); }
		public TerminalNode EQ() { return getToken(HBaseSQLParser.EQ, 0); }
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public TsequalContext(TsRangeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitTsequal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Tsrange_startContext extends TsRangeContext {
		public TerminalNode STARTTS() { return getToken(HBaseSQLParser.STARTTS, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public Tsrange_startContext(TsRangeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitTsrange_start(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Tsrange_endContext extends TsRangeContext {
		public TerminalNode ENDTS() { return getToken(HBaseSQLParser.ENDTS, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public TsExpContext tsExp() {
			return getRuleContext(TsExpContext.class,0);
		}
		public Tsrange_endContext(TsRangeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitTsrange_end(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Tsrange_startAndEndContext extends TsRangeContext {
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public TerminalNode STARTTS() { return getToken(HBaseSQLParser.STARTTS, 0); }
		public GtOperContext gtOper() {
			return getRuleContext(GtOperContext.class,0);
		}
		public List<TsExpContext> tsExp() {
			return getRuleContexts(TsExpContext.class);
		}
		public TsExpContext tsExp(int i) {
			return getRuleContext(TsExpContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(HBaseSQLParser.COMMA, 0); }
		public TerminalNode ENDTS() { return getToken(HBaseSQLParser.ENDTS, 0); }
		public LeOperContext leOper() {
			return getRuleContext(LeOperContext.class,0);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public Tsrange_startAndEndContext(TsRangeContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitTsrange_startAndEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TsRangeContext tsRange() throws RecognitionException {
		TsRangeContext _localctx = new TsRangeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_tsRange);
		try {
			setState(363);
			switch (_input.LA(1)) {
			case LR_BRACKET:
				_localctx = new Tsrange_startAndEndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				match(LR_BRACKET);
				setState(343);
				match(STARTTS);
				setState(344);
				gtOper();
				setState(345);
				tsExp();
				setState(346);
				match(COMMA);
				setState(347);
				match(ENDTS);
				setState(348);
				leOper();
				setState(349);
				tsExp();
				setState(350);
				match(RR_BRACKET);
				}
				break;
			case STARTTS:
				_localctx = new Tsrange_startContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(352);
				match(STARTTS);
				setState(353);
				gtOper();
				setState(354);
				tsExp();
				}
				break;
			case ENDTS:
				_localctx = new Tsrange_endContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(356);
				match(ENDTS);
				setState(357);
				leOper();
				setState(358);
				tsExp();
				}
				break;
			case TS:
				_localctx = new TsequalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(360);
				match(TS);
				setState(361);
				match(EQ);
				setState(362);
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

	public static class ConstantContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode NULL() { return getToken(HBaseSQLParser.NULL, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_constant);
		try {
			setState(375);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(365);
				match(T__1);
				setState(366);
				value();
				setState(367);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				value();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(370);
				match(T__1);
				setState(371);
				match(NULL);
				setState(372);
				match(T__1);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(373);
				match(T__1);
				setState(374);
				match(T__1);
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

	public static class ConstantListContext extends ParserRuleContext {
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public ConstantListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitConstantList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantListContext constantList() throws RecognitionException {
		ConstantListContext _localctx = new ConstantListContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_constantList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			match(LR_BRACKET);
			setState(378);
			constant();
			setState(383);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(379);
				match(COMMA);
				setState(380);
				constant();
				}
				}
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(386);
			match(RR_BRACKET);
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			match(T__2);
			setState(389);
			match(ID);
			setState(390);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarListContext extends ParserRuleContext {
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public VarListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitVarList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarListContext varList() throws RecognitionException {
		VarListContext _localctx = new VarListContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_varList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			match(LR_BRACKET);
			setState(393);
			var();
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(394);
				match(COMMA);
				setState(395);
				var();
				}
				}
				setState(400);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(401);
			match(RR_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiVersionExpContext extends ParserRuleContext {
		public MaxVersionExpContext maxVersionExp() {
			return getRuleContext(MaxVersionExpContext.class,0);
		}
		public TsRangeContext tsRange() {
			return getRuleContext(TsRangeContext.class,0);
		}
		public MultiVersionExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiVersionExp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitMultiVersionExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiVersionExpContext multiVersionExp() throws RecognitionException {
		MultiVersionExpContext _localctx = new MultiVersionExpContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_multiVersionExp);
		try {
			setState(405);
			switch (_input.LA(1)) {
			case MAXVERSION:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				maxVersionExp();
				}
				break;
			case LR_BRACKET:
			case TS:
			case STARTTS:
			case ENDTS:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				tsRange();
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

	public static class MaxVersionExpContext extends ParserRuleContext {
		public TerminalNode MAXVERSION() { return getToken(HBaseSQLParser.MAXVERSION, 0); }
		public TerminalNode EQ() { return getToken(HBaseSQLParser.EQ, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public MaxVersionExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxVersionExp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitMaxVersionExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MaxVersionExpContext maxVersionExp() throws RecognitionException {
		MaxVersionExpContext _localctx = new MaxVersionExpContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_maxVersionExp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			match(MAXVERSION);
			setState(408);
			match(EQ);
			setState(409);
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

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_integer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
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
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitTsExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TsExpContext tsExp() throws RecognitionException {
		TsExpContext _localctx = new TsExpContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_tsExp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
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
		public TerminalNode ID() { return getToken(HBaseSQLParser.ID, 0); }
		public TimestampContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timestamp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitTimestamp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimestampContext timestamp() throws RecognitionException {
		TimestampContext _localctx = new TimestampContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_timestamp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
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

	public static class WherecContext extends ParserRuleContext {
		public ConditioncContext conditionc() {
			return getRuleContext(ConditioncContext.class,0);
		}
		public WherecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wherec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitWherec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WherecContext wherec() throws RecognitionException {
		WherecContext _localctx = new WherecContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_wherec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			conditionc(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditioncContext extends ParserRuleContext {
		public ConditioncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionc; }
	 
		public ConditioncContext() { }
		public void copyFrom(ConditioncContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NotequalvarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode NOTEQ() { return getToken(HBaseSQLParser.NOTEQ, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public NotequalvarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitNotequalvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessequalvarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode LESSEQUAL() { return getToken(HBaseSQLParser.LESSEQUAL, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public LessequalvarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitLessequalvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotbetweenvarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode NOT() { return getToken(HBaseSQLParser.NOT, 0); }
		public TerminalNode BETWEEN() { return getToken(HBaseSQLParser.BETWEEN, 0); }
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public TerminalNode AND() { return getToken(HBaseSQLParser.AND, 0); }
		public NotbetweenvarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitNotbetweenvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndconditionContext extends ConditioncContext {
		public List<ConditioncContext> conditionc() {
			return getRuleContexts(ConditioncContext.class);
		}
		public ConditioncContext conditionc(int i) {
			return getRuleContext(ConditioncContext.class,i);
		}
		public TerminalNode AND() { return getToken(HBaseSQLParser.AND, 0); }
		public AndconditionContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitAndcondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterequalconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode GREATEREQUAL() { return getToken(HBaseSQLParser.GREATEREQUAL, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public GreaterequalconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitGreaterequalconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualvarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode EQ() { return getToken(HBaseSQLParser.EQ, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public EqualvarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitEqualvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(HBaseSQLParser.GREATER, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public GreaterconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitGreaterconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BetweenvarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode BETWEEN() { return getToken(HBaseSQLParser.BETWEEN, 0); }
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public TerminalNode AND() { return getToken(HBaseSQLParser.AND, 0); }
		public BetweenvarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitBetweenvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BetweenconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode BETWEEN() { return getToken(HBaseSQLParser.BETWEEN, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode AND() { return getToken(HBaseSQLParser.AND, 0); }
		public BetweenconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitBetweenconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsnotnullcContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode IS() { return getToken(HBaseSQLParser.IS, 0); }
		public TerminalNode NOT() { return getToken(HBaseSQLParser.NOT, 0); }
		public TerminalNode NULL() { return getToken(HBaseSQLParser.NULL, 0); }
		public IsnotnullcContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitIsnotnullc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InconstantlistContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode IN() { return getToken(HBaseSQLParser.IN, 0); }
		public ConstantListContext constantList() {
			return getRuleContext(ConstantListContext.class,0);
		}
		public InconstantlistContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitInconstantlist(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotbetweenconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode NOT() { return getToken(HBaseSQLParser.NOT, 0); }
		public TerminalNode BETWEEN() { return getToken(HBaseSQLParser.BETWEEN, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode AND() { return getToken(HBaseSQLParser.AND, 0); }
		public NotbetweenconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitNotbetweenconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotinconstantlistContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode NOT() { return getToken(HBaseSQLParser.NOT, 0); }
		public TerminalNode IN() { return getToken(HBaseSQLParser.IN, 0); }
		public ConstantListContext constantList() {
			return getRuleContext(ConstantListContext.class,0);
		}
		public NotinconstantlistContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitNotinconstantlist(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrconditionContext extends ConditioncContext {
		public List<ConditioncContext> conditionc() {
			return getRuleContexts(ConditioncContext.class);
		}
		public ConditioncContext conditionc(int i) {
			return getRuleContext(ConditioncContext.class,i);
		}
		public TerminalNode OR() { return getToken(HBaseSQLParser.OR, 0); }
		public OrconditionContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitOrcondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsnullcContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode IS() { return getToken(HBaseSQLParser.IS, 0); }
		public TerminalNode NULL() { return getToken(HBaseSQLParser.NULL, 0); }
		public IsnullcContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitIsnullc(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqualconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode EQ() { return getToken(HBaseSQLParser.EQ, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public EqualconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitEqualconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreaterequalvarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode GREATEREQUAL() { return getToken(HBaseSQLParser.GREATEREQUAL, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public GreaterequalvarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitGreaterequalvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessvarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode LESS() { return getToken(HBaseSQLParser.LESS, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public LessvarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitLessvar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotequalconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode NOTEQ() { return getToken(HBaseSQLParser.NOTEQ, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public NotequalconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitNotequalconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InvarlistContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode IN() { return getToken(HBaseSQLParser.IN, 0); }
		public VarListContext varList() {
			return getRuleContext(VarListContext.class,0);
		}
		public InvarlistContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitInvarlist(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode LESS() { return getToken(HBaseSQLParser.LESS, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public LessconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitLessconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionwrapperContext extends ConditioncContext {
		public TerminalNode LR_BRACKET() { return getToken(HBaseSQLParser.LR_BRACKET, 0); }
		public ConditioncContext conditionc() {
			return getRuleContext(ConditioncContext.class,0);
		}
		public TerminalNode RR_BRACKET() { return getToken(HBaseSQLParser.RR_BRACKET, 0); }
		public ConditionwrapperContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitConditionwrapper(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotinvarlistContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode NOT() { return getToken(HBaseSQLParser.NOT, 0); }
		public TerminalNode IN() { return getToken(HBaseSQLParser.IN, 0); }
		public VarListContext varList() {
			return getRuleContext(VarListContext.class,0);
		}
		public NotinvarlistContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitNotinvarlist(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LessequalconstantContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode LESSEQUAL() { return getToken(HBaseSQLParser.LESSEQUAL, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public LessequalconstantContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitLessequalconstant(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GreatervarContext extends ConditioncContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(HBaseSQLParser.GREATER, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public GreatervarContext(ConditioncContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitGreatervar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditioncContext conditionc() throws RecognitionException {
		return conditionc(0);
	}

	private ConditioncContext conditionc(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditioncContext _localctx = new ConditioncContext(_ctx, _parentState);
		ConditioncContext _prevctx = _localctx;
		int _startState = 78;
		enterRecursionRule(_localctx, 78, RULE_conditionc, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				_localctx = new ConditionwrapperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(420);
				match(LR_BRACKET);
				setState(421);
				conditionc(0);
				setState(422);
				match(RR_BRACKET);
				}
				break;
			case 2:
				{
				_localctx = new EqualconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(424);
				column();
				setState(425);
				match(EQ);
				setState(426);
				constant();
				}
				break;
			case 3:
				{
				_localctx = new EqualvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(428);
				column();
				setState(429);
				match(EQ);
				setState(430);
				var();
				}
				break;
			case 4:
				{
				_localctx = new NotequalconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(432);
				column();
				setState(433);
				match(NOTEQ);
				setState(434);
				constant();
				}
				break;
			case 5:
				{
				_localctx = new NotequalvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(436);
				column();
				setState(437);
				match(NOTEQ);
				setState(438);
				var();
				}
				break;
			case 6:
				{
				_localctx = new LessconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(440);
				column();
				setState(441);
				match(LESS);
				setState(442);
				constant();
				}
				break;
			case 7:
				{
				_localctx = new LessvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(444);
				column();
				setState(445);
				match(LESS);
				setState(446);
				var();
				}
				break;
			case 8:
				{
				_localctx = new GreaterconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(448);
				column();
				setState(449);
				match(GREATER);
				setState(450);
				constant();
				}
				break;
			case 9:
				{
				_localctx = new GreatervarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(452);
				column();
				setState(453);
				match(GREATER);
				setState(454);
				var();
				}
				break;
			case 10:
				{
				_localctx = new LessequalconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(456);
				column();
				setState(457);
				match(LESSEQUAL);
				setState(458);
				constant();
				}
				break;
			case 11:
				{
				_localctx = new LessequalvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(460);
				column();
				setState(461);
				match(LESSEQUAL);
				setState(462);
				var();
				}
				break;
			case 12:
				{
				_localctx = new GreaterequalconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(464);
				column();
				setState(465);
				match(GREATEREQUAL);
				setState(466);
				constant();
				}
				break;
			case 13:
				{
				_localctx = new GreaterequalvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(468);
				column();
				setState(469);
				match(GREATEREQUAL);
				setState(470);
				var();
				}
				break;
			case 14:
				{
				_localctx = new InconstantlistContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(472);
				column();
				setState(473);
				match(IN);
				setState(474);
				constantList();
				}
				break;
			case 15:
				{
				_localctx = new InvarlistContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(476);
				column();
				setState(477);
				match(IN);
				setState(478);
				varList();
				}
				break;
			case 16:
				{
				_localctx = new NotinconstantlistContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(480);
				column();
				setState(481);
				match(NOT);
				setState(482);
				match(IN);
				setState(483);
				constantList();
				}
				break;
			case 17:
				{
				_localctx = new NotinvarlistContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(485);
				column();
				setState(486);
				match(NOT);
				setState(487);
				match(IN);
				setState(488);
				varList();
				}
				break;
			case 18:
				{
				_localctx = new BetweenconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(490);
				column();
				setState(491);
				match(BETWEEN);
				setState(492);
				constant();
				setState(493);
				match(AND);
				setState(494);
				constant();
				}
				break;
			case 19:
				{
				_localctx = new BetweenvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(496);
				column();
				setState(497);
				match(BETWEEN);
				setState(498);
				var();
				setState(499);
				match(AND);
				setState(500);
				var();
				}
				break;
			case 20:
				{
				_localctx = new NotbetweenconstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(502);
				column();
				setState(503);
				match(NOT);
				setState(504);
				match(BETWEEN);
				setState(505);
				constant();
				setState(506);
				match(AND);
				setState(507);
				constant();
				}
				break;
			case 21:
				{
				_localctx = new NotbetweenvarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(509);
				column();
				setState(510);
				match(NOT);
				setState(511);
				match(BETWEEN);
				setState(512);
				var();
				setState(513);
				match(AND);
				setState(514);
				var();
				}
				break;
			case 22:
				{
				_localctx = new IsnullcContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(516);
				column();
				setState(517);
				match(IS);
				setState(518);
				match(NULL);
				}
				break;
			case 23:
				{
				_localctx = new IsnotnullcContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(520);
				column();
				setState(521);
				match(IS);
				setState(522);
				match(NOT);
				setState(523);
				match(NULL);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(535);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(533);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
					case 1:
						{
						_localctx = new AndconditionContext(new ConditioncContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_conditionc);
						setState(527);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(528);
						match(AND);
						setState(529);
						conditionc(25);
						}
						break;
					case 2:
						{
						_localctx = new OrconditionContext(new ConditioncContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_conditionc);
						setState(530);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(531);
						match(OR);
						setState(532);
						conditionc(24);
						}
						break;
					}
					} 
				}
				setState(537);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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

	public static class LimitExpContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(HBaseSQLParser.LIMIT, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public LimitExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitExp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HBaseSQLVisitor ) return ((HBaseSQLVisitor<? extends T>)visitor).visitLimitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitExpContext limitExp() throws RecognitionException {
		LimitExpContext _localctx = new LimitExpContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_limitExp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			match(LIMIT);
			setState(539);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 39:
			return conditionc_sempred((ConditioncContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean conditionc_sempred(ConditioncContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 24);
		case 1:
			return precpred(_ctx, 23);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3C\u0220\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\5\2\\\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3k\n\3\3\3\5\3n\n\3\3\4\3\4\3\4\3\4\3\4\5\4u\n\4\3\5\3"+
		"\5\3\5\3\5\5\5{\n\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0083\n\6\3\7\3\7\3\b"+
		"\3\b\3\b\7\b\u008a\n\b\f\b\16\b\u008d\13\b\3\t\3\t\3\t\5\t\u0092\n\t\3"+
		"\t\5\t\u0095\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\7\f\u009e\n\f\f\f\16\f"+
		"\u00a1\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\5\20\u00b1\n\20\3\21\3\21\3\21\7\21\u00b6\n\21\f\21\16\21\u00b9\13"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c7"+
		"\n\22\3\22\5\22\u00ca\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u00d4\n\23\f\23\16\23\u00d7\13\23\3\24\3\24\3\24\7\24\u00dc\n\24\f\24"+
		"\16\24\u00df\13\24\3\25\6\25\u00e2\n\25\r\25\16\25\u00e3\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\5\26\u00ee\n\26\3\26\3\26\5\26\u00f2\n\26\3"+
		"\26\5\26\u00f5\n\26\3\26\5\26\u00f8\n\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u0102\n\27\3\27\3\27\3\27\3\27\5\27\u0108\n\27\3\27\5"+
		"\27\u010b\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\7\30\u0126\n\30\f\30\16\30\u0129\13\30\3\30\3\30\3\30\3\30\3\30\5\30"+
		"\u0130\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u013a\n\31\3"+
		"\32\3\32\3\32\3\32\7\32\u0140\n\32\f\32\16\32\u0143\13\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0153"+
		"\n\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u016e"+
		"\n\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u017a\n\37"+
		"\3 \3 \3 \3 \7 \u0180\n \f \16 \u0183\13 \3 \3 \3!\3!\3!\3!\3\"\3\"\3"+
		"\"\3\"\7\"\u018f\n\"\f\"\16\"\u0192\13\"\3\"\3\"\3#\3#\5#\u0198\n#\3$"+
		"\3$\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\3)\5)\u0210\n)\3)\3)\3)\3)\3)\3)\7)\u0218\n)\f)\16)\u021b\13)\3*"+
		"\3*\3*\3*\2\3P+\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJLNPR\2\5\5\2\67\67::?@\3\2\16\17\3\2\20\21\u0241\2[\3"+
		"\2\2\2\4]\3\2\2\2\6o\3\2\2\2\bv\3\2\2\2\n|\3\2\2\2\f\u0084\3\2\2\2\16"+
		"\u0086\3\2\2\2\20\u008e\3\2\2\2\22\u0096\3\2\2\2\24\u0098\3\2\2\2\26\u009a"+
		"\3\2\2\2\30\u00a2\3\2\2\2\32\u00aa\3\2\2\2\34\u00ac\3\2\2\2\36\u00b0\3"+
		"\2\2\2 \u00b2\3\2\2\2\"\u00ba\3\2\2\2$\u00cb\3\2\2\2&\u00d8\3\2\2\2(\u00e1"+
		"\3\2\2\2*\u00e5\3\2\2\2,\u00f9\3\2\2\2.\u012f\3\2\2\2\60\u0139\3\2\2\2"+
		"\62\u013b\3\2\2\2\64\u0152\3\2\2\2\66\u0154\3\2\2\28\u0156\3\2\2\2:\u016d"+
		"\3\2\2\2<\u0179\3\2\2\2>\u017b\3\2\2\2@\u0186\3\2\2\2B\u018a\3\2\2\2D"+
		"\u0197\3\2\2\2F\u0199\3\2\2\2H\u019d\3\2\2\2J\u019f\3\2\2\2L\u01a1\3\2"+
		"\2\2N\u01a3\3\2\2\2P\u020f\3\2\2\2R\u021c\3\2\2\2T\\\5\4\3\2U\\\5\6\4"+
		"\2V\\\5\b\5\2W\\\5\n\6\2X\\\5\"\22\2Y\\\5*\26\2Z\\\5,\27\2[T\3\2\2\2["+
		"U\3\2\2\2[V\3\2\2\2[W\3\2\2\2[X\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\\3\3\2\2"+
		"\2]^\7 \2\2^_\7#\2\2_`\7$\2\2`a\5\f\7\2ab\7\7\2\2bc\5\16\b\2cj\7\b\2\2"+
		"de\7&\2\2ef\7\'\2\2fg\7\7\2\2gh\5\26\f\2hi\7\b\2\2ik\3\2\2\2jd\3\2\2\2"+
		"jk\3\2\2\2km\3\2\2\2ln\7\n\2\2ml\3\2\2\2mn\3\2\2\2n\5\3\2\2\2op\7!\2\2"+
		"pq\7#\2\2qr\7$\2\2rt\5\f\7\2su\7\n\2\2ts\3\2\2\2tu\3\2\2\2u\7\3\2\2\2"+
		"vw\7\"\2\2wx\7#\2\2xz\7%\2\2y{\7\n\2\2zy\3\2\2\2z{\3\2\2\2{\t\3\2\2\2"+
		"|}\7\"\2\2}~\7 \2\2~\177\7#\2\2\177\u0080\7$\2\2\u0080\u0082\5\f\7\2\u0081"+
		"\u0083\7\n\2\2\u0082\u0081\3\2\2\2\u0082\u0083\3\2\2\2\u0083\13\3\2\2"+
		"\2\u0084\u0085\7?\2\2\u0085\r\3\2\2\2\u0086\u008b\5\20\t\2\u0087\u0088"+
		"\7\t\2\2\u0088\u008a\5\20\t\2\u0089\u0087\3\2\2\2\u008a\u008d\3\2\2\2"+
		"\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\17\3\2\2\2\u008d\u008b"+
		"\3\2\2\2\u008e\u008f\5\22\n\2\u008f\u0091\5\24\13\2\u0090\u0092\7;\2\2"+
		"\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0095"+
		"\7(\2\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\21\3\2\2\2\u0096"+
		"\u0097\7?\2\2\u0097\23\3\2\2\2\u0098\u0099\7?\2\2\u0099\25\3\2\2\2\u009a"+
		"\u009f\5\30\r\2\u009b\u009c\7\t\2\2\u009c\u009e\5\30\r\2\u009d\u009b\3"+
		"\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\27\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3\7\13\2\2\u00a3\u00a4\7?\2"+
		"\2\u00a4\u00a5\7\13\2\2\u00a5\u00a6\7\f\2\2\u00a6\u00a7\7\13\2\2\u00a7"+
		"\u00a8\7?\2\2\u00a8\u00a9\7\13\2\2\u00a9\31\3\2\2\2\u00aa\u00ab\7?\2\2"+
		"\u00ab\33\3\2\2\2\u00ac\u00ad\7?\2\2\u00ad\35\3\2\2\2\u00ae\u00b1\7\24"+
		"\2\2\u00af\u00b1\5 \21\2\u00b0\u00ae\3\2\2\2\u00b0\u00af\3\2\2\2\u00b1"+
		"\37\3\2\2\2\u00b2\u00b7\5\32\16\2\u00b3\u00b4\7\t\2\2\u00b4\u00b6\5\32"+
		"\16\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8!\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\7)\2\2\u00bb"+
		"\u00bc\7*\2\2\u00bc\u00bd\5\f\7\2\u00bd\u00be\7\7\2\2\u00be\u00bf\5 \21"+
		"\2\u00bf\u00c0\7\b\2\2\u00c0\u00c1\7+\2\2\u00c1\u00c6\5$\23\2\u00c2\u00c3"+
		"\7\62\2\2\u00c3\u00c4\7\35\2\2\u00c4\u00c5\7\f\2\2\u00c5\u00c7\5J&\2\u00c6"+
		"\u00c2\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00ca\7\n"+
		"\2\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca#\3\2\2\2\u00cb\u00cc"+
		"\7\7\2\2\u00cc\u00cd\5&\24\2\u00cd\u00d5\7\b\2\2\u00ce\u00cf\7\t\2\2\u00cf"+
		"\u00d0\7\7\2\2\u00d0\u00d1\5&\24\2\u00d1\u00d2\7\b\2\2\u00d2\u00d4\3\2"+
		"\2\2\u00d3\u00ce\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6%\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00dd\5(\25\2"+
		"\u00d9\u00da\7\t\2\2\u00da\u00dc\5(\25\2\u00db\u00d9\3\2\2\2\u00dc\u00df"+
		"\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\'\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00e0\u00e2\t\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4)\3\2\2\2\u00e5\u00e6"+
		"\7,\2\2\u00e6\u00e7\5\36\20\2\u00e7\u00e8\7\60\2\2\u00e8\u00e9\5\f\7\2"+
		"\u00e9\u00ea\7\62\2\2\u00ea\u00ed\5.\30\2\u00eb\u00ec\7\63\2\2\u00ec\u00ee"+
		"\5N(\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef"+
		"\u00f0\7\63\2\2\u00f0\u00f2\5D#\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2"+
		"\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f5\5R*\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f8\7\n\2\2\u00f7\u00f6\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8+\3\2\2\2\u00f9\u00fa\7/\2\2\u00fa\u00fb\5\36\20\2"+
		"\u00fb\u00fc\7\60\2\2\u00fc\u00fd\5\f\7\2\u00fd\u00fe\7\62\2\2\u00fe\u0101"+
		"\5.\30\2\u00ff\u0100\7\63\2\2\u0100\u0102\5N(\2\u0101\u00ff\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0107\3\2\2\2\u0103\u0104\7\63\2\2\u0104\u0105\7"+
		"\35\2\2\u0105\u0106\7\f\2\2\u0106\u0108\5J&\2\u0107\u0103\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u010b\7\n\2\2\u010a\u0109\3\2"+
		"\2\2\u010a\u010b\3\2\2\2\u010b-\3\2\2\2\u010c\u010d\7<\2\2\u010d\u010e"+
		"\5\66\34\2\u010e\u010f\5\60\31\2\u010f\u0110\7\63\2\2\u0110\u0111\7=\2"+
		"\2\u0111\u0112\58\35\2\u0112\u0113\5\60\31\2\u0113\u0130\3\2\2\2\u0114"+
		"\u0115\7<\2\2\u0115\u0116\5\66\34\2\u0116\u0117\5\60\31\2\u0117\u0130"+
		"\3\2\2\2\u0118\u0119\7=\2\2\u0119\u011a\58\35\2\u011a\u011b\5\60\31\2"+
		"\u011b\u0130\3\2\2\2\u011c\u011d\7:\2\2\u011d\u011e\7\f\2\2\u011e\u0130"+
		"\5\60\31\2\u011f\u0120\7:\2\2\u0120\u0121\7\65\2\2\u0121\u0122\7\7\2\2"+
		"\u0122\u0127\5\60\31\2\u0123\u0124\7\t\2\2\u0124\u0126\5\60\31\2\u0125"+
		"\u0123\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2"+
		"\2\2\u0128\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012b\7\b\2\2\u012b"+
		"\u0130\3\2\2\2\u012c\u012d\7:\2\2\u012d\u012e\7\66\2\2\u012e\u0130\5\60"+
		"\31\2\u012f\u010c\3\2\2\2\u012f\u0114\3\2\2\2\u012f\u0118\3\2\2\2\u012f"+
		"\u011c\3\2\2\2\u012f\u011f\3\2\2\2\u012f\u012c\3\2\2\2\u0130/\3\2\2\2"+
		"\u0131\u0132\7\7\2\2\u0132\u0133\5\60\31\2\u0133\u0134\7\b\2\2\u0134\u013a"+
		"\3\2\2\2\u0135\u013a\5(\25\2\u0136\u0137\5\34\17\2\u0137\u0138\5\62\32"+
		"\2\u0138\u013a\3\2\2\2\u0139\u0131\3\2\2\2\u0139\u0135\3\2\2\2\u0139\u0136"+
		"\3\2\2\2\u013a\61\3\2\2\2\u013b\u013c\7\7\2\2\u013c\u0141\5\64\33\2\u013d"+
		"\u013e\7\t\2\2\u013e\u0140\5\64\33\2\u013f\u013d\3\2\2\2\u0140\u0143\3"+
		"\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0144\3\2\2\2\u0143"+
		"\u0141\3\2\2\2\u0144\u0145\7\b\2\2\u0145\63\3\2\2\2\u0146\u0147\7\3\2"+
		"\2\u0147\u0148\5(\25\2\u0148\u0149\7\3\2\2\u0149\u0153\3\2\2\2\u014a\u014b"+
		"\7\4\2\2\u014b\u014c\5(\25\2\u014c\u014d\7\4\2\2\u014d\u0153\3\2\2\2\u014e"+
		"\u0153\5(\25\2\u014f\u0150\7\4\2\2\u0150\u0153\7\4\2\2\u0151\u0153\3\2"+
		"\2\2\u0152\u0146\3\2\2\2\u0152\u014a\3\2\2\2\u0152\u014e\3\2\2\2\u0152"+
		"\u014f\3\2\2\2\u0152\u0151\3\2\2\2\u0153\65\3\2\2\2\u0154\u0155\t\3\2"+
		"\2\u0155\67\3\2\2\2\u0156\u0157\t\4\2\2\u01579\3\2\2\2\u0158\u0159\7\7"+
		"\2\2\u0159\u015a\7\36\2\2\u015a\u015b\5\66\34\2\u015b\u015c\5J&\2\u015c"+
		"\u015d\7\t\2\2\u015d\u015e\7\37\2\2\u015e\u015f\58\35\2\u015f\u0160\5"+
		"J&\2\u0160\u0161\7\b\2\2\u0161\u016e\3\2\2\2\u0162\u0163\7\36\2\2\u0163"+
		"\u0164\5\66\34\2\u0164\u0165\5J&\2\u0165\u016e\3\2\2\2\u0166\u0167\7\37"+
		"\2\2\u0167\u0168\58\35\2\u0168\u0169\5J&\2\u0169\u016e\3\2\2\2\u016a\u016b"+
		"\7\35\2\2\u016b\u016c\7\f\2\2\u016c\u016e\5J&\2\u016d\u0158\3\2\2\2\u016d"+
		"\u0162\3\2\2\2\u016d\u0166\3\2\2\2\u016d\u016a\3\2\2\2\u016e;\3\2\2\2"+
		"\u016f\u0170\7\4\2\2\u0170\u0171\5(\25\2\u0171\u0172\7\4\2\2\u0172\u017a"+
		"\3\2\2\2\u0173\u017a\5(\25\2\u0174\u0175\7\4\2\2\u0175\u0176\7\67\2\2"+
		"\u0176\u017a\7\4\2\2\u0177\u0178\7\4\2\2\u0178\u017a\7\4\2\2\u0179\u016f"+
		"\3\2\2\2\u0179\u0173\3\2\2\2\u0179\u0174\3\2\2\2\u0179\u0177\3\2\2\2\u017a"+
		"=\3\2\2\2\u017b\u017c\7\7\2\2\u017c\u0181\5<\37\2\u017d\u017e\7\t\2\2"+
		"\u017e\u0180\5<\37\2\u017f\u017d\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f"+
		"\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0184\3\2\2\2\u0183\u0181\3\2\2\2\u0184"+
		"\u0185\7\b\2\2\u0185?\3\2\2\2\u0186\u0187\7\5\2\2\u0187\u0188\7?\2\2\u0188"+
		"\u0189\7\6\2\2\u0189A\3\2\2\2\u018a\u018b\7\7\2\2\u018b\u0190\5@!\2\u018c"+
		"\u018d\7\t\2\2\u018d\u018f\5@!\2\u018e\u018c\3\2\2\2\u018f\u0192\3\2\2"+
		"\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2\2\2\u0192\u0190"+
		"\3\2\2\2\u0193\u0194\7\b\2\2\u0194C\3\2\2\2\u0195\u0198\5F$\2\u0196\u0198"+
		"\5:\36\2\u0197\u0195\3\2\2\2\u0197\u0196\3\2\2\2\u0198E\3\2\2\2\u0199"+
		"\u019a\7>\2\2\u019a\u019b\7\f\2\2\u019b\u019c\5H%\2\u019cG\3\2\2\2\u019d"+
		"\u019e\7?\2\2\u019eI\3\2\2\2\u019f\u01a0\5L\'\2\u01a0K\3\2\2\2\u01a1\u01a2"+
		"\7?\2\2\u01a2M\3\2\2\2\u01a3\u01a4\5P)\2\u01a4O\3\2\2\2\u01a5\u01a6\b"+
		")\1\2\u01a6\u01a7\7\7\2\2\u01a7\u01a8\5P)\2\u01a8\u01a9\7\b\2\2\u01a9"+
		"\u0210\3\2\2\2\u01aa\u01ab\5\32\16\2\u01ab\u01ac\7\f\2\2\u01ac\u01ad\5"+
		"<\37\2\u01ad\u0210\3\2\2\2\u01ae\u01af\5\32\16\2\u01af\u01b0\7\f\2\2\u01b0"+
		"\u01b1\5@!\2\u01b1\u0210\3\2\2\2\u01b2\u01b3\5\32\16\2\u01b3\u01b4\7\r"+
		"\2\2\u01b4\u01b5\5<\37\2\u01b5\u0210\3\2\2\2\u01b6\u01b7\5\32\16\2\u01b7"+
		"\u01b8\7\r\2\2\u01b8\u01b9\5@!\2\u01b9\u0210\3\2\2\2\u01ba\u01bb\5\32"+
		"\16\2\u01bb\u01bc\7\20\2\2\u01bc\u01bd\5<\37\2\u01bd\u0210\3\2\2\2\u01be"+
		"\u01bf\5\32\16\2\u01bf\u01c0\7\20\2\2\u01c0\u01c1\5@!\2\u01c1\u0210\3"+
		"\2\2\2\u01c2\u01c3\5\32\16\2\u01c3\u01c4\7\16\2\2\u01c4\u01c5\5<\37\2"+
		"\u01c5\u0210\3\2\2\2\u01c6\u01c7\5\32\16\2\u01c7\u01c8\7\16\2\2\u01c8"+
		"\u01c9\5@!\2\u01c9\u0210\3\2\2\2\u01ca\u01cb\5\32\16\2\u01cb\u01cc\7\21"+
		"\2\2\u01cc\u01cd\5<\37\2\u01cd\u0210\3\2\2\2\u01ce\u01cf\5\32\16\2\u01cf"+
		"\u01d0\7\21\2\2\u01d0\u01d1\5@!\2\u01d1\u0210\3\2\2\2\u01d2\u01d3\5\32"+
		"\16\2\u01d3\u01d4\7\17\2\2\u01d4\u01d5\5<\37\2\u01d5\u0210\3\2\2\2\u01d6"+
		"\u01d7\5\32\16\2\u01d7\u01d8\7\17\2\2\u01d8\u01d9\5@!\2\u01d9\u0210\3"+
		"\2\2\2\u01da\u01db\5\32\16\2\u01db\u01dc\7\65\2\2\u01dc\u01dd\5> \2\u01dd"+
		"\u0210\3\2\2\2\u01de\u01df\5\32\16\2\u01df\u01e0\7\65\2\2\u01e0\u01e1"+
		"\5B\"\2\u01e1\u0210\3\2\2\2\u01e2\u01e3\5\32\16\2\u01e3\u01e4\78\2\2\u01e4"+
		"\u01e5\7\65\2\2\u01e5\u01e6\5> \2\u01e6\u0210\3\2\2\2\u01e7\u01e8\5\32"+
		"\16\2\u01e8\u01e9\78\2\2\u01e9\u01ea\7\65\2\2\u01ea\u01eb\5B\"\2\u01eb"+
		"\u0210\3\2\2\2\u01ec\u01ed\5\32\16\2\u01ed\u01ee\7\32\2\2\u01ee\u01ef"+
		"\5<\37\2\u01ef\u01f0\7\63\2\2\u01f0\u01f1\5<\37\2\u01f1\u0210\3\2\2\2"+
		"\u01f2\u01f3\5\32\16\2\u01f3\u01f4\7\32\2\2\u01f4\u01f5\5@!\2\u01f5\u01f6"+
		"\7\63\2\2\u01f6\u01f7\5@!\2\u01f7\u0210\3\2\2\2\u01f8\u01f9\5\32\16\2"+
		"\u01f9\u01fa\78\2\2\u01fa\u01fb\7\32\2\2\u01fb\u01fc\5<\37\2\u01fc\u01fd"+
		"\7\63\2\2\u01fd\u01fe\5<\37\2\u01fe\u0210\3\2\2\2\u01ff\u0200\5\32\16"+
		"\2\u0200\u0201\78\2\2\u0201\u0202\7\32\2\2\u0202\u0203\5@!\2\u0203\u0204"+
		"\7\63\2\2\u0204\u0205\5@!\2\u0205\u0210\3\2\2\2\u0206\u0207\5\32\16\2"+
		"\u0207\u0208\7\27\2\2\u0208\u0209\7\67\2\2\u0209\u0210\3\2\2\2\u020a\u020b"+
		"\5\32\16\2\u020b\u020c\7\27\2\2\u020c\u020d\78\2\2\u020d\u020e\7\67\2"+
		"\2\u020e\u0210\3\2\2\2\u020f\u01a5\3\2\2\2\u020f\u01aa\3\2\2\2\u020f\u01ae"+
		"\3\2\2\2\u020f\u01b2\3\2\2\2\u020f\u01b6\3\2\2\2\u020f\u01ba\3\2\2\2\u020f"+
		"\u01be\3\2\2\2\u020f\u01c2\3\2\2\2\u020f\u01c6\3\2\2\2\u020f\u01ca\3\2"+
		"\2\2\u020f\u01ce\3\2\2\2\u020f\u01d2\3\2\2\2\u020f\u01d6\3\2\2\2\u020f"+
		"\u01da\3\2\2\2\u020f\u01de\3\2\2\2\u020f\u01e2\3\2\2\2\u020f\u01e7\3\2"+
		"\2\2\u020f\u01ec\3\2\2\2\u020f\u01f2\3\2\2\2\u020f\u01f8\3\2\2\2\u020f"+
		"\u01ff\3\2\2\2\u020f\u0206\3\2\2\2\u020f\u020a\3\2\2\2\u0210\u0219\3\2"+
		"\2\2\u0211\u0212\f\32\2\2\u0212\u0213\7\63\2\2\u0213\u0218\5P)\33\u0214"+
		"\u0215\f\31\2\2\u0215\u0216\7\64\2\2\u0216\u0218\5P)\32\u0217\u0211\3"+
		"\2\2\2\u0217\u0214\3\2\2\2\u0218\u021b\3\2\2\2\u0219\u0217\3\2\2\2\u0219"+
		"\u021a\3\2\2\2\u021aQ\3\2\2\2\u021b\u0219\3\2\2\2\u021c\u021d\7\34\2\2"+
		"\u021d\u021e\5H%\2\u021eS\3\2\2\2\'[jmtz\u0082\u008b\u0091\u0094\u009f"+
		"\u00b0\u00b7\u00c6\u00c9\u00d5\u00dd\u00e3\u00ed\u00f1\u00f4\u00f7\u0101"+
		"\u0107\u010a\u0127\u012f\u0139\u0141\u0152\u016d\u0179\u0181\u0190\u0197"+
		"\u020f\u0217\u0219";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}