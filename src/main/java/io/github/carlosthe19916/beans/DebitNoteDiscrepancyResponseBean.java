package io.github.carlosthe19916.beans;

import io.github.carlosthe19916.beans.catalogs.Catalogo10;
import io.github.carlosthe19916.beans.catalogs.Catalogo9;

import javax.validation.constraints.NotNull;

public class DebitNoteDiscrepancyResponseBean extends DiscrepancyResponseBean {
    @NotNull
    private Catalogo10 responseCode;

    public Catalogo10 getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Catalogo10 responseCode) {
        this.responseCode = responseCode;
    }
}
