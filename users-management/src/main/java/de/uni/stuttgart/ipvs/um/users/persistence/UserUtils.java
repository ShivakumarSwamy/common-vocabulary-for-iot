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

        var usersGroupedById = selectResults.getResults().getBindings().stream()
                .collect(Collectors.groupingBy(GROUP_BY_USER_ID, Collectors.toMap(PROPERTY_NAME, PROPERTY_VALUE)));

        return usersGroupedById.values();
    }

    static UserDetailsImpl getUserFromSelectResults(SelectResults selectResults) {

        var usersGroupedById = getUsersPropertyMapFromSelectResults(selectResults);

        var collectionWithSingleUser = getUserFromUsersGroupById(usersGroupedById);

        var iterator = collectionWithSingleUser.iterator();

        return iterator.hasNext() ? iterator.next() : new UserDetailsImpl();
    }

    private static Collection<UserDetailsImpl> getUserFromUsersGroupById(Collection<Map<String, String>> usersGroupedById) {

        return usersGroupedById.stream()
                .map(UserUtils::getUser)
                .collect(Collectors.toList());
    }

    private static UserDetailsImpl getUser(Map<String, String> userPropertyMap) {
        // the keys should match with vocabulary designed
        var userDetails = new UserDetailsImpl();
        userDetails.setId(userPropertyMap.get("id"));
        userDetails.setRole(userPropertyMap.get("role"));
        userDetails.setUsername(userPropertyMap.get("username"));
        userDetails.setPassword(userPropertyMap.get("password"));

        return userDetails;
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
