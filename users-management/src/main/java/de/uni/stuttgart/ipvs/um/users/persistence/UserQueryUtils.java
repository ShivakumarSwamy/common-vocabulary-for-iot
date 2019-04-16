package de.uni.stuttgart.ipvs.um.users.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.node.Subject;
import de.uni.stuttgart.ipvs.sparql.query.QueryImpl;
import de.uni.stuttgart.ipvs.sparql.query.SelectQuery;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;
import de.uni.stuttgart.ipvs.vocabulary.USER;

import java.util.List;

import static de.uni.stuttgart.ipvs.um.users.persistence.QVExprConstants.*;

class UserQueryUtils {

    private UserQueryUtils() {
        throw new IllegalStateException(getClass().getName());
    }


    static String findAllUsers() {

        return findAllUsersOrSingleUser(QV_USER, false);
    }

    static String findUser(String id) {
        return findAllUsersOrSingleUser(PrefixedName.of(USER.USER_PREFIX_LABEL, id), false);
    }

    static String findUserForAuthentication(String id) {
        return findAllUsersOrSingleUser(PrefixedName.of(USER.USER_PREFIX_LABEL, id), true);
    }

    private static String findAllUsersOrSingleUser(Subject subject, boolean isPasswordNeeded) {

        var whereClause = new WhereClause(UserSparqlUtils.userPropertyIdentify(subject));
        whereClause.add(UserSparqlUtils.propertyLabel());
        whereClause.add(UserSparqlUtils.unionOwlProperties(subject));

        if (!isPasswordNeeded) whereClause.add(UserSparqlUtils.minusPasswordProperty());

        var queryForm = new SelectQuery(List.of(QV_USER_ID, EXPR_LCASE, QV_PROPERTY_VALUE));
        queryForm.setWhereClause(whereClause);

        var query = new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlUser(), queryForm);

        return query.getString();
    }
}
