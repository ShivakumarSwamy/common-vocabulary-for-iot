package de.uni.stuttgart.ipvs.um.users.persistence;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static de.uni.stuttgart.ipvs.um.users.persistence.QVExprConstants.*;

public class UserUtils {


    private static final Function<Map<String, VariableBinding>, String> GROUP_BY_USER_ID;
    private static final Function<Map<String, VariableBinding>, String> PROPERTY_NAME;
    private static final Function<Map<String, VariableBinding>, String> PROPERTY_VALUE;


    private UserUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static Collection<Map<String, String>> getUsersPropertyMapFromSelectResults(@NonNull SelectResults selectResults) {

        var collect = selectResults.getResults().getBindings().stream()
                .collect(Collectors.groupingBy(GROUP_BY_USER_ID, Collectors.toMap(PROPERTY_NAME, PROPERTY_VALUE)));

        return collect.values();
    }

    static {

        GROUP_BY_USER_ID = stringVariableBindingMap ->
                stringVariableBindingMap.get(QV_USER_ID.getVariableName()).getValue();

        PROPERTY_NAME = stringVariableBindingMap ->
                stringVariableBindingMap.get(QV_PROPERTY_NAME.getVariableName()).getValue();

        PROPERTY_VALUE = stringVariableBindingMap ->
                stringVariableBindingMap.get(QV_PROPERTY_VALUE.getVariableName()).getValue();


    }
}
