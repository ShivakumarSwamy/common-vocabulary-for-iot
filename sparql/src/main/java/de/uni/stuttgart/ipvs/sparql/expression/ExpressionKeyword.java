package de.uni.stuttgart.ipvs.sparql.expression;

public enum ExpressionKeyword {

    LOWERCASE("LCASE");

    private final String symbol;

    ExpressionKeyword(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
