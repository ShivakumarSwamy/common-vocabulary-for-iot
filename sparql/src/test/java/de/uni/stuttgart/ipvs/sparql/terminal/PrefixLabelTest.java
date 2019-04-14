package de.uni.stuttgart.ipvs.sparql.terminal;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.SparqlSyntax;

import static org.junit.jupiter.api.Assertions.*;

class PrefixLabelTest {

    @Test
    void getString() {

        SparqlSyntax rdf = new PrefixLabel("rdf");

        assertEquals("rdf:", rdf.getString());
    }
}