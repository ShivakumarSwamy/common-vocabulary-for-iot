package de.uni.stuttgart.ipvs.sparql.update;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.ArrayList;
import java.util.Collection;

public class UpdateImpl implements Update {

    private Collection<Prologue> prologues = new ArrayList<>();
    private Collection<UpdateForm> updateForms = new ArrayList<>();

    public UpdateImpl(Collection<Prologue> prologues) {
        this(prologues, new ArrayList<>());
    }

    public UpdateImpl(@NonNull Collection<Prologue> prologues, @NonNull Collection<UpdateForm> updateForm) {
        this.prologues.addAll(prologues);
        this.updateForms.addAll(updateForm);
    }

    public void add(@NonNull UpdateForm updateForm) {
        this.updateForms.add(updateForm);
    }

    @Override
    public Collection<Prologue> getPrologues() {
        return prologues;
    }

    @Override
    public Collection<UpdateForm> getUpdateForms() {
        return updateForms;
    }

    @Override
    public String getString() {
        String finalUpdateString = "";

        String prologuesString = SparqlUtils.joinPrologues(this.prologues);
        finalUpdateString += prologuesString.isEmpty() ? "" : prologuesString + "\n";

        String updateFormsString = SparqlUtils.joinUpdateForms(this.updateForms);
        finalUpdateString += updateFormsString.isEmpty() ? "" : updateFormsString + "\n";

        return finalUpdateString;
    }
}
