package de.uni.stuttgart.ipvs.um.users.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.query.QueryImpl;
import de.uni.stuttgart.ipvs.sparql.query.SelectQuery;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

import java.util.List;

import static de.uni.stuttgart.ipvs.um.users.persistence.QVExprConstants.*;

class UserQueryUtils {

    private UserQueryUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String findAllUsers() {

        var whereClause = new WhereClause(UserSparqlUtils.userPropertyIdentify());
        whereClause.add(UserSparqlUtils.propertyLabel());
        whereClause.add(UserSparqlUtils.unionOwlProperties());
        whereClause.add(UserSparqlUtils.minusPasswordProperty());

        var queryForm = new SelectQuery(List.of(QV_USER_ID, EXPR_LCASE, QV_PROPERTY_VALUE));
        queryForm.setWhereClause(whereClause);

        var query = new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlUser(), queryForm);

        return query.getString();
    }
}
