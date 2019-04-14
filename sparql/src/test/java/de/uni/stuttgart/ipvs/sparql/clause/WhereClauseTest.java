package de.uni.stuttgart.ipvs.sparql.clause;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhereClauseTest {

    @Test
    void getStringEmpty() {
        WhereClause clause = new WhereClause();
        assertEquals("WHERE {\n" +
                "}", clause.getString());
    }

    @Test
    void getStringTriplesBlock() {
        WhereClause clause = new WhereClause(TestUtils.userHasId());
        clause.add(TestUtils.tripleRdfLabelExample());
        assertEquals("WHERE {\n" +
                "?user rdf:type usr:user .\n" +
                "?user usr:hasId ?userId .\n" +
                "?user ?property ?object .\n" +
                "?property rdfs:label ?propertyLabel .\n" +
                "}", clause.getString());
    }

    @Test
    void getStringTriplesBlockWithUnion() {

        WhereClause clause = new WhereClause(TestUtils.userHasId());
        clause.add(TestUtils.tripleRdfLabelExample());
        clause.add(TestUtils.union());

        assertEquals("WHERE {\n" +
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
                "}", clause.getString());
    }

}