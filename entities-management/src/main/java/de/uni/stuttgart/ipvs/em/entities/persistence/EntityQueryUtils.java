package de.uni.stuttgart.ipvs.em.entities.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.node.Subject;
import de.uni.stuttgart.ipvs.sparql.query.Query;
import de.uni.stuttgart.ipvs.sparql.query.QueryImpl;
import de.uni.stuttgart.ipvs.sparql.query.SelectQuery;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.vocabulary.CVI;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.em.constant.QVExprConstants.*;

class EntityQueryUtils {

    private EntityQueryUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    /*
     * Query for admin to read all entities with its properties
     * */
    static String allEntitiesOwlProperties() {
        var whereClause = getWhereClauseEntityPropertiesForSubject(QV_ENTITY);
        return getQueryWithSelectWhereProloguesForEntity(whereClause).getString();
    }

    /*
     * Query for manager to read all owned entities and its properties
     * */
    static String allEntitiesOwlProperties(String ownerId) {

        var whereClause = getWhereClauseEntityPropertiesForSubject(QV_ENTITY);
        whereClause.add(EntitySparqlUtils.hasOwner(QV_ENTITY, StringLiteral.of(ownerId)));

        return getQueryWithSelectWhereProloguesForEntity(whereClause).getString();
    }

    /*
     * Query for manager to read all owned entities and its properties based on search terms
     * */
    static String allEntitiesOwlProperties(String ownerId, Collection<String> terms) {

        var whereClause = new WhereClause(EntitySparqlUtils.terms(terms));
        whereClause.add(EntitySparqlUtils.unionOwlProperties(QV_ENTITY));
        whereClause.add(EntitySparqlUtils.entityType(QV_ENTITY));
        whereClause.add(EntitySparqlUtils.hasOwner(QV_ENTITY, StringLiteral.of(ownerId)));
        whereClause.add(EntitySparqlUtils.hasId(QV_ENTITY, QV_ENTITY_ID));
        whereClause.add(EntitySparqlUtils.identifyProperty(QV_ENTITY));
        whereClause.add(EntitySparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForEntity(whereClause).getString();
    }

    /*
     * Query for consumer to read all entities and its properties based on search terms
     * */
    static String allEntitiesOwlProperties(Collection<String> terms) {

        var whereClause = new WhereClause(EntitySparqlUtils.terms(terms));
        whereClause.add(EntitySparqlUtils.unionOwlProperties(QV_ENTITY));
        whereClause.add(EntitySparqlUtils.entityType(QV_ENTITY));
        whereClause.add(EntitySparqlUtils.hasId(QV_ENTITY, QV_ENTITY_ID));
        whereClause.add(EntitySparqlUtils.identifyProperty(QV_ENTITY));
        whereClause.add(EntitySparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForEntity(whereClause).getString();
    }

    /*
     * Query for admin to read entity and its properties
     * */
    static String entityOwlProperties(String entityId) {

        var entityIdAsSubject = PrefixedName.of(CVI.CVI_PREFIX_LABEL, entityId);
        var whereClause = getWhereClauseEntityPropertiesForSubject(entityIdAsSubject);

        return getQueryWithSelectWhereProloguesForEntity(whereClause).getString();
    }

    /*
     * Query for manager to read owned entity and its properties
     * */
    static String entityOwlProperties(String ownerId, String entityId) {

        var entityIdAsSubject = PrefixedName.of(CVI.CVI_PREFIX_LABEL, entityId);
        var whereClause = getWhereClauseEntityPropertiesForSubject(entityIdAsSubject);
        whereClause.add(EntitySparqlUtils.hasOwner(entityIdAsSubject, StringLiteral.of(ownerId)));

        return getQueryWithSelectWhereProloguesForEntity(whereClause).getString();
    }

    private static WhereClause getWhereClauseEntityPropertiesForSubject(Subject subject) {

        var whereClauseEntityProperties = new WhereClause();
        whereClauseEntityProperties.add(EntitySparqlUtils.unionOwlProperties(subject));
        whereClauseEntityProperties.add(EntitySparqlUtils.entityType(subject));
        whereClauseEntityProperties.add(EntitySparqlUtils.hasId(subject, QV_ENTITY_ID));
        whereClauseEntityProperties.add(EntitySparqlUtils.identifyProperty(subject));
        whereClauseEntityProperties.add(EntitySparqlUtils.propertyLabel());

        return whereClauseEntityProperties;
    }

    private static Query getQueryWithSelectWhereProloguesForEntity(WhereClause whereClause) {

        var selectQuery = new SelectQuery(List.of(QV_ENTITY_ID, EXPR_LCASE, QV_PROPERTY_VALUE));
        selectQuery.setWhereClause(whereClause);

        return new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlCvi(), selectQuery);
    }

}
