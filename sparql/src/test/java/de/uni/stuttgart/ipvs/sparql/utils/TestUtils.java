package de.uni.stuttgart.ipvs.sparql.utils;

import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriples;
import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriplesImpl;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.triple.TripleImpl;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubjectImpl;
import de.uni.stuttgart.ipvs.sparql.variable.QueryVariable;

import java.util.List;

public class TestUtils {

    private TestUtils() {
        throw new IllegalStateException(getClass().getName());
    }


    public static GraphPatternNotTriples union() {
        QueryVariable property = QueryVariable.of("property");
        PrefixedName rdfType = PrefixedName.of("rdf", "type");
        PrefixedName owlDp = PrefixedName.of("owl", "DatatypeProperty");

        QueryVariable user = QueryVariable.of("user");
        QueryVariable propertyValue = QueryVariable.of("propertyValue");
        TripleImpl triple1 = new TripleImpl(property, rdfType, owlDp);
        TripleImpl triple2 = new TripleImpl(user, property, propertyValue);

        QueryVariable object = QueryVariable.of("object");
        PrefixedName rdfsLabel = PrefixedName.of("rdfs", "label");
        PrefixedName owlOp = PrefixedName.of("owl", "ObjectProperty");
        TripleImpl triple3 = new TripleImpl(property, rdfType, owlOp);
        TripleImpl triple4 = new TripleImpl(object, rdfsLabel, propertyValue);


        return GraphPatternNotTriplesImpl.unionOf(List.of(triple1, triple2), List.of(triple3, triple4));
    }

    public static GraphPatternNotTriples minusOf() {
        QueryVariable user = QueryVariable.of("user");
        QueryVariable propertyValue = QueryVariable.of("propertyValue");

        PrefixedName prefixedName = PrefixedName.of("usr", "hasPassword");

        TripleImpl triple = new TripleImpl(user, prefixedName, propertyValue);

        return GraphPatternNotTriplesImpl.minusOf(triple);
    }

    public static TripleSameSubject userHasId() {

        QueryVariable property = QueryVariable.of("property");
        QueryVariable user = QueryVariable.of("user");
        QueryVariable object = QueryVariable.of("object");
        QueryVariable userId = QueryVariable.of("userId");

        TripleSameSubjectImpl tripleSameSubject = new TripleSameSubjectImpl(user);
        tripleSameSubject.add(PrefixedName.of("rdf", "type"),
                PrefixedName.of("usr", "user"));
        tripleSameSubject.add(PrefixedName.of("usr", "hasId"), userId);
        tripleSameSubject.add(property, object);

        return tripleSameSubject;
    }

    public static Triple tripleRdfLabelExample() {

        QueryVariable property = QueryVariable.of("property");
        QueryVariable propertyLabel = QueryVariable.of("propertyLabel");

        return new TripleImpl(property, PrefixedName.of("rdfs", "label"), propertyLabel);
    }

    public static TripleSameSubject userDetails() {
        PrefixedName usr = PrefixedName.of("usr", "9e59445c-0150-4cd3-84c8-324b5a39b4b2");
        PrefixedName consumer = PrefixedName.of("usr", "consumer");

        TripleSameSubjectImpl tripleSameSubject = new TripleSameSubjectImpl(usr);
        tripleSameSubject.add(PrefixedName.of("rdf", "type"), consumer);
        tripleSameSubject.add(PrefixedName.of("usr", "hasRole"), consumer);
        tripleSameSubject.add(PrefixedName.of("usr", "hasId"),
                StringLiteral.of("9e59445c-0150-4cd3-84c8-324b5a39b4b2"));

        tripleSameSubject.add(PrefixedName.of("usr", "hasUsername"),
                StringLiteral.of("username-password"));

        return tripleSameSubject;
    }
}
