package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.Collection;
import java.util.List;

public class ProloguesFactory {

    public static Collection<Prologue> getProloguesRdfRdfsOwlUser() {
        return List.of(
                RDF.RDF_PREFIX_DECLARATION, RDFS.RDFS_PREFIX_DECLARATION,
                OWL.OWL_PREFIX_DECLARATION, USER.USER_PREFIX_DECLARATION
        );
    }
}
