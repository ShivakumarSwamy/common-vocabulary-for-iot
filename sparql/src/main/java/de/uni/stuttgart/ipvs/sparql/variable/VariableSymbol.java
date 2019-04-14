package de.uni.stuttgart.ipvs.sparql.variable;

public enum VariableSymbol {

    DOLLAR("$"), QUESTION_MARK("?");

    private final String symbol;

    VariableSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
