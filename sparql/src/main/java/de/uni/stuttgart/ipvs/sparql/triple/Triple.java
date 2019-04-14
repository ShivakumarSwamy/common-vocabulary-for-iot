package de.uni.stuttgart.ipvs.sparql.triple;

import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Property;
import de.uni.stuttgart.ipvs.sparql.node.Subject;


public interface Triple {

    Subject getSubject();

    Property getProperty();

    Obj getObj();

    String getString();

}
