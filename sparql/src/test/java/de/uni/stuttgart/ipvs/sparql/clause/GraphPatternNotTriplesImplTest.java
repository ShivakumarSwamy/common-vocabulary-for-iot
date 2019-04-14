package de.uni.stuttgart.ipvs.sparql.clause;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphPatternNotTriplesImplTest {

    @Test
    void unionOf() {

        assertEquals("{\n" +
                "?property rdf:type owl:ObjectProperty .\n" +
                "?object rdfs:label ?propertyValue .\n" +
                "}\n" +
                "UNION {\n" +
                "?property rdf:type owl:DatatypeProperty .\n" +
                "?user ?property ?propertyValue .\n" +
                "}\n", TestUtils.union().getString());
    }

    @Test
    void minusOf() {

        assertEquals("MINUS {\n" +
                "?user usr:hasPassword ?propertyValue .\n" +
                "}\n", TestUtils.minusOf().getString());
    }
}