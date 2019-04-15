package de.uni.stuttgart.ipvs.repository.utils;

import lombok.NonNull;

import de.uni.stuttgart.ipvs.repository.dto.RepositoryConfigDetails;

import java.util.Map;

public class RepositoryUtils {

    private RepositoryUtils() {
        throw new IllegalStateException(getClass().getName());
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
}
