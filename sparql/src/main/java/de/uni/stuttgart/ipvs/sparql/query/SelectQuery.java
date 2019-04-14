package de.uni.stuttgart.ipvs.sparql.query;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.variable.Variable;

import java.util.Collection;

public class SelectQuery implements QueryForm {

    private final Collection<Variable> variables;
    private WhereClause whereClause = new WhereClause();

    public SelectQuery(@NonNull Collection<Variable> variables) {
        this.variables = variables;
    }

    public void setWhereClause(WhereClause whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public WhereClause getWhereClause() {
        return this.whereClause;
    }

    @Override
    public String getString() {
        return "SELECT " +
                SparqlUtils.joinVariables(this.variables) + "\n" + this.whereClause.getString();
    }
}
