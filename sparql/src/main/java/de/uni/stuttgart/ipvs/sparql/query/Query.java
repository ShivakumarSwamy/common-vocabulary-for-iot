package de.uni.stuttgart.ipvs.sparql.query;

import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.Collection;

public interface Query {

    Collection<Prologue> getPrologues();

    QueryForm getQueryForm();


    String getString();

}
