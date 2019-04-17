package de.uni.stuttgart.ipvs.tm.persistence;

import de.uni.stuttgart.ipvs.sparql.update.GraphUpdate;
import de.uni.stuttgart.ipvs.tm.service.HardwareType;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

public class CviUpdateUtils {
    private CviUpdateUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String newHardwareType(HardwareType hardwareType) {

        var tripleSameSubject = CviSparqlUtils.newHardwareType(hardwareType);
        var graphUpdate = GraphUpdate.insertDataOf(tripleSameSubject);
        graphUpdate.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return graphUpdate.getString();
    }

}
