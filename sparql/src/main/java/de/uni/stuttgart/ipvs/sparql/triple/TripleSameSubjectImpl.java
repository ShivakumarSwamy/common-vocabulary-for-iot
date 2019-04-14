package de.uni.stuttgart.ipvs.sparql.triple;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.SparqlUtils;
import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Property;
import de.uni.stuttgart.ipvs.sparql.node.Subject;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class TripleSameSubjectImpl implements TripleSameSubject {

    private final Subject subject;
    private Set<Triple> triples = new LinkedHashSet<>();

    public TripleSameSubjectImpl(@NonNull Subject subject) {
        this.subject = subject;
    }

    public boolean add(@NonNull Property property, @NonNull Obj obj) {
        return this.triples.add(new TripleImpl(this.subject, property, obj));
    }

    @Override
    public Subject getSubject() {
        return this.subject;
    }

    @Override
    public Set<Triple> getTriples() {
        return this.triples;
    }

    @Override
    public String getString() {
        return SparqlUtils.joinTriples(this.triples);
    }

}
