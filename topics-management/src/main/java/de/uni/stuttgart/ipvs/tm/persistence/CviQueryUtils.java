package de.uni.stuttgart.ipvs.tm.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.query.QueryImpl;
import de.uni.stuttgart.ipvs.sparql.query.SelectQuery;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;

public class CviQueryUtils {

    private CviQueryUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String meaningOfTerms(Collection<String> terms) {

        var whereClause = new WhereClause();
        whereClause.add(CviSparqlUtils.unionOfTerms(terms));

        var selectQuery = new SelectQuery(List.of(QV_SEARCH_ID, QV_LABEL, QV_COMMENT));
        selectQuery.setWhereClause(whereClause);

        var query = new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlCvi(), selectQuery);

        return query.getString();
    }

    static String allHardwareTypes() {

        var whereClause = new WhereClause(CviSparqlUtils.identifyComponent());
        whereClause.add(CviSparqlUtils.identifyCategory());
        whereClause.add(CviSparqlUtils.searchItemDetailsHardwareType());

        var selectQuery = new SelectQuery(List.of(QV_SEARCH_ID, QV_LABEL, QV_COMMENT,
                QV_CATEGORY_LABEL, QV_COMPONENT_LABEL));
        selectQuery.setWhereClause(whereClause);

        var query = new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlCvi(), selectQuery);

        return query.getString();
    }


}
