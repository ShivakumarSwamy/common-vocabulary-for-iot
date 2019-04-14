package de.uni.stuttgart.ipvs.sparql.expression;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.variable.Variable;

public class StrFunctionImpl implements StrFunction {

    private final ExpressionKeyword expressionKeyword;
    private final Variable variable;

    public StrFunctionImpl(@NonNull ExpressionKeyword expressionKeyword, @NonNull Variable variable) {
        this.expressionKeyword = expressionKeyword;
        this.variable = variable;
    }

    @Override
    public ExpressionKeyword getExpressionKeyword() {
        return this.expressionKeyword;
    }

    @Override
    public Variable getVariable() {
        return this.variable;
    }

    @Override
    public String getString() {
        return this.expressionKeyword.getSymbol() + "(" + this.variable.getString() + ")";
    }
}
