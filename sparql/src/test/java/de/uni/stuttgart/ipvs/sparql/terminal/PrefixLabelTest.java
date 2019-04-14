package de.uni.stuttgart.ipvs.sparql.terminal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrefixLabelTest {

    @Test
    void getString() {

        PrefixLabel rdf = new PrefixLabel("rdf");

        assertEquals("rdf:", rdf.getString());
    }
}