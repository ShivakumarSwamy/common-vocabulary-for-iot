package de.uni.stuttgart.ipvs.tm.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriples;
import de.uni.stuttgart.ipvs.sparql.node.Subject;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubjectImpl;
import de.uni.stuttgart.ipvs.tm.service.HardwareType;

import java.util.Collection;
import java.util.stream.Collectors;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;
import static de.uni.stuttgart.ipvs.vocabulary.CVI.*;
import static de.uni.stuttgart.ipvs.vocabulary.RDF.RDF_TYPE;
import static de.uni.stuttgart.ipvs.vocabulary.RDFS.*;

public class CviSparqlUtils {

    private static final PrefixedName RDFS_SUBCLASS_OF = PrefixedName.of(RDFS_PREFIX_LABEL, "subClassOf");

    private CviSparqlUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static GraphPatternNotTriples unionOfTerms(Collection<String> terms) {

        var tripleSameSubjects = terms.stream()
                .map(CviSparqlUtils::searchItemDetailsTerm)
                .collect(Collectors.toList());

        return UnionImpl.unionOf(tripleSameSubjects);
    }


    private static TripleSameSubject searchItemDetailsTerm(String term) {
        return searchItemDetails(PrefixedName.of(CVI_PREFIX_LABEL, term));
    }

    static TripleSameSubject searchItemDetailsHardwareType() {
        var tripleSameSubject = searchItemDetails(QV_HARDWARE_TYPE);

        tripleSameSubject.add(RDF_TYPE, CVI_HARDWARE_TYPE_CLASS);
        tripleSameSubject.add(RDFS_SUBCLASS_OF, QV_CATEGORY);

        return tripleSameSubject;
    }

    static TripleSameSubject newHardwareType(HardwareType hardwareType) {
        var tripleSameSubject = new TripleSameSubjectImpl(PrefixedName.of(CVI_PREFIX_LABEL, hardwareType.getSearchId()));

        tripleSameSubject.add(CVI_HAS_SEARCH_ID, StringLiteral.of(hardwareType.getSearchId()));
        tripleSameSubject.add(RDFS_LABEL, StringLiteral.of(hardwareType.getLabel()));
        tripleSameSubject.add(RDFS_COMMENT, StringLiteral.of(hardwareType.getComment()));

        tripleSameSubject.add(RDF_TYPE, RDFS_CLASS);
        tripleSameSubject.add(RDF_TYPE, CVI_HARDWARE_TYPE_CLASS);

        tripleSameSubject.add(RDFS_SUBCLASS_OF, PrefixedName.of(CVI_PREFIX_LABEL, hardwareType.getCategory()));

        return tripleSameSubject;
    }

    /**
     * category is the subclass of component (i.e sensor and actuator in vocabulary)
     */
    static TripleSameSubject identifyCategory() {

        var tripleSameSubject = new TripleSameSubjectImpl(QV_CATEGORY);

        tripleSameSubject.add(RDFS_SUBCLASS_OF, QV_COMPONENT);
        tripleSameSubject.add(RDF_TYPE, RDFS_CLASS);
        tripleSameSubject.add(RDFS_LABEL, QV_CATEGORY_LABEL);
        return tripleSameSubject;
    }

    /**
     * sensor and actuator subclass of component
     */
    static TripleSameSubject identifyComponent() {

        var tripleSameSubject = new TripleSameSubjectImpl(QV_COMPONENT);

        tripleSameSubject.add(RDFS_SUBCLASS_OF, CVI_COMPONENT_CLASS);
        tripleSameSubject.add(RDF_TYPE, RDFS_CLASS);
        tripleSameSubject.add(RDFS_LABEL, QV_COMPONENT_LABEL);
        return tripleSameSubject;
    }

    private static TripleSameSubjectImpl searchItemDetails(Subject subject) {

        var tripleSameSubject = new TripleSameSubjectImpl(subject);

        tripleSameSubject.add(RDF_TYPE, RDFS_CLASS);
        tripleSameSubject.add(CVI_HAS_SEARCH_ID, QV_SEARCH_ID);
        tripleSameSubject.add(RDFS_LABEL, QV_LABEL);
        tripleSameSubject.add(RDFS_COMMENT, QV_COMMENT);

        return tripleSameSubject;
    }

}
