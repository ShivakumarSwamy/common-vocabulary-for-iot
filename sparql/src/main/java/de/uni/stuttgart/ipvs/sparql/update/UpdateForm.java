package de.uni.stuttgart.ipvs.sparql.update;

import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;

import java.util.Collection;
import java.util.Set;

public interface UpdateForm {

    Collection<Prologue> getPrologues();

    Set<Triple> getTriples();

    String getString();

}
