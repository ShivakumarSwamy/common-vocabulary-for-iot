package de.uni.stuttgart.ipvs.tm.dto;

import lombok.Data;

@Data
public class HardwareTypeCreateDTO {

    private String hardwareComponent;
    private String category;
    private String label;
    private String comment;
}
