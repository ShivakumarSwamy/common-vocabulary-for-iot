package de.uni.stuttgart.ipvs.results;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsEmptyTest {

    @Test
    void isEmptyHead() {
        Head head = new Head();
        assertTrue(head.isEmpty());
    }

    @Test
    void isEmptyResults() {
        Results results = new Results();
        assertTrue(results.isEmpty());
    }

    @Test
    void isEmptySelectResults() {
        SelectResults selectResults = new SelectResults();
        assertTrue(selectResults.isEmpty());
    }

}