package de.uni.stuttgart.ipvs.tm.response;

import lombok.Data;

@Data
public class HardwareTypeItemDetails {

    private String category;
    private String hardwareComponent;

    private String searchId;
    private String label;
    private String comment;

    public void of(SearchItemDetails searchItemDetails) {
        this.searchId = searchItemDetails.getSearchId();
        this.label = searchItemDetails.getLabel();
        this.comment = searchItemDetails.getComment();
    }
}