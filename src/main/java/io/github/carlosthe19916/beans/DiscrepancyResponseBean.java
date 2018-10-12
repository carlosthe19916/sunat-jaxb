package io.github.carlosthe19916.beans;


import javax.validation.constraints.NotNull;

public class DiscrepancyResponseBean {
    @NotNull
    private String referenceID;

    @NotNull
    private String description;

    public String getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
