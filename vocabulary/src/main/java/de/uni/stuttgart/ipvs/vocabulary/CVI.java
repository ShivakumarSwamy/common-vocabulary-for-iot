package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.terminal.Iri;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixLabel;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;

public class CVI {

    private static final Iri CVI_IRI;

    public static final PrefixLabel CVI_PREFIX_LABEL;

    public static final PrefixDeclaration CVI_PREFIX_DECLARATION;

    public static final PrefixedName CVI_HAS_SEARCH_ID;

    public static final PrefixedName CVI_ENTITY_CLASS;
    public static final PrefixedName CVI_HAS_ID;
    public static final PrefixedName CVI_HAS_OWNER;

    public static final PrefixedName CVI_TOPIC_CLASS;
    public static final PrefixedName CVI_HAS_PATH;
    public static final PrefixedName CVI_HAS_MIDDLEWARE_ENDPOINT;
    public static final PrefixedName CVI_HAS_DATA_TYPE;
    public static final PrefixedName CVI_HAS_TOPIC_TYPE;
    public static final PrefixedName CVI_HAS_PROTOCOL;

    public static final PrefixedName CVI_MESSAGE_CLASS;
    public static final PrefixedName CVI_HAS_MESSAGE_FORMAT;
    public static final PrefixedName CVI_HAS_META_MODEL;
    public static final PrefixedName CVI_HAS_META_MODEL_TYPE;

    public static final PrefixedName CVI_HARDWARE_CLASS;
    public static final PrefixedName CVI_HAS_HARDWARE_TYPE;
    public static final PrefixedName CVI_HAS_UNIT;

    public static final PrefixedName CVI_COMPONENT_TYPE_CLASS;
    public static final PrefixedName CVI_COMPONENT_CLASS;

    static {
        CVI_IRI = new Iri("http://www.cvi.com/vocabulary#");

        CVI_PREFIX_LABEL = new PrefixLabel("cvi");

        CVI_PREFIX_DECLARATION = new PrefixDeclaration(CVI_PREFIX_LABEL, CVI_IRI);

        CVI_HAS_SEARCH_ID = new PrefixedName(CVI_PREFIX_LABEL, "hasSearchId");

        CVI_ENTITY_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "entity");
        CVI_HAS_ID = new PrefixedName(CVI_PREFIX_LABEL, "hasId");
        CVI_HAS_OWNER = new PrefixedName(CVI_PREFIX_LABEL, "hasOwner");

        CVI_TOPIC_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "topic");
        CVI_HAS_PATH = new PrefixedName(CVI_PREFIX_LABEL, "hasPath");
        CVI_HAS_MIDDLEWARE_ENDPOINT = new PrefixedName(CVI_PREFIX_LABEL, "hasMiddlewareEndpoint");
        CVI_HAS_DATA_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasDataType");
        CVI_HAS_TOPIC_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasTopicType");
        CVI_HAS_PROTOCOL = new PrefixedName(CVI_PREFIX_LABEL, "hasProtocol");

        CVI_MESSAGE_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "message");
        CVI_HAS_MESSAGE_FORMAT = new PrefixedName(CVI_PREFIX_LABEL, "hasMessageFormat");
        CVI_HAS_META_MODEL = new PrefixedName(CVI_PREFIX_LABEL, "hasMetaModel");
        CVI_HAS_META_MODEL_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasMetaModelType");

        CVI_HARDWARE_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "hardware");
        CVI_HAS_HARDWARE_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasHardwareType");
        CVI_HAS_UNIT = new PrefixedName(CVI_PREFIX_LABEL, "hasUnit");

        CVI_COMPONENT_TYPE_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "component-type");
        CVI_COMPONENT_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "component");
    }
}
