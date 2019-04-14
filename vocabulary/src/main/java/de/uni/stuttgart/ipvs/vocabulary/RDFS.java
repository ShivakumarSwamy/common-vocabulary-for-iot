package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.terminal.Iri;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixLabel;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;

public class RDFS {

    private static final Iri RDFS_IRI;
    private static final PrefixLabel RDFS_PREFIX_LABEL;

    public static final PrefixDeclaration RDFS_PREFIX_DECLARATION;

    public static final PrefixedName RDFS_CLASS;

    public static final PrefixedName RDFS_LABEL;

    static {
        RDFS_IRI = new Iri("http://www.w3.org/2000/01/rdf-schema#");
        RDFS_PREFIX_LABEL = new PrefixLabel("rdfs");

        RDFS_PREFIX_DECLARATION = new PrefixDeclaration(RDFS_PREFIX_LABEL, RDFS_IRI);

        RDFS_CLASS = new PrefixedName(RDFS_PREFIX_LABEL, "Class");
        RDFS_LABEL = new PrefixedName(RDFS_PREFIX_LABEL, "label");
    }
}
