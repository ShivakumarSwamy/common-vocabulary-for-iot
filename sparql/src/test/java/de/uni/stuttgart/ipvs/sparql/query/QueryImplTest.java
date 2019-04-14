package de.uni.stuttgart.ipvs.sparql.query;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.SparqlSyntax;
import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryImplTest {

    @Test
    void getString() {

        Prologue rdfs = PrefixDeclaration.of("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        Prologue owl = PrefixDeclaration.of("owl", "http://www.w3.org/2002/07/owl#");

        SparqlSyntax query = new QueryImpl(List.of(rdfs, owl));

        assertEquals("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>", query.getString());
    }
}