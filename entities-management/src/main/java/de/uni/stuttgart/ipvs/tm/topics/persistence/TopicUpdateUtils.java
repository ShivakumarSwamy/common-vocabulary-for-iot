package de.uni.stuttgart.ipvs.tm.topics.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.sparql.triple.TripleImpl;
import de.uni.stuttgart.ipvs.sparql.update.GraphUpdate;
import de.uni.stuttgart.ipvs.tm.topics.service.Topic;
import de.uni.stuttgart.ipvs.vocabulary.CVI;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;

class TopicUpdateUtils {

    private TopicUpdateUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String newTopic(Topic topic) {

        var graphUpdate = GraphUpdate.insertDataOf(TopicSparqlUtils.entityProperties(topic));
        graphUpdate.add(TopicSparqlUtils.hardwareProperties(topic.getId(), topic));
        graphUpdate.add(TopicSparqlUtils.messageProperties(topic.getId(), topic));
        graphUpdate.add(TopicSparqlUtils.topicProperties(topic.getId(), topic));
        graphUpdate.add(TopicSparqlUtils.locationProperties(topic.getId(), topic));

        graphUpdate.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return graphUpdate.getString();
    }

    static String updateTopic(Topic topic) {

        var insertDelete = new InsertDelete();
        insertDelete.addInsertTriples(TopicSparqlUtils.entityProperties(topic));
        insertDelete.addInsertTriples(TopicSparqlUtils.hardwareProperties(topic.getId(), topic));
        insertDelete.addInsertTriples(TopicSparqlUtils.messageProperties(topic.getId(), topic));
        insertDelete.addInsertTriples(TopicSparqlUtils.topicProperties(topic.getId(), topic));
        insertDelete.addInsertTriples(TopicSparqlUtils.locationProperties(topic.getId(), topic));

        insertDelete.addDeleteTriple(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));

        var whereClause = new WhereClause();
        whereClause.add(TopicSparqlUtils.hasId(QV_ENTITY, StringLiteral.of(topic.getId())));
        whereClause.add(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));
        insertDelete.setWhereClause(whereClause);

        insertDelete.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return insertDelete.getString();
    }

    static String deleteTopic(String entityID) {
        var insertDelete = new InsertDelete();
        insertDelete.addDeleteTriple(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));

        var whereClause = new WhereClause();
        whereClause.add(TopicSparqlUtils.hasId(QV_ENTITY, StringLiteral.of(entityID)));
        whereClause.add(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));
        insertDelete.setWhereClause(whereClause);

        insertDelete.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return insertDelete.getString();
    }

    static String deleteTopic(String ownerID, String entityID) {
        var insertDelete = new InsertDelete();
        insertDelete.addDeleteTriple(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));

        var whereClause = new WhereClause();
        whereClause.add(TopicSparqlUtils.hasId(QV_ENTITY, StringLiteral.of(entityID)));
        whereClause.add(new TripleImpl(QV_ENTITY, QV_PROPERTY_NAME, QV_PROPERTY_VALUE));
        whereClause.add(new TripleImpl(QV_ENTITY, CVI.CVI_HAS_OWNER, StringLiteral.of(ownerID)));
        insertDelete.setWhereClause(whereClause);

        insertDelete.setPrologues(ProloguesFactory.getProloguesRdfRdfsOwlCvi());

        return insertDelete.getString();

    }


}
