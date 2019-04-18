package de.uni.stuttgart.ipvs.tm.topics.persistence;

import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriples;
import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriplesImpl;
import de.uni.stuttgart.ipvs.sparql.node.Obj;
import de.uni.stuttgart.ipvs.sparql.node.Subject;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;
import de.uni.stuttgart.ipvs.sparql.terminal.StringLiteral;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.triple.TripleImpl;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubject;
import de.uni.stuttgart.ipvs.sparql.triple.TripleSameSubjectImpl;
import de.uni.stuttgart.ipvs.tm.topics.properties.EntityProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.HardwareProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.MessageProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.TopicProperties;
import de.uni.stuttgart.ipvs.vocabulary.OWL;
import de.uni.stuttgart.ipvs.vocabulary.RDFS;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;
import static de.uni.stuttgart.ipvs.vocabulary.CVI.*;
import static de.uni.stuttgart.ipvs.vocabulary.RDF.RDF_TYPE;

class TopicSparqlUtils {

    private TopicSparqlUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static GraphPatternNotTriples unionOwlProperties(Subject subject) {

        var tripleDP = new TripleImpl(QV_PROPERTY, RDF_TYPE, OWL.OWL_DATATYPE_PROPERTY);
        var tripleDPV = new TripleImpl(subject, QV_PROPERTY, QV_PROPERTY_VALUE);

        var tripleOP = new TripleImpl(QV_PROPERTY, RDF_TYPE, OWL.OWL_OBJECT_PROPERTY);
        var tripleOPV = new TripleImpl(QV_OBJECT, RDFS.RDFS_LABEL, QV_PROPERTY_VALUE);

        return GraphPatternNotTriplesImpl.unionOf(List.of(tripleDP, tripleDPV), List.of(tripleOP, tripleOPV));
    }

    static Triple identifyProperty(Subject subject) {
        return new TripleImpl(subject, QV_PROPERTY, QV_OBJECT);
    }

    static Triple propertyLabel() {
        return new TripleImpl(QV_PROPERTY, RDFS.RDFS_LABEL, QV_PROPERTY_LABEL);
    }

    static Triple entityType(Subject subject) {
        return new TripleImpl(subject, RDF_TYPE, ENTITY_CLASS);
    }

    static Triple hasOwner(Subject subject, Obj obj) {
        return new TripleImpl(subject, HAS_OWNER, obj);
    }

    static Triple hasId(Subject subject, Obj obj) {
        return new TripleImpl(subject, HAS_ID, obj);
    }

    static Triple topicType(Subject subject) {
        return new TripleImpl(subject, RDF_TYPE, TOPIC_CLASS);
    }

    static Subject entityIdSubject(String entityID) {
        return PrefixedName.of(CVI_PREFIX_LABEL, entityID);
    }

