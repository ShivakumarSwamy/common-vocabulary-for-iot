package de.uni.stuttgart.ipvs.vocabulary;

import de.uni.stuttgart.ipvs.sparql.prologue.PrefixDeclaration;
import de.uni.stuttgart.ipvs.sparql.terminal.Iri;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixLabel;
import de.uni.stuttgart.ipvs.sparql.terminal.PrefixedName;

public class CVI {

    private static final Iri CVI_IRI;

    public static final PrefixLabel CVI_PREFIX_LABEL;

    public static final PrefixDeclaration CVI_PREFIX_DECLARATION;

    public static final PrefixedName HAS_SEARCH_ID;

    public static final PrefixedName ENTITY_CLASS;
    public static final PrefixedName HAS_ID;
    public static final PrefixedName HAS_OWNER;

    public static final PrefixedName TOPIC_CLASS;
    public static final PrefixedName HAS_PATH;
    public static final PrefixedName HAS_MIDDLEWARE_ENDPOINT;
    public static final PrefixedName HAS_DATA_TYPE;
    public static final PrefixedName HAS_TOPIC_TYPE;
    public static final PrefixedName HAS_PROTOCOL;

    public static final PrefixedName MESSAGE_CLASS;
    public static final PrefixedName HAS_MESSAGE_FORMAT;
    public static final PrefixedName HAS_META_MODEL;
    public static final PrefixedName HAS_META_MODEL_TYPE;

    public static final PrefixedName HARDWARE_CLASS;
    public static final PrefixedName HAS_HARDWARE_TYPE;
    public static final PrefixedName HAS_UNIT;

    public static final PrefixedName HARDWARE_TYPE_CLASS;

    static {
        CVI_IRI = new Iri("http://www.cvi.com/vocabulary#");

        CVI_PREFIX_LABEL = new PrefixLabel("cvi");

        CVI_PREFIX_DECLARATION = new PrefixDeclaration(CVI_PREFIX_LABEL, CVI_IRI);

        HAS_SEARCH_ID = new PrefixedName(CVI_PREFIX_LABEL, "hasSearchId");

        ENTITY_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "entity");
        HAS_ID = new PrefixedName(CVI_PREFIX_LABEL, "hasId");
        HAS_OWNER = new PrefixedName(CVI_PREFIX_LABEL, "hasOwner");

        TOPIC_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "topic");
        HAS_PATH = new PrefixedName(CVI_PREFIX_LABEL, "hasPath");
        HAS_MIDDLEWARE_ENDPOINT = new PrefixedName(CVI_PREFIX_LABEL, "hasMiddlewareEndpoint");
        HAS_DATA_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasDataType");
        HAS_TOPIC_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasTopicType");
        HAS_PROTOCOL = new PrefixedName(CVI_PREFIX_LABEL, "hasProtocol");

        MESSAGE_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "message");
        HAS_MESSAGE_FORMAT = new PrefixedName(CVI_PREFIX_LABEL, "hasMessageFormat");
        HAS_META_MODEL = new PrefixedName(CVI_PREFIX_LABEL, "hasMetaModel");
        HAS_META_MODEL_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasMetaModelType");

        HARDWARE_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "hardware");
        HAS_HARDWARE_TYPE = new PrefixedName(CVI_PREFIX_LABEL, "hasHardwareType");
        HAS_UNIT = new PrefixedName(CVI_PREFIX_LABEL, "hasUnit");

        HARDWARE_TYPE_CLASS = new PrefixedName(CVI_PREFIX_LABEL, "hardware-type");
    }
}
