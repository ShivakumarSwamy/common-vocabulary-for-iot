package de.uni.stuttgart.ipvs.tm.service;

import lombok.Value;

import de.uni.stuttgart.ipvs.tm.dto.HardwareTypeCreateDTO;

@Value
public class HardwareType {

    private final String category;
    private final String searchId;
    private final String label;
    private final String comment;

    public static HardwareType build(HardwareTypeCreateDTO hardwareTypeCreateDTO) {

        String category = requiredTermsTextFormat(hardwareTypeCreateDTO.getCategory());
        String searchId = requiredTermsTextFormat(hardwareTypeCreateDTO.getLabel());

        return new HardwareType(category, searchId, hardwareTypeCreateDTO.getLabel(), hardwareTypeCreateDTO.getComment());
    }

    private static String requiredTermsTextFormat(String text) {
        return text.toLowerCase().replaceAll("\\s+", "-");
    }
}
