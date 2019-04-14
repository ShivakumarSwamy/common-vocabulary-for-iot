package de.uni.stuttgart.ipvs.sparql.query;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.Collection;

public class QueryImpl implements Query {

    private final Collection<Prologue> prologues;

    public QueryImpl(@NonNull Collection<Prologue> prologues) {
        this.prologues = prologues;
    }

    @Override
    public Collection<Prologue> prologues() {
        return this.prologues;
    }

    @Override
    public String getString() {
        return SparqlUtils.joinCollection(this.prologues, "\n");
    }
}
