package de.uni.stuttgart.ipvs.sparql.terminal;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Property;
import de.uni.stuttgart.ipvs.sparql.node.Subject;

public class Iri implements Subject, Property, Obj {

    private final String referent;

    public Iri(@NonNull String referent) {
        this.referent = referent;
    }

    public String getReferent() {
        return this.referent;
    }

    @Override
    public String getString() {
        return "<" + this.referent + ">";
    }
}
