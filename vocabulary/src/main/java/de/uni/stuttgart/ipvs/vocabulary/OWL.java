package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.terminal.Iri;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixLabel;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;

public class OWL {

    private static final Iri OWL_IRI;
    public static final PrefixLabel OWL_PREFIX_LABEL;

    public static final PrefixDeclaration OWL_PREFIX_DECLARATION;

    public static final PrefixedName OWL_DATATYPE_PROPERTY;
    public static final PrefixedName OWL_OBJECT_PROPERTY;

    static {
        OWL_IRI = new Iri("http://www.w3.org/2002/07/owl#");
        OWL_PREFIX_LABEL = new PrefixLabel("owl");

        OWL_PREFIX_DECLARATION = new PrefixDeclaration(OWL_PREFIX_LABEL, OWL_IRI);

        OWL_DATATYPE_PROPERTY = new PrefixedName(OWL_PREFIX_LABEL, "DatatypeProperty");
        OWL_OBJECT_PROPERTY = new PrefixedName(OWL_PREFIX_LABEL, "ObjectProperty");
    }
}
