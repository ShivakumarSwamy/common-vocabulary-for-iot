package de.uni.stuttgart.ipvs.sparql.expression;

import de.uni.stuttgart.ipvs.sparql.variable.Variable;

public interface Expression extends Variable {

    Variable getVariable();

    StrFunction getStrFunction();

    String getString();

}
