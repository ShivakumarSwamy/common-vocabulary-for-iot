package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProloguesFactory {

    private ProloguesFactory() {
        throw new IllegalStateException(getClass().getName());
    }

    public static Collection<Prologue> getProloguesRdfRdfsOwlUser() {
        return addPrologueToRdfRdfsOwl(getUser());
    }

    public static Collection<Prologue> getProloguesRdfRdfsOwlCvi() {
        return addPrologueToRdfRdfsOwl(getCvi());
    }

    public static Collection<Prologue> getProloguesRdfUser() {
        return addPrologueToUser(getRdf());
    }

    public static Collection<Prologue> getProloguesRdfsUser() {
        return addPrologueToUser(getRdfs());
    }

    public static Collection<Prologue> getProloguesRdfCvi() {
        return addPrologueToCvi(getRdf());
    }

    public static Collection<Prologue> getProloguesRdfsCvi() {
        return addPrologueToCvi(getRdfs());
    }

    private static Collection<Prologue> addPrologueToCvi(Prologue prologue) {
        return twoPrologues(getCvi(), prologue);
    }


    private static Collection<Prologue> addPrologueToUser(Prologue prologue) {
        return twoPrologues(getUser(), prologue);
    }

    private static Collection<Prologue> twoPrologues(Prologue prologue1, Prologue prologue2) {
        return List.of(prologue1, prologue2);
    }

    private static Collection<Prologue> addPrologueToRdfRdfsOwl(Prologue prologue) {
        var prologues = new ArrayList<>(getRdfRdfsOwl());
        prologues.add(prologue);
        return prologues;
    }

    private static Collection<Prologue> getRdfRdfsOwl() {
        return List.of(getRdf(), getRdfs(), getOwl());
    }

    private static Prologue getRdf() {
        return RDF.RDF_PREFIX_DECLARATION;
    }

    private static Prologue getRdfs() {
        return RDFS.RDFS_PREFIX_DECLARATION;
    }

    private static Prologue getUser() {
        return USER.USER_PREFIX_DECLARATION;
    }

    private static Prologue getCvi() {
        return CVI.CVI_PREFIX_DECLARATION;
    }

    private static Prologue getOwl() {
        return OWL.OWL_PREFIX_DECLARATION;
    }
}
