package de.uni.stuttgart.ipvs.sparql.prologue;

import de.uni.stuttgart.ipvs.sparql.SparqlSyntax;
import de.uni.stuttgart.ipvs.sparql.terminal.Iri;

public interface Prologue extends SparqlSyntax {

    Iri getIri();
}
