package de.uni.stuttgart.ipvs.sparql.update;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class GraphUpdate implements UpdateForm {

    private Collection<Prologue> prologues = new ArrayList<>();
    private Set<Triple> triples = new LinkedHashSet<>();

    private final GraphUpdateKeyword graphUpdateKeyword;

    public GraphUpdate(GraphUpdateKeyword graphUpdateKeyword,
                       @NonNull TripleSameSubject tripleSameSubject) {
        this(graphUpdateKeyword, tripleSameSubject.getTriples());
    }

    public GraphUpdate(@NonNull GraphUpdateKeyword graphUpdateKeyword,
                       @NonNull Collection<Triple> triples) {
        this.graphUpdateKeyword = graphUpdateKeyword;
        this.triples.addAll(triples);
    }

    public static GraphUpdate insertDataOf(TripleSameSubject tripleSameSubject) {
        return new GraphUpdate(GraphUpdateKeyword.INSERT_DATA, tripleSameSubject);
    }


    public void add(@NonNull Triple triple) {
        this.triples.add(triple);
    }

    public void add(@NonNull TripleSameSubject tripleSameSubject) {
        this.triples.addAll(tripleSameSubject.getTriples());
    }

    @Override
    public Collection<Prologue> getPrologues() {
        return this.prologues;
    }

    @Override
    public Set<Triple> getTriples() {
        return this.triples;
    }

    @Override
    public String getString() {

        return SparqlUtils.joinPrologues(this.prologues) + "\n" +
                this.graphUpdateKeyword.getKeyword() + " " + SparqlUtils.groupGraphPattern(this.triples);
    }
}
