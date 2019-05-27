package de.uni.stuttgart.ipvs.em.dto;

import lombok.Data;

@Data
public class ComponentTypeCreateDto {

    private String component;
    private String componentCategory;
    private String label;
    private String comment;
}
