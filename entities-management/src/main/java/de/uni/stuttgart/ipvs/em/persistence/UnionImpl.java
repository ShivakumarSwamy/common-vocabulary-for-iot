package de.uni.stuttgart.ipvs.em.persistence;

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
public class UnionImpl implements GraphPatternNotTriples {

    private Set<Set<Triple>> setOfSetOfTriples;

    private final ClauseKeyword clauseKeyword;

    public UnionImpl(ClauseKeyword clauseKeyword, Triple triple) {
        this(clauseKeyword, Set.of(), List.of(triple));
    }

    public UnionImpl(ClauseKeyword clauseKeyword, Collection<Triple> rightTriples) {
        this(clauseKeyword, Set.of(), rightTriples);
    }

    public UnionImpl(@NonNull ClauseKeyword clauseKeyword,
                     @NonNull Collection<Triple> leftTriples,
                     @NonNull Collection<Triple> rightTriples) {
        this(clauseKeyword, Set.of(new LinkedHashSet<>(leftTriples), new LinkedHashSet<>(rightTriples)));
    }

    public UnionImpl(ClauseKeyword clauseKeyword, Set<Set<Triple>> setOfSetOfTriples) {
        this.clauseKeyword = clauseKeyword;
        this.setOfSetOfTriples = setOfSetOfTriples;
    }


    public static GraphPatternNotTriples unionOf(Collection<TripleSameSubject> tripleSameSubjects) {
        return new UnionImpl(ClauseKeyword.UNION,
                tripleSameSubjectAsSetOfSetOfTriples(tripleSameSubjects));
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
        return this.stringForManyElements();
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
