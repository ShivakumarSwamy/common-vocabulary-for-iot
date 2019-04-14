package de.uni.stuttgart.ipvs.sparql.variable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryVariableTest {

    @Test
    void getString() {

        QueryVariable user = QueryVariable.of("user");
        assertEquals("?user", user.getString());

    }
}