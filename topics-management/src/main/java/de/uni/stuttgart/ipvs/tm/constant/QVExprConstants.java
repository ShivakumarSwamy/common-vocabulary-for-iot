package de.uni.stuttgart.ipvs.tm.constant;

import de.uni.stuttgart.ipvs.sparql.variable.QueryVariable;

public class QVExprConstants {
    private QVExprConstants() {
        throw new IllegalStateException(getClass().getName());
    }

    public static final QueryVariable QV_SEARCH_ID;
    public static final QueryVariable QV_LABEL;
    public static final QueryVariable QV_COMMENT;

    public static final QueryVariable QV_HARDWARE_TYPE;

    public static final QueryVariable QV_CATEGORY;
    public static final QueryVariable QV_CATEGORY_LABEL;

    public static final QueryVariable QV_HARDWARE_COMPONENT;
    public static final QueryVariable QV_HARDWARE_COMPONENT_LABEL;

    public static final QueryVariable QV_SUB_CLASS;

    static {
        QV_SEARCH_ID = QueryVariable.of("searchId");
        QV_LABEL = QueryVariable.of("label");
        QV_COMMENT = QueryVariable.of("comment");

        QV_HARDWARE_TYPE = QueryVariable.of("hardwareType");

        QV_CATEGORY = QueryVariable.of("category");
        QV_CATEGORY_LABEL = QueryVariable.of("categoryLabel");

        QV_HARDWARE_COMPONENT = QueryVariable.of("hardwareComponent");
        QV_HARDWARE_COMPONENT_LABEL = QueryVariable.of("hardwareComponentLabel");

        QV_SUB_CLASS = QueryVariable.of("subClass");
    }
}
