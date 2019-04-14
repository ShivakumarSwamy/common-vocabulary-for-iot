package de.uni.stuttgart.ipvs.sparql.terminal;

import lombok.NonNull;

public class PrefixLabel {

    private final String label;

    public PrefixLabel(@NonNull String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getString() {
        return this.label + ":";
    }
}
