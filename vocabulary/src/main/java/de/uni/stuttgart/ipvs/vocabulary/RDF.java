package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.terminal.Iri;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixLabel;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;

public class RDF {

    private static final Iri RDF_IRI;
    public static final PrefixLabel RDF_PREFIX_LABEL;

    public static final PrefixDeclaration RDF_PREFIX_DECLARATION;

    public static final PrefixedName RDF_TYPE;

    static {
        RDF_IRI = new Iri("http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        RDF_PREFIX_LABEL = new PrefixLabel("rdf");

        RDF_PREFIX_DECLARATION = new PrefixDeclaration(RDF_PREFIX_LABEL, RDF_IRI);

        RDF_TYPE = new PrefixedName(RDF_PREFIX_LABEL, "type");
    }
}
