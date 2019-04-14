package de.uni.stuttgart.ipvs.sparql.query;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.Collection;

public class QueryImpl implements Query {

    private final Collection<Prologue> prologues;
    private final QueryForm queryForm;

    public QueryImpl(@NonNull Collection<Prologue> prologues, @NonNull QueryForm queryForm) {
        this.prologues = prologues;
        this.queryForm = queryForm;
    }

    @Override
    public Collection<Prologue> getPrologues() {
        return prologues;
    }

    @Override
    public QueryForm getQueryForm() {
        return queryForm;
    }

    @Override
    public String getString() {
        String finalQueryString = "";

        String prologuesString = SparqlUtils.joinPrologues(this.prologues);
        finalQueryString += prologuesString.isEmpty() ? "" : prologuesString + "\n";

        String queryFormString = this.queryForm.getString();
        finalQueryString += queryFormString.isEmpty() ? "" : queryFormString + "\n";

        return finalQueryString;
    }
}
