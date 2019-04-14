package de.uni.stuttgart.ipvs.sparql.query;

import de.uni.stuttgart.ipvs.sparql.SparqlSyntax;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.Collection;

public interface Query extends SparqlSyntax {

    Collection<Prologue> prologues();

}
