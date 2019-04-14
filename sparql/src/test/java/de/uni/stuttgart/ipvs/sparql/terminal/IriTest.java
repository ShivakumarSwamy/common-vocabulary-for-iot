package de.uni.stuttgart.ipvs.sparql.terminal;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.SparqlSyntax;

import static org.junit.jupiter.api.Assertions.*;

class IriTest {

    @Test
    void getString() {
        SparqlSyntax iri = new Iri("http://www.w3.org/2000/01/rdf-schema#");

        assertEquals("<http://www.w3.org/2000/01/rdf-schema#>", iri.getString());
    }
}