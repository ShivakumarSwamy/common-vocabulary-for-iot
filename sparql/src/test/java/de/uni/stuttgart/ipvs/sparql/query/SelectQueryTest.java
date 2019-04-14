package de.uni.stuttgart.ipvs.sparql.query;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.expression.Expression;
import de.uni.stuttgart.ipvs.sparql.expression.StrExpression;
import de.uni.stuttgart.ipvs.sparql.utils.TestUtils;
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

        WhereClause whereClause = new WhereClause(TestUtils.userHasId());
        whereClause.add(TestUtils.tripleRdfLabelExample());
        whereClause.add(TestUtils.union());
        whereClause.add(TestUtils.minusOf());

        SelectQuery query = new SelectQuery(List.of(user, lowercase, propertyValue));
        query.setWhereClause(whereClause);

        assertEquals("SELECT ?user (LCASE(?propertyName) as ?propertyLabel) ?propertyValue\n" +
                "WHERE {\n" +
                "?user rdf:type usr:user .\n" +
                "?user usr:hasId ?userId .\n" +
                "?user ?property ?object .\n" +
                "?property rdfs:label ?propertyLabel .\n" +
                "{\n" +
                "?property rdf:type owl:ObjectProperty .\n" +
                "?object rdfs:label ?propertyValue .\n" +
                "}\n" +
                "UNION {\n" +
                "?property rdf:type owl:DatatypeProperty .\n" +
                "?user ?property ?propertyValue .\n" +
                "}\n" +
                "MINUS {\n" +
                "?user usr:hasPassword ?propertyValue .\n" +
                "}\n" +
                "}", query.getString());

    }
}