package de.uni.stuttgart.ipvs.sparql.terminal;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlSyntax;

public class PrefixLabel implements SparqlSyntax {

    private final String label;

    public PrefixLabel(@NonNull String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getString() {
        return this.label + ":";
    }
}
