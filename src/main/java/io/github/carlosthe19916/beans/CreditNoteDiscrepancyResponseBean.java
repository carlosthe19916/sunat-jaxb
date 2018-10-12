package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo9;

import javax.validation.constraints.NotNull;

public class CreditNoteDiscrepancyResponseBean extends  DiscrepancyResponseBean {

    @NotNull
    private Catalogo9 responseCode;

    public Catalogo9 getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Catalogo9 responseCode) {
        this.responseCode = responseCode;
    }
}
