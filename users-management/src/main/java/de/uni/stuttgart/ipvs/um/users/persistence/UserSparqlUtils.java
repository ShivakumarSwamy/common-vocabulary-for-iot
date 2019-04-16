package de.uni.stuttgart.ipvs.um.users.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriples;
import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriplesImpl;
import de.uni.stuttgart.ipvs.sparql.node.Subject;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.triple.TripleImpl;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubjectImpl;
import de.uni.stuttgart.ipvs.vocabulary.OWL;
import de.uni.stuttgart.ipvs.vocabulary.RDF;
import de.uni.stuttgart.ipvs.vocabulary.RDFS;
import de.uni.stuttgart.ipvs.vocabulary.USER;

import java.util.List;

import static de.uni.stuttgart.ipvs.um.users.persistence.QVExprConstants.*;
import static de.uni.stuttgart.ipvs.vocabulary.USER.*;

class UserSparqlUtils {

    private UserSparqlUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static GraphPatternNotTriples unionOwlProperties(Subject subject) {

        var tripleDP = new TripleImpl(QV_PROPERTY, RDF.RDF_TYPE, OWL.OWL_DATATYPE_PROPERTY);
        var tripleDPV = new TripleImpl(subject, QV_PROPERTY, QV_PROPERTY_VALUE);

        var tripleOP = new TripleImpl(QV_PROPERTY, RDF.RDF_TYPE, OWL.OWL_OBJECT_PROPERTY);
        var tripleOPV = new TripleImpl(QV_OBJECT, RDFS.RDFS_LABEL, QV_PROPERTY_VALUE);

        return GraphPatternNotTriplesImpl.unionOf(List.of(tripleDP, tripleDPV), List.of(tripleOP, tripleOPV));
    }


    static TripleSameSubject userPropertyIdentify(Subject subject) {

        var tripleSameSubject = new TripleSameSubjectImpl(subject);
        tripleSameSubject.add(RDF.RDF_TYPE, USER.USER_CLASS);
        tripleSameSubject.add(USER_HAS_ID, QV_USER_ID);
        tripleSameSubject.add(QV_PROPERTY, QV_OBJECT);

        return tripleSameSubject;
    }

    static Triple propertyLabel() {
        return new TripleImpl(QV_PROPERTY, RDFS.RDFS_LABEL, QV_PROPERTY_LABEL);
    }

    static GraphPatternNotTriples minusPasswordProperty() {
        TripleImpl triple = new TripleImpl(QV_USER, USER_HAS_PASSWORD, QV_PROPERTY_VALUE);
        return GraphPatternNotTriplesImpl.minusOf(triple);
    }

    static TripleSameSubject newUserDetails(UserDetailsImpl userDetails) {

        var userSubject = PrefixedName.of(USER_PREFIX_LABEL, userDetails.getId());
        var tripleSameSubject = new TripleSameSubjectImpl(userSubject);

        tripleSameSubject.add(USER_HAS_ID, StringLiteral.of(userDetails.getId()));
        tripleSameSubject.add(USER_HAS_USERNAME, StringLiteral.of(userDetails.getUsername()));
        tripleSameSubject.add(USER_HAS_PASSWORD, StringLiteral.of(userDetails.getPassword()));

        var roleClass = PrefixedName.of(USER_PREFIX_LABEL, userDetails.getRole());
        tripleSameSubject.add(USER_HAS_ROLE, roleClass);
        tripleSameSubject.add(RDF.RDF_TYPE, roleClass);

        return tripleSameSubject;
    }

}
