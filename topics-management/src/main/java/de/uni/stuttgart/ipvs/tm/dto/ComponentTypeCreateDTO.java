package de.uni.stuttgart.ipvs.tm.dto;

import lombok.Data;

@Data
public class ComponentTypeCreateDTO {

    private String component;
    private String category;
    private String label;
    private String comment;
}
