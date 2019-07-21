package de.uni.stuttgart.ipvs.em.entities.persistence;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;
import de.uni.stuttgart.ipvs.sparql.update.UpdateForm;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class InsertDelete implements UpdateForm {

    private Collection<Prologue> prologues;

    private Set<Triple> deleteTriples;
    private Set<Triple> insertTriples;

    private WhereClause whereClause;

    public InsertDelete() {
        this.deleteTriples = new LinkedHashSet<>();
        this.insertTriples = new LinkedHashSet<>();
        this.whereClause = new WhereClause();
    }

    public void addDeleteTriple(@NonNull Triple triple) {
        this.deleteTriples.add(triple);
    }

    public void addInsertTriples(@NonNull TripleSameSubject tripleSameSubject) {
        this.insertTriples.addAll(tripleSameSubject.getTriples());
    }


    public void setWhereClause(WhereClause whereClause) {
        this.whereClause = whereClause;
    }

    public void setPrologues(Collection<Prologue> prologues) {
        this.prologues = prologues;
    }

    @Override
    public Collection<Prologue> getPrologues() {
        return this.prologues;
    }

    @Override
    public Set<Triple> getTriples() {
        var triples = new HashSet<>(this.deleteTriples);
        triples.addAll(this.insertTriples);
        triples.addAll(this.whereClause.getTriples());
        return triples;
    }

    @Override
    public String getString() {
        String finalString = "";
        finalString += SparqlUtils.joinPrologues(this.prologues) + "\n";

        if (!this.deleteTriples.isEmpty())
            finalString += "DELETE " + SparqlUtils.groupGraphPattern(this.deleteTriples);

        if (!this.insertTriples.isEmpty())
            finalString += "INSERT " + SparqlUtils.groupGraphPattern(this.insertTriples);

        if (!this.whereClause.isEmpty())
            finalString += this.whereClause.getString();

        return finalString;
    }
}
