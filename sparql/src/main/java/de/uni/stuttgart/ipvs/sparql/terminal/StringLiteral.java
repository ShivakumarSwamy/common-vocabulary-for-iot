package de.uni.stuttgart.ipvs.sparql.terminal;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.node.Obj;

public class StringLiteral implements Obj {

    private final StringLiteralSymbol stringLiteralSymbol;
    private final String literalValue;

    public StringLiteral(@NonNull StringLiteralSymbol stringLiteralSymbol, @NonNull String literalValue) {
        this.stringLiteralSymbol = stringLiteralSymbol;
        this.literalValue = literalValue;
    }

    public static StringLiteral of(String literalValue) {
        return new StringLiteral(StringLiteralSymbol.SINGLE_DOUBLE_QUOTE, literalValue);
    }

    @Override
    public String getString() {
        return this.stringLiteralSymbol.getSymbol() + this.literalValue + this.stringLiteralSymbol.getSymbol();
    }
}
