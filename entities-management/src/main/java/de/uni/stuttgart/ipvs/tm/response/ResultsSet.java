package de.uni.stuttgart.ipvs.tm.response;

import lombok.Value;

import java.util.Collection;
import java.util.List;

@Value
public class ResultsSet<T> {

    public static final ResultsSet<Collection> EMPTY;

    private final T results;

    static {
        EMPTY = new ResultsSet<>(List.of());
    }

}
