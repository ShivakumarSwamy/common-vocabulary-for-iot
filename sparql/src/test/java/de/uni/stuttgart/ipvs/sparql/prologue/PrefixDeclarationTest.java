package de.uni.stuttgart.ipvs.sparql.prologue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrefixDeclarationTest {

    @Test
    void getString() {

        PrefixDeclaration rdfs = PrefixDeclaration.of("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        assertEquals("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>", rdfs.getString());
    }
}