package de.uni.stuttgart.ipvs.em.dto;

import lombok.Data;

@Data
public class ComponentTypeCreateDTO {

    private String component;
    private String category;
    private String label;
    private String comment;
}
