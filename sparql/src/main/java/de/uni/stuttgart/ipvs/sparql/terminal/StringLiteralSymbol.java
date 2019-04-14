package de.uni.stuttgart.ipvs.sparql.terminal;

public enum StringLiteralSymbol {

    SINGLE_DOUBLE_QUOTE("\"");

    private final String symbol;

    StringLiteralSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
