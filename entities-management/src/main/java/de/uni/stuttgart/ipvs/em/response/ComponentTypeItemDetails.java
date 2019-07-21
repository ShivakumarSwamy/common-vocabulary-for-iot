package de.uni.stuttgart.ipvs.em.response;

import lombok.Data;

@Data
public class ComponentTypeItemDetails {

    private String component;
    private String componentCategory;

    private String searchId;
    private String label;
    private String comment;

    public void of(SearchItemDetails searchItemDetails) {
        this.searchId = searchItemDetails.getSearchId();
        this.label = searchItemDetails.getLabel();
        this.comment = searchItemDetails.getComment();
    }
}
