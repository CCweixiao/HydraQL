// Generated from /Users/leojie/other_project/HydraQL/antlr/test/HydraQLParser.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HydraQLParser}.
 */
public interface HydraQLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#hql}.
	 * @param ctx the parse tree
	 */
	void enterHql(HydraQLParser.HqlContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#hql}.
	 * @param ctx the parse tree
	 */
	void exitHql(HydraQLParser.HqlContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#batch}.
	 * @param ctx the parse tree
	 */
	void enterBatch(HydraQLParser.BatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#batch}.
	 * @param ctx the parse tree
	 */
	void exitBatch(HydraQLParser.BatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#sql_command}.
	 * @param ctx the parse tree
	 */
	void enterSql_command(HydraQLParser.Sql_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#sql_command}.
	 * @param ctx the parse tree
	 */
	void exitSql_command(HydraQLParser.Sql_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#ddl_command}.
	 * @param ctx the parse tree
	 */
	void enterDdl_command(HydraQLParser.Ddl_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#ddl_command}.
	 * @param ctx the parse tree
	 */
	void exitDdl_command(HydraQLParser.Ddl_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#dml_command}.
	 * @param ctx the parse tree
	 */
	void enterDml_command(HydraQLParser.Dml_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#dml_command}.
	 * @param ctx the parse tree
	 */
	void exitDml_command(HydraQLParser.Dml_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#create_virtual_table_command}.
	 * @param ctx the parse tree
	 */
	void enterCreate_virtual_table_command(HydraQLParser.Create_virtual_table_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#create_virtual_table_command}.
	 * @param ctx the parse tree
	 */
	void exitCreate_virtual_table_command(HydraQLParser.Create_virtual_table_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#table_options}.
	 * @param ctx the parse tree
	 */
	void enterTable_options(HydraQLParser.Table_optionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#table_options}.
	 * @param ctx the parse tree
	 */
	void exitTable_options(HydraQLParser.Table_optionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#options_}.
	 * @param ctx the parse tree
	 */
	void enterOptions_(HydraQLParser.Options_Context ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#options_}.
	 * @param ctx the parse tree
	 */
	void exitOptions_(HydraQLParser.Options_Context ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(HydraQLParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(HydraQLParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#drop_table_command}.
	 * @param ctx the parse tree
	 */
	void enterDrop_table_command(HydraQLParser.Drop_table_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#drop_table_command}.
	 * @param ctx the parse tree
	 */
	void exitDrop_table_command(HydraQLParser.Drop_table_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#show_tables_command}.
	 * @param ctx the parse tree
	 */
	void enterShow_tables_command(HydraQLParser.Show_tables_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#show_tables_command}.
	 * @param ctx the parse tree
	 */
	void exitShow_tables_command(HydraQLParser.Show_tables_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#show_table_command}.
	 * @param ctx the parse tree
	 */
	void enterShow_table_command(HydraQLParser.Show_table_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#show_table_command}.
	 * @param ctx the parse tree
	 */
	void exitShow_table_command(HydraQLParser.Show_table_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#if_exists}.
	 * @param ctx the parse tree
	 */
	void enterIf_exists(HydraQLParser.If_existsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#if_exists}.
	 * @param ctx the parse tree
	 */
	void exitIf_exists(HydraQLParser.If_existsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#if_not_exists}.
	 * @param ctx the parse tree
	 */
	void enterIf_not_exists(HydraQLParser.If_not_existsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#if_not_exists}.
	 * @param ctx the parse tree
	 */
	void exitIf_not_exists(HydraQLParser.If_not_existsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#table_ref}.
	 * @param ctx the parse tree
	 */
	void enterTable_ref(HydraQLParser.Table_refContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#table_ref}.
	 * @param ctx the parse tree
	 */
	void exitTable_ref(HydraQLParser.Table_refContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#upsert_values_command}.
	 * @param ctx the parse tree
	 */
	void enterUpsert_values_command(HydraQLParser.Upsert_values_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#upsert_values_command}.
	 * @param ctx the parse tree
	 */
	void exitUpsert_values_command(HydraQLParser.Upsert_values_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#insert_values}.
	 * @param ctx the parse tree
	 */
	void enterInsert_values(HydraQLParser.Insert_valuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#insert_values}.
	 * @param ctx the parse tree
	 */
	void exitInsert_values(HydraQLParser.Insert_valuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#column_ref_list}.
	 * @param ctx the parse tree
	 */
	void enterColumn_ref_list(HydraQLParser.Column_ref_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#column_ref_list}.
	 * @param ctx the parse tree
	 */
	void exitColumn_ref_list(HydraQLParser.Column_ref_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#column_def_list}.
	 * @param ctx the parse tree
	 */
	void enterColumn_def_list(HydraQLParser.Column_def_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#column_def_list}.
	 * @param ctx the parse tree
	 */
	void exitColumn_def_list(HydraQLParser.Column_def_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#delete_command}.
	 * @param ctx the parse tree
	 */
	void enterDelete_command(HydraQLParser.Delete_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#delete_command}.
	 * @param ctx the parse tree
	 */
	void exitDelete_command(HydraQLParser.Delete_commandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tsrange_startAndEnd}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void enterTsrange_startAndEnd(HydraQLParser.Tsrange_startAndEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tsrange_startAndEnd}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void exitTsrange_startAndEnd(HydraQLParser.Tsrange_startAndEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tsrange_start}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void enterTsrange_start(HydraQLParser.Tsrange_startContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tsrange_start}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void exitTsrange_start(HydraQLParser.Tsrange_startContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tsrange_end}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void enterTsrange_end(HydraQLParser.Tsrange_endContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tsrange_end}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void exitTsrange_end(HydraQLParser.Tsrange_endContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tsequal}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void enterTsequal(HydraQLParser.TsequalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tsequal}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 */
	void exitTsequal(HydraQLParser.TsequalContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#tsExp}.
	 * @param ctx the parse tree
	 */
	void enterTsExp(HydraQLParser.TsExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#tsExp}.
	 * @param ctx the parse tree
	 */
	void exitTsExp(HydraQLParser.TsExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#timestamp}.
	 * @param ctx the parse tree
	 */
	void enterTimestamp(HydraQLParser.TimestampContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#timestamp}.
	 * @param ctx the parse tree
	 */
	void exitTimestamp(HydraQLParser.TimestampContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#gtOper}.
	 * @param ctx the parse tree
	 */
	void enterGtOper(HydraQLParser.GtOperContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#gtOper}.
	 * @param ctx the parse tree
	 */
	void exitGtOper(HydraQLParser.GtOperContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#leOper}.
	 * @param ctx the parse tree
	 */
	void enterLeOper(HydraQLParser.LeOperContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#leOper}.
	 * @param ctx the parse tree
	 */
	void exitLeOper(HydraQLParser.LeOperContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#versions_clause}.
	 * @param ctx the parse tree
	 */
	void enterVersions_clause(HydraQLParser.Versions_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#versions_clause}.
	 * @param ctx the parse tree
	 */
	void exitVersions_clause(HydraQLParser.Versions_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#limit_clause}.
	 * @param ctx the parse tree
	 */
	void enterLimit_clause(HydraQLParser.Limit_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#limit_clause}.
	 * @param ctx the parse tree
	 */
	void exitLimit_clause(HydraQLParser.Limit_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void enterWhere_clause(HydraQLParser.Where_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void exitWhere_clause(HydraQLParser.Where_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#select_command}.
	 * @param ctx the parse tree
	 */
	void enterSelect_command(HydraQLParser.Select_commandContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#select_command}.
	 * @param ctx the parse tree
	 */
	void exitSelect_command(HydraQLParser.Select_commandContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#select_statement}.
	 * @param ctx the parse tree
	 */
	void enterSelect_statement(HydraQLParser.Select_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#select_statement}.
	 * @param ctx the parse tree
	 */
	void exitSelect_statement(HydraQLParser.Select_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(HydraQLParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(HydraQLParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectAllFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void enterSelectAllFamilyAndCol(HydraQLParser.SelectAllFamilyAndColContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectAllFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void exitSelectAllFamilyAndCol(HydraQLParser.SelectAllFamilyAndColContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectOneFamilyAllCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void enterSelectOneFamilyAllCol(HydraQLParser.SelectOneFamilyAllColContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectOneFamilyAllCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void exitSelectOneFamilyAllCol(HydraQLParser.SelectOneFamilyAllColContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void enterSelectFamilyAndCol(HydraQLParser.SelectFamilyAndColContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void exitSelectFamilyAndCol(HydraQLParser.SelectFamilyAndColContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectWithFuncCall}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void enterSelectWithFuncCall(HydraQLParser.SelectWithFuncCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectWithFuncCall}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 */
	void exitSelectWithFuncCall(HydraQLParser.SelectWithFuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link HydraQLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterUdfFunctionCall(HydraQLParser.UdfFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link HydraQLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitUdfFunctionCall(HydraQLParser.UdfFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#funcName}.
	 * @param ctx the parse tree
	 */
	void enterFuncName(HydraQLParser.FuncNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#funcName}.
	 * @param ctx the parse tree
	 */
	void exitFuncName(HydraQLParser.FuncNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArgs(HydraQLParser.FunctionArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArgs(HydraQLParser.FunctionArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnName(HydraQLParser.FullColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnName(HydraQLParser.FullColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#family_name}.
	 * @param ctx the parse tree
	 */
	void enterFamily_name(HydraQLParser.Family_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#family_name}.
	 * @param ctx the parse tree
	 */
	void exitFamily_name(HydraQLParser.Family_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#quoted_name}.
	 * @param ctx the parse tree
	 */
	void enterQuoted_name(HydraQLParser.Quoted_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#quoted_name}.
	 * @param ctx the parse tree
	 */
	void exitQuoted_name(HydraQLParser.Quoted_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void enterColumn_alias(HydraQLParser.Column_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#column_alias}.
	 * @param ctx the parse tree
	 */
	void exitColumn_alias(HydraQLParser.Column_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(HydraQLParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(HydraQLParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(HydraQLParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(HydraQLParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#table_spec}.
	 * @param ctx the parse tree
	 */
	void enterTable_spec(HydraQLParser.Table_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#table_spec}.
	 * @param ctx the parse tree
	 */
	void exitTable_spec(HydraQLParser.Table_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#aliased_table_ref}.
	 * @param ctx the parse tree
	 */
	void enterAliased_table_ref(HydraQLParser.Aliased_table_refContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#aliased_table_ref}.
	 * @param ctx the parse tree
	 */
	void exitAliased_table_ref(HydraQLParser.Aliased_table_refContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void enterTable_alias(HydraQLParser.Table_aliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#table_alias}.
	 * @param ctx the parse tree
	 */
	void exitTable_alias(HydraQLParser.Table_aliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#positive_decimal}.
	 * @param ctx the parse tree
	 */
	void enterPositive_decimal(HydraQLParser.Positive_decimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#positive_decimal}.
	 * @param ctx the parse tree
	 */
	void exitPositive_decimal(HydraQLParser.Positive_decimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#namespace_name}.
	 * @param ctx the parse tree
	 */
	void enterNamespace_name(HydraQLParser.Namespace_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#namespace_name}.
	 * @param ctx the parse tree
	 */
	void exitNamespace_name(HydraQLParser.Namespace_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(HydraQLParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(HydraQLParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#column_def}.
	 * @param ctx the parse tree
	 */
	void enterColumn_def(HydraQLParser.Column_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#column_def}.
	 * @param ctx the parse tree
	 */
	void exitColumn_def(HydraQLParser.Column_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#column_ref}.
	 * @param ctx the parse tree
	 */
	void enterColumn_ref(HydraQLParser.Column_refContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#column_ref}.
	 * @param ctx the parse tree
	 */
	void exitColumn_ref(HydraQLParser.Column_refContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(HydraQLParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(HydraQLParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(HydraQLParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(HydraQLParser.Data_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionColName}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionColName(HydraQLParser.ExpressionColNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionColName}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionColName(HydraQLParser.ExpressionColNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionLikeOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionLikeOrNot(HydraQLParser.ExpressionLikeOrNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionLikeOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionLikeOrNot(HydraQLParser.ExpressionLikeOrNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionCompOp}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionCompOp(HydraQLParser.ExpressionCompOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionCompOp}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionCompOp(HydraQLParser.ExpressionCompOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionIsNullOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionIsNullOrNot(HydraQLParser.ExpressionIsNullOrNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionIsNullOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionIsNullOrNot(HydraQLParser.ExpressionIsNullOrNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionAnd}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAnd(HydraQLParser.ExpressionAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionAnd}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAnd(HydraQLParser.ExpressionAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionConstantValue}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionConstantValue(HydraQLParser.ExpressionConstantValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionConstantValue}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionConstantValue(HydraQLParser.ExpressionConstantValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionBetweenOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionBetweenOrNot(HydraQLParser.ExpressionBetweenOrNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionBetweenOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionBetweenOrNot(HydraQLParser.ExpressionBetweenOrNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionOr}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionOr(HydraQLParser.ExpressionOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionOr}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionOr(HydraQLParser.ExpressionOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionWrapper}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionWrapper(HydraQLParser.ExpressionWrapperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionWrapper}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionWrapper(HydraQLParser.ExpressionWrapperContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionIn}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionIn(HydraQLParser.ExpressionInContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionIn}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionIn(HydraQLParser.ExpressionInContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionVariable}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionVariable(HydraQLParser.ExpressionVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionVariable}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionVariable(HydraQLParser.ExpressionVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionWithFunc}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionWithFunc(HydraQLParser.ExpressionWithFuncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionWithFunc}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionWithFunc(HydraQLParser.ExpressionWithFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void enterComp_op(HydraQLParser.Comp_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#comp_op}.
	 * @param ctx the parse tree
	 */
	void exitComp_op(HydraQLParser.Comp_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(HydraQLParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(HydraQLParser.Expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(HydraQLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(HydraQLParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(HydraQLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(HydraQLParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(HydraQLParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(HydraQLParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#varString}.
	 * @param ctx the parse tree
	 */
	void enterVarString(HydraQLParser.VarStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#varString}.
	 * @param ctx the parse tree
	 */
	void exitVarString(HydraQLParser.VarStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#numeric}.
	 * @param ctx the parse tree
	 */
	void enterNumeric(HydraQLParser.NumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#numeric}.
	 * @param ctx the parse tree
	 */
	void exitNumeric(HydraQLParser.NumericContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(HydraQLParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(HydraQLParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#decimal}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(HydraQLParser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#decimal}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(HydraQLParser.DecimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#true_false}.
	 * @param ctx the parse tree
	 */
	void enterTrue_false(HydraQLParser.True_falseContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#true_false}.
	 * @param ctx the parse tree
	 */
	void exitTrue_false(HydraQLParser.True_falseContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#fun_name}.
	 * @param ctx the parse tree
	 */
	void enterFun_name(HydraQLParser.Fun_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#fun_name}.
	 * @param ctx the parse tree
	 */
	void exitFun_name(HydraQLParser.Fun_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#dimension_int}.
	 * @param ctx the parse tree
	 */
	void enterDimension_int(HydraQLParser.Dimension_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#dimension_int}.
	 * @param ctx the parse tree
	 */
	void exitDimension_int(HydraQLParser.Dimension_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#precision_int}.
	 * @param ctx the parse tree
	 */
	void enterPrecision_int(HydraQLParser.Precision_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#precision_int}.
	 * @param ctx the parse tree
	 */
	void exitPrecision_int(HydraQLParser.Precision_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#scale_int}.
	 * @param ctx the parse tree
	 */
	void enterScale_int(HydraQLParser.Scale_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#scale_int}.
	 * @param ctx the parse tree
	 */
	void exitScale_int(HydraQLParser.Scale_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#sql_data_type}.
	 * @param ctx the parse tree
	 */
	void enterSql_data_type(HydraQLParser.Sql_data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#sql_data_type}.
	 * @param ctx the parse tree
	 */
	void exitSql_data_type(HydraQLParser.Sql_data_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HydraQLParser#hbase_data_type}.
	 * @param ctx the parse tree
	 */
	void enterHbase_data_type(HydraQLParser.Hbase_data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HydraQLParser#hbase_data_type}.
	 * @param ctx the parse tree
	 */
	void exitHbase_data_type(HydraQLParser.Hbase_data_typeContext ctx);
}