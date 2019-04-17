package de.uni.stuttgart.ipvs.tm.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriples;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubjectImpl;

import java.util.Collection;
import java.util.stream.Collectors;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;
import static de.uni.stuttgart.ipvs.vocabulary.CVI.CVI_PREFIX_LABEL;
import static de.uni.stuttgart.ipvs.vocabulary.CVI.HAS_SEARCH_ID;
import static de.uni.stuttgart.ipvs.vocabulary.RDF.RDF_TYPE;
import static de.uni.stuttgart.ipvs.vocabulary.RDFS.*;

public class CviSparqlUtils {
    private CviSparqlUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static GraphPatternNotTriples unionOfTerms(Collection<String> terms) {

        var tripleSameSubjects = terms.stream()
                .map(CviSparqlUtils::searchItemDetailsTerm)
                .collect(Collectors.toList());

        return GraphPatternNotTriplesImpl2.unionOf(tripleSameSubjects);
    }


    private static TripleSameSubject searchItemDetailsTerm(String term) {

        var tripleSameSubject = new TripleSameSubjectImpl(PrefixedName.of(CVI_PREFIX_LABEL, term));

        tripleSameSubject.add(RDF_TYPE, RDFS_CLASS);
        tripleSameSubject.add(HAS_SEARCH_ID, QV_SEARCH_ID);
        tripleSameSubject.add(RDFS_LABEL, QV_LABEL);
        tripleSameSubject.add(RDFS_COMMENT, QV_COMMENT);

        return tripleSameSubject;

    }
}
