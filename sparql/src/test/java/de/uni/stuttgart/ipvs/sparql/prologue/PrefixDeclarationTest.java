package de.uni.stuttgart.ipvs.sparql.prologue;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.SparqlSyntax;

import static org.junit.jupiter.api.Assertions.*;

class PrefixDeclarationTest {

    @Test
    void getString() {

        SparqlSyntax rdfs = PrefixDeclaration.of("rdfs", "http://www.w3.org/2000/01/rdf-schema#");

        assertEquals("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>", rdfs.getString());
    }
}