package de.uni.stuttgart.ipvs.sparql.clause;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;

import java.util.*;

public class GraphPatternNotTriplesImpl implements GraphPatternNotTriples {

    private Set<Triple> leftTriples = new LinkedHashSet<>();
    private Set<Triple> rightTriples = new LinkedHashSet<>();

    private final ClauseKeyword clauseKeyword;

    public GraphPatternNotTriplesImpl(ClauseKeyword clauseKeyword, Triple triple) {
        this(clauseKeyword, List.of(triple), Set.of());
    }

    public GraphPatternNotTriplesImpl(ClauseKeyword clauseKeyword, Collection<Triple> rightTriples) {
        this(clauseKeyword, rightTriples, Set.of());
    }

    public GraphPatternNotTriplesImpl(@NonNull ClauseKeyword clauseKeyword,
                                      @NonNull Collection<Triple> rightTriples,
                                      @NonNull Collection<Triple> leftTriples) {
        this.clauseKeyword = clauseKeyword;
        this.rightTriples.addAll(rightTriples);
        this.leftTriples.addAll(leftTriples);
    }

    public static GraphPatternNotTriples unionOf(Collection<Triple> rightTriples, Collection<Triple> leftTriples) {
        return new GraphPatternNotTriplesImpl(ClauseKeyword.UNION, rightTriples, leftTriples);
    }

    public static GraphPatternNotTriples minusOf(Triple triple) {
        return new GraphPatternNotTriplesImpl(ClauseKeyword.MINUS, triple);
    }

    @Override
    public Set<Triple> getTriples() {
        rightTriples.addAll(leftTriples);
        return this.rightTriples;
    }

    @Override
    public ClauseKeyword getClauseKeyword() {
        return this.clauseKeyword;
    }

    @Override
    public String getString() {

        String finalString = this.leftTriples.isEmpty() ? "" : SparqlUtils.groupGraphPattern(this.leftTriples);
        finalString += this.clauseKeyword.name() + " " + SparqlUtils.groupGraphPattern(this.rightTriples);

        return finalString;
    }
}
