package de.uni.stuttgart.ipvs.um.users.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.node.Subject;
import de.uni.stuttgart.ipvs.sparql.query.AskQuery;
import de.uni.stuttgart.ipvs.sparql.query.Query;
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
        return findAllUsersOrSingleUser(QV_USER);
    }

    static String findUserByUsername(String username) {

        var whereClause = getWhereClauseOwlPropertiesForSubject(QV_USER);
        whereClause.add(UserSparqlUtils.userHasUsername(QV_USER, username));

        var query = getQueryWithSelectWhereProloguesForUser(whereClause);
        return query.getString();
    }

    static String findUserByUserId(String id) {
        return findAllUsersOrSingleUser(PrefixedName.of(USER.USER_PREFIX_LABEL, id));
    }

    static String askUsernameExists(String username) {

        var whereClause = new WhereClause(UserSparqlUtils.userHasUsername(QV_USER, username));

        var askQueryForm = new AskQuery();
        askQueryForm.setWhereClause(whereClause);

        var query = new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlUser(), askQueryForm);

        return query.getString();
    }

    private static String findAllUsersOrSingleUser(Subject subject) {

        var whereClause = getWhereClauseOwlPropertiesForSubject(subject);
        whereClause.add(UserSparqlUtils.minusPasswordProperty());

        var query = getQueryWithSelectWhereProloguesForUser(whereClause);
        return query.getString();
    }

    private static Query getQueryWithSelectWhereProloguesForUser(WhereClause whereClause) {
        var queryForm = new SelectQuery(List.of(QV_USER_ID, EXPR_LCASE, QV_PROPERTY_VALUE));
        queryForm.setWhereClause(whereClause);

        return new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlUser(), queryForm);
    }

    private static WhereClause getWhereClauseOwlPropertiesForSubject(Subject subject) {
        var whereClause = new WhereClause(UserSparqlUtils.userPropertyIdentify(subject));
        whereClause.add(UserSparqlUtils.propertyLabel());
        whereClause.add(UserSparqlUtils.unionOwlProperties(subject));
        return whereClause;
    }
}
