package de.uni.stuttgart.ipvs.sparql.expression;

import de.uni.stuttgart.ipvs.sparql.variable.Variable;

public interface StrFunction {

    ExpressionKeyword getExpressionKeyword();

    Variable getVariable();

    String getString();

}
