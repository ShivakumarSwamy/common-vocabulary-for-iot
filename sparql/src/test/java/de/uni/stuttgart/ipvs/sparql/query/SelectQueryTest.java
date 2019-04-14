package de.uni.stuttgart.ipvs.sparql.query;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.expression.Expression;
import de.uni.stuttgart.ipvs.sparql.expression.StrExpression;
import de.uni.stuttgart.ipvs.sparql.variable.QueryVariable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SelectQueryTest {

    @Test
    void getString() {

        QueryVariable user = QueryVariable.of("user");
        Expression lowercase =
                StrExpression.lowercase(QueryVariable.of("propertyLabel"), QueryVariable.of("propertyName"));

        QueryVariable propertyValue = QueryVariable.of("propertyValue");

        SelectQuery query = new SelectQuery(List.of(user, lowercase, propertyValue));

        assertEquals("SELECT ?user (LCASE(?propertyName) as ?propertyLabel) ?propertyValue", query.getString());

    }
}