package de.uni.stuttgart.ipvs.tm.topics.persistence;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static de.uni.stuttgart.ipvs.tm.constant.QVExprConstants.*;

public class TopicUtils {
    private static final Function<Map<String, VariableBinding>, String> GROUP_BY_ENTITY_ID;
    private static final Function<Map<String, VariableBinding>, String> PROPERTY_NAME;
    private static final Function<Map<String, VariableBinding>, String> PROPERTY_VALUE;

    private TopicUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static Collection<Map<String, String>> getTopicsPropertyMapFromSelectResults(@NonNull SelectResults selectResults) {

        var topicsGroupedById = selectResults.getResults().getBindings().stream()
                .collect(Collectors.groupingBy(GROUP_BY_ENTITY_ID, Collectors.toMap(PROPERTY_NAME, PROPERTY_VALUE)));

        return topicsGroupedById.values();
    }

    static {
        GROUP_BY_ENTITY_ID = stringVariableBindingMap ->
                stringVariableBindingMap.get(QV_ENTITY_ID.getVariableName()).getValue();

        PROPERTY_NAME = stringVariableBindingMap ->
                stringVariableBindingMap.get(QV_PROPERTY_NAME.getVariableName())
                        .getValue().replaceAll("\\s+", "_");

        PROPERTY_VALUE = stringVariableBindingMap ->
                stringVariableBindingMap.get(QV_PROPERTY_VALUE.getVariableName()).getValue();
    }

}
