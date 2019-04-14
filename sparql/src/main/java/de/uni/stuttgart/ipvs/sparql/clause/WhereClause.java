package de.uni.stuttgart.ipvs.sparql.clause;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;

import java.util.*;

public class WhereClause {

    private Set<Triple> triples = new LinkedHashSet<>();

    private Collection<GraphPatternNotTriples> graphPatternNotTriples = new ArrayList<>();

    public WhereClause() {
    }

    public WhereClause(@NonNull Collection<Triple> triples) {
        this.triples.addAll(triples);
    }

    public WhereClause(@NonNull TripleSameSubject tripleSameSubject) {
        this.triples.addAll(tripleSameSubject.getTriples());
    }

    public void add(@NonNull TripleSameSubject tripleSameSubject) {
        this.triples.addAll(tripleSameSubject.getTriples());
    }

    public void add(@NonNull Triple triple) {
        this.triples.add(triple);
    }

    public void add(@NonNull GraphPatternNotTriples graphPatternNotTriples) {
        this.graphPatternNotTriples.add(graphPatternNotTriples);
    }

    public String getString() {
        String finalString = "WHERE {\n" + SparqlUtils.joinTriples(this.triples);

        finalString += this.graphPatternNotTriples.isEmpty() ? "" :
                SparqlUtils.joinGraphPatternNotTriples(this.graphPatternNotTriples);

        return finalString + "}";
    }

    public Set<Triple> getTriples() {
        return this.triples;
    }
}
