package de.uni.stuttgart.ipvs.um.users.persistence;

import de.uni.stuttgart.ipvs.sparql.update.GraphUpdate;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

class UserUpdateUtils {

    private UserUpdateUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String newUserDetails(UserDetailsImpl userDetails) {

        var tripleSameSubject = UserSparqlUtils.newUserDetails(userDetails);
        var graphUpdate = GraphUpdate.insertDataOf(tripleSameSubject);
        graphUpdate.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlUser());

        return graphUpdate.getString();
    }
}
