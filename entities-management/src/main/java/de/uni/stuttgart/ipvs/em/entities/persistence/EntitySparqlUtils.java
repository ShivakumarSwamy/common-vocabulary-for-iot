package de.uni.stuttgart.ipvs.em.entities.persistence;

import de.uni.stuttgart.ipvs.em.entities.properties.*;
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
import de.uni.stuttgart.ipvs.vocabulary.OWL;
import de.uni.stuttgart.ipvs.vocabulary.RDFS;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static de.uni.stuttgart.ipvs.em.constant.QVExprConstants.*;
import static de.uni.stuttgart.ipvs.vocabulary.CVI.*;
import static de.uni.stuttgart.ipvs.vocabulary.RDF.RDF_TYPE;

class EntitySparqlUtils {

    private EntitySparqlUtils() {
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
        return new TripleImpl(subject, RDF_TYPE, CVI_ENTITY_CLASS);
    }

    static Triple hasOwner(Subject subject, Obj obj) {
        return new TripleImpl(subject, CVI_HAS_OWNER, obj);
    }

    static Triple hasId(Subject subject, Obj obj) {
        return new TripleImpl(subject, CVI_HAS_ID, obj);
    }

    private static Subject entityIdSubject(String entityID) {
        return PrefixedName.of(CVI_PREFIX_LABEL, entityID);
    }

    static TripleSameSubject entityProperties(EntityProperties entityProperties) {

        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityProperties.getId()));
        tripleSameSubject.add(RDF_TYPE, CVI_ENTITY_CLASS);
        tripleSameSubject.add(CVI_HAS_ID, StringLiteral.of(entityProperties.getId()));
        tripleSameSubject.add(CVI_HAS_OWNER, StringLiteral.of(entityProperties.getOwner()));

        return tripleSameSubject;
    }


    static TripleSameSubject messageProperties(String entityID, MessageProperties messageProperties) {
        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityID));
        tripleSameSubject.add(RDF_TYPE, CVI_MESSAGE_CLASS);

        var messageFormatClass = PrefixedName.of(CVI_PREFIX_LABEL, messageProperties.getMessageFormat());
        tripleSameSubject.add(CVI_HAS_MESSAGE_FORMAT, messageFormatClass);
        tripleSameSubject.add(RDF_TYPE, messageFormatClass);

        var metaModelTypeClass = PrefixedName.of(CVI_PREFIX_LABEL, messageProperties.getMetaModelType());
        tripleSameSubject.add(CVI_HAS_META_MODEL_TYPE, metaModelTypeClass);
        tripleSameSubject.add(RDF_TYPE, metaModelTypeClass);

        tripleSameSubject.add(CVI_HAS_META_MODEL, StringLiteral.of(messageProperties.getMetaModel()));

        return tripleSameSubject;
    }

    static TripleSameSubject hardwareProperties(String entityId, HardwareProperties hardwareProperties) {

        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityId));
        tripleSameSubject.add(RDF_TYPE, CVI_HARDWARE_CLASS);

        var hardwareTypeClass = PrefixedName.of(CVI_PREFIX_LABEL, hardwareProperties.getHardwareType());
        tripleSameSubject.add(CVI_HAS_HARDWARE_TYPE, hardwareTypeClass);
        tripleSameSubject.add(RDF_TYPE, hardwareTypeClass);

        tripleSameSubject.add(CVI_HAS_UNIT, StringLiteral.of(hardwareProperties.getUnit()));

        return tripleSameSubject;

    }

    static TripleSameSubject topicProperties(String entityId, TopicProperties topicProperties) {

        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityId));
        tripleSameSubject.add(RDF_TYPE, CVI_TOPIC_CLASS);

        tripleSameSubject.add(CVI_HAS_PATH, StringLiteral.of(topicProperties.getPath()));
        tripleSameSubject.add(CVI_HAS_MIDDLEWARE_ENDPOINT, StringLiteral.of(topicProperties.getMiddlewareEndpoint()));

        var dataTypeClass = PrefixedName.of(CVI_PREFIX_LABEL, topicProperties.getDataType());
        tripleSameSubject.add(CVI_HAS_DATA_TYPE, dataTypeClass);
        tripleSameSubject.add(RDF_TYPE, dataTypeClass);

        var protocolClass = PrefixedName.of(CVI_PREFIX_LABEL, topicProperties.getProtocol());
        tripleSameSubject.add(CVI_HAS_PROTOCOL, protocolClass);
        tripleSameSubject.add(RDF_TYPE, protocolClass);

        var topicTypeClass = PrefixedName.of(CVI_PREFIX_LABEL, topicProperties.getTopicType());
        tripleSameSubject.add(CVI_HAS_TOPIC_TYPE, topicTypeClass);
        tripleSameSubject.add(RDF_TYPE, topicTypeClass);

        return tripleSameSubject;
    }

    static TripleSameSubject locationProperties(String entityId, LocationProperties locationProperties) {

        var tripleSameSubject = new TripleSameSubjectImpl(entityIdSubject(entityId));
        tripleSameSubject.add(RDF_TYPE, CVI_LOCATION_CLASS);

        tripleSameSubject.add(CVI_HAS_COUNTRY, PrefixedName.of(CVI_PREFIX_LABEL, locationProperties.getCountry()));
        tripleSameSubject.add(CVI_HAS_STATE, PrefixedName.of(CVI_PREFIX_LABEL, locationProperties.getState()));
        tripleSameSubject.add(CVI_HAS_CITY, PrefixedName.of(CVI_PREFIX_LABEL, locationProperties.getCity()));
        tripleSameSubject.add(CVI_HAS_STREET, PrefixedName.of(CVI_PREFIX_LABEL, locationProperties.getStreet()));

        var pointClass = PrefixedName.of(CVI_PREFIX_LABEL, locationProperties.getPoint());
        tripleSameSubject.add(CVI_HAS_POINT, pointClass);
        tripleSameSubject.add(RDF_TYPE, pointClass);

        return tripleSameSubject;
    }

    static List<Triple> terms(Collection<String> terms) {

        return terms.stream()
                .map(EntitySparqlUtils::typeClassTerm)
                .collect(Collectors.toList());
    }

    private static Triple typeClassTerm(String term) {
        return new TripleImpl(QV_ENTITY, RDF_TYPE, PrefixedName.of(CVI_PREFIX_LABEL, term));
    }

}
