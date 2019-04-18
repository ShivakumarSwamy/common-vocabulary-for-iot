package de.uni.stuttgart.ipvs.tm.topics.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.WhereClause;
import de.uni.stuttgart.ipvs.sparql.query.Query;
import de.uni.stuttgart.ipvs.sparql.query.QueryImpl;
import de.uni.stuttgart.ipvs.sparql.query.SelectQuery;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.vocabulary.CVI;
import de.uni.stuttgart.ipvs.vocabulary.ProloguesFactory;

import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;

public class TopicQueryUtils {

    private TopicQueryUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static String allTopicsOwlProperties() {

        var whereClause = new WhereClause();
        whereClause.add(TopicSparqlUtils.unionOwlProperties(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.entityType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.topicType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.hasId(QV_ENTITY, QV_ENTITY_ID));
        whereClause.add(TopicSparqlUtils.identifyProperty(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForTopic(whereClause).getString();
    }

    static String allTopicsOwlProperties(String ownerId) {

        var whereClause = new WhereClause();
        whereClause.add(TopicSparqlUtils.unionOwlProperties(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.entityType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.topicType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.hasOwner(QV_ENTITY, StringLiteral.of(ownerId)));
        whereClause.add(TopicSparqlUtils.hasId(QV_ENTITY, QV_ENTITY_ID));
        whereClause.add(TopicSparqlUtils.identifyProperty(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForTopic(whereClause).getString();
    }

    static String allTopicsOwlProperties(String ownerId, Collection<String> terms) {

        var whereClause = new WhereClause(TopicSparqlUtils.terms(terms));
        whereClause.add(TopicSparqlUtils.unionOwlProperties(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.entityType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.topicType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.hasOwner(QV_ENTITY, StringLiteral.of(ownerId)));
        whereClause.add(TopicSparqlUtils.hasId(QV_ENTITY, QV_ENTITY_ID));
        whereClause.add(TopicSparqlUtils.identifyProperty(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForTopic(whereClause).getString();
    }

    static String allTopicsOwlProperties(Collection<String> terms) {

        var whereClause = new WhereClause(TopicSparqlUtils.terms(terms));
        whereClause.add(TopicSparqlUtils.unionOwlProperties(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.entityType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.topicType(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.hasId(QV_ENTITY, QV_ENTITY_ID));
        whereClause.add(TopicSparqlUtils.identifyProperty(QV_ENTITY));
        whereClause.add(TopicSparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForTopic(whereClause).getString();
    }

    static String topicOwlProperties(String entityId) {

        var entityIdSubject = PrefixedName.of(CVI.CVI_PREFIX_LABEL, entityId);

        var whereClause = new WhereClause();
        whereClause.add(TopicSparqlUtils.unionOwlProperties(entityIdSubject));
        whereClause.add(TopicSparqlUtils.entityType(entityIdSubject));
        whereClause.add(TopicSparqlUtils.topicType(entityIdSubject));
        whereClause.add(TopicSparqlUtils.hasId(entityIdSubject, QV_ENTITY_ID));
        whereClause.add(TopicSparqlUtils.identifyProperty(entityIdSubject));
        whereClause.add(TopicSparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForTopic(whereClause).getString();
    }


    static String topicOwlProperties(String ownerId, String entityId) {

        var entityIdSubject = PrefixedName.of(CVI.CVI_PREFIX_LABEL, entityId);

        var whereClause = new WhereClause();
        whereClause.add(TopicSparqlUtils.unionOwlProperties(entityIdSubject));
        whereClause.add(TopicSparqlUtils.entityType(entityIdSubject));
        whereClause.add(TopicSparqlUtils.topicType(entityIdSubject));
        whereClause.add(TopicSparqlUtils.hasOwner(entityIdSubject, StringLiteral.of(ownerId)));
        whereClause.add(TopicSparqlUtils.hasId(entityIdSubject, QV_ENTITY_ID));
        whereClause.add(TopicSparqlUtils.identifyProperty(entityIdSubject));
        whereClause.add(TopicSparqlUtils.propertyLabel());

        return getQueryWithSelectWhereProloguesForTopic(whereClause).getString();
    }

    private static Query getQueryWithSelectWhereProloguesForTopic(WhereClause whereClause) {
        var queryForm = new SelectQuery(List.of(QV_ENTITY_ID, EXPR_LCASE, QV_PROPERTY_VALUE));
        queryForm.setWhereClause(whereClause);

        return new QueryImpl(ProloguesFactory.getProloguesRdfRdfsOwlCvi(), queryForm);
    }

//    private static final Triple TOPIC_TYPE;
//    private static final ClauseQueryVariables SELECT_ENTITY;
//
//    static String sparqlQueryAllTopicsOwlProperties(String ownerId) {
//        return sparqlQueryAllOwlPropertiesOfTypes(
//                List.of(TOPIC_TYPE, Triple.of(QV_ENTITY, CIV_HAS_OWNER, new Literal(ownerId))));
//    }
//
//    static String sparqlQueryAllTopicsOwlProperties(String[] terms) {
//
//        var triplesForTerms = TopicTripleUtils.getTriplesForTerms(terms);
//        return sparqlQueryAllOwlPropertiesOfTypes(triplesForTerms);
//    }
//
//    static String sparqlQueryAllTopicsOwlProperties(String ownerId, String[] terms) {
//
//        var triples = new ArrayList<>(TopicTripleUtils.getTriplesForTerms(terms));
//        triples.add(Triple.of(QV_ENTITY, CIV_HAS_OWNER, new Literal(ownerId)));
//        return sparqlQueryAllOwlPropertiesOfTypes(triples);
//    }
//
//
//    static String sparqlQueryTopicOwlProperties(String ownerID, String topicId) {
//        return sparqlQueryEntityOwlPropertiesOfType(ownerID, topicId, TOPIC_TYPE, SELECT_ENTITY);
//    }
//
//    static String askTopicEntityHardwareExists(String hardware) {
//
//        var classHardwareType = Triple.of(new QName(CIV_PREFIX, hardware), RDF_TYPE, RDFS_CLASS);
//        var clauseAsk = new ClauseGroupGraphPatternImpl(ASK, GroupGraphPattern.of(classHardwareType));
//
//        return SparqlUtils.sparqlQueryOrUpdate(NamespaceFactory.getRdfRdfsWithCustomNamespace(CIV_NS), clauseAsk);
//    }
//
//    static String askHardwareSubClassOfSensorOrActuator(String hardware, String sensorOrActuator) {
//
//        var hardwareTypeSensorOrActuator = Triple.of(
//                new QName(CIV_PREFIX, hardware), RDFS_SUB_CLASS_OF, new QName(CIV_PREFIX, sensorOrActuator));
//        var clauseAsk = new ClauseGroupGraphPatternImpl(ASK, GroupGraphPattern.of(hardwareTypeSensorOrActuator));
//
//        return SparqlUtils.sparqlQueryOrUpdate(NamespaceFactory.getRdfsWithCustomNamespace(CIV_NS), clauseAsk);
//    }
//
//
//    static String askTopicEntityExists(String path, String protocol, String hardwareType) {
//
//        var entityType = Triple.of(QV_ENTITY, RDF_TYPE, CIV_ENTITY);
//        var topicType = entityType.insert(2, CIV_TOPIC);
//        var protocolType = entityType.insert(2, new QName(CIV_PREFIX, protocol));
//        var pathToLookFor = new Triple(QV_ENTITY, TOPIC_HAS_PATH_DP_QN, new Literal(path));
//        var hardware = entityType.insert(2, new QName(CIV_PREFIX, hardwareType));
//
//        var clauseAsk = new ClauseGroupGraphPatternImpl(
//                ASK, GroupGraphPattern.of(entityType, topicType, protocolType, pathToLookFor, hardware)
//        );
//
//        return SparqlUtils.sparqlQueryOrUpdate(NamespaceFactory.getRdfWithCustomNamespace(CIV_NS), clauseAsk);
//    }
//
//    static {
//        TOPIC_TYPE = Triple.of(QV_ENTITY, RDF_TYPE, CIV_TOPIC);
//        SELECT_ENTITY = ClauseFactory.selectOf(QV_PROPERTY_NAME, QV_PROPERTY_VALUE);
//
//    }

}
