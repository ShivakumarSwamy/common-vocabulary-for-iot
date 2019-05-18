package de.uni.stuttgart.ipvs.em.entities.persistence;

import de.uni.stuttgart.ipvs.em.entities.service.Entity;
import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.sparql.triple.TripleImpl;
import de.uni.stuttgart.ipvs.sparql.update.GraphUpdate;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

import static de.uni.stuttgart.ipvs.em.constant.QVExprConstants.*;

class EntityUpdateUtils {

    private EntityUpdateUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String newEntity(Entity entity) {

        var graphUpdate = GraphUpdate.insertDataOf(EntitySparqlUtils.entityProperties(entity));
        graphUpdate.add(EntitySparqlUtils.hardwareProperties(entity.getId(), entity));
        graphUpdate.add(EntitySparqlUtils.messageProperties(entity.getId(), entity));
        graphUpdate.add(EntitySparqlUtils.topicProperties(entity.getId(), entity));
        graphUpdate.add(EntitySparqlUtils.locationProperties(entity.getId(), entity));

        graphUpdate.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return graphUpdate.getString();
    }

    static String updateEntity(Entity entity) {

        var insertDelete = new InsertDelete();
        insertDelete.addDeleteTriple(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));

        insertDelete.addInsertTriples(EntitySparqlUtils.entityProperties(entity));
        insertDelete.addInsertTriples(EntitySparqlUtils.hardwareProperties(entity.getId(), entity));
        insertDelete.addInsertTriples(EntitySparqlUtils.messageProperties(entity.getId(), entity));
        insertDelete.addInsertTriples(EntitySparqlUtils.topicProperties(entity.getId(), entity));
        insertDelete.addInsertTriples(EntitySparqlUtils.locationProperties(entity.getId(), entity));

        insertDelete.setWhereClause(getWhereClauseToIdentifyEntityAndProperty(entity.getId()));
        insertDelete.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return insertDelete.getString();
    }

    static String deleteEntity(String entityId) {
        var insertDelete = new InsertDelete();
        insertDelete.addDeleteTriple(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));
        insertDelete.setWhereClause(getWhereClauseToIdentifyEntityAndProperty(entityId));
        insertDelete.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return insertDelete.getString();
    }

    static String deleteEntity(String ownerId, String entityID) {
        var insertDelete = new InsertDelete();
        insertDelete.addDeleteTriple(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));

        var whereClause = getWhereClauseToIdentifyEntityAndProperty(entityID);
        whereClause.add(EntitySparqlUtils.hasOwner(QV_ENTITY, StringLiteral.of(ownerId)));
        insertDelete.setWhereClause(whereClause);

        insertDelete.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return insertDelete.getString();
    }

    private static WhereClause getWhereClauseToIdentifyEntityAndProperty(String entityId) {

        var whereClauseEntityProperty = new WhereClause();
        whereClauseEntityProperty.add(EntitySparqlUtils.hasId(QV_ENTITY, StringLiteral.of(entityId)));
        whereClauseEntityProperty.add(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));

        return whereClauseEntityProperty;
    }

}
