package de.uni.stuttgart.ipvs.sparql.prologue;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.terminal.Iri;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixLabel;

public class PrefixDeclaration implements Prologue {

    private final PrefixLabel prefixLabel;
    private final Iri iri;

    public PrefixDeclaration(@NonNull PrefixLabel prefixLabel, @NonNull Iri iri) {
        this.prefixLabel = prefixLabel;
        this.iri = iri;
    }

    public static PrefixDeclaration of(String prefixLabel, String referent) {
        return new PrefixDeclaration(new PrefixLabel(prefixLabel), new Iri(referent));
    }

    @Override
    public Iri getIri() {
        return this.iri;
    }

    public PrefixLabel getPrefixLabel() {
        return this.prefixLabel;
    }

    @Override
    public String getString() {
        return "PREFIX " + this.prefixLabel.getString() + " " + this.iri.getString();
    }
}
