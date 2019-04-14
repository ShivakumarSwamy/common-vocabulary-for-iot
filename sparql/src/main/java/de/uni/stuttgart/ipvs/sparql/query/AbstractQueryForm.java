package de.uni.stuttgart.ipvs.sparql.query;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;

public abstract class AbstractQueryForm implements QueryForm {

    private WhereClause whereClause = new WhereClause();

    @Override
    public WhereClause getWhereClause() {
        return this.whereClause;
    }

    public void setWhereClause(WhereClause whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String getString() {
        return this.whereClause.getString();
    }

}
