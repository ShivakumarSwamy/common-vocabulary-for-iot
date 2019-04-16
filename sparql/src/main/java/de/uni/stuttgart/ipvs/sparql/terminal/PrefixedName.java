package de.uni.stuttgart.ipvs.sparql.terminal;

import lombok.NonNull;
import lombok.Value;

import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Property;
import de.uni.stuttgart.ipvs.sparql.node.Subject;

@Value
public class PrefixedName implements Subject, Property, Obj {

    @NonNull
    private final PrefixLabel prefixLabel;

    @NonNull
    private final String localName;

    public static PrefixedName of(String prefixLabel, String localName) {
        return new PrefixedName(new PrefixLabel(prefixLabel), localName);
    }

    public static PrefixedName of(PrefixLabel prefixLabel, String localName) {
        return new PrefixedName(prefixLabel, localName);
    }

    public String getString() {
        return this.prefixLabel.getString() + this.localName;
    }


}
