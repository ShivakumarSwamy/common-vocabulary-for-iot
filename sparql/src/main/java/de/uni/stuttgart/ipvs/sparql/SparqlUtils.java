package de.uni.stuttgart.ipvs.sparql;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.variable.Variable;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SparqlUtils {

    private SparqlUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    public static String joinPrologues(@NonNull Collection<Prologue> prologues) {

        return joinCollection(prologues, Prologue::getString, "\n");
    }

    public static String joinVariables(@NonNull Collection<Variable> variables) {
        return joinCollection(variables, Variable::getString, " ");
    }

    private static <T> String joinCollection(Collection<T> elements,
                                             Function<T, String> mapFunction,
                                             String delimiter) {

        return elements.stream()
                .map(mapFunction)
                .collect(Collectors.joining(delimiter));
    }
}
