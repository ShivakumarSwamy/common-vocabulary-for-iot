package de.uni.stuttgart.ipvs.sparql.query;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;

public interface QueryForm {

    String getString();

    WhereClause getWhereClause();
}
