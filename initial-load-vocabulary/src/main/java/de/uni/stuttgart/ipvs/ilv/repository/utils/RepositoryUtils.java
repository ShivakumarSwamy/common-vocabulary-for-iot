package de.uni.stuttgart.ipvs.ilv.repository.utils;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.ilv.repository.dto.RepositoryConfigDetails;
import de.uni.stuttgart.ipvs.results.SelectResults;
import de.uni.stuttgart.ipvs.results.VariableBinding;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class RepositoryUtils {

    private static final Predicate<Map<String, VariableBinding>> CONTAINS_KEY_ID_IN_MAP;
    private static final Function<Map<String, VariableBinding>, VariableBinding> GET_KEY_ID_IN_MAP;
    private RepositoryUtils() {
        throw new IllegalStateException(getClass().getName());
    }

    public static boolean checkRepositoryIdInSelectResults(@NonNull SelectResults selectResults, String repositoryId) {

        return selectResults.getResults().getBindings().stream()
                .filter(Objects::nonNull)
                .filter(CONTAINS_KEY_ID_IN_MAP)
                .map(GET_KEY_ID_IN_MAP)
                .anyMatch(variableBinding -> variableBinding.getValue().equals(repositoryId));

    }

    public static RepositoryConfigDetails defaultRepositoryConfigDetails(@NonNull String repositoryId) {
        var repositoryConfigDetails = new RepositoryConfigDetails();

        repositoryConfigDetails.setId(repositoryId);
        repositoryConfigDetails.setTitle(repositoryId);

        repositoryConfigDetails.setType("free");
        repositoryConfigDetails.setSesameType("graphdb:FreeSailRepository");

        repositoryConfigDetails.setParams(createDefaultRuleset());

        return repositoryConfigDetails;
    }

    private static Map<String, Map<String, String>> createDefaultRuleset() {
        return Map.of(
                "ruleset",
                Map.of(
                        "label", "Ruleset",
                        "name", "ruleset",
                        "value", "rdfsplus-optimized")
        );
    }

    static {
        CONTAINS_KEY_ID_IN_MAP = stringVariableBindingMap -> stringVariableBindingMap.containsKey("id");
        GET_KEY_ID_IN_MAP = stringVariableBindingMap -> stringVariableBindingMap.get("id");
    }
}
