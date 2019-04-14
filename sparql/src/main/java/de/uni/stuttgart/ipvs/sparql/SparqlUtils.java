package de.uni.stuttgart.ipvs.sparql;

import lombok.NonNull;

import java.util.Collection;
import java.util.stream.Collectors;

public class SparqlUtils {

    private SparqlUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    public static String joinCollection(@NonNull Collection<? extends SparqlSyntax> elements,
                                        @NonNull String delimiter) {

        return elements.stream()
                .map(SparqlSyntax::getString)
                .collect(Collectors.joining(delimiter));
    }

}
