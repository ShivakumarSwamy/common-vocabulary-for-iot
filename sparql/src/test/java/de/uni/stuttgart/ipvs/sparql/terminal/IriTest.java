package de.uni.stuttgart.ipvs.sparql.terminal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IriTest {

    @Test
    void getString() {
        Iri iri = new Iri("http://www.w3.org/2000/01/rdf-schema#");
        assertEquals("<http://www.w3.org/2000/01/rdf-schema#>", iri.getString());
    }
}