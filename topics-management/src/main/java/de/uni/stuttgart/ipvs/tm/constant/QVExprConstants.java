package de.uni.stuttgart.ipvs.tm.constant;

import de.uni.stuttgart.ipvs.sparql.variable.QueryVariable;

public class QVExprConstants {
    private QVExprConstants() {
        throw new IllegalStateException(getClass().getName());
    }

    public static final QueryVariable QV_SEARCH_ID;
    public static final QueryVariable QV_LABEL;
    public static final QueryVariable QV_COMMENT;

    static {
        QV_SEARCH_ID = QueryVariable.of("searchId");
        QV_LABEL = QueryVariable.of("label");
        QV_COMMENT = QueryVariable.of("comment");
    }
}
