package de.uni.stuttgart.ipvs.um.users.persistence;

import de.uni.stuttgart.ipvs.sparql.update.GraphUpdate;

class UserUpdateUtils {

    private UserUpdateUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String newUserDetails(UserDetailsImpl userDetails) {

        var tripleSameSubject = UserSparqlUtils.newUserDetails(userDetails);
        var graphUpdate = GraphUpdate.insertDataOf(tripleSameSubject);

        return graphUpdate.getString();
    }
}
