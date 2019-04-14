package de.uni.stuttgart.ipvs.sparql.triple;

import de.uni.stuttgart.ipvs.sparql.node.Subject;

import java.util.Set;

public interface TripleSameSubject {

    Subject getSubject();

    Set<Triple> getTriples();

    String getString();

}