    static TripleSameSubject entityProperties(EntityProperties entityProperties) {

        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityProperties.getId()));
        tripleSameSubject.add(RDF_TYPE, ENTITY_CLASS);
        tripleSameSubject.add(HAS_ID, StringLiteral.of(entityProperties.getId()));
        tripleSameSubject.add(HAS_OWNER, StringLiteral.of(entityProperties.getOwner()));

        return tripleSameSubject;
    }


    static TripleSameSubject messageProperties(String entityID, MessageProperties messageProperties) {
        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityID));
        tripleSameSubject.add(RDF_TYPE, MESSAGE_CLASS);

        var messageFormatClass = PrefixedName.of(CVI_PREFIX_LABEL, messageProperties.getMessageFormat());
        tripleSameSubject.add(HAS_MESSAGE_FORMAT, messageFormatClass);
        tripleSameSubject.add(RDF_TYPE, messageFormatClass);

        var metaModelTypeClass = PrefixedName.of(CVI_PREFIX_LABEL, messageProperties.getMetaModelType());
        tripleSameSubject.add(HAS_META_MODEL_TYPE, metaModelTypeClass);
        tripleSameSubject.add(RDF_TYPE, metaModelTypeClass);

        tripleSameSubject.add(HAS_META_MODEL, StringLiteral.of(messageProperties.getMetaModel()));

        return tripleSameSubject;
    }

    static TripleSameSubject hardwareProperties(String entityId, HardwareProperties hardwareProperties) {

        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityId));
        tripleSameSubject.add(RDF_TYPE, HARDWARE_CLASS);

        var hardwareTypeClass = PrefixedName.of(CVI_PREFIX_LABEL, hardwareProperties.getHardwareType());
        tripleSameSubject.add(HAS_HARDWARE_TYPE, hardwareTypeClass);
        tripleSameSubject.add(RDF_TYPE, hardwareTypeClass);

        tripleSameSubject.add(HAS_UNIT, StringLiteral.of(hardwareProperties.getUnit()));

        return tripleSameSubject;

    }

    static TripleSameSubject topicProperties(String entityId, TopicProperties topicProperties) {

        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityId));
        tripleSameSubject.add(RDF_TYPE, TOPIC_CLASS);

        tripleSameSubject.add(HAS_PATH, StringLiteral.of(topicProperties.getPath()));
        tripleSameSubject.add(HAS_MIDDLEWARE_ENDPOINT, StringLiteral.of(topicProperties.getMiddlewareEndpoint()));
        tripleSameSubject.add(HAS_DATA_TYPE, StringLiteral.of(topicProperties.getDataType()));

        var protocolClass = PrefixedName.of(CVI_PREFIX_LABEL, topicProperties.getProtocol());
        tripleSameSubject.add(HAS_PROTOCOL, protocolClass);
        tripleSameSubject.add(RDF_TYPE, protocolClass);

        var topicTypeClass = PrefixedName.of(CVI_PREFIX_LABEL, topicProperties.getTopicType());
        tripleSameSubject.add(HAS_TOPIC_TYPE, topicTypeClass);
        tripleSameSubject.add(RDF_TYPE, topicTypeClass);

        return tripleSameSubject;
    }

    static List<Triple> terms(Collection<String> terms) {

        return terms.stream()
                .map(TopicSparqlUtils::typeClassTerm)
                .collect(Collectors.toList());
    }

    private static Triple typeClassTerm(String term) {
        return new TripleImpl(QV_ENTITY, RDF_TYPE, PrefixedName.of(CVI_PREFIX_LABEL, term));
    }


//    static final Triple DELETE_PROPERTY;
//
//    private static final Function<String, Node> QN_CIV_CLASS_TYPE;
//
//    private static final Function<Node, Triple> TRIPLE_ENTITY_TYPE;
//
//    static Collection<Triple> getTriplesTopic(Topic topic) {
//        var entityId = topic.getId();
//        var triplesTopicEntity = new ArrayList<Triple>();
//
//        try {
//            triplesTopicEntity.addAll(getTriplesEntity(topic));
//            triplesTopicEntity.addAll(getTriplesTopic(entityId, topic));
//            triplesTopicEntity.addAll(getTriplesMessage(entityId, topic));
//            triplesTopicEntity.addAll(getTriplesHardware(entityId, topic));
//        } catch (IllegalArgumentException failedNodeCreation) {
//            throw new TopicSparqlException(failedNodeCreation);
//        }
//        return triplesTopicEntity;
//    }
//

//
//    static Collection<Triple> getTriplesHasIdWithProperty(String uuid) {
//        var hasIdDP = Triple.of(QV_ID, CIV_HAS_ID, new Literal(uuid));
//        return List.of(hasIdDP, DELETE_PROPERTY);
//    }
//
//
//    static Collection<Triple> getTriplesForTerms(String[] terms) {
//
//        return Arrays.stream(terms)
//                .map(QN_CIV_CLASS_TYPE)
//                .map(TRIPLE_ENTITY_TYPE)
//                .collect(toList());
//
//    }
//
//    static {
//        DELETE_PROPERTY = Triple.of(QV_ID, QV_PROPERTY_NAME, QV_PROPERTY_VALUE);
//
//        QN_CIV_CLASS_TYPE = term -> new QName(CIV_PREFIX, term);
//
//        TRIPLE_ENTITY_TYPE = classType -> Triple.of(QV_ENTITY, RDF_TYPE, classType);
//    }
}
