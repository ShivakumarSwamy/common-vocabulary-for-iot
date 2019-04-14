package de.uni.stuttgart.ipvs.sparql.update;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.*;

class GraphUpdateTest {

    @Test
    void getStringInsertData() {

        GraphUpdate graphUpdate = GraphUpdate.insertDataOf(TestUtils.userDetails());
        assertEquals("\n" +
                "INSERT DATA {\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 rdf:type usr:consumer .\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 usr:hasRole usr:consumer .\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 usr:hasId \"9e59445c-0150-4cd3-84c8-324b5a39b4b2\" .\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 usr:hasUsername \"username-password\" .\n" +
                "}\n", graphUpdate.getString());

    }
}