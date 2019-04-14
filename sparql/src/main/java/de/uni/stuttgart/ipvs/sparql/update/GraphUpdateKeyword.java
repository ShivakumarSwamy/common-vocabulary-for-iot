package de.uni.stuttgart.ipvs.sparql.update;

public enum GraphUpdateKeyword {

    INSERT_DATA("INSERT DATA");

    private final String keyword;

    GraphUpdateKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }
}
