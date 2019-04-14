package de.uni.stuttgart.ipvs.sparql.query;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.expression.Expression;
import de.uni.stuttgart.ipvs.sparql.expression.StrExpression;
import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.utils.TestUtils;
import de.uni.stuttgart.ipvs.sparql.variable.QueryVariable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryImplTest {

    @Test
    void getString() {

        Prologue rdfs = PrefixDeclaration.of("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        Prologue rdf = PrefixDeclaration.of("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        Prologue usr = PrefixDeclaration.of("usr", "http://www.example.com/vocabulary/users#");
        Prologue owl = PrefixDeclaration.of("owl", "http://www.w3.org/2002/07/owl#");

        QueryVariable user = QueryVariable.of("user");
        Expression lowercase =
                StrExpression.lowercase(QueryVariable.of("propertyLabel"), QueryVariable.of("propertyName"));

        QueryVariable propertyValue = QueryVariable.of("propertyValue");

        WhereClause whereClause = new WhereClause(TestUtils.tripleSameSubject());
        whereClause.add(TestUtils.tripleRdfLabelExample());
        whereClause.add(TestUtils.union());
        whereClause.add(TestUtils.minusOf());

        SelectQuery selectQuery = new SelectQuery(List.of(user, lowercase, propertyValue));
        selectQuery.setWhereClause(whereClause);

        Query query = new QueryImpl(List.of(rdf, usr, rdfs, owl), selectQuery);

        assertEquals("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX usr: <http://www.example.com/vocabulary/users#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?user (LCASE(?propertyName) as ?propertyLabel) ?propertyValue\n" +
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
                "}\n", query.getString());
    }
}