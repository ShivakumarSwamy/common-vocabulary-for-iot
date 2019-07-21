package de.uni.stuttgart.ipvs.em.entities.persistence;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static de.uni.stuttgart.ipvs.em.constant.QVExprConstants.*;

class EntityUtils {
    private static final Function<Map<String, VariableBinding>, String> GROUP_BY_ENTITY_ID;
    private static final Function<Map<String, VariableBinding>, String> PROPERTY_NAME;
    private static final Function<Map<String, VariableBinding>, String> PROPERTY_VALUE;

    private EntityUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static Collection<Map<String, String>> getEntitiesPropertyMapFromSelectResults(@NonNull SelectResults selectResults) {

        var entitiesGroupedById = selectResults.getResults().getBindings().stream()
                .collect(Collectors.groupingBy(GROUP_BY_ENTITY_ID, Collectors.toMap(PROPERTY_NAME, PROPERTY_VALUE)));

        return entitiesGroupedById.values();
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
