package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.terminal.Iri;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixLabel;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;

public class USER {

    private static final Iri USER_IRI;
    private static final PrefixLabel USER_PREFIX_LABEL;

    public static final PrefixDeclaration USER_PREFIX_DECLARATION;

    public static final PrefixedName USER_CLASS;

    public static final PrefixedName USER_HAS_ID;
    public static final PrefixedName USER_HAS_USERNAME;
    public static final PrefixedName USER_HAS_PASSWORD;

    static {
        USER_IRI = new Iri("http://www.example.com/vocabulary/user#");
        USER_PREFIX_LABEL = new PrefixLabel("usr");

        USER_PREFIX_DECLARATION = new PrefixDeclaration(USER_PREFIX_LABEL, USER_IRI);

        USER_CLASS = new PrefixedName(USER_PREFIX_LABEL, "user");

        USER_HAS_ID = new PrefixedName(USER_PREFIX_LABEL, "hasId");
        USER_HAS_USERNAME = new PrefixedName(USER_PREFIX_LABEL, "hasUsername");
        USER_HAS_PASSWORD = new PrefixedName(USER_PREFIX_LABEL, "hasPassword");

    }
}
