package de.uni.stuttgart.ipvs.sparql.expression;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.variable.Variable;
import de.uni.stuttgart.ipvs.sparql.variable.VariableSymbol;

public class StrExpression implements Expression {

    private final Variable variable;
    private final StrFunction strFunction;

    public StrExpression(@NonNull Variable variable, @NonNull StrFunction strFunction) {
        this.variable = variable;
        this.strFunction = strFunction;
    }

    public static Expression lowercase(Variable variable, Variable aliasVariable) {
        return new StrExpression(variable, new StrFunctionImpl(ExpressionKeyword.LOWERCASE, aliasVariable));
    }

    @Override
    public Variable getVariable() {
        return this.variable;
    }

    @Override
    public StrFunction getStrFunction() {
        return this.strFunction;
    }

    @Override
    public VariableSymbol getVariableSymbol() {
        return this.variable.getVariableSymbol();
    }

    @Override
    public String getVariableName() {
        return this.variable.getVariableName();
    }

    @Override
    public String getString() {
        return "(" + this.strFunction.getString() + " as " + this.variable.getString() + ")";
    }
}
