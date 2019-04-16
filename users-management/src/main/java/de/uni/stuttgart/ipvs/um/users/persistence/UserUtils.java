package de.uni.stuttgart.ipvs.um.users.persistence;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserUtils {

    private static final Map<String, BiConsumer<UserDetailsImpl, String>> USER_PROPERTY_FUNCTION_MAP;

    private static final Function<Map<String, VariableBinding>, String> GROUP_BY_USER_ID;


    private UserUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    static Map<String, List<Map<String, VariableBinding>>> getUsersFromSelectResults(@NonNull SelectResults selectResults) {

        var collect = selectResults.getResults().getBindings().stream()
                .collect(Collectors.groupingBy(GROUP_BY_USER_ID));

        return collect;
    }


    static {
        USER_PROPERTY_FUNCTION_MAP = new HashMap<>();
        USER_PROPERTY_FUNCTION_MAP.put("id", UserDetailsImpl::setId);
        USER_PROPERTY_FUNCTION_MAP.put("username", UserDetailsImpl::setUsername);
        USER_PROPERTY_FUNCTION_MAP.put("role", UserDetailsImpl::setRole);

        GROUP_BY_USER_ID = stringVariableBindingMap ->
                stringVariableBindingMap.get(QVExprConstants.QV_USER_ID.getVariableName()).getValue();



    }
}
