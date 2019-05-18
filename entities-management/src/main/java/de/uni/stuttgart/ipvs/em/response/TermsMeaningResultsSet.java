package de.uni.stuttgart.ipvs.em.response;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collection;

@Value
public class TermsMeaningResultsSet {

    public static final TermsMeaningResultsSet EMPTY;

    private final Collection<SearchItemDetails> exactResults;
    private final Collection<SearchItemDetails> relatedResults;

    static {
        EMPTY = new TermsMeaningResultsSet(new ArrayList<>(), new ArrayList<>());
    }
}
