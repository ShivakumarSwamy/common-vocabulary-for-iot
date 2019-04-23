package de.uni.stuttgart.ipvs.tm.service;

import lombok.Value;

import de.uni.stuttgart.ipvs.tm.dto.ComponentTypeCreateDTO;

@Value
public class ComponentType {

    private final String category;
    private final String searchId;
    private final String label;
    private final String comment;

    public static ComponentType build(ComponentTypeCreateDTO componentTypeCreateDTO) {

        String category = requiredTermsTextFormat(componentTypeCreateDTO.getCategory());
        String searchId = requiredTermsTextFormat(componentTypeCreateDTO.getLabel());

        return new ComponentType(category, searchId, componentTypeCreateDTO.getLabel(), componentTypeCreateDTO.getComment());
    }

    private static String requiredTermsTextFormat(String text) {
        return text.toLowerCase().replaceAll("\\s+", "-");
    }
}
