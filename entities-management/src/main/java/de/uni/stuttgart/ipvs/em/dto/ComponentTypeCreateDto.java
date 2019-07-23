package de.uni.stuttgart.ipvs.em.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ComponentTypeCreateDto {

    @ApiModelProperty(allowableValues = "sensor,actuator")
    private String component;

    @ApiModelProperty(value = "Instances of component category and component")
    private String componentCategory;

    private String label;
    private String comment;
}
