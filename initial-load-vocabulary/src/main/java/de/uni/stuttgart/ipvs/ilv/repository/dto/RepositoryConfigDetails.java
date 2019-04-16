package de.uni.stuttgart.ipvs.ilv.repository.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RepositoryConfigDetails {

    private String id;
    private String title;

    private String type;
    private String sesameType;

    private Map<String, Map<String, String>> params;

}
