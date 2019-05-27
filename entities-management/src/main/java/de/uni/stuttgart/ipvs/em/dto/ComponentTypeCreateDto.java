package de.uni.stuttgart.ipvs.em.component.types.dto;

import lombok.Data;

@Data
public class ComponentTypeCreateDto {

    private String component;
    private String category;
    private String label;
    private String comment;
}
