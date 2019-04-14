package de.uni.stuttgart.ipvs.sparql;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.sparql.clause.GraphPatternNotTriples;
import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;
import de.uni.stuttgart.ipvs.sparql.triple.Triple;
import de.uni.stuttgart.ipvs.sparql.update.UpdateForm;
import de.uni.stuttgart.ipvs.sparql.variable.Variable;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SparqlUtils {

    private SparqlUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    public static String joinPrologues(@NonNull Collection<Prologue> prologues) {
        if (prologues.isEmpty()) return "";

        return joinCollection(prologues, Prologue::getString, "\n");
    }

    public static String joinVariables(@NonNull Collection<Variable> variables) {
        return joinCollection(variables, Variable::getString, " ");
    }

    public static String groupGraphPattern(@NonNull Collection<Triple> triples) {
        return isEmptyOrSingle(triples) ? emptyOrSingleTriple(triples) : groupGraphPatternTriples(triples);
    }

    public static String emptyOrSingleTriple(@NonNull Collection<Triple> triples) {
        if (triples.isEmpty()) return "{}";

        return "{\n" + joinTriples(triples) + " .\n}\n";
    }

    public static String joinTriples(@NonNull Collection<Triple> triples) {
        return joinCollection(triples, Triple::getString, " .\n") + addDotToLastTripleOrNot(triples);
    }

    private static String groupGraphPatternTriples(Collection<Triple> triples) {
        return "{\n" + joinTriples(triples) + "}\n";
    }

    private static String addDotToLastTripleOrNot(Collection<Triple> triples) {
        return isEmptyOrSingle(triples) ? "" : " .\n";
    }

    private static boolean isEmptyOrSingle(Collection<Triple> triples) {
        return triples.isEmpty() || triples.size() == 1;
    }

    public static String joinGraphPatternNotTriples(Collection<GraphPatternNotTriples> graphPatternNotTriples) {
        return joinCollection(graphPatternNotTriples, GraphPatternNotTriples::getString, "");

    }

    public static String joinUpdateForms(Collection<UpdateForm> updateForms) {
        return joinCollection(updateForms, UpdateForm::getString, " ;\n");
    }

    private static <T> String joinCollection(Collection<T> elements,
                                             Function<T, String> mapFunction,
                                             String delimiter) {

        return elements.stream()
                .filter(Objects::nonNull)
                .map(mapFunction)
                .collect(Collectors.joining(delimiter));
    }
}
