package de.uni.stuttgart.ipvs.sparql.clause;

import de.uni.stuttgart.ipvs.sparql.triple.Triple;

import java.util.Set;

public interface GraphPatternNotTriples {

    Set<Triple> getTriples();

    ClauseKeyword getClauseKeyword();

    String getString();
}
