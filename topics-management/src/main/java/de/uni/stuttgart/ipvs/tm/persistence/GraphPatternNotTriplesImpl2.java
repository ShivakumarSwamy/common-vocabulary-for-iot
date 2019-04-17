package de.uni.stuttgart.ipvs.tm.persistence;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.clause.ClauseKeyword;
import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriples;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

// TODO update GraphPatternNotTriples impl
public class GraphPatternNotTriplesImpl2 implements GraphPatternNotTriples {

    private Set<Set<Triple>> setOfSetOfTriples = new LinkedHashSet<>();

    private final ClauseKeyword clauseKeyword;

    public GraphPatternNotTriplesImpl2(ClauseKeyword clauseKeyword, Triple triple) {
        this(clauseKeyword, Set.of(), List.of(triple));
    }

    public GraphPatternNotTriplesImpl2(ClauseKeyword clauseKeyword, Collection<Triple> rightTriples) {
        this(clauseKeyword, Set.of(), rightTriples);
    }

    public GraphPatternNotTriplesImpl2(@NonNull ClauseKeyword clauseKeyword,
                                       @NonNull Collection<Triple> leftTriples,
                                       @NonNull Collection<Triple> rightTriples) {
        this(clauseKeyword, Set.of(new LinkedHashSet<>(leftTriples), new LinkedHashSet<>(rightTriples)));
    }

    public GraphPatternNotTriplesImpl2(ClauseKeyword clauseKeyword, Set<Set<Triple>> setOfSetOfTriples) {
        this.clauseKeyword = clauseKeyword;
        this.setOfSetOfTriples = setOfSetOfTriples;
    }

    public static GraphPatternNotTriples unionOf(Collection<Triple> rightTriples, Collection<Triple> leftTriples) {
        return new GraphPatternNotTriplesImpl2(ClauseKeyword.UNION, rightTriples, leftTriples);
    }

    public static GraphPatternNotTriples unionOf(Collection<TripleSameSubject> tripleSameSubjects) {
        return new GraphPatternNotTriplesImpl2(ClauseKeyword.UNION,
                tripleSameSubjectAsSetOfSetOfTriples(tripleSameSubjects));
    }

    public static GraphPatternNotTriples minusOf(Triple triple) {
        return new GraphPatternNotTriplesImpl2(ClauseKeyword.MINUS, triple);
    }

    @Override
    public Set<Triple> getTriples() {
        return this.setOfSetOfTriples.stream()
                .flatMap(Set::stream)
                .collect(toSet());
    }

    @Override
    public ClauseKeyword getClauseKeyword() {
        return this.clauseKeyword;
    }

    @Override
    public String getString() {

        if (this.hasSingleElement()) return this.stringForSingleElement();

        return this.stringForManyElements();
    }

    private boolean hasSingleElement() {
        return this.setOfSetOfTriples.size() == 1;
    }

    private String stringForSingleElement() {
        var iterator = this.setOfSetOfTriples.iterator();
        return this.clauseKeyword.name() + " " + SparqlUtils.groupGraphPattern(iterator.next());
    }

    private String stringForManyElements() {
        return this.setOfSetOfTriples.stream()
                .map(SparqlUtils::groupGraphPattern)
                .collect(Collectors.joining(this.clauseKeyword.name()));
    }

    private static Set<Set<Triple>> tripleSameSubjectAsSetOfSetOfTriples(Collection<TripleSameSubject> tripleSameSubjects) {

        return tripleSameSubjects.stream()
                .map(tripleSameSubject -> new LinkedHashSet<>(tripleSameSubject.getTriples()))
                .collect(Collectors.toSet());
    }

}
