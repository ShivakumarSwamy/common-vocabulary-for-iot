package de.uni.stuttgart.ipvs.sparql.update;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.utils.TestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpdateImplTest {

    @Test
    void getStringInsertData() {

        Prologue rdf = PrefixDeclaration.of("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        Prologue usr = PrefixDeclaration.of("usr", "http://www.example.com/vocabulary/users#");

        UpdateImpl update = new UpdateImpl(List.of(rdf, usr));
        update.add(GraphUpdate.insertDataOf(TestUtils.userDetails()));

        assertEquals("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX usr: <http://www.example.com/vocabulary/users#>\n" +
                "\n" +
                "INSERT DATA {\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 rdf:type usr:consumer .\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 usr:hasRole usr:consumer .\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 usr:hasId \"9e59445c-0150-4cd3-84c8-324b5a39b4b2\" .\n" +
                "usr:9e59445c-0150-4cd3-84c8-324b5a39b4b2 usr:hasUsername \"username-password\" .\n" +
                "}\n" +
                "\n", update.getString());
    }
}