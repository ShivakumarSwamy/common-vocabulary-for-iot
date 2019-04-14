package de.uni.stuttgart.ipvs.sparql.triple;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Property;
import de.uni.stuttgart.ipvs.sparql.node.Subject;

@EqualsAndHashCode
@ToString
public class TripleImpl implements Triple {

    private final Subject subject;
    private final Property property;
    private final Obj obj;

    public TripleImpl(Subject subject, Property property, Obj obj) {
        this.subject = subject;
        this.property = property;
        this.obj = obj;
    }

    @Override
    public Subject getSubject() {
        return this.subject;
    }

    @Override
    public Property getProperty() {
        return this.property;
    }

    @Override
    public Obj getObj() {
        return this.obj;
    }

    @Override
    public String getString() {
        return this.subject.getString() + " " + this.property.getString() + " " + this.obj.getString();
    }
}
