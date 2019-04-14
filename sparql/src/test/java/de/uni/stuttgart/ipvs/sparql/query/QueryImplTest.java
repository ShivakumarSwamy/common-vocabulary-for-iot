package de.uni.stuttgart.ipvs.sparql.query;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.expression.Expression;
import de.uni.stuttgart.ipvs.sparql.expression.StrExpression;
import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.variable.QueryVariable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryImplTest {

    @Test
    void getString() {

        Prologue rdfs = PrefixDeclaration.of("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        Prologue owl = PrefixDeclaration.of("owl", "http://www.w3.org/2002/07/owl#");

        QueryVariable user = QueryVariable.of("user");
        Expression lowercase =
                StrExpression.lowercase(QueryVariable.of("propertyLabel"), QueryVariable.of("propertyName"));

        QueryVariable propertyValue = QueryVariable.of("propertyValue");

        SelectQuery selectQuery = new SelectQuery(List.of(user, lowercase, propertyValue));

        Query query = new QueryImpl(List.of(rdfs, owl), selectQuery);

        assertEquals("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?user (LCASE(?propertyName) as ?propertyLabel) ?propertyValue\n", query.getString());
    }
}