// Generated from ./HydraQLParser.g4 by ANTLR 4.5.1

package com.hydraql.dsl.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HydraQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HydraQLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(HydraQLParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#batch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBatch(HydraQLParser.BatchContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#sql_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_command(HydraQLParser.Sql_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#ddl_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdl_command(HydraQLParser.Ddl_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#dml_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDml_command(HydraQLParser.Dml_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#create_virtual_table_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_virtual_table_command(HydraQLParser.Create_virtual_table_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#table_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_options(HydraQLParser.Table_optionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#options_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptions_(HydraQLParser.Options_Context ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(HydraQLParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#drop_table_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrop_table_command(HydraQLParser.Drop_table_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#show_tables_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_tables_command(HydraQLParser.Show_tables_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#show_table_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow_table_command(HydraQLParser.Show_table_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#if_exists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_exists(HydraQLParser.If_existsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#if_not_exists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_not_exists(HydraQLParser.If_not_existsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#table_ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_ref(HydraQLParser.Table_refContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#upsert_values_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpsert_values_command(HydraQLParser.Upsert_values_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#insert_values}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert_values(HydraQLParser.Insert_valuesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column_ref_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_ref_list(HydraQLParser.Column_ref_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column_def_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_def_list(HydraQLParser.Column_def_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#delete_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_command(HydraQLParser.Delete_commandContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsrange_startAndEnd}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsrange_startAndEnd(HydraQLParser.Tsrange_startAndEndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsrange_start}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsrange_start(HydraQLParser.Tsrange_startContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsrange_end}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsrange_end(HydraQLParser.Tsrange_endContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsequal}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsequal(HydraQLParser.TsequalContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#tsExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsExp(HydraQLParser.TsExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#timestamp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimestamp(HydraQLParser.TimestampContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#gtOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtOper(HydraQLParser.GtOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#leOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeOper(HydraQLParser.LeOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#versions_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVersions_clause(HydraQLParser.Versions_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#limit_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit_clause(HydraQLParser.Limit_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#where_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere_clause(HydraQLParser.Where_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#select_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_command(HydraQLParser.Select_commandContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#select_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_statement(HydraQLParser.Select_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(HydraQLParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectAllFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectAllFamilyAndCol(HydraQLParser.SelectAllFamilyAndColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectOneFamilyAllCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectOneFamilyAllCol(HydraQLParser.SelectOneFamilyAllColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFamilyAndCol(HydraQLParser.SelectFamilyAndColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectWithFuncCall}
	 * labeled alternative in {@link HydraQLParser#select_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectWithFuncCall(HydraQLParser.SelectWithFuncCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link HydraQLParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUdfFunctionCall(HydraQLParser.UdfFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#funcName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncName(HydraQLParser.FuncNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#functionArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArgs(HydraQLParser.FunctionArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#fullColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnName(HydraQLParser.FullColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#family_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFamily_name(HydraQLParser.Family_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#quoted_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuoted_name(HydraQLParser.Quoted_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column_alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_alias(HydraQLParser.Column_aliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(HydraQLParser.AliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(HydraQLParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#namespace_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace_name(HydraQLParser.Namespace_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(HydraQLParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_def(HydraQLParser.Column_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column_ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_ref(HydraQLParser.Column_refContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(HydraQLParser.Column_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type(HydraQLParser.Data_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionColName}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionColName(HydraQLParser.ExpressionColNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionLikeOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionLikeOrNot(HydraQLParser.ExpressionLikeOrNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionCompOp}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionCompOp(HydraQLParser.ExpressionCompOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionIsNullOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionIsNullOrNot(HydraQLParser.ExpressionIsNullOrNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAnd}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAnd(HydraQLParser.ExpressionAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionConstantValue}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionConstantValue(HydraQLParser.ExpressionConstantValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionBetweenOrNot}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionBetweenOrNot(HydraQLParser.ExpressionBetweenOrNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionOr}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOr(HydraQLParser.ExpressionOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionWrapper}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionWrapper(HydraQLParser.ExpressionWrapperContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionIn}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionIn(HydraQLParser.ExpressionInContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionVariable}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionVariable(HydraQLParser.ExpressionVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionWithFunc}
	 * labeled alternative in {@link HydraQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionWithFunc(HydraQLParser.ExpressionWithFuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_op(HydraQLParser.Comp_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(HydraQLParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(HydraQLParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(HydraQLParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(HydraQLParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#varString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarString(HydraQLParser.VarStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#numeric}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric(HydraQLParser.NumericContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(HydraQLParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#decimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal(HydraQLParser.DecimalContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#true_false}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue_false(HydraQLParser.True_falseContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#dimension_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimension_int(HydraQLParser.Dimension_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#precision_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrecision_int(HydraQLParser.Precision_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#scale_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScale_int(HydraQLParser.Scale_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#sql_data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSql_data_type(HydraQLParser.Sql_data_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#hbase_data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHbase_data_type(HydraQLParser.Hbase_data_typeContext ctx);
}