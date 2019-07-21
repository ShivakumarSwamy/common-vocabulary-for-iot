package de.uni.stuttgart.ipvs.em.service;

import lombok.Value;

import de.uni.stuttgart.ipvs.em.dto.ComponentTypeCreateDto;

@Value
public class ComponentType {

    private final String componentCategory;
    private final String searchId;
    private final String label;
    private final String comment;

    public static ComponentType build(ComponentTypeCreateDto componentTypeCreateDto) {

        String componentCategory = requiredTermsTextFormat(componentTypeCreateDto.getComponentCategory());
        String searchId = requiredTermsTextFormat(componentTypeCreateDto.getLabel());

        String label = componentTypeCreateDto.getLabel();
        String comment = componentTypeCreateDto.getComment();

        return new ComponentType(componentCategory, searchId, label, comment);
    }

    private static String requiredTermsTextFormat(String text) {
        return text.toLowerCase().replaceAll("\\s+", "-");
    }
}
