package de.uni.stuttgart.ipvs.sparql.query;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.variable.Variable;

import java.util.Collection;

public class SelectQuery extends AbstractQueryForm implements QueryForm {

    private final Collection<Variable> variables;

    public SelectQuery(@NonNull Collection<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String getString() {
        return "SELECT " +
                SparqlUtils.joinVariables(this.variables) + "\n" + super.getString();
    }
}
