package de.uni.stuttgart.ipvs.sparql.query;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

class AskQueryTest {

    @Test
    void getStringEmpty() {

        AskQuery query = new AskQuery();
        assertEquals("ASK WHERE {}", query.getString());
    }

    @Test
    void getStringTriplesBlock() {

        AskQuery query = new AskQuery();

        query.setWhereClause(new WhereClause(TestUtils.userHasId()));
        assertEquals("ASK WHERE {\n" +
                "?user rdf:type usr:user .\n" +
                "?user usr:hasId ?userId .\n" +
                "?user ?property ?object .\n" +
                "}", query.getString());
    }

}