package de.uni.stuttgart.ipvs.um.users.persistence;

import de.uni.stuttgart.ipvs.sparql.expression.Expression;
import de.uni.stuttgart.ipvs.sparql.expression.StrExpression;
import de.uni.stuttgart.ipvs.sparql.variable.QueryVariable;

public class QVExprConstants {
    private QVExprConstants() {
        throw new IllegalStateException(getClass().getName());
    }

    static final QueryVariable QV_PROPERTY;
    static final QueryVariable QV_PROPERTY_VALUE;
    static final QueryVariable QV_PROPERTY_NAME;
    static final QueryVariable QV_PROPERTY_LABEL;

    static final QueryVariable QV_USER;
    static final QueryVariable QV_USER_ID;

    static final QueryVariable QV_OBJECT;

    static final Expression EXPR_LCASE;

    static {
        QV_PROPERTY = QueryVariable.of("property");
        QV_PROPERTY_VALUE = QueryVariable.of("propertyValue");
        QV_PROPERTY_NAME = QueryVariable.of("propertyName");
        QV_PROPERTY_LABEL = QueryVariable.of("propertyLabel");

        QV_USER = QueryVariable.of("user");
        QV_USER_ID = QueryVariable.of("userId");

        QV_OBJECT = QueryVariable.of("object");

        EXPR_LCASE = StrExpression.lowercase(QV_PROPERTY_NAME, QV_PROPERTY_LABEL);
    }
}
