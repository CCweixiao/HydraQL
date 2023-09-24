// Generated from ./HydraQLParser.g4 by ANTLR 4.13.1

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
	 * Visit a parse tree produced by {@link HydraQLParser#upsert_column_def_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpsert_column_def_list(HydraQLParser.Upsert_column_def_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column_def_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_def_list(HydraQLParser.Column_def_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#delete_column_def_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_column_def_list(HydraQLParser.Delete_column_def_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#delete_command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_command(HydraQLParser.Delete_commandContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsRangeStartAndEnd}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsRangeStartAndEnd(HydraQLParser.TsRangeStartAndEndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsRangeStart}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsRangeStart(HydraQLParser.TsRangeStartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsRangeEnd}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsRangeEnd(HydraQLParser.TsRangeEndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tsRangeEq}
	 * labeled alternative in {@link HydraQLParser#timestamp_range_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTsRangeEq(HydraQLParser.TsRangeEqContext ctx);
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
	 * Visit a parse tree produced by the {@code deleteOneFamilyAllCol}
	 * labeled alternative in {@link HydraQLParser#delete_column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteOneFamilyAllCol(HydraQLParser.DeleteOneFamilyAllColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code deleteFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#delete_column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteFamilyAndCol(HydraQLParser.DeleteFamilyAndColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectAllFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectAllFamilyAndCol(HydraQLParser.SelectAllFamilyAndColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectOneFamilyAllCol}
	 * labeled alternative in {@link HydraQLParser#select_column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectOneFamilyAllCol(HydraQLParser.SelectOneFamilyAllColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectFamilyAndCol}
	 * labeled alternative in {@link HydraQLParser#select_column_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFamilyAndCol(HydraQLParser.SelectFamilyAndColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectWithFuncCall}
	 * labeled alternative in {@link HydraQLParser#select_column_def}.
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
	 * Visit a parse tree produced by {@link HydraQLParser#conditionVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionVal(HydraQLParser.ConditionValContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#conditionValList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionValList(HydraQLParser.ConditionValListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(HydraQLParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(HydraQLParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(HydraQLParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn(HydraQLParser.ColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#rowKey}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKey(HydraQLParser.RowKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowKeyStartAndEnd}
	 * labeled alternative in {@link HydraQLParser#whereRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKeyStartAndEnd(HydraQLParser.RowKeyStartAndEndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowKeyStart}
	 * labeled alternative in {@link HydraQLParser#whereRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKeyStart(HydraQLParser.RowKeyStartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowKeyEnd}
	 * labeled alternative in {@link HydraQLParser#whereRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKeyEnd(HydraQLParser.RowKeyEndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowKeyEqOne}
	 * labeled alternative in {@link HydraQLParser#whereRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKeyEqOne(HydraQLParser.RowKeyEqOneContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowKeyInSomeKeys}
	 * labeled alternative in {@link HydraQLParser#whereRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKeyInSomeKeys(HydraQLParser.RowKeyInSomeKeysContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rowKeyLike}
	 * labeled alternative in {@link HydraQLParser#whereRow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowKeyLike(HydraQLParser.RowKeyLikeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#whereCol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereCol(HydraQLParser.WhereColContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionIsNullOrNot}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionIsNullOrNot(HydraQLParser.ColConditionIsNullOrNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionInOrNotIn}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionInOrNotIn(HydraQLParser.ColConditionInOrNotInContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionCompOp}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionCompOp(HydraQLParser.ColConditionCompOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionBetweenOrNot}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionBetweenOrNot(HydraQLParser.ColConditionBetweenOrNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionLikeOrNot}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionLikeOrNot(HydraQLParser.ColConditionLikeOrNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionAnd}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionAnd(HydraQLParser.ColConditionAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionOr}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionOr(HydraQLParser.ColConditionOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code colConditionWrapper}
	 * labeled alternative in {@link HydraQLParser#colCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColConditionWrapper(HydraQLParser.ColConditionWrapperContext ctx);
	/**
	 * Visit a parse tree produced by {@link HydraQLParser#comp_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_op(HydraQLParser.Comp_opContext ctx);
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