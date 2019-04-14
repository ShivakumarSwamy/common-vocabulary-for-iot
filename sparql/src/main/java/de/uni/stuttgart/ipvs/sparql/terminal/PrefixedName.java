package de.uni.stuttgart.ipvs.sparql.terminal;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Property;
import de.uni.stuttgart.ipvs.sparql.node.Subject;

@EqualsAndHashCode
@ToString
public class PrefixedName implements Subject, Property, Obj {

    private final PrefixLabel prefixLabel;
    private final String localName;

    public PrefixedName(PrefixLabel prefixLabel, String localName) {
        this.prefixLabel = prefixLabel;
        this.localName = localName;
    }

    public static PrefixedName of(String prefixLabel, String localName) {
        return new PrefixedName(new PrefixLabel(prefixLabel), localName);
    }

    public PrefixLabel getPrefixLabel() {
        return this.prefixLabel;
    }

    public String getLocalName() {
        return this.localName;
    }

    public String getString() {
        return this.prefixLabel.getString() + this.localName;
    }


}
