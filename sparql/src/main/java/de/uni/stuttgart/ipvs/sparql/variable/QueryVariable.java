package de.uni.stuttgart.ipvs.sparql.variable;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Property;
import de.uni.stuttgart.ipvs.sparql.node.Subject;

@EqualsAndHashCode
@ToString
public class QueryVariable implements Variable, Subject, Property, Obj {

    private final VariableSymbol variableSymbol;
    private final String variableName;

    public QueryVariable(@NonNull VariableSymbol variableSymbol, @NonNull String variableName) {
        this.variableSymbol = variableSymbol;
        this.variableName = variableName;
    }

    public static QueryVariable of(String variableName) {
        return new QueryVariable(VariableSymbol.QUESTION_MARK, variableName);
    }

    @Override
    public VariableSymbol getVariableSymbol() {
        return this.variableSymbol;
    }

    @Override
    public String getVariableName() {
        return this.variableName;
    }

    @Override
    public String getString() {
        return this.variableSymbol.getSymbol() + this.variableName;
    }
}
