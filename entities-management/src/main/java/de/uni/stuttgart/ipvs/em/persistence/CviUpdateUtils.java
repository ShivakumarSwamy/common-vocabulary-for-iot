package de.uni.stuttgart.ipvs.em.persistence;

import de.uni.stuttgart.ipvs.em.service.ComponentType;
import de.uni.stuttgart.ipvs.sparql.update.GraphUpdate;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

public class CviUpdateUtils {
    private CviUpdateUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String newComponentType(ComponentType componentType) {

        var tripleSameSubject = CviSparqlUtils.newComponentType(componentType);
        var graphUpdate = GraphUpdate.insertDataOf(tripleSameSubject);
        graphUpdate.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return graphUpdate.getString();
    }

}
